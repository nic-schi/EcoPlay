package com.jadehs.ma.ecoplay;

import android.os.Bundle;

public class QuizActivity extends EcoPlayActivity {

    public QuizActivity() {
        super(R.string.Startseite_quiz, R.drawable.quiz, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
}