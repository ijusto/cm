package pt.ua.nextweather.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;
import pt.ua.nextweather.network.WeatherTypesResultsObserver;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    private static RecyclerView mCitiesRecyclerView;
    private static CityListAdapter mCitiesAdapter;
    private static Context mainContext;
    private InfoDetailFragment fragment;

    static IpmaWeatherClient client = new IpmaWeatherClient();
    private static HashMap<String, City> cities;
    private static HashMap<Integer, WeatherType> weatherDescriptions;

    /* To save the boolean value representing the Fragment display state, define a key for the
       Fragment state to use in the savedInstanceState Bundle. */
    static final String STATE_FRAGMENT = "state_of_fragment";
    static final String DETAILS = "state_of_details";
    private static String details = "";
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a handle to the RecyclerView.
        mCitiesRecyclerView = findViewById(R.id.citiesRecyclerView);
        // Give the RecyclerView a default layout manager.
        // Change the LinearLayoutManager for the RecyclerView to a GridLayoutManager,
        // passing in the context and the newly created integer:
        mCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(mainContext));
        // Create an adapter and supply the data to be displayed.
        mCitiesAdapter = new CityListAdapter(this, cities);
        mCitiesAdapter.setClickListener((ItemClickListener) mainContext);
        // Connect the adapter with the RecyclerView.
        mCitiesRecyclerView.setAdapter(mCitiesAdapter);
        getWeatherDescriptions(null);
        mainContext = this;

                /* It checks to see if the instance state of the Activity was saved for some reason, such as
           a configuration change (the user switching from vertical to horizontal). If the saved
           instance was not saved, it would be null. If the saved instance is not null, the code
           retrieves the Fragment state from the saved instance, and sets the Button text: */
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            details = savedInstanceState.getString(DETAILS);
            if (isFragmentDisplayed) {
                ((TextView) findViewById(R.id.tvFeedback)).append(details);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getWeatherDescriptions(null);
        mainContext = this;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void getWeatherDescriptions(String city) {

        Log.i("MainActivity", "\nGetting forecast for: " + city + "\n");

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                weatherDescriptions = descriptorsCollection;
                getCitiesList(city);
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.i("MainActivity", "Failed to get weather conditions!");
            }
        });

    }

    private static void getCitiesList(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                if(cities == null){

                    mCitiesAdapter.setmCityList(citiesCollection);
                    mCitiesAdapter.setClickListener((ItemClickListener) mainContext);
                    // Connect the adapter with the RecyclerView.
                    mCitiesRecyclerView.setAdapter(mCitiesAdapter);
                }
                cities = citiesCollection;
                if(city != null) {
                    City cityFound = cities.get(city);
                    if( null != cityFound) {
                        int locationId = cityFound.getGlobalIdLocal();
                        getForecastForCity(locationId);
                    } else {
                        Log.i("MainActivity", "unknown city: " + city);
                    }
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                Log.i("MainActivity","Failed to get cities list!");
            }
        });
    }

    private static void getForecastForCity(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                details = "";
                for (Weather day : forecast) {
                    details += day.toString() + "\t";
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.i("MainActivity","Failed to get forecast for 5 days");
            }
        });

    }

    @Override
    public void onListItemClick(View view, int position){
        String mCurrent = "";
        int p = 0;
        for(String key : cities.keySet()){
            if(p == position){
                mCurrent = Objects.requireNonNull(cities.get(key)).getLocal();
                getWeatherDescriptions(mCurrent);

                Log.i("MainActivity", "details: " + details);

                Log.i("MainActivity", "Orientation: " + view.getResources().getConfiguration().orientation);
                if(view.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    // Get the FragmentManager and start a transaction.
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment = InfoDetailFragment.newInstance(details);
                    if(!isFragmentDisplayed) {
                        /* This code adds a new Fragment using the add() transaction method. The first argument
                           passed to add() is the layout resource (detail_fragment_container) for the ViewGroup in which
                           the Fragment should be placed. The second parameter is the Fragment (InfoDetailFragment) to
                           add. The code then calls commit() for the transaction to take effect.*/
                        fragmentTransaction.add(R.id.detail_fragment_container, fragment)
                                            .addToBackStack(null)
                                            .commitAllowingStateLoss();
                    } else {
                        fragmentTransaction.replace(R.id.detail_fragment_container, fragment)
                                            .addToBackStack(null)
                                            .commitAllowingStateLoss();
                    }
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, WeatherInfo.class);
                    intent.putExtra("details", details);
                    context.startActivity(intent);
                }
                break;
            }
            p++;
        }
    }

    /* To save the state of the Fragment if the configuration changes */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        savedInstanceState.putString(DETAILS, details);
        super.onSaveInstanceState(savedInstanceState);
    }
}
