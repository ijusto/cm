package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mReceiver = new CustomReceiver();

    /* One of the simplest ways to get your app's package name is to use BuildConfig.APPLICATION_ID,
       which returns the applicationId property's value from your module-level build.gradle file.
       You'll use this variable as the broadcast Intent action. */
    public static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Intent filters specify the types of intents a component can receive. They are used in
           filtering out the intents based on Intent values like action and category.
           When the system receives an Intent as a broadcast, it searches the broadcast receivers
           based on the action value specified in the IntentFilter object.*/
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);

        LocalBroadcastManager.getInstance(this)
                             .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }
    @Override
    protected void onDestroy() {
        /* To save system resources and avoid leaks, dynamic receivers must be unregistered when
           they are no longer needed or before the corresponding activity or app is destroyed,
           depending on the context used. */
        //Unregister the receiver
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this)
                             .unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        /* Because this broadcast is meant to be used solely by your app, use LocalBroadcastManager
           to manage the broadcast. LocalBroadcastManager is a class that allows you to register for
           and send broadcasts that are of interest to components within your app.
           By keeping broadcasts local, you ensure that your app data isn't shared with other
           Android apps. Local broadcasts keep your information more secure and maintain system
           efficiency.*/
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}
