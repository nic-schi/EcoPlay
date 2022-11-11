package com.jadehs.ma.ecoplay.utils;

import android.content.Context;

public abstract class Manager<T> {
    private Context ctx;

    public Manager(Context ctx) {
        this.ctx = ctx;
    }

    public abstract T read(String filename);

    public abstract void write(String filename, T data);

    public Context getContext() {
        return ctx;
    }

    public void setContext(Context ctx) {
        this.ctx = ctx;
    }

}
