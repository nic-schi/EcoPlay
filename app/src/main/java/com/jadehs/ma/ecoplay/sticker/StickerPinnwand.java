package com.jadehs.ma.ecoplay.sticker;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class StickerPinnwand extends ArrayList<StickerPinnwandItem> {

    public StickerPinnwand(JSONArray ar) {
        for (int i = 0; i < ar.length(); i++) {
            try {
                this.add(StickerPinnwandItem.from(ar.getJSONObject(i)));
            } catch (JSONException ignored) {
            }
        }
    }

    public StickerPinnwandItem hatSticker(String tag) {
        for (StickerPinnwandItem item : this) {
            if (item.tag.equals(tag)) {
                return item;
            }
        }
        return null;
    }

    public void remove(String tag) {
        StickerPinnwandItem item = this.hatSticker(tag);
        if (item != null) {
            this.remove(item);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return new JSONArray(this).toString();
    }

    public void update(String tag, float x, float y) {
        StickerPinnwandItem item = this.hatSticker(tag);
        if (item != null) {
            item.setTag(tag);
            item.setX(x);
            item.setY(y);
            return;
        }
        this.add(new StickerPinnwandItem(tag, x, y));
    }
}
