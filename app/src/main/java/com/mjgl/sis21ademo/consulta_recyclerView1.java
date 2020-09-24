package com.mjgl.sis21ademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class consulta_recyclerView1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdaptadorPersonas adaptadorPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view1);

        recyclerView = findViewById(R.id.rview1);

        //Funcion para mejorar el desempeño
        recyclerView.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);

        adaptadorPersonas = new AdaptadorPersonas(obtenerPersonas());
        recyclerView.setAdapter(adaptadorPersonas);

    }


    private List<person> obtenerPersonas() {
        List<person> persons= new ArrayList<>();
        persons.add(new person("Manuel Gamez", "35 years old", R.drawable.computadora2));
        persons.add(new person("Daniel Gamez", "4 years old", R.drawable.computadora3));
        persons.add(new person("Alejandra Gamez", "8 years old", R.drawable.computadora1));
        return persons;
    }



}