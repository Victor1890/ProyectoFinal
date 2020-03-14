package com.example.proyectofinal.API;

import com.example.proyectofinal.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovie(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopMovie(@Query("api_key") String api_key);
}
