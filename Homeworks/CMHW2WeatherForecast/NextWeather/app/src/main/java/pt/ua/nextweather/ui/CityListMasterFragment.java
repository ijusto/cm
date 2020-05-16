package pt.ua.nextweather.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.ua.nextweather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityListMasterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public CityListMasterFragment() {
        // Required empty public constructor
    }

    public static CityListMasterFragment newInstance() {
        return new CityListMasterFragment();
    }
}
