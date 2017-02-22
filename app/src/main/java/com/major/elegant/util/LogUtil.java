package com.major.elegant.util;

import android.util.Log;

/**
 * Created by Administrator on 2017/2/22.
 */
public class LogUtil {

    private static final String TAG = "==##ele";

    public static void w(String msg) {
        Log.w(TAG, "" + msg);
    }

    public static void e(String msg) {
        Log.e(TAG, "" + msg);
    }
}
