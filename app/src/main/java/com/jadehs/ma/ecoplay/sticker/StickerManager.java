package com.jadehs.ma.ecoplay.sticker;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.JSONFileManager;
import com.jadehs.ma.ecoplay.utils.Manager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;
import java.util.Set;

public class StickerManager extends Manager<Sticker[]> {
    public static final String FILENAME = "sticker.json";
    private final JSONFileManager jManager;

    public StickerManager(Context ctx) {
        super(ctx);
        this.jManager = new JSONFileManager(ctx);
    }

    private Sticker[] getDefault() {
        return new Sticker[]{
        };
    }

    public void unlockArchievement(String tag, String name) {
        SharedPreferences pref = getContext().getSharedPreferences("ECOPLAY", Context.MODE_PRIVATE);
        Set<String> sticker = new HashSet<>(pref.getStringSet("sticker", new HashSet<>()));

        if (!sticker.contains(tag)) {
            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.levelup);
            mp.setVolume(0.15f, 0.15f);
            mp.start();

            Toast toast = Toast.makeText(getContext(), getContext().getString(R.string.sticker_unlocked, name), Toast.LENGTH_SHORT);
            toast.show();

            sticker.add(tag);
            pref.edit().putStringSet("sticker", sticker).apply();
        }
    }

    @Override
    public Sticker[] read(String filename) {
        try {
            JSONArray array = this.jManager.read(filename);
            Sticker[] sticker = new Sticker[array.length()];

            for (int i = 0; i < array.length(); i++) {
                sticker[i] = Sticker.from(array.getJSONObject(i));
            }
            return sticker;
        } catch (JSONException e) {
            // Fehler
        }
        return null;
    }

    @Override
    public void write(String filename, Sticker[] data) {
        try {
            this.jManager.write(filename, new JSONArray(data));
        } catch (JSONException e) {
            // Fehler
        }
    }

    public void writeDefault() {
        this.write(FILENAME, this.getDefault());
    }
}
