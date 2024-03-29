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

import com.jadehs.ma.ecoplay.BuildConfig;
import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Difficulty;

import java.util.Locale;

public abstract class InhaltActivity extends EcoPlayActivity {
    private final boolean maintenance;
    private final int[] easyTexte;
    private final Fragment inhaltFragment;
    private int[] alleTexte;
    private int[] hardTexte = null;

    private TextToSpeech tts;
    private long startTime;

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] easyTexte, boolean maintenance) {
        super(true, actionBarTitelResourceID, logo, true, true);
        this.inhaltFragment = inhaltFragment;
        this.easyTexte = easyTexte;
        this.alleTexte = easyTexte;
        this.maintenance = maintenance;
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, int inhaltFragmentLayout, int[] easyTexte, boolean maintenance) {
        this(actionBarTitelResourceID, logo, new Fragment(inhaltFragmentLayout), easyTexte, maintenance);
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] easyTexte) {
        this(actionBarTitelResourceID, logo, inhaltFragment, easyTexte, !BuildConfig.DEBUG);
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, int inhaltFragmentLayout, int[] easyTexte) {
        this(actionBarTitelResourceID, logo, new Fragment(inhaltFragmentLayout), easyTexte);
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] easyTexte, int[] hardTexte) {
        this(actionBarTitelResourceID, logo, inhaltFragment, easyTexte, hardTexte, !BuildConfig.DEBUG);
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] easyTexte, int[] hardTexte, boolean maintenance) {
        this(actionBarTitelResourceID, logo, inhaltFragment, easyTexte, maintenance);
        this.hardTexte = hardTexte;
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, int inhaltFragmentLayout, int[] easyTexte, int[] hardTexte) {
        this(actionBarTitelResourceID, logo, inhaltFragmentLayout, easyTexte, hardTexte, !BuildConfig.DEBUG);
    }

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, int inhaltFragmentLayout, int[] easyTexte, int[] hardTexte, boolean maintenance) {
        this(actionBarTitelResourceID, logo, new Fragment(inhaltFragmentLayout), easyTexte, hardTexte, maintenance);
    }

    protected abstract void onHasRead(long millisecondsSpent, long secondsSpent, long minutesSpent);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalt);

        if (maintenance) {
            setContentView(R.layout.fragment_maintenance);
        } else {
            this.startTime = System.currentTimeMillis();
            this.determineDifficulty();

            // Füge inhalt hinzu oder maintenance
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(R.id.inhaltContainer, this.inhaltFragment, getString(this.getActionbarTitelRessourceID()).toLowerCase() + "-fragment");
            trans.commit();
        }

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
        if (!this.maintenance) {
            View view = this.inhaltFragment.getView();
            if (view != null) {
                this.setTexte(view);
            }
        }
    }

    /**
     * Setzt die Texte des Inhalts auf die TextViews in der ersten Iteraktion.
     * So können Header und Inhalt seperat gesetzt werden.
     * Alle TextViews die daher unmittelbar dem Inhaltfragment untergeordnet sind, werden ersetzt.
     *
     * @param view Das InhaltFragment
     */
    private void setTexte(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            String noReplace = getResources().getString(R.string.replace_tag);

            int j = 0;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View textView = viewGroup.getChildAt(i);
                if (textView instanceof TextView) {
                    if (textView.getTag() == null || textView.getTag().equals(noReplace)) {
                        ((TextView) textView).setText(this.alleTexte[j]);
                        j++;
                    }
                }
            }
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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
