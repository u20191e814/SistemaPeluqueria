package com.example.sistemapeluqueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegistrarClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
    }
    public void crearCuenta(View v){
        EditText nombre = this.findViewById(R.id.txtNombreRegistrar);
        EditText apellido = this.findViewById(R.id.txtApellidoRegistar) ;
        EditText telefono = this.findViewById(R.id.txtTelefonoRegistrar);
        EditText email = this.findViewById(R.id.txtEmailRegistrar);
        EditText clave = this.findViewById(R.id.txtClaveRegistrar);


        String u = nombre.getText().toString();
        String w = apellido.getText().toString();
        String x = telefono.getText().toString();
        String y = email.getText().toString();
        String z = clave.getText().toString();

        Log.i("===>", u);
        Log.i("===>", w);
        Log.i("===>", x);
        Log.i("===>", y);
        Log.i("===>", z);

        startActivity(new Intent(this, MainActivity.class));
    }
}