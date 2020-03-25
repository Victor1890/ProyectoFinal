package com.example.proyectofinal.API;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.MovieTrailer;
import com.example.proyectofinal.Model.Reviews;
import com.example.proyectofinal.Model.Search;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    public static Boolean networkStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    public static ArrayList<Movie> fetchData(String url){
        ArrayList<Movie> movies = new ArrayList<>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseJson(results, movies);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static ArrayList<Search> fetchDataSearch(String url){
        ArrayList<Search> searches = new ArrayList<>();
        try {
            URL new_url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = IOUtils.toString(inputStream);

            parseJsonSearch(result,searches);

            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return searches;
    }

    public static ArrayList<Reviews> fetchDataReviews(String url){
        ArrayList<Reviews> reviews = new ArrayList<>();
        try {
            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();
            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseJsonReviews(results, reviews);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public static void parseJsonSearch(String data, ArrayList<Search> list){
        try {
            JSONObject mainObject = new JSONObject(data);
            Search search = new Search();
            JSONArray resArray = mainObject.getJSONArray("results");

            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);

                search.setPoster_path(jsonObject.getString("poster_path"));
                search.setTitle(jsonObject.getString("title"));
                search.setVote_average(jsonObject.getDouble("vote_average"));
                search.setId(jsonObject.getInt("id"));
                list.add(search);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "Ocurrio un error en el Parsing de JSON", e);
        }
    }

    public static void parseJsonReviews(String data, ArrayList<Reviews> list){
        try{
            JSONObject mainObject = new JSONObject(data);


            JSONArray resArray = mainObject.getJSONArray("results");

            for(int i = 0; i < resArray.length(); i++){
                JSONObject jsonObject = resArray.getJSONObject(i);
                Reviews reviews = new Reviews();
                reviews.setAuthor(jsonObject.getString("author"));
                reviews.setContent(jsonObject.getString("content"));
                reviews.setId(jsonObject.getString("id"));
                reviews.setUrl(jsonObject.getString("url"));

                list.add(reviews);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "Ocurrio un error en el Parsing de JSON", e);
        }
    }

    public static void parseJson(String data, ArrayList<Movie> list){

        try {
            JSONObject mainObject = new JSONObject(data);

            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int k = 0; k < resArray.length(); k++) {
                JSONObject jsonObject = resArray.getJSONObject(k);
                Movie movie = new Movie();
                movie.setId(jsonObject.getInt("id"));
                movie.setVoteAverage(jsonObject.getDouble("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.getString("original_title"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.getString("backdrop_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setPosterPath(jsonObject.getString("poster_path"));
                list.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Ocurrio un error en el Parsing de JSON", e);
        }
    }
}
