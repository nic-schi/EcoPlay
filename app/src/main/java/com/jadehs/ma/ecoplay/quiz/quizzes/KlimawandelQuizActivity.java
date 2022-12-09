package com.jadehs.ma.ecoplay.quiz.quizzes;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.QuizActivity;
import com.jadehs.ma.ecoplay.sticker.StickerManager;

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
        if (correct >= 5 && correct == count) {
            new StickerManager(this).unlockArchievement("quiz.klima.2", this.getString(R.string.sticker_13_stickername));
        }
        if (correct >= 3) {
            new StickerManager(this).unlockArchievement("quiz.klima.1", this.getString(R.string.sticker_12_stickername));
        }
    }

}
