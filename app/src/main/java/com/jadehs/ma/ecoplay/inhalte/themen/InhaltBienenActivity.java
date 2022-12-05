package com.jadehs.ma.ecoplay.inhalte.themen;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;


public class InhaltBienenActivity extends InhaltActivity {

    public InhaltBienenActivity() {
        super(
                R.string.inhalte_1_thema,
                R.drawable.bee,
                R.layout.inhalt_bienen,
                new int[]{
                        R.string.inhalt_1_text_easy_1,
                        R.string.inhalt_1_text_easy_2
                },
                new int[]{
                        R.string.inhalt_1_text_hard_1,
                        R.string.inhalt_1_text_hard_2
                }
        );
    }

    @Override
    protected void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent) {
        if (secondsSpent >= 150 /* 2:30 min */) {
            new StickerManager(this).unlockArchievement("bee01", this.getString(R.string.sticker_1_stickername));
        }
    }

}