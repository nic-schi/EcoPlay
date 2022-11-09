package com.jadehs.ma.ecoplay;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class EinstellungsActivity extends EcoPlayActivity {

    public EinstellungsActivity() {
        super(R.string.Startseite_settings, R.drawable.settings, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}