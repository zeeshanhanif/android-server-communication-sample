package com.demo.demoservercommunication;

import android.app.Application;

/**
 * Created by zeeshan on 4/28/2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequestHandler.setApplicationContext(getApplicationContext());
    }
}
