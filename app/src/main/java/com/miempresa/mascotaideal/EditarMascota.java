package com.miempresa.mascotaideal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarMascota extends AppCompatActivity {

    EditText etNombre, etEdad, etRaza, etTamano, etCiudad, etLatitud, etLongitud;
    Button editar, btnUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);
        etNombre = findViewById(R.id.etnombrein);
        etEdad = findViewById(R.id.etedadin);
        etCiudad = findViewById(R.id.etciudadin);
        etRaza = findViewById(R.id.etrazain);
        etTamano = findViewById(R.id.ettamanoin);
        editar = findViewById(R.id.button2);
        etLatitud = findViewById(R.id.etlatitud);
        etLongitud = findViewById(R.id.etlongitud);
        btnUbicacion = findViewById(R.id.btnubicacion);

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre");
        String tamano = extras.getString("tamano");
        String raza = extras.getString("raza");
        String ciudad = extras.getString("ciudad");
        double latitud = extras.getDouble("latitud");
        double longitud = extras.getDouble("longitud");
        int edad = extras.getInt("edad");

        etNombre.setText(nombre);
        etRaza.setText(raza);
        etTamano.setText(tamano);
        etCiudad.setText(ciudad);
        etEdad.setText(String.valueOf(edad));
        etLatitud.setText(String.valueOf(latitud));
        etLongitud.setText(String.valueOf(longitud));

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri ubicacion = Uri.parse("geo:" + latitud + "," + longitud);
               // Intent i = new Intent(Intent.ACTION_VIEW, ubicacion);
                //i.setPackage("com.google.android.apps.maps");
                //startActivity(i);
                Intent i = new Intent(EditarMascota.this, MapsActivity.class);
                i.putExtra("latitud", latitud);
                i.putExtra("longitud", longitud);
                i.putExtra("ciudad", ciudad);
                startActivity(i);


            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarMascota();
            }
        });

    }


    public int editarMascota(){
        return 1;
    }
}