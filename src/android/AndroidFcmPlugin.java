package com.androidfcm.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.webengage.sdk.android.WebEngage;
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
               FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                           @Override
                           public void onComplete(@NonNull Task<String> task) {
                                           try {
                                               String token = task.getResult();
                                               com.webengage.sdk.android.Logger.d(TAG, "Updating WebEngage FCM token: " + token);
                                               com.webengage.sdk.android.WebEngage.get().setRegistrationID(token);
                                           } catch (Exception e) {
                                                 com.webengage.sdk.android.Logger.e(TAG, "FCM token error", e);
                                           }
                                       }
                                   });


            }
    }


    private void getToken(CallbackContext callbackContext) {
        if (callbackContext != null) {

                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            try {
                                                String token = task.getResult();
                                                 try {
                                                                         callbackContext.success(token);
                                                                     } catch (Throwable t) {
                                                                         callbackContext.error(t.getMessage());
                                                                     }
                                            } catch (Exception e) {
                                    com.webengage.sdk.android.Logger.e(TAG, "FCM token error", e);
                                            }
                                        }
                                    });

        }
    }
}
