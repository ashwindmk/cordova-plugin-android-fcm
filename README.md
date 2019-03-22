# WebEngage Cordova SDK for Firebase Cloud Messaging (FCM) on Android

## Pre-requisites

1. WebEngage Cordova Plugin

Make sure you have integrated [webengage cordova plugin](https://github.com/WebEngage/cordova-plugin).


## Installation

1. Add this plugin to your Cordova project.

```gradle
cordova plugin add https://github.com/WebEngage/cordova-plugin-android-fcm.git --fetch
```

2. Add google-services.json file.

Follow the steps at [Android FCM Documentation](https://firebase.google.com/docs/android/setup) to get the google-services.json file from Firebase Cloud.

Save google-services.json file in the root of your project directory.

Done. Run and test push notifications from WebEngage dashboard on your Android app.


## Sample Application

You can refer to our [sample project](https://github.com/WebEngage/cordova-sample).
