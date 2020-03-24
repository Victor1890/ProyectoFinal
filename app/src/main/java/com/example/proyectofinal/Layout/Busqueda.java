package com.example.proyectofinal.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.proyectofinal.API.FetchMoviesSearch;
import com.example.proyectofinal.Model.Search;
import com.example.proyectofinal.MovieDetalles;
import com.example.proyectofinal.R;

public class Busqueda extends AppCompatActivity {

    private SearchView buscadorchingon;
    private GridView gridView;
    private ProgressBar indeterminateBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        buscadorchingon = findViewById(R.id.buscadorchingon);
        gridView = findViewById(R.id.gridView_Buscar);
        indeterminateBar1 = findViewById(R.id.indeterminateBar1);

        FetchMoviesSearch FM = new FetchMoviesSearch(this, indeterminateBar1,gridView);
        FM.execute();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Search search = (Search) adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(Busqueda.this, MovieDetalles.class);
                intent.putExtra("detalles_search", (Parcelable) search);
                startActivity(intent);
            }
        });
    }
}
