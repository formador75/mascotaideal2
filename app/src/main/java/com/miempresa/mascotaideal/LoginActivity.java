package com.miempresa.mascotaideal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnIngresar, btnIrRegistro;
    private FirebaseAuth miAutenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        btnIngresar = findViewById(R.id.btningresar);
        btnIrRegistro = findViewById(R.id.btnirregistro);
        miAutenticacion = FirebaseAuth.getInstance();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //codigo para ingresar
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                ingresar(email, password);

            }
        });

        btnIrRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
        actualizarUI(usuarioActual);
    }

    public void actualizarUI(FirebaseUser usuario){

        if(usuario != null){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(LoginActivity.this, "Ingrese sus datos de acceso", Toast.LENGTH_LONG).show();

        }

    }

    public void ingresar(String email, String password){

        miAutenticacion.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "El ingreso ha sido exitoso", Toast.LENGTH_LONG).show();
                            FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
                            actualizarUI(usuarioActual);

                        }else{
                            Toast.makeText(LoginActivity.this, "Los datos son incorrecotos", Toast.LENGTH_LONG).show();
                        }



                    }
                });


    }



}