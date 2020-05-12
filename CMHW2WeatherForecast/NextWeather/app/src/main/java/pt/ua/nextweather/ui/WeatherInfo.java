package pt.ua.nextweather.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import java.util.Objects;

import pt.ua.nextweather.R;

public class WeatherInfo extends AppCompatActivity {

    private String info;
    private static String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        city = (String) Objects.requireNonNull(getIntent().getExtras()).get("mCurrent");
        Log.i("CIDADE", city);
    }

    public static void getInfo(){
        MainActivity.getWeatherDescriptions(city);
    }
}
