package com.jadehs.ma.ecoplay.quiz.frage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public class FrageFragment extends Fragment {
    private final String frageRessourceID;
    private final boolean randomize;

    protected Map<Frage, String> answers;
    protected Map<Frage, String> randomizedAnswers;
    private Frage solution;

    public FrageFragment(String frageRessourceID) {
        this(frageRessourceID, new LinkedHashMap<>(), true);
    }

    public FrageFragment(String frageRessourceID, boolean randomize) {
        this(frageRessourceID, new LinkedHashMap<>(), randomize);
    }

    public FrageFragment(String frageRessourceID, Map<Frage, String> answers) {
        this(frageRessourceID, answers, true);
    }

    public FrageFragment(String frageRessourceID, Map<Frage, String> answers, boolean randomize) {
        this.frageRessourceID = frageRessourceID;
        this.answers = answers;
        this.randomize = randomize;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frage, container, false);

        // füge frage hinzu
        ((TextView) view.findViewById(R.id.quizfrageTitel)).setText(this.frageRessourceID);

        // randomize antworten
        if (randomize) this.randomizeAntworten();

        // füge antworten hinzu
        this.randomizedAnswers.forEach((f, r) -> {
            RadioButton btn = null;
            switch (f.name()) {
                case "A":
                    btn = view.findViewById(R.id.A);
                    break;
                case "B":
                    btn = view.findViewById(R.id.B);
                    break;
                case "C":
                    btn = view.findViewById(R.id.C);
                    break;
                case "D":
                    btn = view.findViewById(R.id.D);
                    break;
            }
            if (btn != null) {
                btn.setText(r);
            }
        });
        Log.v("eco", randomizedAnswers.toString());

        return view;
    }

    private void randomizeAntworten() {
        ArrayList<Frage> preset = new ArrayList<>(Arrays.asList(Frage.values()));
        Collections.shuffle(preset);
        Map<Frage, String> newAnswers = new LinkedHashMap<>();

        int i = 0;
        for (Frage frage : preset) {
            newAnswers.put(frage, new ArrayList<>(answers.values()).get(i));
            i++;
        }
        this.randomizedAnswers = newAnswers;
    }

    public boolean isAnswered() {
        RadioGroup group = this.requireView().findViewById(R.id.alleAntworten);
        return group.getCheckedRadioButtonId() != -1;
    }

    public Frage getChoosenAnswer() {
        RadioGroup group = this.requireView().findViewById(R.id.alleAntworten);
        RadioButton btn = this.requireView().findViewById(group.getCheckedRadioButtonId());
        Frage frage = Frage.values()[group.indexOfChild(btn)];
        frage = Frage.values()[new ArrayList<>(randomizedAnswers.keySet()).indexOf(frage)];

        return frage;
    }

    public boolean isCorrect() {
        return this.isSolution(this.getChoosenAnswer());
    }

    public void setAntworten(String[] antworten) {
        Frage[] fragen = Frage.values();
        if (antworten.length == 4 && fragen.length == 4) {
            for (int i = 0; i < 4; i++) {
                this.answers.put(fragen[i], antworten[i]);
            }
        }
        this.randomizedAnswers = answers;
    }

    public boolean isSolution(Frage frage) {
        return solution.equals(frage);
    }

    public void setSolution(Frage id) {
        solution = id;
    }

}