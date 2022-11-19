package com.jadehs.ma.ecoplay.inhalte.bienen;


import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;

public class InhaltBienenActivity extends InhaltActivity {

    public InhaltBienenActivity() {
        super(
                R.string.inhalte_1_thema,
                R.drawable.bee,
                new InhaltBienenFragment(),
                new int[]{
                        R.string.inhalt_1_text_1
                }
        );
    }

}