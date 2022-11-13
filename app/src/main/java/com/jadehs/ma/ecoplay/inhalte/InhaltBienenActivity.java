package com.jadehs.ma.ecoplay.inhalte;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class InhaltBienenActivity extends EcoPlayActivity {

    public InhaltBienenActivity() {
        super(true, R.string.inhalte_1_thema, R.drawable.bee, true, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inhalt_bienen);
    }
}