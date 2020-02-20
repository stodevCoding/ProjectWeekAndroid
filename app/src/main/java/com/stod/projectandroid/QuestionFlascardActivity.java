package com.stod.projectandroid;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionFlascardActivity extends AppCompatActivity {
    private Pokemon pokemon;
    private String goodAnswer;
    private String difficulty;
    private int compteur;
    private int numQuestion;

    private String nom;
    @DrawableRes
    public int imageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_flascard);

        //pokemon = getIntent().getParcelableExtra("pokemon");
        Intent intent = getIntent();
        difficulty = intent.getStringExtra("selection");
        compteur = intent.getIntExtra("numQuestion",1);
        Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, difficulty);

        numQuestion = compteur;
        final TextView noQuestion = findViewById(R.id.noQuestionText);
        noQuestion.setText("Question "+numQuestion);
        compteur+=1;
        nom="bulbizare";
        imageId=R.drawable.bulbizarre;

        final ImageView pokemonImage = findViewById(R.id.pokemonImageView);
        pokemonImage.setImageResource(imageId);
        goodAnswer = nom;

        final Button validate = findViewById(R.id.validateButton);
        final RadioGroup radioGroup = findViewById(R.id.answerRadioGroup);
        
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idButtonChecked = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = findViewById(idButtonChecked);
                String value = selectedButton.getText().toString();
                Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, value + "");

                if(value.contains("RadioButton3")){
                    new AlertDialog.Builder(QuestionFlascardActivity.this)
                            .setTitle("Bonne réponse")
                            .setMessage("La bonne réponse était "+ nom)
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(QuestionFlascardActivity.this, QuestionFlascardActivity.class);
                                    intent2.putExtra("numQuestion", compteur);
                                    startActivity(intent2);
                                    finish();

                                }
                            }).show();
                    Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, "Bonne reponse");
                }
                else{
                    new AlertDialog.Builder(QuestionFlascardActivity.this)
                            .setTitle("Mauvaise réponse")
                            .setMessage("La bonne réponse était "+ nom)
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(QuestionFlascardActivity.this, QuestionFlascardActivity.class);
                                    intent2.putExtra("numQuestion", compteur);
                                    startActivity(intent2);
                                    finish();

                                }
                            }).show();
                    Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, "Bonne reponse");
                }

            }
        });

    }
}
