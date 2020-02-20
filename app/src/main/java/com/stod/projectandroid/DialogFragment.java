package com.stod.projectandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class DialogFragment extends androidx.fragment.app.DialogFragment implements Parcelable {

    private String selection = "";

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
                            setSelection("easy");

                        } else if (which == 1) {
                            setSelection("medium");


                        } else if (which == 2) {
                            setSelection("hard");

                        }

                        Log.i(HomeActivity.ACCESSIBILITY_SERVICE, selection+"");
                        Intent intent = new Intent();
                        intent.putExtra("selection", selection);


                    }
                });
        //Create the alert and return it
        return builder.create();
    }

    public DialogFragment(String selection) {
        this.selection = selection;
    }
    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(selection);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DialogFragment> CREATOR = new Creator<DialogFragment>() {
        @Override
        public DialogFragment createFromParcel(Parcel in) {
            return new DialogFragment(in.readString());
        }

        @Override
        public DialogFragment[] newArray(int size) {
            return new DialogFragment[size];
        }
    };

}
