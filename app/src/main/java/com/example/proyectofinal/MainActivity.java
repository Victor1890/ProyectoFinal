package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import com.example.proyectofinal.API.FetchMovies;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Layout.MoviePages;
import com.example.proyectofinal.Model.Movie;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.proyectofinal.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;
    @BindView(R.id.pop_movies_grid)
    GridView pelis;


    private Intent intent;
    private Utilidades util = Utilidades.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        mProgressBar = findViewById(R.id.indeterminateBar);
        pelis = findViewById(R.id.pop_movies_grid);

        ButterKnife.bind(MainActivity.this);
        mProgressBar.setVisibility(View.INVISIBLE);

        FetchMovies FM = new FetchMovies(this,mProgressBar,pelis);
        FM.execute();

        pelis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie peliselect = (Movie) adapterView.getAdapter().getItem(position);
                //MovieTrailer trailer = (MovieTrailer) adapterView.getAdapter().getItem(position);
                Intent intentdetails = new Intent(MainActivity.this,MovieDetalles.class);
                intentdetails.putExtra("detalles", peliselect);
                //intentdetails.putExtra("trailer", trailer);
                startActivity(intentdetails);
            }
        });
    }

    public void test(View v){
        intent = new Intent(MainActivity.this, MoviePages.class);
        startActivity(intent);
    }
}
