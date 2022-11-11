package com.jadehs.ma.ecoplay.sticker;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.FileManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashSet;
import java.util.Set;

public class StickerManager {
    private final Context ctx;
    private final FileManager manager;

    public StickerManager(Context ctx) {
        this.ctx = ctx;
        this.manager = new FileManager(ctx);
    }

    private Sticker[] getDefault() {
        return new Sticker[]{
                new Sticker(
                        ctx.getString(R.string.sticker_4_stickername),
                        ctx.getString(R.string.sticker_4_description),
                        R.drawable.logo_small,
                        "onboarding"
                ),
                new Sticker(
                        ctx.getString(R.string.sticker_1_stickername),
                        ctx.getString(R.string.sticker_1_description),
                        R.drawable.bees01,
                        "bee01"
                ),
                new Sticker(
                        ctx.getString(R.string.sticker_2_stickername),
                        ctx.getString(R.string.sticker_2_description),
                        R.drawable.bees02,
                        "bee02"
                ),
                new Sticker(
                        ctx.getString(R.string.sticker_3_stickername),
                        ctx.getString(R.string.sticker_3_description),
                        R.drawable.fireplace,
                        "fireplace"
                )
        };
    }

    public void unlockedArchievement(String tag, String name) {
        MediaPlayer mp = MediaPlayer.create(this.ctx, R.raw.levelup);
        mp.setVolume(0.15f, 0.15f);
        mp.start();

        Toast toast = Toast.makeText(this.ctx, ctx.getString(R.string.sticker_unlocked, name), Toast.LENGTH_SHORT);
        toast.show();

        SharedPreferences pref = ctx.getSharedPreferences("ECOPLAY", Context.MODE_PRIVATE);
        Set<String> sticker = new HashSet<>(pref.getStringSet("sticker", new HashSet<>()));
        sticker.add(tag);
        pref.edit().putStringSet("sticker", sticker).apply();
    }

    public Sticker[] lese() {
        try {
            String content = manager.readFile("sticker.json");
            JSONArray array = new JSONArray(content);
            Sticker[] sticker = new Sticker[array.length()];

            for (int i = 0; i < array.length(); i++) {
                sticker[i] = Sticker.from(array.getJSONObject(i));
            }
            return sticker;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void schreibe() {
        try {
            manager.writeToFile("sticker.json", new JSONArray(this.getDefault()).toString());
        } catch (JSONException e) {
            // fehler
        }
    }
}
