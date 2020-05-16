package pt.ua.nextweather.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

    private static TextView feedback;
    private static RecyclerView mCitiesRecyclerView;
    private static CityListAdapter mCitiesAdapter;
    private static Context mainContext;
    private static int gridColumnCount;

    static IpmaWeatherClient client = new IpmaWeatherClient();
    private static HashMap<String, City> cities;
    private static HashMap<Integer, WeatherType> weatherDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a handle to the RecyclerView.
        mCitiesRecyclerView = findViewById(R.id.citiesRecyclerView);
        // Give the RecyclerView a default layout manager.
        // Change the LinearLayoutManager for the RecyclerView to a GridLayoutManager,
        // passing in the context and the newly created integer:
        mCitiesRecyclerView.setLayoutManager(new
                GridLayoutManager(mainContext, gridColumnCount));
        // Create an adapter and supply the data to be displayed.
        mCitiesAdapter = new CityListAdapter(this, cities);
        mCitiesAdapter.setClickListener((ItemClickListener) mainContext);
        // Connect the adapter with the RecyclerView.
        mCitiesRecyclerView.setAdapter(mCitiesAdapter);
        getWeatherDescriptions(null);
        feedback = findViewById(R.id.tvFeedback);
        mainContext = this;
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

        if (city != null) {
            feedback.append("\nGetting forecast for: " + city + "\n");
        }

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                weatherDescriptions = descriptorsCollection;
                getCitiesList(city);
            }
            @Override
            public void onFailure(Throwable cause) {
                if(city != null){
                    feedback.append("Failed to get weather conditions!");
                }
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
                        feedback.append("unknown city: " + city);
                    }
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                if(city != null) {
                    feedback.append("Failed to get cities list!");
                }
            }
        });
    }

    private static void getForecastForCity(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    feedback.append(day.toString());
                    feedback.append("\t");
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append( "Failed to get forecast for 5 days");
            }
        });

    }

    @Override
    public void onClick(View view, int position) {
        String mCurrent = "";
        int p = 0;
        for(String key : cities.keySet()){
            if(p == position){
                mCurrent = Objects.requireNonNull(cities.get(key)).getLocal();
                Context context = view.getContext();
                if(view.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    InfoDetailFragment fragment = InfoDetailFragment.newInstance(mCurrent);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detail_fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    Intent intent = new Intent(context, WeatherInfo.class);
                    intent.putExtra("mCurrent", mCurrent);
                    context.startActivity(intent);
                }
                break;
            }
            p++;
        }
    }

}
