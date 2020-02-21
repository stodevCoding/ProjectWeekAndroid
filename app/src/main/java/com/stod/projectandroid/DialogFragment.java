package com.stod.projectandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;

import com.stod.projectandroid.api.AnswersData;
import com.stod.projectandroid.api.AnwsersDifficultyWrapper;
import com.stod.projectandroid.api.ExchangeApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.stod.projectandroid.api.AnswersData;
import com.stod.projectandroid.api.AnwsersDifficultyWrapper;
import com.stod.projectandroid.api.ExchangeApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    public interface OnSelectListener {
        void onDifficultySelected(String difficulty);
    }

    public OnSelectListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.difficultylevel)
                .setItems(R.array.difficulty_level, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String difficulty = "";

                        switch (which) {
                            case 0:
                                difficulty = "easy";
                                break;
                            case 1:
                                difficulty = "medium";
                                break;
                            case 2:
                                difficulty = "hard";
                                break;
                        }
                        listener.onDifficultySelected(difficulty);

                    }
                });
        //Create the alert and return it
        return builder.create();
    }



    //END HTTP REQUEST
}
