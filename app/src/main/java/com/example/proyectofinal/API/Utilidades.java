package com.example.proyectofinal.API;

import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.Reviews;
import com.example.proyectofinal.Model.Search;

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
    public ArrayList<Search> searches = new ArrayList<>();

    public final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w500";
    public static String BASE_URL = "http://api.themoviedb.org/3/";
    public String popularMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY;
    public String topRatedMovies = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=" + API_KEY;
}
