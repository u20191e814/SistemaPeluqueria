package com.example.sistemapeluqueria.model;

public class MostrarEspecialistaModel {
    private int id_especialista;
    private  String nombre;
    private  String direccion;
    private  double Latitud;
    private  double  Longitud;
    private  int Calificacion;
    private  int fk_categoria;
    private  int imagen;

public MostrarEspecialistaModel(int id_especialista, String nombre,int imagen,String direccion,double latitud,double Longitud,int Calificacion,int fk_categoria){
    this.id_especialista=id_especialista;
    this.nombre = nombre;
    this.direccion=direccion;
    this.Latitud=latitud;
    this.Longitud=Longitud;
    this.Calificacion=Calificacion;
    this.fk_categoria=fk_categoria;
    this.imagen=imagen;

}

    public int getId_especialista() {
        return id_especialista;
    }

    public void setId_especialista(int id_especialista) {
        this.id_especialista = id_especialista;
    }

    public double getLatitud() {
        return Latitud;
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

    public double getlatitud() {
        return Latitud;
    }

    public void setLatitud(int latitud) {
        this.Latitud = latitud;
    }

    public double getLongitud() {
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
