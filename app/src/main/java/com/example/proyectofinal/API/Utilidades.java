package com.example.proyectofinal.API;

import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.Reviews;
import com.example.proyectofinal.Model.SearchMovie;

import java.util.ArrayList;

public class Utilidades {
    private static Utilidades instance = null;

    public static Utilidades getInstance() {
        if(instance == null){
            instance = new Utilidades();
        }
        return instance;
    }

    public String API_KEY = "54ab07c73593d2ae04ed17bde50c990a";

    public ArrayList<Movie> mPopularList = new ArrayList<>();;
    public ArrayList<Movie> mTopTopRatedList = new ArrayList<>();;
    public ArrayList<Reviews> mReviews = new ArrayList<>();
    public ArrayList<SearchMovie> searches = new ArrayList<>();

    public String parametros(String _buscar){
        String buscar = "";
        return buscar;
    }

    public final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w500";
    public String popularMovies = "https://api.themoviedb.org/3/movie/popular?api_key="+ API_KEY+"&language=en-US&page=1";
    public String topRatedMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=" + API_KEY;

}
