package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class TaschengeldmanagerActivity extends EcoPlayActivity {

    public TaschengeldmanagerActivity() {
        super(true, R.string.startseite_taschengeld, R.drawable.wallet, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taschengeldmanager);
    }
}