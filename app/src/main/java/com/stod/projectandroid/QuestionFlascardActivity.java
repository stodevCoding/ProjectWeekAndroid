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

public class QuestionFlascardActivity extends AppCompatActivity {
    private Pokemon pokemon;
    private String goodAnswer;
    private String difficulty;
    private int numQuestion;

    private String nom;
    @DrawableRes
    public int imageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_flascard);

        //pokemon = getIntent().getParcelableExtra("pokemon");

        nom="bulbizare";
        imageId=R.drawable.bulbizarre;

        final ImageView pokemonImage = findViewById(R.id.pokemonImageView);
        pokemonImage.setImageResource(imageId);
        goodAnswer = nom;

        final Button validate = findViewById(R.id.validateButton);
        final RadioGroup radioGroup = findViewById(R.id.answerRadioGroup);

        Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, "numero question : "+numQuestion+"");

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
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
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
                                    Intent intent = getIntent();
                                    finish();
                                    //intent.putExtra("numQuestion", 2); ne fonctionne pas
                                    startActivity(intent);
                                }
                            }).show();
                    Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, "Bonne reponse");
                }

            }
        });

    }
}
