package com.jadehs.ma.ecoplay.inhalte.themen;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;


public class InhaltKlimawandelActivity extends InhaltActivity {

    public InhaltKlimawandelActivity() {
        super(
                R.string.inhalte_3_thema,
                R.drawable.earth_globe,
                new Fragment(),
                new int[]{},
                new int[]{}
        );
    }

    @Override
    protected void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent) {

    }

}