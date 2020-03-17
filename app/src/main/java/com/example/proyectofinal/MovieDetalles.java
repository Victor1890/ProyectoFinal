package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.Model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetalles extends AppCompatActivity {
    private static final String TAG = MovieDetalles.class.getSimpleName();
    public static final String BASE_URL = "https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_popular_items);
        Intent intenrecibe = getIntent();
        Movie mov_intent = (Movie) intenrecibe.getSerializableExtra("detalles");
        ImageView photo = findViewById(R.id.photo);
        TextView title,sinopsis;
        Picasso.get().load(BASE_URL + mov_intent.getPosterPath()).into(photo);
        title = findViewById(R.id.MovieName);
        sinopsis = findViewById(R.id.sinopsis);
        title.setText(mov_intent.getTitle());
        sinopsis.setText(mov_intent.getOverview());
    }
}
