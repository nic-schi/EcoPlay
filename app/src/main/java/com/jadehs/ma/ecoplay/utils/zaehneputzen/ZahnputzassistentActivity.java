package com.jadehs.ma.ecoplay.utils.zaehneputzen;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class ZahnputzassistentActivity extends EcoPlayActivity {
    private double totalTime = 0;

    public ZahnputzassistentActivity() {
        super(true, R.string.startseite_zahnputzassistent, R.drawable.toothbrush, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zahnputzassistent);
    }

    public void countUp() {
        this.totalTime++;
    }

    public double getCount() {
        return this.totalTime;
    }

}