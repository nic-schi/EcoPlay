package com.jadehs.ma.ecoplay.startseite;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.jadehs.ma.ecoplay.R;

public class StartseiteItemFragment extends Fragment implements View.OnClickListener {

    private String text;
    private int ressourceID;
    private int redirect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_startseite_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text = this.text;
        if (savedInstanceState != null) {
            text = savedInstanceState.getString("text");
        }

        // setze text
        TextView textView = view.findViewById(R.id.item_text);
        textView.setText(text);

        int ressourceID = this.ressourceID;
        if (savedInstanceState != null) {
            ressourceID = savedInstanceState.getInt("ressourceID");
        }

        // setze bild
        ImageButton imageButton = view.findViewById(R.id.item_image);
        imageButton.setImageResource(ressourceID);
        imageButton.setOnClickListener(this);
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StartseiteItemFragment);

        this.ressourceID = a.getResourceId(R.styleable.StartseiteItemFragment_image, -1);
        this.text = a.getString(R.styleable.StartseiteItemFragment_text);
        this.redirect = a.getResourceId(R.styleable.StartseiteItemFragment_redirect, -1);

        a.recycle();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("ressourceID", this.ressourceID);
        outState.putString("text", this.text);
        outState.putInt("redirect", this.redirect);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.text = savedInstanceState.getString("text");
            this.ressourceID = savedInstanceState.getInt("ressourceID");
            this.redirect = savedInstanceState.getInt("redirect");
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(this.redirect);
    }

}
