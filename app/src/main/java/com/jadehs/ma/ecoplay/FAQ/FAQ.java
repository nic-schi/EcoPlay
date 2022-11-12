package com.jadehs.ma.ecoplay.FAQ;

import org.json.JSONException;
import org.json.JSONObject;

public class FAQ extends JSONObject {
    private final String frage;
    private final String antwort;

    public FAQ(String frage, String antwort) {
        this.antwort = antwort;
        this.frage = frage;

        try {
            this.put("frage", this.frage);
            this.put("antwort", this.antwort);
        } catch (JSONException e) {
            // fehler
        }
    }

    public static FAQ from(JSONObject o) {
        try {
            return new FAQ(
                    o.getString("frage"),
                    o.getString("antwort")
            );
        } catch (JSONException e) {
            // fehler
        }
        return null;
    }

    public String getAntwort() {
        return antwort;
    }

    public String getFrage() {
        return frage;
    }

}
