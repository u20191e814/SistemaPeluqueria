package com.example.sistemapeluqueria.model;

public class CategoryModel {
    private int Id_categoria;
    private  String nombre;
    private  int rutaImagen;

    public CategoryModel( int Id_categoria,   String nombre, int rutaImagen) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.Id_categoria = Id_categoria;
    }

    public int getId_categoria() {
        return Id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        Id_categoria = id_categoria;
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
