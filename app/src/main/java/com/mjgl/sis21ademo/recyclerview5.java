package com.mjgl.sis21ademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class recyclerview5 extends AppCompatActivity {
    private RecyclerView rv;
    private AdaptadorArticulos5 adaptadorArticulos5;
    ConexionSQLite datos = new ConexionSQLite(recyclerview5.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview5);

        rv = (RecyclerView)findViewById(R.id.review);
        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        rv.setHasFixedSize(true);
        // Nuestro RecyclerView usará un linear layout manager
        rv.setLayoutManager(new LinearLayoutManager(this));
        //adaptadorArticulos = new AdaptadorArticulos(consulta_recyclerView.this,obtenerArticulos());
        adaptadorArticulos5 = new AdaptadorArticulos5(recyclerview5.this,datos.mostrarArticulos());

        rv.setAdapter(adaptadorArticulos5);

    }
}