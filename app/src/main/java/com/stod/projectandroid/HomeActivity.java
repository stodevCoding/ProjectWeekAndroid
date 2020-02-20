package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button demarrer = findViewById(R.id.demarrer);
        final Button apropos = findViewById(R.id.apropos);
        final Button  solutions = findViewById(R.id.solutions);
        final ImageView pokeball = findViewById(R.id.pokeball);
        final TextView title = findViewById(R.id.title);

        demarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DialogFragment();
                newFragment.show(getSupportFragmentManager(), "difficulty");
                Log.i("HomeActivity", "Click");

            }
        });
        apropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HomeActivity", "Click");

            }
        });

        solutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HomeActivity", "Click");
            }
        });
    }

}
