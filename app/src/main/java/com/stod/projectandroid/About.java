package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView versionValueTextView = findViewById(R.id.versionValueTextView);
        versionValueTextView.setText(BuildConfig.VERSION_NAME);
    }
}
