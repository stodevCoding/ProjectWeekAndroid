package com.stod.projectandroid.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExchangeApi {

    @GET("latest")
    Call<RatesWrapper> getQuestions();
}
