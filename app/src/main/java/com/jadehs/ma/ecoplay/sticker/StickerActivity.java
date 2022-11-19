package com.jadehs.ma.ecoplay.sticker;

import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Utils;
import com.jadehs.ma.ecoplay.utils.confirmalert.ConfirmAlert;

public class StickerActivity extends EcoPlayActivity {

    public StickerActivity() {
        super(true, R.string.startseite_sticker, null, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker);

//        FragmentManager fragM = this.getSupportFragmentManager();
//        FragmentTransaction trans = fragM.beginTransaction();
//
//        Sticker[] allesticker = new StickerManager(this).read(StickerManager.FILENAME);
//        for (Sticker sticker : allesticker) {
//            StickerFragment frag = StickerFragment.newInstance(sticker);
//            trans.add(R.id.allesticker, frag, sticker.getTag());
//        }
//        trans.commit();

        StickerPinnwand pinnwand = this.getStickerpinnwand();

        for (StickerPinnwandItem item : pinnwand) {
            LinearLayout start = this.findViewById(R.id.layout);

            View found = null;
            for (int i = 0; i < start.getChildCount(); i++) {
                View view = start.getChildAt(i);
                if (view.getTag().equals("pw-" + item.tag)) {
                    found = view;
                    break;
                }
            }

            assert found != null;
            start.removeView(found);

            found.setX(item.x);
            found.setY(item.y);

            ConstraintLayout ziel = this.findViewById(R.id.drop_ziel);
            ziel.addView(found);
        }

        Button resetKnopf = this.findViewById(R.id.resetKnopf);
        resetKnopf.setOnClickListener(v -> {
            ConfirmAlert alert = new ConfirmAlert(
                    this,
                    R.string.sticker_confirm_dialog_msg,
                    () -> {
                        this.resetStickerpinnwand();
                        Utils.refreshActivity(this);
                    }
            );
            alert.show();
        });

        // Drag listener
        View.OnDragListener draglistener = (view, e) -> {
            switch (e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                case DragEvent.ACTION_DRAG_ENTERED:
                case DragEvent.ACTION_DRAG_EXITED:
                    view.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    View v = (View) e.getLocalState();
                    v.setVisibility(View.VISIBLE);
                    view.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DROP:
                    view.invalidate();

                    v = (View) e.getLocalState();

                    float x = e.getX() - ((float) v.getWidth() / 2);
                    float y = e.getY() - ((float) v.getHeight() / 2);
                    v.setX(x);
                    v.setY(y);

                    ViewGroup vg = (ViewGroup) v.getParent();
                    vg.removeView(v);

                    ViewGroup destination = (ViewGroup) view;
                    if (destination.getTag() != null ) {
                        if (destination.getTag().equals("drop_start")) {
                            v.setX(0.0f);
                            v.setY(0.0f);
                            ((ViewGroup) this.findViewById(R.id.layout)).addView(v);

                            pinnwand.remove(e.getClipData().getItemAt(0).getText().toString());
                        } else if (destination.getTag().equals("drop_ziel")) {
                            destination.addView(v);

                            pinnwand.update(e.getClipData().getItemAt(0).getText().toString(), x, y);
                        } else {
                            return false;
                        }
                        this.setStickerpinnwand(pinnwand);
                    } else {
                        return false;
                    }

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(v.getLayoutParams());
                    params.setMargins(4,4,4,4);
                    v.setLayoutParams(params);
                    v.setVisibility(View.VISIBLE);

                    return true;
                default:
                    return false;
            }
        };

        // add drag listener
        this.findViewById(R.id.drop_start).setOnDragListener(draglistener);
        this.findViewById(R.id.drop_ziel).setOnDragListener(draglistener);
    }

}