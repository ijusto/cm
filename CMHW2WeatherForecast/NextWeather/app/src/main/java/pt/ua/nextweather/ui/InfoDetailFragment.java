package pt.ua.nextweather.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Objects;

import pt.ua.nextweather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoDetailFragment extends Fragment {

    public InfoDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_detail, container, false);
        WeatherInfo.getInfo();

        return view;
    }

    public void setText(String text) {

    }

    public static InfoDetailFragment newInstance() {
        return new InfoDetailFragment();
    }
}
