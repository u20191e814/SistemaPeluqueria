package com.example.sistemapeluqueria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarClienteActivity extends AppCompatActivity {

    EditText txtNombreRegistrar,txtApellidoRegistar,txtTelefonoRegistrar,txtEmailRegistrar,txtClaveRegistrar;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        txtNombreRegistrar = findViewById(R.id.txtNombreRegistrar);
        txtApellidoRegistar = findViewById(R.id.txtApellidoRegistar);
        txtTelefonoRegistrar = findViewById(R.id.txtTelefonoRegistrar);
        txtEmailRegistrar = findViewById(R.id.txtEmailRegistrar);
        txtClaveRegistrar = findViewById(R.id.txtClaveRegistrar);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Patrón para validar el email
                Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                // El email a validar
                String nombre = txtNombreRegistrar.getText().toString();
                String apellido = txtApellidoRegistar.getText().toString();
                String telefono = txtTelefonoRegistrar.getText().toString();
                String email = txtEmailRegistrar.getText().toString();
                String clave = txtClaveRegistrar.getText().toString();

                Log.i("email======>", email);
                Matcher mather = pattern.matcher(email);
                if (email.isEmpty()|| nombre.isEmpty()|| apellido.isEmpty()|| telefono.isEmpty()|| clave.isEmpty()) {
                    Log.i("email en blanco======>", email);
                    Toast.makeText(getApplicationContext(), "Todos los campos es requerido.", Toast.LENGTH_LONG).show();

                } else if (mather.find() == true){
                    Log.i("email valido======>", email);
                    createUser("http://3.145.140.134:9090/api/peluqueria/client");
                }else {
                    Toast.makeText(getApplicationContext(), "El email ingresado es inválido.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void createUser(String URL){

        JSONObject jsonobject = new JSONObject();
        try {

            jsonobject.put("nombre", txtNombreRegistrar.getText().toString());
            jsonobject.put("apellido", txtApellidoRegistar.getText().toString());
            jsonobject.put("telefono", txtTelefonoRegistrar.getText().toString());
            jsonobject.put("correo", txtEmailRegistrar.getText().toString());
            jsonobject.put("clave", txtClaveRegistrar.getText().toString());
            Log.i("json======>", jsonobject.toString());
        } catch (JSONException e) {
            Log.i("errorjon======>", e.getMessage());
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL, jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Success Callback
                        Log.i("response======>", response.toString());

                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            Integer data = obj.getInt("data");

                            if (data > 0){
                                Toast.makeText(RegistrarClienteActivity.this, "New Register Successfull", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getBaseContext(), InicioSesionActivity.class));
                                Log.i("status======>", data.toString());
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrarClienteActivity.this, "server error", Toast.LENGTH_SHORT).show();
                        Log.i("error======>", error.getMessage());
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);

    }

}