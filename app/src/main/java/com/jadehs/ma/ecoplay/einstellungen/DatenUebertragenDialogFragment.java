package com.jadehs.ma.ecoplay.einstellungen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jadehs.ma.ecoplay.R;

public class DatenUebertragenDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(R.string.datenübertragen_übertragen, (dialog, which) -> {
        });
        builder.setNegativeButton(R.string.datenübertragen_abbrechen, (dialog, which) -> {
        });
        builder.setIcon(R.drawable.database);
        builder.setTitle(R.string.datenübertragen_title);
        builder.setView(R.layout.dialog_daten_uebertragen);

        return builder.create();
    }
}
