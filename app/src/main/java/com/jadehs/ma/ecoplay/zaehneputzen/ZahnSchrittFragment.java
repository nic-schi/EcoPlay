package com.jadehs.ma.ecoplay.zaehneputzen;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.HeaderFragment;

public abstract class ZahnSchrittFragment extends Fragment {

    private final Handler handler = new Handler();
    private final long secondsToBrush;
    private final int action;
    private final int title;
    private final int schrittLayout;

    private double timer = 0;
    private SchrittTimer runnable = new SchrittTimer();

    public ZahnSchrittFragment(int title, int schrittLayout, int action, long secondsToBrush) {
        this.title = title;
        this.schrittLayout = schrittLayout;
        this.action = action;
        this.secondsToBrush = secondsToBrush;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zahn_schritt, container, false);
        FragmentTransaction trans = requireActivity().getSupportFragmentManager().beginTransaction();

        // füge header hinzu
        FrameLayout layout = view.findViewById(R.id.zahn_schritt_header);
        trans.add(layout.getId(), new HeaderFragment(getString(this.title)));

        // adde content
        trans.add(R.id.schrittContent, new Fragment(this.schrittLayout));
        trans.commit();

        // Füge listener hinzu
        Button btn = view.findViewById(R.id.putzenKnopf);
        btn.setOnClickListener(this::onPutzen);

        Button btn2 = view.findViewById(R.id.zahnweiterKnopf);
        btn2.setOnClickListener(this::onWeiter);

        // Setze Timer
        this.setTimer(this.secondsToBrush, view.findViewById(R.id.timer));

        return view;
    }

    public void onPutzen(View view) {
        Button btn = requireView().findViewById(R.id.putzenKnopf);
        btn.setOnClickListener(this::onTimerStop);
        btn.setText(R.string.zaehne_schritt_stop);

        this.setTimer(this.secondsToBrush);

        this.runnable = new SchrittTimer();
        handler.postDelayed(this.runnable, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this.runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(this.runnable);
    }

    public void onTimerStop(View view) {
        Button btn = requireView().findViewById(R.id.putzenKnopf);
        btn.setOnClickListener(this::onPutzen);
        btn.setText(R.string.zaehne_schritt_putzen);

        handler.removeCallbacks(this.runnable);
    }

    private void onWeiter(View view) {
        handler.removeCallbacks(this.runnable);

        ZahnputzassistentActivity activity = (ZahnputzassistentActivity) this.requireActivity();
        double accuracy = (this.timer / this.secondsToBrush) * 100;
        activity.addAccuracy(Math.min(100.0, accuracy));

        Navigation.findNavController(this.requireActivity(), R.id.container).navigate(this.action);
    }

    private void setTimer(double timer, TextView view) {
        double timerAbs = Math.abs(timer);

        int min = (int) Math.floor((timerAbs % 3600) / 60);
        int sec = (int) Math.floor(timerAbs % 60);

        int minAbs = Math.abs(min);
        int secAbs = Math.abs(sec);

        // baue output
        String minS = (minAbs < 10) ? ("0" + minAbs) : Integer.toString(minAbs);
        String secS = (secAbs < 10) ? ("0" + secAbs) : Integer.toString(secAbs);
        String text = String.format(getString(R.string.zaehne_schritt_time), minS, secS);

        if (timer < 0) {
            text = "-" + text;
        }

        view.setText(text);
    }

    private void setTimer(double timer) {
        TextView timerView = this.requireView().findViewById(R.id.timer);
        this.setTimer(timer, timerView);
    }

    private class SchrittTimer implements Runnable {

        @Override
        public void run() {
            ZahnSchrittFragment.this.timer++;

            ZahnSchrittFragment.this.setTimer(ZahnSchrittFragment.this.secondsToBrush - ZahnSchrittFragment.this.timer);

            ZahnputzassistentActivity activity = (ZahnputzassistentActivity) ZahnSchrittFragment.this.requireActivity();
            activity.countUp();

            handler.postDelayed(this, 1000);
        }
    }

}
