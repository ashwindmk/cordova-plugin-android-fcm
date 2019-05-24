package com.androidfcm.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

/**
 * This class echoes a string called from JavaScript.
 */
public class AndroidFcmPlugin extends CordovaPlugin {
    private static final String TAG = "cordova-plugin-android-fcm";

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
                        com.webengage.sdk.android.Logger.d(TAG, "Updating WebEngage FCM token: " + token);
                        com.webengage.sdk.android.WebEngage.get().setRegistrationID(token);
                    } catch (Throwable t) {
                        com.webengage.sdk.android.Logger.e(TAG, "FCM token error", t);
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
