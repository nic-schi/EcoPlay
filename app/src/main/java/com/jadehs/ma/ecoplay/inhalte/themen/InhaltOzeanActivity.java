package com.jadehs.ma.ecoplay.inhalte.themen;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;


public class InhaltOzeanActivity extends InhaltActivity {

    public InhaltOzeanActivity() {
        super(
                R.string.inhalte_5_thema,
                R.drawable.turtle,
                new Fragment(),
                new int[]{},
                new int[]{}
        );
    }

    @Override
    protected void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent) {
        if (secondsSpent >= 150 /* 2:30 min */) {
            new StickerManager(this).unlockArchievement("text.ozean.1", this.getString(R.string.sticker_7_stickername));
        }
    }

}