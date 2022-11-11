package com.jadehs.ma.ecoplay.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileManager extends Manager<String> {

    public FileManager(Context ctx) {
        super(ctx);
    }

    @Override
    public String read(String filename) {
        try {
            FileInputStream fis = this.getContext().openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                // fehler
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(String filename, String data) {
        try (FileOutputStream fos = this.getContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
