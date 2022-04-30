package com.example.sistemapeluqueria.model;

public class ContractedServiceModel {
    private String nombrePersonal;
    private String nombreServicio;
    private int total;
    private String direccion;
    private String fecha;
    private String estado;

    public ContractedServiceModel(String nombrePersonal, String nombreServicio, int total, String direccion, String fecha, String estado) {
        this.nombrePersonal = nombrePersonal;
        this.nombreServicio = nombreServicio;
        this.total = total;
        this.direccion = direccion;
        this.fecha = fecha;
        this.estado = estado;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
