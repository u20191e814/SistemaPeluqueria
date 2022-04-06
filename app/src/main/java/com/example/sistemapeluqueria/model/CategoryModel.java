package com.example.sistemapeluqueria.model;

public class CategoryModel {
    private  String nombre;
    private  int rutaImagen;

    public CategoryModel(String nombre, int rutaImagen) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(int rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
