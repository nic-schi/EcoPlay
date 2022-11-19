package com.jadehs.ma.ecoplay.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageChanger {

    public LanguageChanger(Context ctx, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = ctx.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}
