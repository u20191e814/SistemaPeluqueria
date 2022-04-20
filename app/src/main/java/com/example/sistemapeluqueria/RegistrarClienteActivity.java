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
import com.example.sistemapeluqueria.model.RegisterModel;

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

                RegisterModel userRegister = new RegisterModel(
                        txtNombreRegistrar.getText().toString(),
                        txtApellidoRegistar.getText().toString(),
                        txtTelefonoRegistrar.getText().toString(),
                        txtEmailRegistrar.getText().toString(),
                        txtClaveRegistrar.getText().toString()
                );

                if (userRegister.isEmpty()) {
                    Toast.makeText(getApplicationContext(), userRegister.getMessageValidation(), Toast.LENGTH_LONG).show();

                }else if (userRegister.validatePhone()){
                    Toast.makeText(getApplicationContext(), userRegister.getMessageValidation(), Toast.LENGTH_LONG).show();
                }else if (userRegister.validateEmail()){
                    createUser(userRegister);
                }else {
                    Toast.makeText(getApplicationContext(), userRegister.getMessageValidation(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void createUser(RegisterModel userRegister){

        //conexion al servicio
        String url = "http://3.145.140.134:9090/api/peluqueria/client";

        JSONObject jsonobject = new JSONObject();
        try {

            jsonobject.put("nombre", userRegister.getNombre());
            jsonobject.put("apellido", userRegister.getApellido());
            jsonobject.put("telefono", userRegister.getTelefono());
            jsonobject.put("correo", userRegister.getEmail());
            jsonobject.put("clave", userRegister.getClave());
            Log.i("json======>", jsonobject.toString());
        } catch (JSONException e) {
            Log.i("errorjon======>", e.getMessage());
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Success Callback
                        Log.i("response======>", response.toString());

                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            Integer data = obj.getInt("data");
                            if (data == 0){
                                Toast.makeText(RegistrarClienteActivity.this, "El correo registrado ya existe", Toast.LENGTH_LONG).show();
                            }
                            if (data > 0){
                                Toast.makeText(RegistrarClienteActivity.this, "New Register Successfull", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getBaseContext(), InicioSesionActivity.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrarClienteActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        Log.i("error======>", error.getMessage());
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);

    }

}