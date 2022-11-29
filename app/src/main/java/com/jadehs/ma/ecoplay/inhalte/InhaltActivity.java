package com.jadehs.ma.ecoplay.inhalte;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Difficulty;

import java.util.Locale;

public abstract class InhaltActivity extends EcoPlayActivity {
    private final Fragment inhaltFragment;

    private final int[] easyTexte;
    private int[] alleTexte;
    private int[] hardTexte = null;

    private TextToSpeech tts;
    private int textIndex = 0;

    private long startTime;

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] easyTexte) {
        super(true, actionBarTitelResourceID, logo, true, true);
        this.inhaltFragment = inhaltFragment;
        this.easyTexte = easyTexte;
        this.alleTexte = easyTexte;
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, int inhaltFragmentLayout, int[] easyTexte) {
        this(actionBarTitelResourceID, logo, new Fragment(inhaltFragmentLayout), easyTexte);
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] easyTexte, int[] hardTexte) {
        this(actionBarTitelResourceID, logo, inhaltFragment, easyTexte);
        this.hardTexte = hardTexte;
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, int inhaltFragmentLayout, int[] easyTexte, int[] hardTexte) {
        this(actionBarTitelResourceID, logo, new Fragment(inhaltFragmentLayout), easyTexte, hardTexte);
    }

    protected abstract void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalt);

        this.startTime = System.currentTimeMillis();
        this.determineDifficulty();

        // Füge inhalt hinzu
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.inhaltContainer, this.inhaltFragment, getString(this.getActionbarTitelRessourceID()).toLowerCase() + "-fragment");
        trans.commit();

        // Baue TTS
        this.tts = new TextToSpeech(this, status -> {
        }, "com.google.android.tts");
        this.tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
            }

            @Override
            public void onDone(String utteranceId) {
                InhaltActivity.this.onStop(null);
            }

            @Override
            public void onError(String utteranceId) {
                InhaltActivity.this.onStop(null);
                Toast.makeText(InhaltActivity.this, R.string.fehler_tts, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void callOnHasRead() {
        String id = this.getClass().getSimpleName();
        long endTime = System.currentTimeMillis();
        long timeSpent = this.getInhaltTimeSpent(id) + (endTime - this.startTime);
        this.setInhaltTimeSpent(id, timeSpent);

        this.onHasRead(timeSpent, timeSpent / 1000, timeSpent / 60000);
        this.startTime = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.callOnHasRead();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.callOnHasRead();
    }

    private void determineDifficulty() {
        // Ermittle die richtigen Texte jenachdem ob easy oder hard
        Difficulty difficulty = this.getDifficulty();

        if (difficulty.equals(Difficulty.HARD) && this.hardTexte != null) {
            // Hard
            this.alleTexte = this.hardTexte;
        } else if (difficulty.equals(Difficulty.EASY)) {
            // Easy
            this.alleTexte = this.easyTexte;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.startTime = System.currentTimeMillis();
        this.determineDifficulty();

        // setze texte
        View view = this.inhaltFragment.requireView();
        this.textIndex = 0;
        this.setTexte(view);
    }

    /**
     * Findet die Textviews mit einem rekursiven call um alle Viewgroups abzugehen
     *
     * @param view Die Rootview
     */
    private void setTexte(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                this.setTexte(viewGroup.getChildAt(i));
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setText(this.alleTexte[textIndex]);
            this.textIndex++;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.textIndex = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.callOnHasRead();
        tts.stop();
        tts.shutdown();
    }

    public void onSpeaker(View view) {
        this.show(false, false, true, true);

        this.tts.setPitch(0.9f);
        this.tts.setSpeechRate(1.0f);
        this.tts.setLanguage(new Locale(this.getLanguage()));

        StringBuilder texte = new StringBuilder();
        for (int text : this.alleTexte) {
            texte.append(this.getString(text)).append("..");
        }

        this.tts.speak(texte.toString(), TextToSpeech.QUEUE_FLUSH, null, "inhalt." + getString(this.getActionbarTitelRessourceID()));
    }

    public void onPause(View view) {
        this.show(false, true, false, true);
    }

    public void onStop(View view) {
        this.show(true, false, false, false);
        if (tts.isSpeaking()) {
            tts.stop();
        }
    }

    public void onPlay(View view) {
        this.show(false, false, true, true);
    }

    private void show(boolean speaker, boolean play, boolean pause, boolean stop) {
        runOnUiThread(() -> {
            this.findViewById(R.id.speaker).setVisibility(speaker ? View.VISIBLE : View.GONE);
//        this.findViewById(R.id.play).setVisibility(play ? View.VISIBLE : View.GONE);
//        this.findViewById(R.id.pause).setVisibility(pause ? View.VISIBLE : View.GONE);
            this.findViewById(R.id.stop).setVisibility(stop ? View.VISIBLE : View.GONE);
        });
    }

}
