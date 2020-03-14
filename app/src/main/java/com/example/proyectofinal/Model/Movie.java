package com.example.proyectofinal.Model;

import java.util.List;

public class Movie {

    /**
     * popularity : 465.338
     * vote_count : 662
     * video : false
     * poster_path : /uPGq1mkEXznUpapDmOSxbsybjfp.jpg
     * id : 475303
     * adult : false
     * backdrop_path : /6fkqwqLEcDZOEAnBBfKAniwNxtx.jpg
     * original_language : en
     * original_title : A Rainy Day in New York
     * genre_ids : [35,10749]
     * title : A Rainy Day in New York
     * vote_average : 6.7
     * overview : Two young people arrive in New York to spend a weekend, but once they arrive they're met with bad weather and a series of adventures.
     * release_date : 2019-07-26
     */

    private double popularity;
    private int vote_count;
    private boolean video;
    private String poster_path;
    private int id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private String title;
    private double vote_average;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;

    //https://image.tmdb.org/t/p/w500/


    public Movie(double popularity, int vote_count, boolean video, String poster_path, int id, boolean adult, String backdrop_path, String original_language, String original_title, String title, double vote_average, String overview, String release_date, List<Integer> genre_ids) {
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.poster_path = poster_path;
        this.id = id;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500/" + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
}
