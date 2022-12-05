package com.jadehs.ma.ecoplay.quiz.quizzes;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.QuizActivity;

public class MuellQuizActivity extends QuizActivity {

    public MuellQuizActivity() {
        super(R.string.quiz_3_name, R.drawable.recycle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFinish(int correct, int count, double success) {

    }

}
