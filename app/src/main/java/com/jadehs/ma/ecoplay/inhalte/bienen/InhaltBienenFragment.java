package com.jadehs.ma.ecoplay.inhalte.bienen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;

public class InhaltBienenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inhalt_bienen, container, false);
    }

}
