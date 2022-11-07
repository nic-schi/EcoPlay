package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class MainActivity extends EcoPlayActivity {

    public MainActivity() {
        super(R.string.app_name, true, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}