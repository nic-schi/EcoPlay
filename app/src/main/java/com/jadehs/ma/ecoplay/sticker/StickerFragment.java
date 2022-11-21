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

import java.util.Set;


public class StickerFragment extends Fragment {

    private String name;
    private String description;
    private int icon;

    public StickerFragment() {
    }

    public StickerFragment(String name, String description, int icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    static StickerFragment newInstance(Sticker sticker) {
        return StickerFragment.newInstance(sticker.getName(), sticker.getDescription(), sticker.getIcon());
    }

    static StickerFragment newInstance(String name, String description, int icon) {
        StickerFragment fragment = new StickerFragment();

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("description", description);
        args.putInt("icon", icon);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            name = args.getString("name");
            icon = args.getInt("icon");
            description = args.getString("description");
        }
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StickerFragment);

        this.name = a.getString(R.styleable.StickerFragment_stickername);
        this.description = a.getString(R.styleable.StickerFragment_description);
        this.icon = a.getResourceId(R.styleable.StickerFragment_stickericon, R.drawable.question);

        a.recycle();
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sticker, container, false);

        String name = this.name;
        int icon = this.icon;
        String description = this.description;

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

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getIcon() {
        return this.icon;
    }

    // TODO: remove in production
    private void unlock() {
        new StickerManager(this.getContext()).unlockArchievement(this.getTag(), this.name);
    }

    public boolean hatSticker() {
        Activity activity = this.requireActivity();
        if (activity instanceof EcoPlayActivity) {
            Set<String> sticker = ((EcoPlayActivity) activity).getSticker();
            return sticker.contains(this.getTag());
        }
        return true;
    }

}