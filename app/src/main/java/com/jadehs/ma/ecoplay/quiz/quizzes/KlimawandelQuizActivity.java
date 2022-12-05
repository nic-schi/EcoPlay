package com.jadehs.ma.ecoplay.quiz.quizzes;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.QuizActivity;

public class KlimawandelQuizActivity extends QuizActivity {

    public KlimawandelQuizActivity() {
        super(R.string.quiz_4_name, R.drawable.earth_globe);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFinish(int correct, int count, double success) {

    }

}
