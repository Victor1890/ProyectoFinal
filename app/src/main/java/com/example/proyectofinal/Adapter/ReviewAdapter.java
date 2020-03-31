package com.example.proyectofinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.Model.Reviews;
import com.example.proyectofinal.MovieDetalles;
import com.example.proyectofinal.R;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    private Context contexto;
    private ArrayList<Reviews> reviewslist;

    public ReviewAdapter(Context context, ArrayList<Reviews> reviews){
        this.contexto = context;
        this.reviewslist = reviews;
    }
    @Override
    public int getCount() {
        return reviewslist.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewslist.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View v = inflater.inflate(R.layout.review,null);
        TextView author = v.findViewById(R.id.Autor);
        TextView content = v.findViewById(R.id.Contenido);
        Reviews reviews = (Reviews) getItem(position);
        if (!reviews.getAuthor().equals(" ")) {
            author.setText(reviews.getAuthor());
            content.setText(reviews.getContent());
            return v;
        }else{
            Toast.makeText(contexto,"Lo siento, esta pelicula no posee ninguna reseña, estamos trabajando para añadir mas...",Toast.LENGTH_LONG).show();
            return null;
        }
    }
}