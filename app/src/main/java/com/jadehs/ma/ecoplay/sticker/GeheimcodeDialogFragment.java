package com.jadehs.ma.ecoplay.sticker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Utils;

public class GeheimcodeDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.dialog_geheimcode_eingeben, null);
        EditText input = view.findViewById(R.id.editNumberSigned);

        builder.setPositiveButton(R.string.geheimcode_try, (dialog, which) -> {
            ;
            String code = input.getText().toString();
            if (code.equals("877")) {
                new StickerManager(this.getContext()).unlockArchievement("ultimatesticker", R.string.sticker_18_stickername);

                FragmentActivity activity = getActivity();
                if (activity instanceof StickerActivity) {
                    Utils.instantRefreshActivity(activity);
                }
            } else {
                Toast toast = Toast.makeText(getContext(), R.string.geheimcode_feedback, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        builder.setNegativeButton(R.string.datenübertragen_abbrechen, (dialog, which) -> {
        });
        builder.setIcon(R.drawable.sticker);
        builder.setTitle(R.string.sticker_geheimcode);
        builder.setView(view);

        return builder.create();
    }

}
