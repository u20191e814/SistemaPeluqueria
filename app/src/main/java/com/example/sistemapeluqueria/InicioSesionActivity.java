package com.example.sistemapeluqueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InicioSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        TextView login = this.findViewById(R.id.lblloginRegistrate);

        login .setOnClickListener(c-> {
            //Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
            //        .show();
                startActivity(new Intent(this, RegistrarClienteActivity.class));

        });
        //login.callOnClick(v->
        //{
        //    startActivity(new Intent(this, RegistrarClienteActivity.class));

        //});
    }

    public void sesion(View v){
        EditText email = this.findViewById(R.id.txtLoginCorreo);
        EditText clave = this.findViewById(R.id.txtLoginContraseña);

        String x = email.getText().toString();
        String y = clave.getText().toString();

        Log.i("===>", x);
        Log.i("===>", y);

        startActivity(new Intent(this, MainActivity.class));
    }
}