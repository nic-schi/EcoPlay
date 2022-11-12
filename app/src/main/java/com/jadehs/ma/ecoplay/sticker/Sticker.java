package com.jadehs.ma.ecoplay.sticker;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Sticker extends JSONObject {
    private final String name;
    private final String description;
    private final int icon;
    private final String tag;

    public Sticker(String name, String description, int icon, String tag) {
        this.description = description;
        this.icon = icon;
        this.name = name;
        this.tag = tag;

        try {
            this.put("icon", this.icon);
            this.put("tag", this.tag);
            this.put("description", this.description);
            this.put("name", this.name);
        } catch (JSONException e) {
            // fehler
        }
    }

    public static Sticker from(JSONObject o) {
        try {
            return new Sticker(
                    o.getString("name"),
                    o.getString("description"),
                    o.getInt("icon"),
                    o.getString("tag")
            );
        } catch (JSONException e) {
            // fehler
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getTag() {
        return this.tag;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tag;
    }
}
