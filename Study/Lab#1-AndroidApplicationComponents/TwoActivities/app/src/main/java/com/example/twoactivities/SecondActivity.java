package com.example.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY ="com.example.twoactivities.extra.REPLY";
    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mReply = findViewById(R.id.editText_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    public void returnReply(View view) {
        String reply = mReply.getText().toString();

        /* create a new intent for the response—don't reuse the Intent object that you received from
           the original request. */
        Intent replyIntent = new Intent();
        /* Add the reply string from the EditText to the new intent as an Intent extra. Because
           extras are key/value pairs, here the key is EXTRA_REPLY, and the value is the reply */
        replyIntent.putExtra(EXTRA_REPLY, reply);
        /* Set the result to RESULT_OK to indicate that the response was successful. The Activity
           class defines the result codes, including RESULT_OK and RESULT_CANCELLED. */
        setResult(RESULT_OK, replyIntent);
        /* Call finish() to close the Activity and return to MainActivity. */
        finish();
    }
}
