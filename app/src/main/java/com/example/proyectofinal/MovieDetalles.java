package com.example.proyectofinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.MovieTrailer;
import com.squareup.picasso.Picasso;

public class MovieDetalles extends AppCompatActivity {
    private static final String TAG = MovieDetalles.class.getSimpleName();
    public static final String BASE_URL = "https://image.tmdb.org/t/p/w500";
    Utilidades util = Utilidades.getInstance();
    TextView title, sinopsis, rating;
    ImageView photo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_popular_items);
        Intent intenrecibe = getIntent();
        Movie mov_intent = (Movie) intenrecibe.getSerializableExtra("detalles");
        //MovieTrailer mov_trailer = (MovieTrailer) intenrecibe.getSerializableExtra("trailer");
        AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                util.mReviews = NetworkUtils.fetchDataReviews("https://api.themoviedb.org/3/movie/"+mov_intent.getId()+"/reviews?api_key=54ab07c73593d2ae04ed17bde50c990a");
                return null;
            }
        };
        task.execute();

        photo = findViewById(R.id.photo);

        Picasso.get().load(BASE_URL + mov_intent.getPosterPath()).into(photo);

        title = findViewById(R.id.MovieName);
        sinopsis = findViewById(R.id.sinopsis);
        rating = findViewById(R.id.MovieRating);
        Double Rating = mov_intent.getVoteAverage();
        title.setText(mov_intent.getTitle());
        sinopsis.setText(mov_intent.getOverview());
        rating.setText(Rating +"/10");
    }

}
