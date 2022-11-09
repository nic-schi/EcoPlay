package com.jadehs.ma.ecoplay.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.Navigation;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.startseite.StartActivity;

public class OnboardingActivity extends EcoPlayActivity {

    public OnboardingActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }

    /**
     * Wird aufgerufen wenn das Onboarding beginne soll
     *
     * @param view Der Knopf
     */
    public void beginOnboarding(View view) {
        Navigation.findNavController(this, R.id.onBoarding_navContent).navigate(R.id.action_onboardingIntroFragment_to_onboardingSettingFragment);
    }

    /**
     * Wird aufgerufen wenn das Onboarding geskipped werden soll
     *
     * @param view Der Knopf
     */
    public void skipOnboarding(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // TODO: set default settings if user is experienced (given by onboarding logic)
        // ...

        startActivity(intent);
    }

}