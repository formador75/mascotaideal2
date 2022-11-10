package com.miempresa.mascotaideal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

//                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                Log.d("prueba","prueba test");
//                startActivity(mapIntent);

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