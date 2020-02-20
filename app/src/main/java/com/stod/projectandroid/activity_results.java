package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class activity_results extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final TextView finalenote = findViewById(R.id.note);
        final TextView results = findViewById(R.id.results);

      // Intent intent = new Intent();

        int total = 25;

        int score = 14;

        String note = score+"/"+total;
        finalenote.setText(Html.fromHtml("<p><u>"+note+"</u></p>"));

        int notation = score*100/total;

        results.setText(Html.fromHtml("<p><u>"+notation+"%</u></p>"));
    }
}
