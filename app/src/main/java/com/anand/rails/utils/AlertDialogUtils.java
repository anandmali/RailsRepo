package com.anand.rails.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.anand.rails.views.screen_contracts.AlertDialogAction;

public class AlertDialogUtils {

    public static void showDialog(final Activity context, String header, String message, String positiveButton,
                                  String negativeButton, final int requestCode) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(header);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButton,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AlertDialogAction alertDialogAction = (AlertDialogAction) context;
                                alertDialogAction.onPositiveClick(requestCode);
                            }
                        })

                .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        AlertDialogAction alertDialogAction = (AlertDialogAction) context;
                        alertDialogAction.onNegativeClick(requestCode);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
