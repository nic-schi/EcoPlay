package com.jadehs.ma.ecoplay.startseite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.onboarding.OnboardingActivity;

public class StartActivity extends EcoPlayActivity {

    public StartActivity() {
        super(true, R.string.app_name, R.drawable.logo_small, true, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        boolean showOnboarding = preferences.getBoolean("onboarding", true);

        if (showOnboarding) {
            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
        }

        preferences.edit().putBoolean("onboarding", false).apply();
        setContentView(R.layout.activity_start);
    }
}