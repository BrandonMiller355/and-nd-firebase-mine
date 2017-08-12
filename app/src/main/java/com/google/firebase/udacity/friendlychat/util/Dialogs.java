package com.google.firebase.udacity.friendlychat.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.udacity.friendlychat.WhatsForLunch;

public class Dialogs extends DialogFragment {

    //TODO: Brandon - figure this out at some point

//    public void showAlertDialog(int title, int message, int positiveText, int negativeText) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(WhatsForLunch.ApplicationContext);
//        builder.setTitle(WhatsForLunch.ApplicationContext.getString(title));
//        builder.setMessage(WhatsForLunch.ApplicationContext.getString(message));
//
//        String positiveTextOption = WhatsForLunch.ApplicationContext.getString(positiveText);
//        builder.setPositiveButton(positiveTextOption,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // positive button logic
//                    }
//                });
//
//        String negativeText = getString(android.R.string.cancel);
//        builder.setNegativeButton(negativeText,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // negative button logic
//                    }
//                });
//
//        AlertDialog dialog = builder.create();
//        // display dialog
//        dialog.show();
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(WhatsForLunch.ApplicationContext);
//        builder.setTitle("Example");
//        builder.setMessage("Sample Message");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                //Toast.makeText(getActivity(), "Pressed OK", Toast.LENGTH_SHORT).show();)
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                //Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return builder.create();
//    }
}
