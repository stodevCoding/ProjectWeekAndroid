package com.stod.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.stod.projectandroid.api.ExchangeApi;
import com.stod.projectandroid.api.RatesData;
import com.stod.projectandroid.api.RatesWrapper;

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
                .baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Génération de notre API
        // à partir du client retrofit
        ExchangeApi api = retrofit.create(ExchangeApi.class);

        // Création de la requête
        Call<RatesWrapper> call = api.getQuestions();

        // Exécution de la requête en asynchrone
        call.enqueue(new Callback<RatesWrapper>() {
            @Override
            public void onResponse(Call<RatesWrapper> call, Response<RatesWrapper> response) {
                RatesData body = response.body().rates;
                Log.i("CurrencyListActivity", "onResponse: " + body);

                questions.add(new Questions(R.drawable.p_of_pokemon, body.USD, "$"));
                questions.add(new Questions(R.drawable.p_of_pokemon, body.GBP, "£"));
                questions.add(new Questions(R.drawable.p_of_pokemon, body.JPY, "Y"));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RatesWrapper> call, Throwable t) {
                Log.e("CurrencyListActivity", "onFailure: ", t);
            }
        });

    }
}
