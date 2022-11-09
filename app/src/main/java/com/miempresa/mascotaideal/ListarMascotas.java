package com.miempresa.mascotaideal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarMascotas extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("mascotas");
    RecyclerView reciclerMacotas;
    AdaptadorMascotas adaptadorMascotas;
    List<Mascota> listaMascotas;
    ArrayList<Mascota> mascotas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mascotas);
        reciclerMacotas = findViewById(R.id.reciclermascotas);
        listaMascotas = new ArrayList<>();
        myRef.addChildEventListener(childEventListener);
        adaptadorMascotas = new AdaptadorMascotas(listaMascotas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        reciclerMacotas.setLayoutManager(mLayoutManager);
        reciclerMacotas.setItemAnimator(new DefaultItemAnimator());
        reciclerMacotas.setAdapter(adaptadorMascotas);
        refrescarLista();

        reciclerMacotas.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), reciclerMacotas, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(ListarMascotas.this)
                        .setPositiveButton("Si",  (dialog ,which)->{
                            Mascota m = listaMascotas.get(position);
                            Intent i = new Intent(ListarMascotas.this, EditarMascota.class);
                            i.putExtra("id", m.getId());
                            i.putExtra("nombre", m.getNombre());
                            i.putExtra("edad", m.getEdad());
                            i.putExtra("ciudad", m.getCiudad());
                            i.putExtra("raza", m.getRaza());
                            i.putExtra("tamano", m.getTamano());
                            startActivity(i);

                        } )
                        .setNegativeButton("No",  (dialog ,which)->{
                            dialog.dismiss();
                        })
                        .setTitle("Prueba click")
                        .setMessage("prueba click corto")
                        .create();
                dialogo.show();
            }

            @Override
            public void onLongClick(View view, int position) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(ListarMascotas.this)
                        .setPositiveButton("Si",  (dialog ,which)->{
                            //Agregar metodo borrar al controlador
                            Mascota m = listaMascotas.get(position);
                            String nombre = m.getNombre();
                            //Log.d("prueba", ""+ m.getId());
                            delete(position);
                            borrarMascota(m);
                            refrescarLista();

                            Toast.makeText(getApplicationContext(), "La mascota se elimino con exito", Toast.LENGTH_LONG).show();


                        } )
                        .setNegativeButton("No",  (dialog ,which)->{
                            dialog.dismiss();
                        })
                        .setTitle("Confirmacion para eliminar")
                        .setMessage("Desea eliminar la mascota")
                        .create();
                dialogo.show();

            }
        }));

    }


    private void delete(int position) {
        // creating a variable for our Database
        // Reference for Firebase.
        //DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("DataValue");
        // we are use add listerner
        // for event listener method
        // which is called with query.
        final String nombre=listaMascotas.get(position).getNombre();
        Query applesQuery = myRef.child(nombre);



        //sQuery query = myRef.child(name);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // remove the value at reference
                dataSnapshot.getRef().removeValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refrescarLista();
    }

    public void borrarMascota(Mascota m){
        mascotas.remove(m);
    }

    public void agregarMascota(Mascota m){
        mascotas.add(m);
        refrescarLista();
    }

    public void refrescarLista(){
        if(adaptadorMascotas == null) return;
        listaMascotas = mascotas;
        adaptadorMascotas.setListaMascotas(listaMascotas);
        adaptadorMascotas.notifyDataSetChanged();


    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Mascota m = snapshot.getValue(Mascota.class);
            if(m!= null) agregarMascota(m);

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Mascota m = snapshot.getValue(Mascota.class);
            if(m!= null) agregarMascota(m);

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            String mascotaid = snapshot.getKey();
            mascotas.remove(snapshot);
            refrescarLista();

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            Mascota m = snapshot.getValue(Mascota.class);
            if(m!= null) agregarMascota(m);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



}