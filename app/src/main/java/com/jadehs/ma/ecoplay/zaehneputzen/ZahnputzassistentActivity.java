package com.jadehs.ma.ecoplay.zaehneputzen;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ZahnputzassistentActivity extends EcoPlayActivity {
    private double totalTime = 0;
    private final ArrayList<Double> accuracys = new ArrayList<>();

    public ZahnputzassistentActivity() {
        super(true, R.string.startseite_zahnputzassistent, R.drawable.toothbrush, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zahnputzassistent);
    }

    public void addAccuracy(double accuracy) {
        this.accuracys.add(accuracy);
    }

    public double getAccuracy() {
        double sum = 0.0;
        for (double v : this.accuracys) {
            sum += v;
        }
        return Math.min(100, sum / this.accuracys.size());
    }

    public void countUp() {
        this.totalTime++;
    }

    public double getCount() {
        return this.totalTime;
    }

}