package com.example.proyectofinal.API;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectofinal.Adapter.MovieAdapter;

import java.util.ArrayList;

public class FetchMovies extends AsyncTask<Void,Void,Void> {

    private Utilidades util = Utilidades.getInstance();

    private ProgressBar progressBar;
    private Context context;
    private GridView gridView;

    public FetchMovies(Context context, ProgressBar progressBar, GridView gridView) {
        this.progressBar = progressBar;
        this.context = context;
        this.gridView = gridView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            if(NetworkUtils.networkStatus(context)){
                util.mPopularList = NetworkUtils.fetchData(util.popularMovies);
                util.mTopTopRatedList = NetworkUtils.fetchData(util.topRatedMovies);
                MovieAdapter adapter = new MovieAdapter(context,util.mPopularList);
                gridView.setAdapter(adapter);
            }else{
                Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            doInBackground(voids);
        }
        return null;
    }
}
