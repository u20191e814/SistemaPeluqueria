package com.example.sistemapeluqueria.model;

public class MostrarEspecialistaModel {
    private  String nombre;
    private  String direccion;
    private  int Latitud;
    private  int Longitud;
    private  int Calificacion;
    private  int fk_categoria;
    private  int imagen;

public MostrarEspecialistaModel(String nombre,int imagen,String Direccion,int latitud,int Longitud,int Calificacion,int fk_categoria){
    this.nombre = nombre;
    this.direccion=direccion;
    this.Latitud=latitud;
    this.Longitud=Longitud;
    this.Calificacion=Calificacion;
    this.fk_categoria=fk_categoria;
    this.imagen=imagen;

}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String nombre) {
        this.direccion = direccion;
    }

    public int getlatitud() {
        return Latitud;
    }

    public void setLatitud(int latitud) {
        this.Latitud = latitud;
    }

    public int getLongitud() {
        return Longitud;
    }

    public void setLongitud(int longitud) {
        this.Longitud = Longitud;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.Calificacion = calificacion;
    }

    public int getFk_categoria() {
        return fk_categoria;
    }

    public void setFk_categoria(int fk_categoria) {
        this.fk_categoria = fk_categoria;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
