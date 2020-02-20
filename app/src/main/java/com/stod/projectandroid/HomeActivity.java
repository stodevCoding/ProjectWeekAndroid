package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public String selec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button demarrer = findViewById(R.id.demarrer);
        final Button apropos = findViewById(R.id.apropos);
        final Button  solutions = findViewById(R.id.solutions);
        final ImageView pokeball = findViewById(R.id.pokeball);
        final TextView title = findViewById(R.id.title);
        demarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DialogFragment("");
                newFragment.show(getSupportFragmentManager(), "difficulty");
                selec = getIntent().getParcelableExtra("selection");

                if(selec != null) {
                    Intent intent = new Intent(HomeActivity.this, QuestionFlascardActivity.class);
                    intent.putExtra("selection", selec+"");
                    startActivityForResult(intent, 1);
                    Log.i("HomeActivity", "navigation flashCard");
                }
                Log.i(HomeActivity.ACCESSIBILITY_SERVICE, selec+" test");

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
