package com.jadehs.ma.ecoplay.quiz.quizzes;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.QuizActivity;
import com.jadehs.ma.ecoplay.quiz.fragen.Frage;
import com.jadehs.ma.ecoplay.quiz.fragen.FrageFragment;
import com.jadehs.ma.ecoplay.sticker.StickerManager;

public class BienenQuizActivity extends QuizActivity {

    public BienenQuizActivity() {
        super(R.string.quiz_1_name, R.drawable.bee);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Frage 1: Bienenalter
        FrageFragment frage1 = new FrageFragment(getString(R.string.quiz_1_frage_1_bezeichnung));
        frage1.addAntworten(getResources().getStringArray(R.array.quiz_1_frage_1_antworten));
        frage1.setSolution(Frage.B);
        this.addFrage(frage1);

        // Frage 2: Bienenalter
        FrageFragment frage2 = new FrageFragment(getString(R.string.quiz_1_frage_2_bezeichnung));
        frage2.addAntworten(getResources().getStringArray(R.array.quiz_1_frage_2_antworten));
        frage2.setSolution(Frage.C);
        this.addFrage(frage2);

        // Frage 3: Bienenalter
        FrageFragment frage3 = new FrageFragment(getString(R.string.quiz_1_frage_3_bezeichnung));
        frage3.addAntworten(getResources().getStringArray(R.array.quiz_1_frage_3_antworten));
        frage3.setSolution(Frage.A);
        this.addFrage(frage3);

        // Frage 4: Bienenalter
        FrageFragment frage4 = new FrageFragment(getString(R.string.quiz_1_frage_4_bezeichnung));
        frage4.addAntworten(getResources().getStringArray(R.array.quiz_1_frage_4_antworten));
        frage4.setSolution(Frage.A);
        this.addFrage(frage4);

        // Frage 5: Bienenalter
        FrageFragment frage5 = new FrageFragment(getString(R.string.quiz_1_frage_5_bezeichnung));
        frage5.addAntworten(getResources().getStringArray(R.array.quiz_1_frage_5_antworten));
        frage5.setSolution(Frage.D);
        this.addFrage(frage5);
    }

    @Override
    protected void onFinish(int correct, int count, double success) {
        if (correct >= (count - 1)) {
            new StickerManager(this).unlockArchievement("bee02", this.getString(R.string.sticker_2_stickername));
        }
    }

}
