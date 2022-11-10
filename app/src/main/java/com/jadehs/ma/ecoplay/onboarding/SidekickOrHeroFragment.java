package com.jadehs.ma.ecoplay.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.Difficulty;
import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class SidekickOrHeroFragment extends Fragment {

    private String header;
    private String antwort_sidekick;
    private String antwort_held;

    private int btn_bg;
    private int btn_active_bg;

    private ImageButton sidekick;
    private ImageButton held;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sidekick_or_hero, container, false);

        this.btn_bg = this.getResources().getColor(R.color.onboarding_wissenspruefung_btn_bg, null);
        this.btn_active_bg = this.getResources().getColor(R.color.onboarding_wissenspruefung_btn_active_bg, null);

        this.sidekick = view.findViewById(R.id.sidekick);
        this.held = view.findViewById(R.id.held);

        // Setze fragentext
        TextView frage = view.findViewById(R.id.frage);
        frage.setText(this.header);

        // Setze antwortentext
        this.resetDifficultyButtonColors();
        Activity activity = this.requireActivity();

        if (activity instanceof EcoPlayActivity) {
            Difficulty diff = ((EcoPlayActivity) activity).getDifficulty();
            TextView antwort = view.findViewById(R.id.antwort);


            if (diff == Difficulty.HELD) {
                antwort.setText(this.antwort_held);
                this.setDifficultyButtonActiveColor(this.held);
            } else {
                antwort.setText(this.antwort_sidekick);
                this.setDifficultyButtonActiveColor(this.sidekick);
            }
        }

        // setze onClickListener
        this.sidekick.setOnClickListener(v -> this.onDifficultyButtonClick(Difficulty.SIDEKICK));
        this.held.setOnClickListener(v -> this.onDifficultyButtonClick(Difficulty.HELD));

        return view;
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SidekickOrHeroFragment);

        this.header = a.getString(R.styleable.SidekickOrHeroFragment_frage);
        this.antwort_sidekick = a.getString(R.styleable.SidekickOrHeroFragment_antwort_sidekick);
        this.antwort_held = a.getString(R.styleable.SidekickOrHeroFragment_antwort_held);

        a.recycle();
    }

    /**
     * Wird aufgerufen wenn im Onboarding auf Sidekick oder Held geklickt wird
     *
     * @param diff Die Difficulty
     */
    public void onDifficultyButtonClick(Difficulty diff) {
        this.resetDifficultyButtonColors();

        TextView antwort = this.requireView().findViewById(R.id.antwort);

        // prüf ob Held oder Sidekick, dann setze den Text und die active color für den Button
        if (diff == Difficulty.HELD) {
            this.setDifficultyButtonActiveColor(this.held);
            antwort.setText(this.antwort_held);
        } else if (diff == Difficulty.SIDEKICK) {
            this.setDifficultyButtonActiveColor(this.sidekick);
            antwort.setText(this.antwort_sidekick);
        }

        Activity activity = this.requireActivity();

        // setze neue difficulty
        if (activity instanceof EcoPlayActivity) {
            ((EcoPlayActivity) activity).setDifficulty(diff);
        }
    }

    public void setDifficultyButtonActiveColor(ImageButton btn) {
        ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(this.btn_active_bg));
    }

    public void resetDifficultyButtonColors() {
        ViewCompat.setBackgroundTintList(this.sidekick, ColorStateList.valueOf(this.btn_bg));
        ViewCompat.setBackgroundTintList(this.held, ColorStateList.valueOf(this.btn_bg));
    }

}