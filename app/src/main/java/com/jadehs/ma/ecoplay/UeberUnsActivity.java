package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class UeberUnsActivity extends EcoPlayActivity {

    public UeberUnsActivity() {
        super(true, R.string.actionbar_menu_action_about, R.drawable.about_us, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ueber_uns);
    }
}