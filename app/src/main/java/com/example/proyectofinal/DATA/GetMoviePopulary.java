package com.example.proyectofinal.DATA;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMoviePopulary {
    @GET("/3/movie/{category}")
    Call<MoviePopulary> getMovie(

            @Path("category") String category,
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") int page
    );
}
