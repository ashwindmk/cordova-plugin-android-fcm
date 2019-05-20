package com.androidfcm.cordova;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.util.Log;
import java.util.Map;

public class CustomFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "custom-fcm-service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Add your custom native code here
        Log.i(TAG, "FCM message received");
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        // Send token to server
        Log.i(TAG, "new fcm token: " + s);
    }
}
