package com.example.cmhw1dialler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateSpeedDial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_speed_dial);
        final SharedPreferences prefs = this.getSharedPreferences("com.example.cmhw1dialler", Context.MODE_PRIVATE);

        final String dialButton = prefs.getString("dialButton", "null");
        ((TextView) findViewById(R.id.speedDialButtonNumber))
                .setText("Quick Dial in button " + dialButton);
        Button cancelBtn = findViewById(R.id.uqdcancelbtn);
        Button okBtn = findViewById(R.id.uqdokbtn);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout textInputSpeedDialLabel = findViewById(R.id.textInputSpeedDialLabel);
                TextInputLayout textInputSpeedDialNumber = findViewById(R.id.textInputSpeedDialNumber);
                if (!textInputSpeedDialLabel.getEditText().getText().equals("") &&
                        !textInputSpeedDialNumber.getEditText().getText().equals("")) {
                    prefs.edit().putString("dialButton", "null").apply();
                    prefs.edit().putString(dialButton + "-phonenumber", String.valueOf(textInputSpeedDialNumber.getEditText().getText())).apply();
                    prefs.edit().putString(dialButton + "-label", String.valueOf(textInputSpeedDialLabel.getEditText().getText())).apply();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
