package com.jadehs.ma.ecoplay.sticker;

import android.content.ClipData;
import android.content.ClipDescription;
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

    public String stickertag;

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DragStickerFragment);

        this.stickertag = a.getString(R.styleable.DragStickerFragment_stickertag);

        a.recycle();
        super.onInflate(context, attrs, savedInstanceState);
    }

    public StickerFragment getAssociatedSticker() {
        return (StickerFragment) this.requireActivity().getSupportFragmentManager().findFragmentByTag(stickertag);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_sticker, container, false);

        ImageView iconView = view.findViewById(R.id.stickericon);
        StickerActivity activity = (StickerActivity) requireActivity();
        StickerFragment frag = (StickerFragment) activity.getSupportFragmentManager().findFragmentByTag(this.stickertag);
        assert frag != null;

        if (frag.hatSticker()) {
            iconView.setImageResource(frag.getIcon());
        } else {
            view.setVisibility(View.GONE);
        }

        // add drag and drop
        iconView.setOnLongClickListener(v -> {
            String clipText = frag.getTag();
            ClipData.Item item = new ClipData.Item(clipText);
            ClipData data = new ClipData(clipText, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

            View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, builder, v, 0);

            v.setVisibility(View.INVISIBLE);
            return true;
        });

        return view;
    }

}
