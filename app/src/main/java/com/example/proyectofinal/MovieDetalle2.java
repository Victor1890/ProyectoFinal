package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MovieDetalle2 extends AppCompatActivity {

    private ImageView photo2;
    private TextView MovieName2, MovieRating2, sinopsis2;
    private ListView listaderev2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_popular_items2);
        photo2 = findViewById(R.id.photo2);
        MovieName2 = findViewById(R.id.MovieName2);
        MovieRating2 = findViewById(R.id.MovieRating2);
        sinopsis2 = findViewById(R.id.sinopsis2);
        listaderev2 = findViewById(R.id.listaderev2);

        listaderev2.setFocusable(false);


    }
}
