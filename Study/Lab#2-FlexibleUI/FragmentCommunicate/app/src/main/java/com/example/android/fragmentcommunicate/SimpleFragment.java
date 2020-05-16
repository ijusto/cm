package com.example.android.fragmentcommunicate;

import android.content.Context;
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

    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;
    OnFragmentInteractionListener mListener;

    private static final String CHOICE = "choice";

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

        if (getArguments().containsKey(CHOICE)) {
            // A choice was made, so get the choice.
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            // Check the radio button choice.
            if (mRadioButtonChoice != NONE) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById(R.id.fragment_header);
                switch (index) {
                    case YES: // User chose "Yes."
                        textView.setText(R.string.yes_message);
                        mRadioButtonChoice = YES;
                        mListener.onRadioButtonChoice(YES);
                        break;
                    case NO: // User chose "No."
                        textView.setText(R.string.no_message);
                        mRadioButtonChoice = NO;
                        mListener.onRadioButtonChoice(NO);
                        break;
                    default: // No choice made.
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);
                        break;
                }
            }
        });


        // Return the View for the fragment's UI.
        return rootView;
    }

    /* To manage a Fragment in your Activity, use FragmentManager. To perform a Fragment
       transaction in your Activity—such as adding, removing, or replacing a Fragment—use
       methods in FragmentTransaction.
       The best practice for instantiating the Fragment in the Activity is to provide a
       newinstance() factory method in the Fragment. Follow these steps to add the newinstance()
       method to SimpleFragment and instantiate the Fragment in MainActivity. You will also add
       the displayFragment() and closeFragment() methods, and use Fragment transactions.
       To provide the user's previous "Yes" or "No" choice from the host Activity to the Fragment,
       pass the choice to the newInstance() method in SimpleFragment when instantiating
       SimpleFragment. You can set a Bundle and use the Fragment.setArguments(Bundle) method to
       supply the construction arguments for the Fragment. */
    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE, choice);
        fragment.setArguments(arguments);
        /* Now that a Bundle of arguments is available in the SimpleFragment, you can add code to
           the onCreateView() method in SimpleFragment to get the choice from the Bundle. Right
           before the statement that sets the radioGroup onCheckedChanged listener, add code to
           retrieve the radio button choice (if a choice was made), and pre-select the radio
           button. */
        return fragment;
    }

    /* Define a listener interface called OnFragmentInteractionListener. In it, specify a callback
       method that you will create, called onRadioButtonChoice(): */
    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(int choice);
    }

    /* The onAttach() method is called as soon as the Fragment is associated with the Activity. The
       code makes sure that the host Activity has implemented the callback interface. If not, it
       throws an exception. */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }
}
