package com.jadehs.ma.ecoplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.jadehs.ma.ecoplay.FAQ.FAQActivity;
import com.jadehs.ma.ecoplay.einstellungen.DatenUebertragenDialogFragment;
import com.jadehs.ma.ecoplay.einstellungen.EinstellungsActivity;
import com.jadehs.ma.ecoplay.onboarding.OnboardingActivity;
import com.jadehs.ma.ecoplay.startseite.StartActivity;
import com.jadehs.ma.ecoplay.sticker.StickerPinnwand;
import com.jadehs.ma.ecoplay.ueberuns.UeberUnsActivity;
import com.jadehs.ma.ecoplay.utils.ActionBarCustomView;
import com.jadehs.ma.ecoplay.utils.Difficulty;
import com.jadehs.ma.ecoplay.utils.LanguageChanger;
import com.jadehs.ma.ecoplay.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public abstract class EcoPlayActivity extends AppCompatActivity {
    public static final String PREFNAME = "ECOPLAY";

    private final Integer logo;
    private final Integer actionBarTitelResourceID;

    private final boolean addMenu;
    private final boolean useBackButton;
    private final boolean showBar;

    private String currentLanguage;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdit;
    private SharedPreferences defaultPref;

    public EcoPlayActivity() {
        this(true, R.string.app_name, null, false);
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

    public void setLanguageOnStart() {
        String sprache = this.getLanguage();
        String prefSprache = this.defaultPref.getString("sprache", null);
        String handydefault = Locale.getDefault().getLanguage();

        if (prefSprache == null) {
            SharedPreferences.Editor edit = this.defaultPref.edit();
            edit.putString("sprache", handydefault);
            edit.apply();
        }
        new LanguageChanger(this, sprache);
    }

    public String getLanguage() {
        String sprache = this.defaultPref.getString("sprache", null);
        String handydefault = Locale.getDefault().getLanguage();
        if (sprache == null) {
            return handydefault;
        } else {
            return sprache;
        }
    }

    public void resetStickerpinnwand() {
        this.prefEdit.putString("stickerpinnwand", "[]");
        this.prefEdit.apply();
    }

    public StickerPinnwand getStickerpinnwand() {
        try {
            String string = this.pref.getString("stickerpinnwand", "");
            JSONArray ar;
            if (string.equals("")) {
                ar = new JSONArray();
            } else {
                ar = new JSONArray(string);
            }
            return new StickerPinnwand(ar);
        } catch (JSONException ignored) {
        }
        return null;
    }

    public void setStickerpinnwand(StickerPinnwand pw) {
        this.prefEdit.putString("stickerpinnwand", pw.toString());
        this.prefEdit.apply();
    }

    public boolean istAufStickerpinnwand(String tag) {
        StickerPinnwand pinnwand = this.getStickerpinnwand();
        return pinnwand.hatSticker(tag) != null;
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

    public Set<String> getSticker() {
        return this.pref.getStringSet("sticker", new HashSet<>());
    }

    /**
     * Setzt die Sticker die der benutzer aktuell hat
     *
     * @param set Das set an stickern
     */
    public void setSticker(Set<String> set) {
        this.prefEdit.putStringSet("sticker", set);
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

    public int getActionbarTitelRessourceID() {
        return this.actionBarTitelResourceID;
    }

    public int getLogoRessourceID() {
        return this.logo;
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = this.getSupportActionBar();

        if (bar != null) {
            if (this.showBar) {
                bar.setDisplayHomeAsUpEnabled(this.useBackButton);
                bar.setHomeButtonEnabled(true);
                bar.setDisplayShowTitleEnabled(false);
                bar.setDisplayShowCustomEnabled(true);

                int title = (actionBarTitelResourceID != null) ? this.actionBarTitelResourceID : R.string.app_name;
                ActionBarCustomView customView = new ActionBarCustomView(this, title);

                // Logo
                if (logo != null) {
                    bar.setLogo(logo);
                } else {
                    bar.setLogo(R.drawable.logo);
                }
                bar.setCustomView(customView);
            } else {
                bar.hide();
            }
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // setze aktuelle sprache
        this.defaultPref = PreferenceManager.getDefaultSharedPreferences(this);
        this.currentLanguage = defaultPref.getString("sprache", "de");
        this.setLanguageOnStart();

        // setze preferences
        this.pref = this.getSharedPreferences(PREFNAME, MODE_PRIVATE);
        this.prefEdit = pref.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // guck nach ob sich sprache geändert hat
        String sprache = this.defaultPref.getString("sprache", null);
        if (sprache != null && !sprache.equals(this.currentLanguage)) {
            Utils.instantRefreshActivity(this);
            currentLanguage = sprache;
        }
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
            // FAQ
            if (item.getItemId() == R.id.action_faq) {
                intent.setClass(this, FAQActivity.class);
                startActivity(intent);
            }
            // Daten übertragen
            if (item.getItemId() == R.id.action_datenuebertragen) {
                DatenUebertragenDialogFragment daten = new DatenUebertragenDialogFragment();
                daten.show(this.getSupportFragmentManager(), "datenuebertragen");
            }
            // TODO: remove in production
            if (item.getItemId() == R.id.action_debug_onboarding) {
                intent.setClass(this, OnboardingActivity.class);
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
