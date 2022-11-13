package com.jadehs.ma.ecoplay.einstellungen;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.preference.PreferenceFragmentCompat;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class EinstellungsActivity extends EcoPlayActivity {

    public EinstellungsActivity() {
        super(true, R.string.startseite_settings, R.drawable.settings, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        // Daten übertragen
        Button uebertragenKnopf = findViewById(R.id.übertragen);
        uebertragenKnopf.setOnClickListener(new DatenUebertragen());

        // Code generieren
        Button generierenKnopf = findViewById(R.id.generieren);
        generierenKnopf.setOnClickListener(new DatenGenerieren());
    }

    public class DatenGenerieren implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView codeView = EinstellungsActivity.this.findViewById(R.id.code);
            String code = Base64.encodeToString(String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000)).getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);

            codeView.setText(code);
        }
    }

    public class DatenUebertragen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatenUebertragenDialogFragment dialog = new DatenUebertragenDialogFragment();
            dialog.show(EinstellungsActivity.this.getSupportFragmentManager(), "datenübertragung");
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

    }
}