package com.example.sistemapeluqueria.model;

public class ubicacionModel {
    private  int Id;
    private String nombre ;

    public ubicacionModel(int id, String nombre) {
        Id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
