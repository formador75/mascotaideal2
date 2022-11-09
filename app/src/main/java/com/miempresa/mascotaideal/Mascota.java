package com.miempresa.mascotaideal;

public class Mascota {

    private int id;
    private String nombre;
    private int edad;
    private String raza;
    private String tamano;
    private int usuario;
    private double latData;
    private double lonData;
    private String ciudad;

    public Mascota() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public double getLatData() {
        return latData;
    }

    public void setLatData(double latData) {
        this.latData = latData;
    }

    public double getLonData() {
        return lonData;
    }

    public void setLonData(double lonData) {
        this.lonData = lonData;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", raza='" + raza + '\'' +
                ", tamano='" + tamano + '\'' +
                ", usuario=" + usuario +
                ", latData=" + latData +
                ", lonData=" + lonData +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
