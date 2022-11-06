package com.jadehs.ma.ecoplay;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

public class StartseiteFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_startseite, container, false);

        Button btn = view.findViewById(R.id.button2);
        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(R.id.action_startseiteFragment_to_quizFragment);
    }
}