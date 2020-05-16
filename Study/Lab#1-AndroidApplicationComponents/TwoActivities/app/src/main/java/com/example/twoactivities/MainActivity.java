package com.example.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /* The Intent extras are key/value pairs in a Bundle. A Bundle is a collection of data, stored
       as key/value pairs. To pass information from one Activity to another, you put keys and values
       into the Intent extra Bundle from the sending Activity, and then get them back out again in
       the receiving Activity.
       Add a public constant at the top of the class to define the key for the Intent extra: */
    public static final String EXTRA_MESSAGE = "com.example.twoactivities.extra.MESSAGE";

    private EditText mMessageEditText;

    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main);

        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        /* The Intent constructor takes two arguments for an explicit Intent:
         * an application Context and
         * the specific component that will receive that Intent. */
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        /* When you use an explicit Intent to start another Activity, you may not expect to get any
           data back—you're just activating that Activity. In that case, you use startActivity() to
           start the new Activity, as you did earlier in this practical. If you want to get data
           back from the activated Activity, however, you need to start it with
           startActivityForResult().
           In this task you modify the app to start SecondActivity expecting a result, to extract
           that return data from the Intent, and to display that data in the TextView elements you
           created in the last task. */
        startActivityForResult(intent, TEXT_REQUEST);
    }

    /* The three arguments to onActivityResult() contain all the information you need to handle the
       return data: the requestCode you set when you launched the Activity with
       startActivityForResult(), the resultCode set in the launched Activity (usually one of
       RESULT_OK or RESULT_CANCELED), and the Intent data that contains the data returned from the
       launch Activity.
      */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* Add code to test for TEXT_REQUEST to make sure you process the right Intent result, in
           case there are several. Also test for RESULT_OK, to make sure that the request was
           successful: */
        if (requestCode == TEXT_REQUEST) {
            /* The Activity class defines the result codes. The code can be RESULT_OK (the request
            was successful), RESULT_CANCELED (the user cancelled the operation), or
            RESULT_FIRST_USER (for defining your own result codes). */
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
