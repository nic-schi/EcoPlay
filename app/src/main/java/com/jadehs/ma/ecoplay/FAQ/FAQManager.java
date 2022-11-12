package com.jadehs.ma.ecoplay.FAQ;

import android.content.Context;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.JSONFileManager;
import com.jadehs.ma.ecoplay.utils.Manager;

import org.json.JSONArray;
import org.json.JSONException;

public class FAQManager extends Manager<FAQ[]> {
    public static final String FILENAME = "faq.json";
    private final JSONFileManager jManager;

    public FAQManager(Context ctx) {
        super(ctx);
        this.jManager = new JSONFileManager(ctx);
    }

    private FAQ[] getDefault() {
        return new FAQ[]{
                new FAQ(
                        getContext().getString(R.string.faq_3_frage),
                        getContext().getString(R.string.faq_3_antwort)
                ),
                new FAQ(
                        getContext().getString(R.string.faq_1_frage),
                        getContext().getString(R.string.faq_1_antwort)
                ),
                new FAQ(
                        getContext().getString(R.string.faq_2_frage),
                        getContext().getString(R.string.faq_2_antwort)
                )
        };
    }

    @Override
    public FAQ[] read(String filename) {
        try {
            JSONArray array = this.jManager.read(filename);
            FAQ[] faqs = new FAQ[array.length()];

            for (int i = 0; i < array.length(); i++) {
                faqs[i] = FAQ.from(array.getJSONObject(i));
            }

            return faqs;
        } catch (JSONException e) {
            // Fehler
        }
        return null;
    }

    @Override
    public void write(String filename, FAQ[] data) {
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
