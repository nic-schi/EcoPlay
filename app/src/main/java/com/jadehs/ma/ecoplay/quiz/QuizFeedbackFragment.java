package com.jadehs.ma.ecoplay.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Utils;

public class QuizFeedbackFragment extends Fragment {
    private final int correct;
    private final int length;

    public QuizFeedbackFragment(int correct, int length) {
        this.correct = correct;
        this.length = length;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_feedback, container, false);

        view.findViewById(R.id.repeat).setOnClickListener(this::repeatQuiz);
        view.findViewById(R.id.other).setOnClickListener(this::otherQuiz);

        // Setze Text
        TextView textView = view.findViewById(R.id.feedbackText);
        textView.setText(String.format(getString(R.string.quiz_feedback), this.correct, this.length));

        // Setze Bild
        ImageView feedbackIcon = view.findViewById(R.id.feedbackIcon);

        double dLength = length;
        double v = dLength - correct;
        double m = dLength / 5;
        int icon;

        if (v >= (m * 5)) {
            icon = R.drawable.stunned;
        } else if (v >= (m * 4)) {
            icon = R.drawable.tired;
        } else if (v >= (m * 3)) {
            icon = R.drawable.cheeky;
        } else if (v >= (m * 2)) {
            icon = R.drawable.happy;
        } else if (v >= m) {
            icon = R.drawable.cool;
        } else if (v >= 0) {
            icon = R.drawable.nerd;
        } else {
            icon = R.drawable.stunned;
        }
        feedbackIcon.setImageResource(icon);

        return view;
    }

    public void otherQuiz(View btn) {
        this.requireActivity().finish();
    }

    public void repeatQuiz(View btn) {
        Utils.refreshActivity(this.requireActivity());
    }

}
