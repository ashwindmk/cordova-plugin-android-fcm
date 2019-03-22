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
        Log.i(TAG, "intialized successfully");
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getToken")) {
            this.getToken(callbackContext);
            return true;
        }
        return false;
    }

    private void getToken(CallbackContext callbackContext) {
        if (callbackContext != null) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    try {
                        String token = instanceIdResult.getToken();
                        Log.i(TAG, "fcm token: " + token);
                        callbackContext.success(token);
                    } catch (Throwable t) {
                        Log.e(TAG, "fcm token error");
                        callbackContext.error("Error while getting token");
                    }
                }
            });
        }
    }
}
