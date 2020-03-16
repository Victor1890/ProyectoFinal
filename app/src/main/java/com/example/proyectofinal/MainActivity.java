package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectofinal.API.NetworkUtils;
import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Adapter.MovieAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.proyectofinal.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.indeterminateBar) ProgressBar mProgressBar;
    @BindView(R.id.pop_movies_grid) GridView pelis;

    private Utilidades util = Utilidades.getInstance();

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
            util.mPopularList = new ArrayList<>();
            util.mTopTopRatedList = new ArrayList<>();
            try {
                if(NetworkUtils.networkStatus(MainActivity.this)){
                    util.mPopularList = NetworkUtils.fetchData(util.popularMovies); //Get popular movies
                    MovieAdapter adapter = new MovieAdapter(MainActivity.this,util.mPopularList);
                    pelis.setAdapter(adapter);
                    util.mTopTopRatedList = NetworkUtils.fetchData(util.topRatedMovies); //Get top rated movies
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
