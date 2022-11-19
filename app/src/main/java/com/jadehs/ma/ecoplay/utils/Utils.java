package com.jadehs.ma.ecoplay.utils;

import android.app.Activity;

public class Utils {

    public static void refreshActivity(Activity activity) {
        activity.finish();
        activity.startActivity(activity.getIntent());
        activity.overridePendingTransition(0, 0);
    }

}
