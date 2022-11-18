package com.jadehs.ma.ecoplay.startseite;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.onboarding.OnboardingActivity;

public class StartActivity extends EcoPlayActivity {
    public static final String TAG = "ECOPLAY";

    public StartActivity() {
        super(true, R.string.app_name, R.drawable.logo, true, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Schreibe Sticker & FAQ
//        new StickerManager(this).writeDefault();
//        new FAQManager(this).writeDefault();

        // prüfe auf onboarding
        if (this.getOnboarding()) {
            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_start);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_start);
    }

}