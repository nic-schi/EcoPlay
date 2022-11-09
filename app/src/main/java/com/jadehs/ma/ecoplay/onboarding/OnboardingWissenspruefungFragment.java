package com.jadehs.ma.ecoplay.onboarding;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.Difficulty;
import com.jadehs.ma.ecoplay.R;

public class OnboardingWissenspruefungFragment extends Fragment {

    private ImageButton sidekick;
    private ImageButton held;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding_wissenspruefung, container, false);

        // hole knöpfe
        this.sidekick = view.findViewById(R.id.sidekick);
        this.held = view.findViewById(R.id.held);

        // füge onClickListener zu Knöpfen hinzu
        sidekick.setOnClickListener(new OnSidekickClick());
        held.setOnClickListener(new OnHeldClick());

        return view;
    }

    public void resetColors() {
        ViewCompat.setBackgroundTintList(
                this.sidekick,
                ColorStateList.valueOf(getResources().getColor(R.color.onboarding_wissenspruefung_btn_bg, null)));
        ViewCompat.setBackgroundTintList(
                this.held,
                ColorStateList.valueOf(getResources().getColor(R.color.onboarding_wissenspruefung_btn_bg, null)));
    }

    public void setActiveColor(ImageButton btn) {
        ViewCompat.setBackgroundTintList(
                btn,
                ColorStateList.valueOf(getResources().getColor(R.color.onboarding_wissenspruefung_btn_active_bg, null)));
    }

    class OnSidekickClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OnboardingWissenspruefungFragment fragment = OnboardingWissenspruefungFragment.this;
            fragment.resetColors();
            fragment.setActiveColor((ImageButton) v);

            SharedPreferences preferences = fragment.requireActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);

            View frag = fragment.getView();
            if (frag != null) {
                TextView antwort = frag.findViewById(R.id.onboardingAntwort1);
                antwort.setText(R.string.onboarding_wissensprüfung_sidekick);
                preferences.edit().putInt("difficulty", Difficulty.SIDEKICK.ordinal()).apply();
            }
        }
    }

    class OnHeldClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OnboardingWissenspruefungFragment fragment = OnboardingWissenspruefungFragment.this;
            fragment.resetColors();
            fragment.setActiveColor((ImageButton) v);

            SharedPreferences preferences = fragment.requireActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);

            View frag = fragment.getView();
            if (frag != null) {
                TextView antwort = frag.findViewById(R.id.onboardingAntwort1);
                antwort.setText(R.string.onboarding_wissensprüfung_held);
                preferences.edit().putInt("difficulty", Difficulty.HELD.ordinal()).apply();
            }
        }
    }


}