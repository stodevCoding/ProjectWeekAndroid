package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class QuestionFlascardActivity extends AppCompatActivity {
    private Pokemon pokemon;
    private String goodAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_flascard);

        pokemon = getIntent().getParcelableExtra("pokemon");

        final ImageView pokemonImage = findViewById(R.id.pokemonImageView);
        pokemonImage.setImageResource(pokemon.imageId);
        goodAnswer = pokemon.nom;

        final Button validate = findViewById(R.id.validateButton);
        final RadioGroup radioGroup = findViewById(R.id.answerRadioGroup);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idButtonChecked = radioGroup.getCheckedRadioButtonId();
                String value = findViewById(R.id.idButtonChecked);
                

            }
        });

    }
}
