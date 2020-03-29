package com.example.proyectofinal.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Model.Movie;
import com.example.proyectofinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter{

    private Utilidades util = Utilidades.getInstance();
    private Context mContext;
    public ArrayList<Movie> list;

    private static MovieAdapter instance = null;

    public static MovieAdapter getInstance(Context context, ArrayList<Movie> list) {
        if(instance == null){
            instance = new MovieAdapter(context, list);
        }
        return instance;
    }

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.mContext = context;
        this.list = movieList;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = null;
        Movie movies = (Movie) getItem(position);
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(5,5,5,5);
            relativeLayout.addView(imageView);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.get().load(util.MOVIE_BASE_URL_1 + movies.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(imageView);
        return imageView;
    }
}