package com.jadehs.ma.ecoplay.utils.zaehneputzen;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

import java.util.Objects;

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