package com.example.cmhw1dialler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // Defining Permission codes.
    // We can give any value
    // but unique for each permission.
    private static final int CALL_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton[] imgsBtns = {findViewById(R.id.imageButton0), findViewById(R.id.imageButton1),
                findViewById(R.id.imageButton2), findViewById(R.id.imageButton3),
                findViewById(R.id.imageButton4), findViewById(R.id.imageButton5),
                findViewById(R.id.imageButton6), findViewById(R.id.imageButton7),
                findViewById(R.id.imageButton8), findViewById(R.id.imageButton9),
                findViewById(R.id.imageButtonAsterisc), findViewById(R.id.imageButtonHashtag)};

        for (final ImageButton imgsBtn : imgsBtns) {
            final String number = getResources().getResourceEntryName(imgsBtn.getId()).split("imageButton")[1];
            final String input = number.equals("Asterisc") ? "*" : (number.equals("Hashtag") ? "#" : number);
            System.out.println(number);
            imgsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputButton(input);
                }
            });
            if (input.equals("0")) {
                imgsBtn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        inputButton("+");
                        return true;
                    }
                });
            }
        }

        ImageButton deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
                if (phoneNumberInput.getText().length() != 0) {
                    phoneNumberInput.setText(phoneNumberInput.getText().subSequence(0, phoneNumberInput.getText().length() - 1));
                }
            }
        });

        FloatingActionButton callBtn = findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
                dialPhoneNumber((String) phoneNumberInput.getText());
            }
        });
    }

    public void inputButton(String input) {
        TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
        if (phoneNumberInput.getText().length() < 13) {
            phoneNumberInput.setText(phoneNumberInput.getText() + input);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat
                        .requestPermissions(
                                MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                CALL_PERMISSION_CODE);
            }
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CALL_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ((TextView) findViewById(R.id.phoneNumberInput)).getText()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Call Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}