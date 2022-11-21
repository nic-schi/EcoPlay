package com.jadehs.ma.ecoplay.inhalte;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

import java.util.Locale;

public abstract class InhaltActivity extends EcoPlayActivity {
    private final Fragment inhaltFragment;
    private final int[] alletexte;
    private TextToSpeech tts;

    public InhaltActivity(Integer actionBarTitelResourceID, Integer logo, Fragment inhaltFragment, int[] alletexte) {
        super(true, actionBarTitelResourceID, logo, true, true);
        this.inhaltFragment = inhaltFragment;
        this.alletexte = alletexte;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalt);

        // Füge inhalt hinzu
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.inhaltContainer, this.inhaltFragment, getString(this.getActionbarTitelRessourceID()) + "-fragment");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
        tts.shutdown();
    }

    public void onSpeaker(View view) {
        this.show(false, false, true, true);

        this.tts.setPitch(0.9f);
        this.tts.setSpeechRate(1.0f);
        this.tts.setLanguage(new Locale(this.getLanguage()));

        StringBuilder texte = new StringBuilder();
        for (int text : this.alletexte) {
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
