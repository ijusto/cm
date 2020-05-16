package com.example.android.fragmentexample1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;

    /* All subclasses of Fragment must include a public no-argument constructor as shown. The
       Android framework often re-instantiates a Fragment object when needed, in particular during
       state restore. The framework needs to be able to find this constructor so it can instantiate
       the Fragment. */
    public SimpleFragment() {
        // Required empty public constructor
    }

    /* The Fragment class uses callback methods that are similar to Activity callback methods.
       For example, onCreateView() provides a LayoutInflater to inflate the Fragment UI from the
       layout resource fragment_simple. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*  This code sets up the View and the RadioGroup, and returns the View (rootView). The
            View and RadioGroup are declared as final, which means they won't change. This is
            because you will need to access them from within an anonymous inner class (the
            OnCheckedChangeListener for the RadioGroup): */

        // Inflate the layout for this fragment.
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById(R.id.fragment_header);
                switch (index) {
                    case YES: // User chose "Yes."
                        textView.setText(R.string.yes_message);
                        break;
                    case NO: // User chose "No."
                        textView.setText(R.string.no_message);
                        break;
                    default: // No choice made.
                        // Do nothing.
                        break;
                }
            }
        });

        // Return the View for the fragment's UI.
        return rootView;
    }
}
