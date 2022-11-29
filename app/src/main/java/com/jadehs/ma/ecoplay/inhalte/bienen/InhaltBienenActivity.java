package com.jadehs.ma.ecoplay.inhalte.bienen;


import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.inhalte.InhaltActivity;

public class InhaltBienenActivity extends InhaltActivity {

    public InhaltBienenActivity() {
        super(
                R.string.inhalte_1_thema,
                R.drawable.bee,
                R.layout.inhalt_bienen,
                new int[]{
                        R.string.inhalt_1_text_easy_1,
                        R.string.inhalt_1_text_easy_2
                },
                new int[]{
                        R.string.inhalt_1_text_hard_1,
                        R.string.inhalt_1_text_hard_2
                }
        );
    }

}