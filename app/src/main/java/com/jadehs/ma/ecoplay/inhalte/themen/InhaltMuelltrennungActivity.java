package com.jadehs.ma.ecoplay.inhalte.themen;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;


public class InhaltMuelltrennungActivity extends InhaltActivity {

    public InhaltMuelltrennungActivity() {
        super(
                R.string.inhalte_4_thema,
                R.drawable.recycle,
                new Fragment(),
                new int[]{},
                new int[]{}
        );
    }

    @Override
    protected void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent) {

    }

}