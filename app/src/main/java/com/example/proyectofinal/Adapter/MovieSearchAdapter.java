package com.example.proyectofinal.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.proyectofinal.API.Utilidades;
import com.example.proyectofinal.Model.SearchMovie;
import com.example.proyectofinal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieSearchAdapter extends BaseAdapter {

    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";
    private Utilidades util = Utilidades.getInstance();
    private List<SearchMovie> list;
    private Context context;

    public MovieSearchAdapter(Context context,List<SearchMovie> list) {
        this.list = list;
        this.context = context;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = null;
        SearchMovie searchMovie = (SearchMovie) getItem(position);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(100, 200));
        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            relativeLayout.addView(imageView);
        } else {
            imageView = (ImageView) view;
        }

        //load data into the ImageView using Picasso
        Picasso.get().load(MOVIE_BASE_URL + searchMovie.getPoster_path())
                .placeholder(R.drawable.image_placeholder)
                .into(imageView);
        return imageView;
    }
}
