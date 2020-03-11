package com.example.proyectofinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API {

    private static String _apiKey = "6cdd7102";
    private static final String SEACH_URL = "http://www.omdbapi.com/?apikey=APIKEY&s=TITLE";

    public static String seachMoviebyTitle(String title, String key) throws MalformedURLException {
        String requestURL = SEACH_URL
                .replace("TITLE", title)
                .replace("APIKEY", key);
        return sendGetRequest(requestURL);
    }

    public static String sendGetRequest(String rquestURL){
        StringBuffer response = new StringBuffer();

        try{
            URL url = new URL(rquestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            InputStream stream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader buffer = new BufferedReader(reader);

            String line;
            while ((line = buffer.readLine()) != null){
                response.append(line);
            }
            buffer.close();
            connection.disconnect();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
