package com.androidfcm.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

/**
 * This class echoes a string called from JavaScript.
 */
public class AndroidFcmPlugin extends CordovaPlugin {
    private static final String TAG = "android-fcm-plugin";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("updateToken".equals(action)) {
            this.updateToken();
            return true;
        } else if ("getToken".equals(action)) {
            this.getToken(callbackContext);
            return true;
        }
        return false;
    }

    private void updateToken() {
        if (CustomFirebaseMessagingService.includesWebEngage()) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    try {
                        String token = instanceIdResult.getToken();
                        // Update FCM token here
                    } catch (Throwable t) {
                        Log.e(TAG, "FCM token error", t);
                    }
                }
            });
        }
    }

    private void getToken(CallbackContext callbackContext) {
        if (callbackContext != null) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    try {
                        String token = instanceIdResult.getToken();
                        callbackContext.success(token);
                    } catch (Throwable t) {
                        callbackContext.error(t.getMessage());
                    }
                }
            });
        }
    }
}
