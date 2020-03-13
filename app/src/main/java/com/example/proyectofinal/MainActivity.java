package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectofinal.DATA.GetMoviePopulary;
import com.example.proyectofinal.DATA.MoviePopulary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt1;

    String URL_BASE = "https://api.themoviedb.org";
    int PAGE = 1;
    String KEY = "54ab07c73593d2ae04ed17bde50c990a";
    String LANGUAGE = "en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create()).build();
        GetMoviePopulary getMoviePopulary = retrofit.create(GetMoviePopulary.class);

        Call<MoviePopulary> call = getMoviePopulary.getMovie("popular",KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<MoviePopulary>() {
            @Override
            public void onResponse(Call<MoviePopulary> call, Response<MoviePopulary> response) {
                MoviePopulary results = response.body();
                List<MoviePopulary.ResultsBean> listOfMovie = results.getResults();
                MoviePopulary.ResultsBean hola = listOfMovie.get(0);
                txt1.setText(hola.getTitle());
            }

            @Override
            public void onFailure(Call<MoviePopulary> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
