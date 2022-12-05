package com.jadehs.ma.ecoplay.inhalte.themen;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;


public class InhaltWaldbraendeActivity extends InhaltActivity {

    public InhaltWaldbraendeActivity() {
        super(
                R.string.inhalte_2_thema,
                R.drawable.fire,
                new Fragment(),
                new int[]{},
                new int[]{}
        );
    }

    @Override
    protected void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent) {
        
    }

}