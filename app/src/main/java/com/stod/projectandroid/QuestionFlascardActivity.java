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
    private QuestionsAdapter adapter;
    private List<Questions> questions = new ArrayList<>();

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
        compteur = intent.getIntExtra("numQuestion",1);
        numQuestion = compteur;
        final TextView noQuestion = findViewById(R.id.noQuestionText);

        compteur+=1;
        //name="pikachu";
        //imageId=R.drawable.pikachu;

        final ImageView pokemonImage = findViewById(R.id.pokemonImageView);
        //pokemonImage.setImageResource(imageId);
        goodAnswer = nom;

        final Button validate = findViewById(R.id.validateButton);
        final RadioGroup radioGroup = findViewById(R.id.answerRadioGroup);

        //  HTTP REQUEST

        // Creation of the retrofit client
        // He will take the data from the baseURL
        // and parse it in JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gryt.tech:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // API generation
        // From the retrofit client
        ExchangeApi api = retrofit.create(ExchangeApi.class);

        String difficulty = getDifficulty();

        // Request Creation
        Call<List<AnwsersDifficultyWrapper>> call = api.getQuestions(difficulty);



        // Asynchronous executive request
        call.enqueue(new Callback<List<AnwsersDifficultyWrapper>>() {
            @Override
            public void onResponse(Call<List<AnwsersDifficultyWrapper>> call, Response<List<AnwsersDifficultyWrapper>> response) {
                for (AnwsersDifficultyWrapper a: response.body()) {
                    String resPokemon = a.asset;
                    String resType = a.asset_type;
                    String resAnimated = a.detail_image;
                    String difficulty = a.difficulty;
                    AnswersData[] answers = a.answers;
                    List<AnswersQuestions> answersPurposeList = new ArrayList<AnswersQuestions>();

                    int resourceId = Resources.getSystem().getIdentifier(resPokemon, "drawable", "com.stod.projectandroid");

                    for (AnswersData i : a.answers) {
                        answersPurposeList.add(new AnswersQuestions(i.sentence, i.isRight));

                    }

                    questions.add(new Questions(resourceId, resType, resAnimated, difficulty,answersPurposeList));
                }



            }

            @Override
            public void onFailure(Call<List<AnwsersDifficultyWrapper>> call, Throwable t) {
                Log.e("CurrencyListActivity", "onFailure: ", t);
            }
        });

        //END HTTP REQUEST

        for (Questions quest : questions) {
            
            pokemonImage.setImageResource(quest.getResPokemon());
            List<AnswersQuestions> responses = quest.getAnswers();
            for (AnswersQuestions answQuest: responses) {
                for (int i=0; i<responses.size(); i++ ) {
                    RadioButton rb = new RadioButton(this);
                    rb.setText(answQuest.sentence);
                    rb.setId(i);
                    rb.setOnClickListener(this);
                    radioGroup.addView(rb);
                }
            }


        }

        noQuestion.setText("Question "+numQuestion+ "sur " + questions.size());


        //called each time an answer is given to confirm his validity.
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
                                    intent2.putExtra("numberQuest", questions.indexOf(compteur));
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

    public String getDifficulty()

    {
        Intent intent = getIntent();
        String difficulty = intent.getStringExtra("difficulty");

        return difficulty;
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, " Name " + ((RadioButton)v).getText() +" Id is "+v.getId());

    }
}
