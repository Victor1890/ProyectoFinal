package com.example.proyectofinal.API;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectofinal.Adapter.MovieSearchAdapter;

public class FetchMoviesSearch extends AsyncTask<Void,Void,Void> {
    private Utilidades util = Utilidades.getInstance();

    private ProgressBar progressBar;
    private Context context;
    private GridView gridView;
    private String search;

    public FetchMoviesSearch(Context context, ProgressBar progressBar, GridView gridView, String search) {
        this.search = search;
        this.progressBar = progressBar;
        this.context = context;
        this.gridView = gridView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.setVisibility(View.INVISIBLE);
        gridView.setVisibility(View.VISIBLE);

        MovieSearchAdapter searchAdapter = new MovieSearchAdapter(context, util.searches);
        gridView.setAdapter(searchAdapter);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            if(NetworkUtils.networkStatus(context)){
                util.searches = NetworkUtils.fetchDataSearch(util.parametros(search));
            }else{
                Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            doInBackground(voids);
        }
        return null;
    }
}
