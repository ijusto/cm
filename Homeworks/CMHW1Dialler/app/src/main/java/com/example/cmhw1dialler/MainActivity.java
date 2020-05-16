package com.example.cmhw1dialler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // Defining Permission codes.
    // We can give any value
    // but unique for each permission.
    private static final int CALL_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Dialog quickDial = new Dialog(MainActivity.this);

        Button[] numberBtns = {findViewById(R.id.button0), findViewById(R.id.button1),
                findViewById(R.id.button2), findViewById(R.id.button3),
                findViewById(R.id.button4), findViewById(R.id.button5),
                findViewById(R.id.button6), findViewById(R.id.button7),
                findViewById(R.id.button8), findViewById(R.id.button9),
                findViewById(R.id.buttonAsterisc), findViewById(R.id.buttonHashtag)};

        SpannableString[] stringBtns = {new SpannableString("0\n+"), new SpannableString("1"),
                new SpannableString("2\nABC"), new SpannableString("3\nDEF"),
                new SpannableString("4\nGHI"), new SpannableString("5\nJKL"),
                new SpannableString("6\nMNO"), new SpannableString("7\nPQRS"),
                new SpannableString("8\nTUV"), new SpannableString("9\nWXYZ"),
                new SpannableString("*"), new SpannableString("#")};
        for (int btnIndex = 0; btnIndex < numberBtns.length; btnIndex++) {
            final Button numBtn = numberBtns[btnIndex];
            stringBtns[btnIndex].setSpan(new AbsoluteSizeSpan(45, true), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            numBtn.setText(stringBtns[btnIndex]);
            final String number = getResources().getResourceEntryName(numBtn.getId()).split("button")[1];
            final String input = number.equals("Asterisc") ? "*" : (number.equals("Hashtag") ? "#" : number);
            System.out.println(number);
            numBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputButton(input);
                }
            });

            numBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (input.equals("0")) {
                        inputButton("+");
                    } else if (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
                        //if(((TextView) findViewById(R.id.phoneNumberInput)).getText().length() == 0){
                            quickDial.setContentView(R.layout.quickdialpopup);
                            ((TextView) quickDial.findViewById(R.id.dialogQuestion)).setText(
                                    "Assign a quick dial number " +
                                    //((TextView) findViewById(R.id.phoneNumberInput)).getText() +
                                    " to this button?");
                            quickDial.findViewById(R.id.qdyesbtn).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SharedPreferences prefs = MainActivity.this.getSharedPreferences("com.example.cmhw1dialler", Context.MODE_PRIVATE);

                                    prefs.edit().putString("dialButton", input).apply();
                                    startActivity(new Intent(getApplicationContext(), UpdateSpeedDial.class));
                                }
                            });
                            quickDial.findViewById(R.id.qdcancelbtn).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    quickDial.dismiss();
                                }
                            });
                            quickDial.show();
                        //}
                    }
                    return true;
                }
            });
        }

        ImageButton deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
                if (phoneNumberInput.getText().length() != 0) {
                    phoneNumberInput.setText(phoneNumberInput.getText().subSequence(0, phoneNumberInput.getText().length() - 1));
                }
                if (phoneNumberInput.getText().length() == 1) {
                    SharedPreferences prefs = MainActivity.this.getSharedPreferences("com.example.cmhw1dialler", Context.MODE_PRIVATE);
                    String quickdiallabel = prefs.getString(phoneNumberInput.getText() + "-label", "null");
                    if (!quickdiallabel.equals("null")) {
                        ((TextView) findViewById(R.id.dialLabeltextview)).setText(quickdiallabel);
                    }
                } else {
                    ((TextView) findViewById(R.id.dialLabeltextview)).setText("");
                }
            }
        });


        FloatingActionButton callBtn = findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = MainActivity.this.getSharedPreferences("com.example.cmhw1dialler", Context.MODE_PRIVATE);

                TextView phoneNumber = findViewById(R.id.phoneNumberInput);
                if(phoneNumber.getText().length() ==1){
                    String quickdiallabel = prefs.getString(phoneNumber.getText() + "-label", "null");
                    if (!quickdiallabel.equals("null")){
                        dialPhoneNumber(prefs.getString(phoneNumber.getText() + "-phonenumber", "null"));
                    }
                } else {
                    dialPhoneNumber((String) phoneNumber.getText());
                }
            }
        });
    }

    public void inputButton(String input) {
        TextView phoneNumberInput = findViewById(R.id.phoneNumberInput);
        if (phoneNumberInput.getText().length() < 13) {
            String phoneNumber = phoneNumberInput.getText() + input;
            SharedPreferences prefs = MainActivity.this.getSharedPreferences("com.example.cmhw1dialler", Context.MODE_PRIVATE);
            if(phoneNumber.length() == 1) {
                String quickdiallabel = prefs.getString(phoneNumber + "-label", "null");
                if (!quickdiallabel.equals("null")) {
                    ((TextView) findViewById(R.id.dialLabeltextview)).setText(quickdiallabel);
                }
            } else {
                ((TextView) findViewById(R.id.dialLabeltextview)).setText("");
            }
            phoneNumberInput.setText(phoneNumber);
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