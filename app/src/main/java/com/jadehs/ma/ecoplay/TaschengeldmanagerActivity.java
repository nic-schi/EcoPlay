package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class TaschengeldmanagerActivity extends EcoPlayActivity {

    public TaschengeldmanagerActivity() {
        super(R.string.Startseite_money_manager, R.drawable.wallet, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taschengeldmanager);
    }
}