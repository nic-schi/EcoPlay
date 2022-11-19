package com.jadehs.ma.ecoplay.quiz;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

public abstract class QuizActivity extends EcoPlayActivity {

    public QuizActivity(int titleResource) {
        super(true, titleResource, R.drawable.questionstripes, false);
    }

    public QuizActivity(int titleResource, int logoResource) {
        super(true, titleResource, logoResource, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
}
