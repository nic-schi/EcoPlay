package com.jadehs.ma.ecoplay.utils.zaehneputzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.jadehs.ma.ecoplay.R;

public class ZahnStartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zahn_start, container, false);

        Button btn = view.findViewById(R.id.starten);
        btn.setOnClickListener(this::onZahnStart);

        return view;
    }

    public void onZahnStart(View view) {
        Navigation.findNavController(this.requireActivity(), R.id.container).navigate(R.id.action_zahnStartFragment_to_zahnVorbereitungFragment);
    }

}
