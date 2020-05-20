package pt.ua.nextweather.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Objects;

import pt.ua.nextweather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoDetailFragment extends Fragment {

    private String details = "";
    private static final String CHOICE = "choice";
    ItemClickListener mListener;
    private TextView feedback;
    public InfoDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            if (getArguments().containsKey(CHOICE)) {
                // A choice was made, so get the choice.
                details = getArguments().getString(CHOICE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info_detail, container, false);
        // Inflate the layout for this fragment.
        feedback = rootView.findViewById(R.id.tvFeedback);
        feedback.setText(details);
        return rootView;
    }

    public static InfoDetailFragment newInstance(String details) {
        InfoDetailFragment fragment = new InfoDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString(CHOICE, details);
        fragment.setArguments(arguments);
        return fragment;
    }

}
