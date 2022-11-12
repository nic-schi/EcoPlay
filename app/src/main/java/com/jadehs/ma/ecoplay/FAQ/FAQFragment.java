package com.jadehs.ma.ecoplay.FAQ;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Animator;

public class FAQFragment extends Fragment {
    private boolean markedAsCollapsed;
    private String frage;
    private String antwort;

    public FAQFragment() {
    }

    public FAQFragment(String frage, String antwort) {
        this.frage = frage;
        this.antwort = antwort;
    }

    public static FAQFragment newInstance(FAQ faq) {
        return FAQFragment.newInstance(faq.getFrage(), faq.getAntwort());
    }

    public static FAQFragment newInstance(String frage, String antwort) {
        FAQFragment fragment = new FAQFragment();

        Bundle args = new Bundle();
        args.putString("frage", frage);
        args.putString("antwort", antwort);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            frage = getArguments().getString("frage");
            antwort = getArguments().getString("antwort");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_a_q, container, false);

        // Setze frage
        TextView frageView = view.findViewById(R.id.faqFrage);
        frageView.setText(this.frage);

        // Setze antwort
        TextView antwortView = view.findViewById(R.id.faqAntwort);
        antwortView.setText(this.antwort);

        ConstraintLayout layout = view.findViewById(R.id.faqContainer);
        layout.setOnClickListener(this::doCollapse);

        if (!this.markedAsCollapsed) {
            antwortView.setVisibility(View.GONE);
        }

        return view;
    }

    public void doCollapse(View view) {
        ImageView icon = view.findViewById(R.id.faqIcon);

        // schließe andere fragen
        FAQActivity activity = (FAQActivity) this.requireActivity();
        activity.closeOtherFAQs(this);

        // Animate text
        TextView antwort = this.requireView().findViewById(R.id.faqAntwort);
        Animator.animateCollapse(view, antwort, icon);
    }

    public void markAsCollapsed(boolean collapsed) {
        this.markedAsCollapsed = collapsed;
    }

}