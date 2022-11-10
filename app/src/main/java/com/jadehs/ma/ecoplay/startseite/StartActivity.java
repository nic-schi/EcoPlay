package com.jadehs.ma.ecoplay.startseite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

        if (this.getOnboarding()) {
            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
        }

        Log.v("ecoplay", getDifficulty().name());

        this.setOnboarding(false);
        setContentView(R.layout.activity_start);
    }
}