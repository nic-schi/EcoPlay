package com.jadehs.ma.ecoplay;

import android.os.Bundle;

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