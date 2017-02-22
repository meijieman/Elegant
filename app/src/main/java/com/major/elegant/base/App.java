package com.major.elegant.base;

import android.app.Application;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:44
 */
public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }
}
