package pt.ua.nextweather.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Objects;

import pt.ua.nextweather.R;

public class WeatherInfo extends AppCompatActivity {

    /* To save the boolean value representing the Fragment display state, define a key for the
       Fragment state to use in the savedInstanceState Bundle. */
    static final String STATE_FRAGMENT = "state_of_fragment";
    static final String DETAILS = "state_of_details";
    private static String details = "";
    private boolean isFragmentDisplayed = false;
    InfoDetailFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getIntent().getExtras()!=null){
            details = (String) getIntent().getExtras().get("details");
        }

                /* It checks to see if the instance state of the Activity was saved for some reason, such as
           a configuration change (the user switching from vertical to horizontal). If the saved
           instance was not saved, it would be null. If the saved instance is not null, the code
           retrieves the Fragment state from the saved instance, and sets the Button text: */
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            details = savedInstanceState.getString(DETAILS);
            if (isFragmentDisplayed) {
                ((TextView) findViewById(R.id.tvFeedback)).setText(details);
            }
        }

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
                    .commit();
        } else {
            fragmentTransaction.replace(R.id.detail_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        ((TextView) findViewById(R.id.tvFeedback)).setText(details);
        Log.i("WeatherInfo", details);
    }

    /* To save the state of the Fragment if the configuration changes */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        savedInstanceState.putString(DETAILS, details);
        super.onSaveInstanceState(savedInstanceState);
    }
}
