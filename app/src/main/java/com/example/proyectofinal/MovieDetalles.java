package com.example.proyectofinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Adapter.ReviewAdapter;
import com.example.proyectofinal.Model.Movie;
import com.squareup.picasso.Picasso;


public class MovieDetalles extends AppCompatActivity {

    Utilidades util = Utilidades.getInstance();
    TextView title, sinopsis, rating;
    ImageView photo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_popular_items);
        Intent intenrecibe = getIntent();
        ListView rev = findViewById(R.id.listaderev);
        rev.setFocusable(false);
        Movie mov_intent = (Movie) intenrecibe.getSerializableExtra("detalles");
        AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
            try {
                if (NetworkUtils.networkStatus(MovieDetalles.this)) {
                    util.mReviews = NetworkUtils.fetchDataReviews("https://api.themoviedb.org/3/movie/" + mov_intent.getId() + "/reviews?api_key=54ab07c73593d2ae04ed17bde50c990a");
                    ReviewAdapter adapter = new ReviewAdapter(MovieDetalles.this,util.mReviews);
                    rev.setAdapter(adapter);
                 }else {
                    Toast.makeText(MovieDetalles.this,"No conexión a internet",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e){
                doInBackground(voids);
            }
           return null;
            }
        };
        task.execute();

        photo = findViewById(R.id.photo);

        Picasso.get().load(util.MOVIE_BASE_URL_1 + mov_intent.getPosterPath()).into(photo);

        title = findViewById(R.id.MovieName);
        sinopsis = findViewById(R.id.sinopsis);
        rating = findViewById(R.id.MovieRating);

        Double Rating = mov_intent.getVoteAverage();
        title.setText(mov_intent.getTitle());
        sinopsis.setText(mov_intent.getOverview());
        rating.setText(" " + Rating +"/10");
    }
}
