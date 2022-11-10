package com.jadehs.ma.ecoplay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.jadehs.ma.ecoplay.startseite.StartActivity;

public abstract class EcoPlayActivity extends AppCompatActivity {
    public static final String PREFNAME = "ECOPLAY";
    private final Integer logo;
    private final Integer actionBarTitelResourceID;
    private final boolean addMenu;
    private final boolean useBackButton;
    private final boolean showBar;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdit;

    public EcoPlayActivity() {
        this(false, R.string.app_name, null, false);
    }

    public EcoPlayActivity(boolean showBar) {
        this(showBar, R.string.app_name, null, false);
    }

    public EcoPlayActivity(boolean showBar, Integer actionBarTitelResourceID) {
        this(showBar, actionBarTitelResourceID, null, false);
    }

    public EcoPlayActivity(boolean showBar, Integer actionBarTitelResourceID, Integer logo, boolean addMenu) {
        this(showBar, actionBarTitelResourceID, logo, addMenu, true);
    }

    public EcoPlayActivity(boolean showBar, Integer actionBarTitelResourceID, Integer logo, boolean addMenu, boolean useBackButton) {
        this.addMenu = addMenu;
        this.actionBarTitelResourceID = actionBarTitelResourceID;
        this.useBackButton = useBackButton;
        this.logo = logo;
        this.showBar = showBar;
    }

    public Difficulty getDifficulty() {
        return Difficulty.values()[this.pref.getInt("difficulty", Difficulty.SIDEKICK.ordinal())];
    }

    /**
     * Setzt die Difficulty in den SharedPreferences
     *
     * @param value Die neue Difficulty
     */
    public void setDifficulty(Difficulty value) {
        this.prefEdit.putInt("difficulty", value.ordinal());
        this.prefEdit.apply();
    }

    public boolean getOnboarding() {
        return this.pref.getBoolean("onboarding", true);
    }

    /**
     * Setzt das Onboarding in den SharedPreferences
     *
     * @param value Das neue Onboarding
     */
    public void setOnboarding(boolean value) {
        this.prefEdit.putBoolean("onboarding", value);
        this.prefEdit.apply();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = this.getSupportActionBar();

        if (bar != null) {
            if (this.showBar) {
                bar.setDisplayHomeAsUpEnabled(this.useBackButton);
                bar.setHomeButtonEnabled(true);
                bar.setDisplayShowTitleEnabled(true);

                // titel
                if (actionBarTitelResourceID != null) {
                    bar.setTitle(this.actionBarTitelResourceID);
                } else {
                    bar.setTitle(R.string.app_name);
                }

                // Logo
                if (logo != null) {
                    bar.setLogo(logo);
                } else {
                    bar.setLogo(R.drawable.logo_small);
                }
            } else {
                bar.hide();
            }
        }

        this.pref = this.getSharedPreferences(PREFNAME, MODE_PRIVATE);
        this.prefEdit = pref.edit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (this.addMenu) {
            Intent intent = new Intent(this, StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Einstellungen
            if (item.getItemId() == R.id.action_setting) {
                intent.setClass(this, EinstellungsActivity.class);
                startActivity(intent);
            }
            // Über uns
            if (item.getItemId() == R.id.action_about) {
                intent.setClass(this, UeberUnsActivity.class);
                startActivity(intent);
            }
        }
        // Zurücknavigation
        if (item.getItemId() == 16908332 /* R.id.home nicht vorhanden, daher statisch */) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menü
        if (this.addMenu) {
            this.getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

}
