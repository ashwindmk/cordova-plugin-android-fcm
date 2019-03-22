package com.androidfcm.cordova;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.util.Log;

public class CustomFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "custom-fcm-service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "fcm message received");
        // Add your custom native code here
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG, "new fcm token: " + s);
        // Send token to server
    }
}
