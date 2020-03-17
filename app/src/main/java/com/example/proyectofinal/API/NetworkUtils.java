package com.example.proyectofinal.API;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.Model.MovieTrailer;

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
    public static ArrayList<Movie> fetchData(String url) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<MovieTrailer> trailers = new ArrayList<>();

        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseJson(results, movies);
            //parseJsonTrailer(results, trailers);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static void parseJsonTrailer(String data, ArrayList<MovieTrailer> listTrailer){
        try{
            JSONObject mainObject = new JSONObject(data);
            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                MovieTrailer trailer = new MovieTrailer(); //New MovieTrailer object

                trailer.setId(jsonObject.getString("id"));
                trailer.setKey(jsonObject.getString("key"));
                listTrailer.add(trailer);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
            Log.e(TAG, "Ocurrio un error en el Parsing de JSON", e);
        }
    }

    public static void parseJson(String data, ArrayList<Movie> list){

        try {
            JSONObject mainObject = new JSONObject(data);

            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);

                Movie movie = new Movie(); //New Movie object

                movie.setId(jsonObject.getInt("id"));
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.getString("original_title"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.getString("backdrop_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setPosterPath(jsonObject.getString("poster_path"));

                //Adding a new movie object into ArrayList
                list.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Ocurrio un error en el Parsing de JSON", e);
        }
    }
}
