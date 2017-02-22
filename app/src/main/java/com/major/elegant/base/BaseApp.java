package com.major.elegant.base;

import android.app.Application;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:44
 */
public class BaseApp extends Application {

    private static BaseApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static BaseApp getInstance() {
        return sInstance;
    }
}
