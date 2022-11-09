package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class StartActivity extends EcoPlayActivity {

    public StartActivity() {
        super(R.string.app_name, null, true, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
}