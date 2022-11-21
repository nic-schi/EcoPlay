package com.jadehs.ma.ecoplay.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.jadehs.ma.ecoplay.R;


@SuppressLint("ViewConstructor")
public class ActionBarCustomView extends androidx.appcompat.widget.AppCompatTextView {

    public ActionBarCustomView(Context context, int titleResourceID) {
        super(context);
        this.setText(titleResourceID);
        this.setTextAppearance(R.style.TextAppearance_Apptitel);
        this.setPadding(40, 0, 40, 0);
    }

}