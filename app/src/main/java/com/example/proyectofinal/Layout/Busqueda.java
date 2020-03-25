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
import android.widget.Toast;

import com.example.proyectofinal.API.FetchMoviesSearch;
import com.example.proyectofinal.Model.SearchMovie;
import com.example.proyectofinal.MovieDetalles;
import com.example.proyectofinal.R;

public class Busqueda extends AppCompatActivity {

    private SearchView searchView;
    private GridView gridView;
    private ProgressBar indeterminateBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        searchView = findViewById(R.id.buscadorchingon);
        gridView = findViewById(R.id.gridView_Buscar);
        indeterminateBar1 = findViewById(R.id.indeterminateBar1);

        indeterminateBar1.setVisibility(View.INVISIBLE);
        gridView.setVisibility(View.INVISIBLE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!s.isEmpty()){
                    FetchMoviesSearch FM = new FetchMoviesSearch(Busqueda.this, indeterminateBar1,gridView,s);
                    FM.execute();
                }
                else {
                    Toast.makeText(getApplicationContext(), "El campo no puede estar Vacio!",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SearchMovie searchMovie = (SearchMovie) adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(Busqueda.this, MovieDetalles.class);
                intent.putExtra("detalles_search", (Parcelable) searchMovie);
                startActivity(intent);
            }
        });
    }
}
