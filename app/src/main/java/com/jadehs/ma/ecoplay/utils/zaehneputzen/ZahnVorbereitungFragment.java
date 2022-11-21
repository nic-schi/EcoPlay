package com.jadehs.ma.ecoplay.utils.zaehneputzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.jadehs.ma.ecoplay.R;

public class ZahnVorbereitungFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zahn_vorbereitung, container, false);

        view.findViewById(R.id.letsgo).setOnClickListener(this::onLetsGo);

        return view;
    }

    public void onLetsGo(View view) {
        Navigation.findNavController(this.requireActivity(), R.id.container).navigate(R.id.action_zahnVorbereitungFragment_to_zahnSchritt1);
    }

}
