package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class InhalteActivity extends EcoPlayActivity {

    public InhalteActivity() {
        super(R.string.Startseite_read, R.drawable.books, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalte);
    }
}