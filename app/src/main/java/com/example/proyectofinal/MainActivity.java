package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyectofinal.API.Client;
import com.example.proyectofinal.API.Service;
import com.example.proyectofinal.Adapter.MovieAdapter;
import com.example.proyectofinal.Layout.Busqueda;
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
        TextView popular = findViewById(R.id.seccion);
        popular.setText("Popular");
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final Intent seaintent = new Intent(MainActivity.this, Busqueda.class);
        final ImageButton search = findViewById(R.id.buscador);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(seaintent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true));


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
                t.printStackTrace();
            }
        });
    }
}
