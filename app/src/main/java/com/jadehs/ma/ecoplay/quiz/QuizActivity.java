package com.jadehs.ma.ecoplay.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.quiz.fragen.Frage;
import com.jadehs.ma.ecoplay.quiz.fragen.FrageFragment;

import java.util.ArrayList;

public abstract class QuizActivity extends EcoPlayActivity {
    private final ArrayList<FrageFragment> fragen = new ArrayList<>();
    private int currentFrage = -1;
    private int correctlyAnswered = 0;

    public QuizActivity(int titleResource) {
        super(true, titleResource, R.drawable.questionstripes, false);
    }

    public QuizActivity(int titleResource, int logoResource) {
        super(true, titleResource, logoResource, false);
    }

    public void weiter(View view) {
        Button btn = (Button) view;

        if (!fragen.isEmpty()) {
            if (currentFrage >= 0) {
                boolean filled = this.checkIfFilled();
                if (!filled) {
                    return;
                }
                checkForCorrectAnswer();
            }

            currentFrage++;

            // setze aktuellen stand
            setCurrentStand();

            // entferne frage
            FrameLayout layout = findViewById(R.id.quizfrage);
            layout.removeAllViews();

            // füge neue frage hinzu
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(layout.getId(), fragen.get(currentFrage));
            trans.commit();

            // Setze progress
            this.updateProgressbar();

            // setze button text falls dieser bei der letzten frage ist
            if ((currentFrage + 1) == fragen.size()) {
                btn.setText(R.string.quiz_fertig);
                btn.setOnClickListener(this::fertig);
            }
        }
    }

    private void setCurrentStand() {
        TextView textView = findViewById(R.id.aktuellerStand);
        textView.setText(String.format(getString(R.string.quiz_current), currentFrage + 1, this.fragen.size()));
    }

    private void checkForCorrectAnswer() {
        FrageFragment aktuelleFrage = fragen.get(currentFrage);
        Frage frage = aktuelleFrage.getAnswer();
        Log.v("eco -> selected", frage.name());

        if (aktuelleFrage.isSolution(frage)) {
            correctlyAnswered++;
        }
    }

    private boolean checkIfFilled() {
        FrageFragment aktuelleFrage = fragen.get(currentFrage);

        if (!aktuelleFrage.isAnswered()) {
            Toast.makeText(this, R.string.quiz_noanswer, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateProgressbar() {
        // Setze progress
        double fragenCount = fragen.size();
        double progress = (currentFrage / fragenCount) * 100;
        double secProgress = ((currentFrage + 1) / fragenCount) * 100;

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress((int) Math.round(progress), true);
        progressBar.setSecondaryProgress((int) Math.round(secProgress));
    }

    private void fertig(View view) {
        if (currentFrage >= 0) {
            boolean filled = this.checkIfFilled();
            if (!filled) {
                return;
            }
            checkForCorrectAnswer();
        }
        currentFrage++;

        // entferne frage
        FrameLayout layout = findViewById(R.id.quizfrage);
        layout.removeAllViews();

        // Setze progress
        this.updateProgressbar();

        // füge feedback hinzu
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.quizfrage, new QuizFeedbackFragment(this.correctlyAnswered, this.fragen.size()));
        trans.commit();

        // verstecke knopf
        view.setVisibility(View.GONE);
    }

    protected void addFrage(FrageFragment frage) {
        this.fragen.add(frage);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        findViewById(R.id.weiterKnopf).setOnClickListener(this::weiter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        findViewById(R.id.weiterKnopf).setClickable(!fragen.isEmpty());
        this.weiter(findViewById(R.id.weiterKnopf));
    }
}
