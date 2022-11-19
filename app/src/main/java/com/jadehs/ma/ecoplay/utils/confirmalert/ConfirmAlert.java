package com.jadehs.ma.ecoplay.utils.confirmalert;

import android.app.AlertDialog;
import android.content.Context;

import com.jadehs.ma.ecoplay.R;

public class ConfirmAlert {
    private final Context context;
    private final int message;
    private final OnConfirmListener listener;

    public ConfirmAlert(Context context, int message, OnConfirmListener listener) {
        this.context = context;
        this.listener = listener;
        this.message = message;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setCancelable(true);
        builder.setTitle(R.string.confirm_dialog_title);
        builder.setMessage(this.message);
        builder.setPositiveButton(R.string.confirm_dialog_confirm, (dialog, which) -> this.listener.onConfirm());
        builder.setNegativeButton(R.string.confirm_dialog_cancel, ((dialog, which) -> {
        }));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
