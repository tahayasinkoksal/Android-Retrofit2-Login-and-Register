package com.example.uyegirisliretrofit.Utils;

import android.app.Application;
import android.content.Context;

import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

public class OneSignalClass extends Application implements OSSubscriptionObserver {

    public static String userNotificaionNumber;




    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: Add OneSignal initialization here
        OneSignal.addSubscriptionObserver(this);
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() &&
                stateChanges.getTo().getSubscribed()) {
            // The user is subscribed
            // Either the user subscribed for the first time
            // Or the user was subscribed -> unsubscribed -> subscribed
            stateChanges.getTo().getUserId();

            userNotificaionNumber = stateChanges.getTo().getUserId();
            System.out.println("TAHA OneSignalClass");
            // Make a POST call to your server with the user ID
        }
    }

    public String getBildirimId() {
        return this.userNotificaionNumber;
    }
}
