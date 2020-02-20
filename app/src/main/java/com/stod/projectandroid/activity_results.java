package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_results extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final TextView finalenote = findViewById(R.id.notefinale);
        final TextView results = findViewById(R.id.results);

      // Intent intent = new Intent();

        int total = 25;

        int score = 14;

        String note = score+"/"+total;
        finalenote.setText(note);

        int notation = score*100/total;

        results.setText(notation);
    }
}
