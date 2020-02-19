package com.stod.projectandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.difficultylevel)
                .setItems(R.array.difficulty_level, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if (which == 0) {

                        } else if (which == 1) {

                        } else if (which == 2) {

                        }
                    }
                });
        //Create the alert and return it
        return builder.create();
    }
}
