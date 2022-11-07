package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class ZahnputzassistentActivity extends EcoPlayActivity {

    public ZahnputzassistentActivity() {
        super(R.string.Startseite_toothbrushing_assistant, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zahnputzassistent);
    }
}