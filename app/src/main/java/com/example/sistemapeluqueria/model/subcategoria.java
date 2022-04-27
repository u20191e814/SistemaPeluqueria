package com.example.sistemapeluqueria.model;

public class subcategoria {
    private int pk_subcategoria;
    private  String nombre ;
    private int Fk_categoria ;
    private double precio ;

    public subcategoria(int pk_subcategoria, String nombre, int fk_categoria, double precio) {
        this.pk_subcategoria = pk_subcategoria;
        this.nombre = nombre;
        Fk_categoria = fk_categoria;
        this.precio = precio;
    }

    public int getPk_subcategoria() {
        return pk_subcategoria;
    }

    public void setPk_subcategoria(int pk_subcategoria) {
        this.pk_subcategoria = pk_subcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFk_categoria() {
        return Fk_categoria;
    }

    public void setFk_categoria(int fk_categoria) {
        Fk_categoria = fk_categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return   nombre  ;
    }
}
