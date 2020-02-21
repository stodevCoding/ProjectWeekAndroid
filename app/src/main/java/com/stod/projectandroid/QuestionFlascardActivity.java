package com.stod.projectandroid;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
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

public class QuestionFlascardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuestionFlascardActivi";
    //private QuestionsAdapter adapter;
    private List<Questions> questions = new ArrayList<>();

    private int compteur;
    private int numQuestion;
    private String namePokPurpose;
    private AnswersQuestions goodAnswer;
    private int nbOfGoodAnswers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_flascard);

        ArrayList<Questions> listFlashCard = getIntent().getParcelableArrayListExtra("listFlashCard");
        Intent intent = getIntent();
        compteur = intent.getIntExtra("numQuestion", 1);
        nbOfGoodAnswers = intent.getIntExtra("nbOfGoodAnswers", 0);
        numQuestion = compteur;
        int numberQuestions = intent.getIntExtra("numberQuestions", 0);
        final TextView noQuestion = findViewById(R.id.noQuestionText);


        final ImageView pokemonImage = findViewById(R.id.pokemonImageView);
        final Button validate = findViewById(R.id.validateButton);
        final RadioGroup radioGroup = findViewById(R.id.answerRadioGroup);

        Questions quest = listFlashCard.get(compteur);


        int resourceId = getResources().getIdentifier(quest.getResPokemon(), "drawable", "com.stod.projectandroid");


        pokemonImage.setImageResource(resourceId);
        List<AnswersQuestions> responses = quest.getAnswers();


        for (AnswersQuestions answQuest : responses) {
            RadioButton rb = new RadioButton(QuestionFlascardActivity.this);
            rb.setText(answQuest.sentence);
            namePokPurpose = rb.getText().toString();
            rb.setTag(answQuest);

            radioGroup.addView(rb);

            if (answQuest.isRight) {
                goodAnswer = answQuest;
                Log.i(TAG, goodAnswer + "");
            }

        }
        noQuestion.setText("Question " + numQuestion + " sur " + numberQuestions);


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idButtonChecked = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = findViewById(idButtonChecked);
                AnswersQuestions value = (AnswersQuestions) selectedButton.getTag();
                Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, value + "");

                if (value.isRight) {
                    new AlertDialog.Builder(QuestionFlascardActivity.this)
                            .setTitle("Bonne réponse")
                            .setMessage("La bonne réponse est " + goodAnswer.sentence)
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG, "compteur : " + compteur + " et numberQuestions : " + numberQuestions);
                                    if (compteur - 1 < numberQuestions) {
                                        compteur += 1;
                                        Intent intent2 = new Intent(QuestionFlascardActivity.this, QuestionFlascardActivity.class);
                                        intent2.putExtra("numQuestion", compteur);
                                        intent2.putExtra("numberQuestions", listFlashCard.size());
                                        intent2.putParcelableArrayListExtra("listFlashCard", listFlashCard);
                                        startActivity(intent2);
                                        finish();
                                    } else if (compteur - 1 == numberQuestions) {
                                        Intent intent2 = new Intent(QuestionFlascardActivity.this, activity_results.class);
                                        intent2.putExtra("numQuestion", compteur);
                                        intent2.putExtra("numberQuestions", listFlashCard.size());
                                        intent2.putExtra("nbOfGoodAnswers", nbOfGoodAnswers);
                                        intent2.putParcelableArrayListExtra("listFlashCard", listFlashCard);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }
                            }).show();
                    Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, "Bonne reponse");
                } else {
                    new AlertDialog.Builder(QuestionFlascardActivity.this)
                            .setTitle("Mauvaise réponse")
                            .setMessage("La bonne réponse était " + goodAnswer.sentence)
                            .setCancelable(true)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG, "compteur : " + compteur + " et numberQuestions : " + numberQuestions);
                                    if (compteur < numberQuestions) {
                                        compteur += 1;
                                        Intent intent2 = new Intent(QuestionFlascardActivity.this, QuestionFlascardActivity.class);
                                        intent2.putExtra("numQuestion", compteur);
                                        intent2.putExtra("numberQuestions", listFlashCard.size());
                                        intent2.putParcelableArrayListExtra("listFlashCard", listFlashCard);
                                        startActivity(intent2);
                                        finish();
                                    } else if (compteur - 1 == numberQuestions) {

                                        Intent intent2 = new Intent(QuestionFlascardActivity.this, activity_results.class);
                                        intent2.putExtra("numQuestion", compteur);
                                        intent2.putExtra("numberQuestions", listFlashCard.size());
                                        intent2.putParcelableArrayListExtra("listFlashCard", listFlashCard);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }
                            }).show();
                    Log.i(QuestionFlascardActivity.ACCESSIBILITY_SERVICE, "Bonne reponse");
                }

            }
        });

    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, " Name " + ((RadioButton) v).getText() + " Id is " + v.getId());

    }
}
