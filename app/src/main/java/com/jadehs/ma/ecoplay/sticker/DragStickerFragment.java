package com.jadehs.ma.ecoplay.sticker;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;

public class DragStickerFragment extends Fragment {

    private String stickertag;

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DragStickerFragment);

        this.stickertag = a.getString(R.styleable.DragStickerFragment_stickertag);

        a.recycle();
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_sticker, container, false);

        StickerActivity activity = (StickerActivity) requireActivity();
        StickerFragment frag = (StickerFragment) activity.getSupportFragmentManager().findFragmentByTag(this.stickertag);
        if (frag != null) {
            if (frag.hatSticker()) {
                ImageView iconView = view.findViewById(R.id.stickericon);
                iconView.setImageResource(frag.getIcon());
            } else {
                view.setVisibility(View.GONE);
            }
        }

        return view;
    }
}
