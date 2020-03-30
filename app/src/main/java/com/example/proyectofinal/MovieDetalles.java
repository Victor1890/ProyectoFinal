package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.Reviews;
import com.example.proyectofinal.Model.SearchMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        ListView rev = findViewById(R.id.listaderev);
        rev.setFocusable(false);
        Movie mov_intent = (Movie) intenrecibe.getSerializableExtra("detalles");
        SearchMovie search_intent = (SearchMovie) intenrecibe.getSerializableExtra("detalles_search");
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

        Picasso.get().load(BASE_URL + mov_intent.getPosterPath()).into(photo);

        title = findViewById(R.id.MovieName);
        sinopsis = findViewById(R.id.sinopsis);
        rating = findViewById(R.id.MovieRating);
        Double Rating = mov_intent.getVoteAverage();
        title.setText(mov_intent.getTitle());
        sinopsis.setText(mov_intent.getOverview());
        rating.setText(" " + Rating +"/10");


    }
    public class ReviewAdapter extends BaseAdapter {
        private Context contexto;
        private ArrayList<Reviews> reviewslist;
        public ReviewAdapter(Context context, ArrayList<Reviews> reviews){
            this.contexto = context;
            this.reviewslist = reviews;
        }
        @Override
        public int getCount() {
            return reviewslist.size();
        }

        @Override
        public Object getItem(int i) {
            return reviewslist.get(i);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            View v = inflater.inflate(R.layout.review,null);
            TextView author = v.findViewById(R.id.Autor);
            TextView content = v.findViewById(R.id.Contenido);
            Reviews reviews = (Reviews) getItem(position);
            if (!reviews.getAuthor().equals(" ")) {
                author.setText(reviews.getAuthor());
                content.setText(reviews.getContent());
                return v;
            }else{
               Toast.makeText(MovieDetalles.this,"Lo siento, esta pelicula no posee ninguna reseña, estamos trabajando para añadir mas...",Toast.LENGTH_LONG).show();
                return null;
            }
        }
    }

}
