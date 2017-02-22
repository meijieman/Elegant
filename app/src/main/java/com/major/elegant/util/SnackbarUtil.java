package com.major.elegant.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.major.elegant.R;

/**
 * Created by Administrator on 2017/2/22.
 */
public class SnackbarUtil {

    public static void show(View view, String msg, String action, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        if (action != null) {
            snackbar.setAction(action, listener)
                    .setActionTextColor(view.getResources().getColor(R.color.colorPrimary));
        }
//        snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.colorPrimary));
//        ((TextView)snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(view.getResources().getColor(android.R.color.holo_red_light));
        snackbar.show();
    }

    public static void show(View view, String msg) {
        show(view, msg, null, null);
    }
}
