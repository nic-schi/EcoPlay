package com.jadehs.ma.ecoplay.quiz;

import android.os.Bundle;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public class QuizListeActivity extends EcoPlayActivity {

    public QuizListeActivity() {
        super(true, R.string.startseite_quiz, R.drawable.questionstripes, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_liste);
    }
}