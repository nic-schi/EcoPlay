package com.jadehs.ma.ecoplay.sticker;

import org.json.JSONException;
import org.json.JSONObject;

public class StickerPinnwandItem extends JSONObject {
    public String tag;
    public float x;
    public float y;

    public StickerPinnwandItem(String tag, float x, float y) {
        this.tag = tag;
        this.x = x;
        this.y = y;

        try {
            this.put("tag", tag);
            this.put("x", x);
            this.put("y", y);
        } catch (JSONException ignored) {}
    }

    public void setTag(String tag) {
        this.tag = tag;
        try {
            this.put("tag", tag);
        } catch (JSONException ignored) {}
    }

    public void setX(float x) {
        this.x = x;
        try {
            this.put("x", x);
        } catch (JSONException ignored) {}
    }

    public void setY(float y) {
        this.y = y;
        try {
            this.put("y", y);
        } catch (JSONException ignored) {}
    }

    public static StickerPinnwandItem from(JSONObject o) {
        try {
            return new StickerPinnwandItem(
                o.getString("tag"),
                (float) o.getDouble("x"),
                (float) o.getDouble("y")
            );
        } catch (JSONException ignored) {}
        return null;
    }
}
