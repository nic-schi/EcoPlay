package com.jadehs.ma.ecoplay.zaehneputzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;

public class ZahnSuccessFragment extends Fragment {
    public static final double bestMin = 120;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zahn_success, container, false);

        ZahnputzassistentActivity activity = (ZahnputzassistentActivity) requireActivity();

        // setze zeit output
        TextView output = view.findViewById(R.id.timeOutput);
        double totalTime = activity.getCount();
        int min = (int) Math.floor((totalTime % 3600) / 60);
        int sec = (int) Math.floor(totalTime % 60);

        String minS = (min < 10) ? ("0" + min) : Integer.toString(min);
        String secS = (sec < 10) ? ("0" + sec) : Integer.toString(sec);

        output.setText(String.format(getString(R.string.zaehne_schritt_success_time), minS, secS));

        // Setze rating
        double rating = (5.0 * ((totalTime / ZahnSuccessFragment.bestMin) * 100)) / 100;
        RatingBar ratingBar = view.findViewById(R.id.rating);
        ratingBar.setRating((float) rating);

        // Setze listener
        Button btn = view.findViewById(R.id.backButton);
        btn.setOnClickListener(this::onBack);

        return view;
    }

    public void onBack(View view) {
        this.requireActivity().finish();
    }

}
