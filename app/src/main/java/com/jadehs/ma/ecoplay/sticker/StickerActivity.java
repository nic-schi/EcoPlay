package com.jadehs.ma.ecoplay.sticker;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class StickerActivity extends EcoPlayActivity {

    public StickerActivity() {
        super(true, R.string.startseite_sticker, null, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker);
    }
}