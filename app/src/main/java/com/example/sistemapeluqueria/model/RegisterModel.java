package com.example.sistemapeluqueria.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterModel {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String clave;

    private String messageValidation;

    // Patrón para validar el email
    private Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public RegisterModel(String nombre, String apellido, String telefono, String email, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMessageValidation() {
        return messageValidation;
    }

    public void setMessageValidation(String messageValidation) {
        this.messageValidation = messageValidation;
    }

    public boolean isEmpty(){
        if (this.nombre.isEmpty()&&this.apellido.isEmpty()&&this.telefono.isEmpty()&&this.email.isEmpty()&&this.clave.isEmpty()){
            setMessageValidation("Todos los campos son requeridos");
            return true;
        }else if (this.nombre.isEmpty()){
            setMessageValidation("Nombre es requerido");
            return true;
        }else if (this.apellido.isEmpty()){
            setMessageValidation("Apellido es requerido");
            return true;
        }else if (this.telefono.isEmpty()){
            setMessageValidation("Teléfono es requerido");
            return true;
        }else if (this.email.isEmpty()){
            setMessageValidation("Correo es requerido");
            return true;
        }else if (this.clave.isEmpty()){
            setMessageValidation("Contraseña es requerido");
            return true;
        }
        return false;
    }

    public boolean validatePhone(){
        if (this.telefono.length()!=9){
            setMessageValidation("Ingrese un número válido.");
            return true;
        }
        return false;
    }

    public  boolean validateEmail(){

        Matcher mather = this.pattern.matcher(this.email);

        if (mather.find()== true){
            return true;
        }else {
            setMessageValidation("El email ingresado es inválido.");
            return false;
        }
    }

}
