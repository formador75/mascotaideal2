package com.miempresa.mascotaideal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarMascota extends AppCompatActivity {

    EditText etNombre, etEdad, etRaza, etTamano, etCiudad;
    Button editar;
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

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre");
        String tamano = extras.getString("tamano");
        String raza = extras.getString("raza");
        String ciudad = extras.getString("ciudad");
        int edad = extras.getInt("edad");

        etNombre.setText(nombre);
        etRaza.setText(raza);
        etTamano.setText(tamano);
        etCiudad.setText(ciudad);
        etEdad.setText(String.valueOf(edad));


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