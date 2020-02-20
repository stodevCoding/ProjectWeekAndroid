package com.stod.projectandroid.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeApi {

    @GET("pokemons/")
    Call<List<AnwsersDifficultyWrapper>> getQuestions(@Query("difficulty") String difficulty);

    @GET("pokemons/")
    Call <List<AnswersWrapper>>getAllQuestions();
}
