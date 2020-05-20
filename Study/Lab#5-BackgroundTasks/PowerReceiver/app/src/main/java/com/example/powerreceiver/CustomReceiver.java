package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.example.powerreceiver.MainActivity.ACTION_CUSTOM_BROADCAST;

public class CustomReceiver extends BroadcastReceiver {

    /* When a broadcast receiver intercepts a broadcast that it's registered for, the Intent is
       delivered to the receiver's onReceive() method. */
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                /* If using the ACTION_HEADSET_PLUG, you may then check whether it was connect or
                   disconnected:
                 */
                case Intent.ACTION_HEADSET_PLUG:
                    int state = intent.getIntExtra("state", -1);
                    switch (state) {
                        case 0:
                            toastMessage = "Headset is unplugged";
                            break;
                        case 1:
                            toastMessage = "Headset is plugged";
                            break;
                        default:
                            toastMessage = "I have no idea what the headset state is";
                    }

                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom Broadcast Received";
                    break;
            }

            //Display the toast.
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
