package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class ZahnputzassistentActivity extends EcoPlayActivity {

    public ZahnputzassistentActivity() {
        super(true, R.string.startseite_zahnputzassistent, R.drawable.toothbrush, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zahnputzassistent);
    }
}