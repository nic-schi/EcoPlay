package com.jadehs.ma.ecoplay.quiz.fragen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.R;

import java.util.HashMap;
import java.util.Map;


public class FrageFragment extends Fragment {
    private final String frageRessourceID;
    protected Map<Frage, String> answers = new HashMap<>();
    private Frage solution;

    public FrageFragment(String frageRessourceID) {
        this.frageRessourceID = frageRessourceID;
    }

    public FrageFragment(String frageRessourceID, Map<Frage, String> answers) {
        this.frageRessourceID = frageRessourceID;
        this.answers = answers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frage, container, false);

        // füge frage hinzu
        ((TextView) view.findViewById(R.id.quizfrageTitel)).setText(this.frageRessourceID);

        // füge antworten hinzu
        this.answers.forEach((f, r) -> {
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

        return view;
    }

    public boolean isAnswered() {
        RadioGroup group = this.requireView().findViewById(R.id.alleAntworten);
        return group.getCheckedRadioButtonId() != -1;
    }

    public Frage getAnswer() {
        RadioGroup group = this.requireView().findViewById(R.id.alleAntworten);
        RadioButton btn = this.requireView().findViewById(group.getCheckedRadioButtonId());
        int index = group.indexOfChild(btn);
        return Frage.values()[index];
    }

    public void addAntworten(String[] antworten) {
        Frage[] fragen = Frage.values();
        if (antworten.length == 4 && fragen.length == 4) {
            for (int i = 0; i < 4; i++) {
                this.answers.put(fragen[i], antworten[i]);
            }
        }
    }

    public void addAntwort(Frage frage, String antwortRessourceID) {
        this.answers.put(frage, antwortRessourceID);
    }

    public boolean isSolution(Frage frage) {
        return solution.equals(frage);
    }

    public void setSolution(Frage id) {
        solution = id;
    }

}