package com.example.sistemapeluqueria.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginModel {
    private String correo;
    private String clave;

    // Patrón para validar el email
    private Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");



    public LoginModel(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEmpty(){
        if (this.correo.isEmpty()||this.clave.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public  boolean validateEmail(){

        Matcher mather = this.pattern.matcher(this.correo);

        if (mather.find()== true){
            return true;
        }else {
            return false;
        }
    }

}
