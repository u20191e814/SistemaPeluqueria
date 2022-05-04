package com.example.sistemapeluqueria.model;

public class ContractedServiceModel {
    private int pk_ContratarServicio;
    private String nombrePersonal;
    private String nombreServicio;
    private double total;
    private String direccion;
    private String fecha;
    private String hora;
    private String estado;
    private double precio;
    private int cantidad;
    private int calificacion;

    public ContractedServiceModel(int pk_ContratarServicio, String nombrePersonal, String nombreServicio, double total, String direccion, String fecha, String hora, String estado,double precio,int cantidad,int calificacion) {
        this.pk_ContratarServicio = pk_ContratarServicio;
        this.nombrePersonal = nombrePersonal;
        this.nombreServicio = nombreServicio;
        this.total = total;
        this.direccion = direccion;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.precio = precio;
        this.cantidad = cantidad;
        this.calificacion= calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getPk_ContratarServicio() {
        return pk_ContratarServicio;
    }

    public void setPk_ContratarServicio(int pk_ContratarServicio) {
        this.pk_ContratarServicio = pk_ContratarServicio;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
