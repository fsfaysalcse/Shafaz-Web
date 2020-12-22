package com.faysal.webview;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public MyApplication(){
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static synchronized MyApplication getInstance(){
        return mInstance;
    }
}