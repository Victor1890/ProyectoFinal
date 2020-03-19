package com.example.proyectofinal.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectofinal.API.FetchMovies;
import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Adapter.MovieAdapter;
import com.example.proyectofinal.MainActivity;
import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.MovieDetalles;
import com.example.proyectofinal.R;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviePages extends AppCompatActivity {

    //@BindView(R.id.progressbar2)
    ProgressBar progressBar2;

    //@BindView(R.id.pop_movies_grid2)
    GridView pelis2;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pages);

        progressBar2 = findViewById(R.id.progressbar2);
        pelis2 = findViewById(R.id.pop_movies_grid2);

        progressBar2.setVisibility(View.INVISIBLE);
        //ButterKnife.bind(MoviePages.this);

        FetchMovies FM = new FetchMovies(this,progressBar2,pelis2);
        FM.execute();

        pelis2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie peliselect = (Movie) adapterView.getAdapter().getItem(position);
                //MovieTrailer trailer = (MovieTrailer) adapterView.getAdapter().getItem(position);
                Intent intentdetails = new Intent(MoviePages.this, MovieDetalles.class);
                intentdetails.putExtra("detalles", peliselect);
                //intentdetails.putExtra("trailer", trailer);
                startActivity(intentdetails);
            }
        });
    }
}