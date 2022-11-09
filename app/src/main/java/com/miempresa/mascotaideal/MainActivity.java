package com.miempresa.mascotaideal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonListarMacotas, agregarMascota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonListarMacotas = findViewById(R.id.btnlistarmascotas);
        agregarMascota = findViewById(R.id.button3);

        botonListarMacotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, ListarMascotas.class);
                startActivity(i);

            }
        });

        agregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AgregarMascota.class);
                startActivity(i);
            }
        });


    }
}