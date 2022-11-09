package com.miempresa.mascotaideal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("usuarios");

    EditText etNombre, etTelefono, etEmail, etPassword, etPassword2;
    Button btnRegistrarse, btnIrLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etNombre = findViewById(R.id.editTextTextPersonName);
        etTelefono = findViewById(R.id.editTextPhone);
        etEmail = findViewById(R.id.editTextTextEmailAddress);
        etPassword = findViewById(R.id.editTextTextPassword);
        etPassword2 = findViewById(R.id.editTextTextPassword2);
        btnRegistrarse = findViewById(R.id.button5);

        myAuth= FirebaseAuth.getInstance();
        myAuth.signOut();


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = etNombre.getText().toString();
                String email = etEmail.getText().toString();
                long telefono = Long.parseLong(etTelefono.getText().toString());
                String password = etPassword.getText().toString();
                String password2 = etPassword2.getText().toString();

                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setEmail(email);
                u.setTelefono(telefono);
                u.setPassword(password);

                registrar(u);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = myAuth.getCurrentUser();
        if (currentUser != null){
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    public void registrar(Usuario u){


        myAuth.createUserWithEmailAndPassword(u.getEmail(), u.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = myAuth.getCurrentUser();
                            String id = user.getUid();
                            u.setId(id);
                            Log.d("prueba", u.toString());
                            myRef.child(id).setValue(u);
                            actualizarUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Fallo el registro.",
                                    Toast.LENGTH_SHORT).show();
                            actualizarUI(null);
                        }
                    }
                });


    }


    public void actualizarUI(FirebaseUser usuario){

        if(usuario != null){
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(RegisterActivity.this, "Ingrese sud datos de registro", Toast.LENGTH_LONG).show();
        }

    }

}