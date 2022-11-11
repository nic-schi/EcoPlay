package com.jadehs.ma.ecoplay.FAQ;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class FAQActivity extends EcoPlayActivity {

    public FAQActivity() {
        super(true, R.string.actionbar_menu_faq, R.drawable.question_round, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);
    }
}