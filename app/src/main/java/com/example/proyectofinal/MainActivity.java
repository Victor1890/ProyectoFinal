package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proyectofinal.API.Client;
import com.example.proyectofinal.API.Service;
import com.example.proyectofinal.Adapter.MovieAdapter;
import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    String URL_BASE = "https://api.themoviedb.org";
    int PAGE = 1;
    String KEY = "54ab07c73593d2ae04ed17bde50c990a";
    String LANGUAGE = "en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true));

        /*test.add(new Test("",""));
        test.add(new Test("",""));
        test.add(new Test("",""));
        test.add(new Test("",""));
        test.add(new Test("",""));

        AdaptadorDatos adaptadorDatos = new AdaptadorDatos(test);
        recyclerView.setAdapter(adaptadorDatos);
        */

        Client client = new Client();
        Service service = Client.getRetrofit().create(Service.class);
        Call<MovieResponse> call = service.getPopularMovie("54ab07c73593d2ae04ed17bde50c990a");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(getApplicationContext(), movies));
                recyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
