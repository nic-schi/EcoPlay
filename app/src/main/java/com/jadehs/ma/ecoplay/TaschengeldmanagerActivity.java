package com.jadehs.ma.ecoplay;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jadehs.ma.ecoplay.sticker.StickerManager;

import java.text.DecimalFormat;

public class TaschengeldmanagerActivity extends EcoPlayActivity {

    public TaschengeldmanagerActivity() {
        super(true, R.string.startseite_taschengeld, R.drawable.wallet, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taschengeldmanager);

        // Setze money wert
        this.refreshBetrag(this.getMoney());

        // Füge listener hinzu
        FloatingActionButton negativ = findViewById(R.id.negativ);
        negativ.setOnClickListener((v) -> this.onMoney(R.id.negativ));

        FloatingActionButton positiv = findViewById(R.id.positiv);
        positiv.setOnClickListener((v) -> this.onMoney(R.id.positiv));

        ((EditText) this.findViewById(R.id.aktuellerbetrag)).addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Float value = TaschengeldmanagerActivity.this.extractValue(R.id.aktuellerbetrag, true);
                if (value != null) {
                    TaschengeldmanagerActivity.this.setMoney(value);
                    TaschengeldmanagerActivity.this.grantSticker();
                }
            }
        });
    }

    private Float extractValue(int res, boolean ignore) {
        EditText feld = findViewById(res);
        String rawValue = feld.getText().toString();
        if (!rawValue.isEmpty()) {
            try {
                return Float.parseFloat(rawValue);
            } catch (NumberFormatException ex) {
                return null;
            }
        } else if (!ignore) {
            Toast.makeText(this, R.string.taschengeld_noinput, Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private void grantSticker() {
        new StickerManager(this).unlockArchievement("money.1", R.string.sticker_16_stickername);
    }

    private void onMoney(int type) {
        Float value = extractValue(R.id.betragToAdd, false);

        if (value != null) {
            float betrag = getMoney();
            this.refreshBetrag((type == R.id.positiv) ? (betrag + value) : (betrag - value));
            this.grantSticker();
        }
    }

    private void refreshBetrag(float neu) {
        EditText edit = this.findViewById(R.id.aktuellerbetrag);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(neu);
        edit.setText(formatted);
        this.setMoney(neu);
    }

}