package com.example.proyectofinal.API;

import com.example.proyectofinal.Model.Movie;

import java.util.ArrayList;

public class Utilidades {
    private static Utilidades instance = null;

    public static Utilidades getInstance() {
        if(instance == null){
            instance = new Utilidades();
        }
        return instance;
    }

    public ArrayList<Movie> mPopularList;
    public ArrayList<Movie> mTopTopRatedList;

    public String popularMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=54ab07c73593d2ae04ed17bde50c990a";
    public String topRatedMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=54ab07c73593d2ae04ed17bde50c990a";
}
