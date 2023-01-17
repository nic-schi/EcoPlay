package com.jadehs.ma.ecoplay.inhalte.themen;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;


public class InhaltBienenActivity extends InhaltActivity implements View.OnClickListener {
    private int clickCount = 0;

    public InhaltBienenActivity() {
        super(
                R.string.inhalte_1_thema,
                R.drawable.bee,
                R.layout.inhalt_bienen,
                new int[]{
                        R.string.inhalt_1_text_easy_1,
                        R.string.inhalt_1_text_easy_2,
                        R.string.inhalt_1_text_easy_3,
                        R.string.inhalt_1_text_hard_4
                },
                new int[]{
                        R.string.inhalt_1_text_hard_1,
                        R.string.inhalt_1_text_hard_2,
                        R.string.inhalt_1_text_hard_3,
                        R.string.inhalt_1_text_hard_4
                },
                false
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView beelove = findViewById(R.id.beelove);
        beelove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ImageView beelove = (ImageView) v;

        int newRessourceID;
        switch (clickCount) {
            case 0:
                newRessourceID = R.drawable.eco_reveal1;
                break;
            case 1:
                newRessourceID = R.drawable.eco_reveal2;
                break;
            case 2:
                newRessourceID = R.drawable.eco_reveal3;
                break;
            case 3:
                newRessourceID = R.drawable.eco_reveal4;
                break;
            case 4:
                newRessourceID = R.drawable.eco_reveal5;
                break;
            case 5:
                newRessourceID = R.drawable.eco_reveal6;
                break;
            case 6:
                newRessourceID = R.drawable.eco_reveal7;
                break;
            default:
                new StickerManager(this).unlockArchievement("secret.bee.1", R.string.sticker_17_stickername);
                newRessourceID = R.drawable.hotel_secret;
                break;
        }

        beelove.setImageResource(newRessourceID);
        clickCount++;
    }

    @Override
    protected void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent) {
        if (secondsSpent >= 150 /* 2:30 min */) {
            new StickerManager(this).unlockArchievement("text.bee.1", this.getString(R.string.sticker_1_stickername));
        }
    }

}