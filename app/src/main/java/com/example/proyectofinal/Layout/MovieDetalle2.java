package com.example.proyectofinal.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Adapter.ReviewAdapter;
import com.example.proyectofinal.Model.SearchMovie;
import com.example.proyectofinal.R;
import com.squareup.picasso.Picasso;

public class MovieDetalle2 extends AppCompatActivity {

    private ImageView photo2;
    private TextView MovieName2, MovieRating2, sinopsis2;
    private ListView listaderev2;

    private Utilidades util = Utilidades.getInstance();

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

        Intent intenrecibe = getIntent();

        SearchMovie search_intent = (SearchMovie) intenrecibe.getSerializableExtra("detalles_search");

        AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    if(NetworkUtils.networkStatus(MovieDetalle2.this)){
                        util.mReviews = NetworkUtils.fetchDataReviews("https://api.themoviedb.org/3/movie/" + search_intent.getId() + "/reviews?api_key=54ab07c73593d2ae04ed17bde50c990a");
                        ReviewAdapter adapter = new ReviewAdapter(MovieDetalle2.this,util.mReviews);
                        listaderev2.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(MovieDetalle2.this, "No conexión a internet", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    doInBackground(voids);
                }
                return null;
            }
        };

        task.execute();

        Picasso.get().load(util.MOVIE_BASE_URL_1 + search_intent.getPoster_path()).into(photo2);

        Double hola = search_intent.getVote_average();
        MovieName2.setText( search_intent.getTitle() );
        MovieRating2.setText(String.valueOf(hola));
        sinopsis2.setText( search_intent.getRelease_date() );
    }
}
