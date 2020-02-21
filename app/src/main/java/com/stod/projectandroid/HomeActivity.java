package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stod.projectandroid.api.AnswersData;
import com.stod.projectandroid.api.AnwsersDifficultyWrapper;
import com.stod.projectandroid.api.ExchangeApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    public static final String TAG = "HomeActivity";
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
                DialogFragment newFragment = new DialogFragment();
                newFragment.listener = new DialogFragment.OnSelectListener() {
                    @Override
                    public void onDifficultySelected(String difficulty) {
                        Log.i(TAG, "onDifficultySelected: " + difficulty) ;
                        builFlashcard(difficulty);
                    }
                };
                newFragment.show(getSupportFragmentManager(), "difficulty");
                Log.i("HomeActivity", "Click");

                selec = getIntent().getParcelableExtra("selection");

                if(selec != null) {
                    Intent intent = new Intent(HomeActivity.this, QuestionFlascardActivity.class);
                    intent.putExtra("selection", selec+"");
                    startActivityForResult(intent, 1);
                    Log.i("HomeActivity", "navigation flashCard");
                }

            }
        });

        apropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, About.class);
                startActivity(intent);
            }
        });


        solutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HomeActivity", "Click");

                Intent intent = new Intent(HomeActivity.this, ListQuestionsRecyclerView.class);
                startActivity(intent);

            }
        });
    }

    public ArrayList<Questions> builFlashcard(String difficulty) {
        ArrayList<Questions> lstFlashcard = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gryt.tech:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExchangeApi api = retrofit.create(ExchangeApi.class);

        Call<List<AnwsersDifficultyWrapper>> call = api.getQuestions(difficulty);

        // Exécution de la requête en asynchrone
        call.enqueue(new Callback<List<AnwsersDifficultyWrapper>>() {
            @Override
            public void onResponse(Call<List<AnwsersDifficultyWrapper>> call, Response<List<AnwsersDifficultyWrapper>> response) {
                for (AnwsersDifficultyWrapper a : response.body()) {
                    String resPokemon = a.asset.substring(0, a.asset.lastIndexOf("."));
                    String resType = a.asset_type;
                    String resAnimated = a.detail_image;
                    String difficulty = a.difficulty;
                    AnswersData[] answers = a.answers;
                    ArrayList<AnswersQuestions> answersPurposeList = new ArrayList<AnswersQuestions>();

                    for (AnswersData i : a.answers) {
                        answersPurposeList.add(new AnswersQuestions(i.sentence, i.isRight));
                    }
                    lstFlashcard.add(new Questions(resPokemon, resType, resAnimated, difficulty, answersPurposeList));
                }
                Intent intent = new Intent(HomeActivity.this, QuestionFlascardActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("numberQuestions", lstFlashcard.size());
                intent.putParcelableArrayListExtra("listFlashCard", lstFlashcard);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<AnwsersDifficultyWrapper>> call, Throwable t) {
                Log.e("CurrencyListActivity", "onFailure: ", t);
            }
        });
        return lstFlashcard;
    }

}
