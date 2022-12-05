package com.jadehs.ma.ecoplay.inhalte;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;

public class InhalteFragment extends Fragment {
    private int icon;
    private int image;
    private String redirect;
    private String thema;

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InhalteFragment);

        this.redirect = a.getString(R.styleable.InhalteFragment_inhaltRedirect);
        this.thema = a.getString(R.styleable.InhalteFragment_thema);
        this.icon = a.getResourceId(R.styleable.InhalteFragment_inhaltIcon, -1);
        this.image = a.getResourceId(R.styleable.InhalteFragment_inhaltImage, R.drawable.placeholder);

        a.recycle();
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inhalte, container, false);

        // Setze Thema
        TextView themaView = view.findViewById(R.id.inhaltName);
        themaView.setText(thema);

        // Setze Image
        ImageView imageView = view.findViewById(R.id.inhalteBild);
        imageView.setImageResource(this.image);

        // Setze Icon
        ImageView iconView = view.findViewById(R.id.inhalteIcon);
        if (this.icon == -1) {
            iconView.setVisibility(View.GONE);
        } else {
            iconView.setImageResource(this.icon);
        }

        // Setze Redirect
        view.findViewById(R.id.inhalteBild).setOnClickListener(this::onRedirect);
        view.findViewById(R.id.inhalteIcon).setOnClickListener(this::onRedirect);
        view.findViewById(R.id.inhaltName).setOnClickListener(this::onRedirect);

        return view;
    }

    private void onRedirect(View view) {
        try {
            Class<?> c = Class.forName("com.jadehs.ma.ecoplay." + this.redirect);
            startActivity(new Intent(this.getContext(), c));
        } catch (ClassNotFoundException e) {
            // fehler
        }
    }
}