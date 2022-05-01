package com.example.sistemapeluqueria.model;

public class subcategoria {
    private int pk_subCategoria;
    private String nombre;
    private  int fk_categoria ;
    private  double precio;

    public subcategoria(int pk_subCategoria, String nombre, int fk_categoria, double precio) {
        this.pk_subCategoria = pk_subCategoria;
        this.nombre = nombre;
        this.fk_categoria = fk_categoria;
        this.precio = precio;
    }

    public int getPk_subCategoria() {
        return pk_subCategoria;
    }

    public void setPk_subCategoria(int pk_subCategoria) {
        this.pk_subCategoria = pk_subCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFk_categoria() {
        return fk_categoria;
    }

    public void setFk_categoria(int fk_categoria) {
        this.fk_categoria = fk_categoria;
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
