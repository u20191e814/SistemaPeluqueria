package com.example.sistemapeluqueria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InicioSesionActivity extends AppCompatActivity {

    EditText txtLoginCorreo,txtLoginContraseña;
    Button btnLoginIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        txtLoginCorreo = findViewById(R.id.txtLoginCorreo);
        txtLoginContraseña = findViewById(R.id.txtLoginContraseña);
        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);

        btnLoginIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Patrón para validar el email
                Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                // El email a validar
                String email = txtLoginCorreo.getText().toString();
                Matcher mather = pattern.matcher(email);
                if (mather.find() == true) {
                    System.out.println("El email ingresado es válido.");
                    validarUsuario("http://3.145.140.134:9090/api/peluqueria/login");
                } else {
                    System.out.println("El email ingresado es inválido.");
                }
            }
        });


        TextView login = this.findViewById(R.id.lblRegisterLogin);

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

    private  void validarUsuario(String URL){

        JSONObject jsonobject = new JSONObject();
        try {

            jsonobject.put("correo", txtLoginCorreo.getText().toString());
            jsonobject.put("clave", txtLoginContraseña.getText().toString());
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
                            String data = obj.getString("data");

                            if (data != "null"){
                                Toast.makeText(InicioSesionActivity.this, "Successfull", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                                Log.i("status======>", data);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicioSesionActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        Log.i("error======>", error.getMessage());
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);

    }
}