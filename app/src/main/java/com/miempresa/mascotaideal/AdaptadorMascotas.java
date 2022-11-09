package com.miempresa.mascotaideal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.CardMascota>{

    private List<Mascota> listaMascotas;

    public AdaptadorMascotas(List<Mascota> listaMascotas){
        this.listaMascotas = listaMascotas;
    }

    public void setListaMascotas(List<Mascota> listaMascotas){
        this.listaMascotas=listaMascotas;
    }

    @NonNull
    @Override
    public CardMascota onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.mascota,parent,false);
        return new CardMascota(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CardMascota holder, int position) {

        Mascota m = listaMascotas.get(position);
        String nombre = m.getNombre();
        String raza = m.getRaza();
        String tamano = m.getTamano();
        String ciudad = m.getCiudad();
        int edad = m.getEdad();

        holder.twnombre.setText(nombre);
        holder.twraza.setText(raza);
        holder.twtamano.setText(tamano);
        holder.twciudad.setText(ciudad);
        holder.twedad.setText(String.valueOf(edad));

            }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    class CardMascota extends RecyclerView.ViewHolder{
        TextView twnombre, twraza, twedad, twtamano, twciudad;

        public CardMascota(@NonNull View itemView) {
            super(itemView);
            this.twnombre = itemView.findViewById(R.id.etnombre);
            this.twraza = itemView.findViewById(R.id.etraza);
            this.twedad = itemView.findViewById(R.id.etedad);
            this.twtamano = itemView.findViewById(R.id.ettamano);
            this.twciudad = itemView.findViewById(R.id.etciudad);

        }
    }

}
