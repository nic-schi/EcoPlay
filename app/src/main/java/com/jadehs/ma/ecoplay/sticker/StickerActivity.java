package com.jadehs.ma.ecoplay.sticker;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;

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
    }
}