package com.jadehs.ma.ecoplay.quiz.quizzes;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.QuizActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;

public class WaldbrandQuizActivity extends QuizActivity {

    public WaldbrandQuizActivity() {
        super(R.string.quiz_5_name, R.drawable.fire);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFinish(int correct, int count, double success) {
        if (correct >= 5 && correct == count) {
            new StickerManager(this).unlockArchievement("quiz.fire.1", this.getString(R.string.sticker_3_stickername));
        }
    }

}
