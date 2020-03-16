package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.Adapter.MovieAdapter;
import com.example.proyectofinal.Model.Movie;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.proyectofinal.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.indeterminateBar) ProgressBar mProgressBar;
    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;
    @BindView(R.id.pop_movies_grid) GridView pelis;
    String KEY= "54ab07c73593d2ae04ed17bde50c990a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        ButterKnife.bind(MainActivity.this);
        mProgressBar.setVisibility(View.INVISIBLE);
        FetchMovies FM = new FetchMovies();
        FM.execute();



    }
    public class FetchMovies extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String popularMovies;
            String topRatedMovies;
            popularMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=54ab07c73593d2ae04ed17bde50c990a";
            topRatedMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=54ab07c73593d2ae04ed17bde50c990a";
            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();
            try {
                if(NetworkUtils.networkStatus(MainActivity.this)){
                    mPopularList = NetworkUtils.fetchData(popularMovies); //Get popular movies
                    MovieAdapter adapter = new MovieAdapter(MainActivity.this,mPopularList);
                    pelis.setAdapter(adapter);
                    mTopTopRatedList = NetworkUtils.fetchData(topRatedMovies); //Get top rated movies
                }else{
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
