package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.stod.projectandroid.api.ExchangeApi;
import com.stod.projectandroid.api.AnswersData;
import com.stod.projectandroid.api.AnswersWrapper;
import com.stod.projectandroid.api.AnwsersDifficultyWrapper;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Response;

public class ListQuestionsRecyclerView extends AppCompatActivity {

    private QuestionsAdapter adapter;
    private List<Questions> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions_recycler_view);

        // Data source definition
        // Today in raw, but can be provided by an API, a BDD, etc.
//        this.currencies.add(new Currency(R.drawable.flag_usa, 1.08f, "$"));
//        this.currencies.add(new Currency(R.drawable.flag_japan, 118.59f, "Y"));

        // initialisation de l'adapter
        adapter = new QuestionsAdapter(this.questions);

        // initialisation de la vue (RecyclerView)
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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

        String difficulty = "";

        // Request
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

    }
}
