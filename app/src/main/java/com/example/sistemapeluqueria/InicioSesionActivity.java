package com.example.sistemapeluqueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class InicioSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
    }
    public void sesion(View v){
        EditText email = this.findViewById(R.id.editTextTextEmailAddress);
        EditText clave = this.findViewById(R.id.editTextTextPassword);

        String x = email.getText().toString();
        String y = clave.getText().toString();

        Log.i("===>", x);
        Log.i("===>", y);

        startActivity(new Intent(this, MainActivity.class));
    }
}