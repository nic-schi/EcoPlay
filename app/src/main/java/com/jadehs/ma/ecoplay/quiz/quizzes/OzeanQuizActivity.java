package com.jadehs.ma.ecoplay.quiz.quizzes;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.QuizActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;

public class OzeanQuizActivity extends QuizActivity {

    public OzeanQuizActivity() {
        super(R.string.quiz_2_name, R.drawable.turtle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onFinish(int correct, int count, double success) {
        if (correct >= 5 && correct == count) {
            new StickerManager(this).unlockArchievement("quiz.ozean.2", this.getString(R.string.sticker_9_stickername));
        }
        if (correct >= 3) {
            new StickerManager(this).unlockArchievement("quiz.ozean.1", this.getString(R.string.sticker_10_stickername));
        }
    }

}
