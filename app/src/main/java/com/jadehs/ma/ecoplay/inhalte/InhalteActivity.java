package com.jadehs.ma.ecoplay.inhalte;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class InhalteActivity extends EcoPlayActivity {

    public InhalteActivity() {
        super(true, R.string.startseite_read, R.drawable.books, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalte);
    }

}