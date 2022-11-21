package com.jadehs.ma.ecoplay.utils.zaehneputzen;

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

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.HeaderFragment;

public abstract class ZahnSchrittFragment extends Fragment {

    private SchrittTimer runnable = new SchrittTimer();
    private final Handler handler = new Handler();

    private final int title;
    private final int schrittLayout;

    public ZahnSchrittFragment(int title, int schrittLayout) {
        this.title = title;
        this.schrittLayout = schrittLayout;
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
        btn2.setOnClickListener(this::onWeiterTemp);

        return view;
    }

    public void onPutzen(View view) {
        Button btn = requireView().findViewById(R.id.putzenKnopf);
        btn.setOnClickListener(this::onTimerStop);
        btn.setText(R.string.zaehne_schritt_stop);

        TextView timerView = requireView().findViewById(R.id.timer);
        timerView.setText(R.string.zaehne_schritt_time_default);

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

    private void onWeiterTemp(View view) {
        handler.removeCallbacks(this.runnable);
        this.onWeiter();
    }

    public abstract void onWeiter();

    private class SchrittTimer implements Runnable {
        private double timer = 0;

        @Override
        public void run() {
            this.timer++;
            TextView timerView = ZahnSchrittFragment.this.requireView().findViewById(R.id.timer);

            int min = (int) Math.floor((timer % 3600) / 60);
            int sec = (int) Math.floor(timer % 60);

            String minS = (min < 10) ? ("0" + min) : Integer.toString(min);
            String secS = (sec < 10) ? ("0" + sec) : Integer.toString(sec);

            timerView.setText(String.format(getString(R.string.zaehne_schritt_time), minS, secS));
            ZahnputzassistentActivity activity = (ZahnputzassistentActivity) ZahnSchrittFragment.this.requireActivity();
            activity.countUp();

            handler.postDelayed(this, 1000);
        }
    }

}
