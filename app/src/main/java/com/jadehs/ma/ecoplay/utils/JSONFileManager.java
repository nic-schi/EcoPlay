package com.jadehs.ma.ecoplay.utils;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONFileManager extends Manager<JSONArray> {
    private final FileManager fManager;

    public JSONFileManager(Context ctx) {
        super(ctx);
        this.fManager = new FileManager(ctx);
    }

    @Override
    public JSONArray read(String filename) {
        try {
            String content = this.fManager.read(filename);
            return new JSONArray(content);
        } catch (JSONException e) {
            // fehler
        }
        return null;
    }

    @Override
    public void write(String filename, JSONArray data) {
        this.fManager.write(filename, data.toString());
    }

}


