package com.jadehs.ma.ecoplay.sticker;

import android.app.Activity;
import android.content.Context;
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

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

import java.util.HashSet;
import java.util.Set;


public class StickerFragment extends Fragment {

    private String name;
    private String description;
    private int icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sticker, container, false);

        String name = this.name;
        String description = this.description;
        int icon = this.icon;

        if (savedInstanceState != null) {
            name = savedInstanceState.getString("stickername");
            description = savedInstanceState.getString("description");
            icon = savedInstanceState.getInt("stickericon");
        }

        // prüfe ob sticker vorhanden
        if (!this.hatSticker()) {
            icon = R.drawable.question;
            name = getString(R.string.sticker_placeholder_name);
        }

        // Setze name
        TextView nameView = view.findViewById(R.id.name);
        nameView.setText(name);

        // Setze description
        TextView descriptionView = view.findViewById(R.id.description);
        descriptionView.setText(description);

        // Setze icon
        ImageView iconView = view.findViewById(R.id.stickerIcon);
        iconView.setImageResource(icon);

//        ColorMatrix matrix = new ColorMatrix();
//        matrix.setSaturation(0.1f);
//        iconView.setColorFilter(new ColorMatrixColorFilter(matrix));

        // TODO: remove in production
        iconView.setOnClickListener(v -> this.unlock());

        return view;
    }

    // TODO: remove in production
    private void unlock() {
        String id = this.getTag();
        Activity activity = this.requireActivity();

        if (activity instanceof EcoPlayActivity) {
            Set<String> sticker = new HashSet<>(((EcoPlayActivity) activity).getSticker());
            sticker.add(id);
            ((EcoPlayActivity) activity).setSticker(sticker);
        }
    }

    private boolean hatSticker() {
        Activity activity = this.requireActivity();
        if (activity instanceof EcoPlayActivity) {
            Set<String> sticker = ((EcoPlayActivity) activity).getSticker();
            return sticker.contains(this.getTag());
        }
        return true;
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StickerFragment);

        this.name = a.getString(R.styleable.StickerFragment_stickername);
        this.description = a.getString(R.styleable.StickerFragment_description);
        this.icon = a.getResourceId(R.styleable.StickerFragment_stickericon, R.drawable.logo_small);

        a.recycle();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.name = savedInstanceState.getString("stickername");
            this.description = savedInstanceState.getString("description");
            this.icon = savedInstanceState.getInt("stickericon");
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("stickername", this.name);
        outState.putString("description", this.description);
        outState.putInt("stickericon", this.icon);
        super.onSaveInstanceState(outState);
    }
}