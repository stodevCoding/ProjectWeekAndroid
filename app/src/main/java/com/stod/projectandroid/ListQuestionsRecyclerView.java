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

        // définition de la source données
        // aujourd'hui en dur, mais peut provenir d'une API, BDD, etc.
//        this.currencies.add(new Currency(R.drawable.flag_usa, 1.08f, "$"));
//        this.currencies.add(new Currency(R.drawable.flag_japan, 118.59f, "Y"));

        // initialisation de l'adapter
        adapter = new QuestionsAdapter(this.questions);

        // initialisation de la vue (RecyclerView)
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // REQUETE HTTP

        // Création du client retrofit
        // il va donc taper sur la baseUrl donnée
        // et parser le résultat en JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gryt.tech:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Génération de notre API
        // à partir du client retrofit
        ExchangeApi api = retrofit.create(ExchangeApi.class);

        String difficulty = "";

        // Création de la requête
        Call<List<AnswersWrapper>> call = api.getAllQuestions();


        // Exécution de la requête en asynchrone
        call.enqueue(new Callback<List<AnswersWrapper>>() {
            @Override
            public void onResponse(Call<List<AnswersWrapper>> call, Response<List<AnswersWrapper>> response) {
                for (AnswersWrapper a : response.body()) {
                    String resPokemon = a.asset;
                    String resType = a.asset_type;
                    String resAnimated = a.detail_image;
                    String difficulty = a.difficulty;
                    AnswersData[] answers = a.answers;
                    ArrayList<AnswersQuestions> answersPurposeList = new ArrayList<AnswersQuestions>();

                    int resourceId = Resources.getSystem().getIdentifier(resPokemon, "drawable", "com.stod.projectandroid");

                    for (AnswersData i : a.answers) {
                        answersPurposeList.add(new AnswersQuestions(i.sentence, i.isRight));

                    }

                    questions.add(new Questions(resPokemon, resType, resAnimated, difficulty, answersPurposeList));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AnswersWrapper>> call, Throwable t) {
                Log.e("CurrencyListActivity", "onFailure: ", t);
            }
        });

    }
}
