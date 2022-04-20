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
import com.example.sistemapeluqueria.model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;

public class InicioSesionActivity extends AppCompatActivity {

    //Crear variables
    EditText txtLoginCorreo,txtLoginContrasenia;
    Button btnLoginIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        //Referencia variables
        txtLoginCorreo = findViewById(R.id.txtLoginCorreo);
        txtLoginContrasenia = findViewById(R.id.txtLoginContrasenia);
        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);


        //Evento click button
        btnLoginIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instaciar LoginModel
                LoginModel userLogin = new LoginModel(txtLoginCorreo.getText().toString(), txtLoginContrasenia.getText().toString());

                if (userLogin.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña requeridos", Toast.LENGTH_LONG).show();
                }
                else  if (userLogin.validateEmail()) {
                    validarUsuario( userLogin);
                } else {
                    Toast.makeText(getApplicationContext(), "El email ingresado es inválido", Toast.LENGTH_LONG).show();
                }
            }
        });

        TextView linkRegister = this.findViewById(R.id.lblRegisterLogin);

        linkRegister .setOnClickListener(c-> {
                startActivity(new Intent(this, RegistrarClienteActivity.class));
        });
    }

    private  void validarUsuario(LoginModel userLogin){

        String url = "http://3.145.140.134:9090/api/peluqueria/login";

        //Instancia JSONObject
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("correo", userLogin.getCorreo());
            jsonobject.put("clave", userLogin.getClave());
        } catch (JSONException e) {
            Log.i("Errorjon======>", e.getMessage());
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            String data = obj.getString("data");

                            if (data != "null"){
                                JSONObject dataRes = obj.getJSONObject("data");
                                Toast.makeText(InicioSesionActivity.this, "Bienvenid@ "+dataRes.getString("nombre"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                                //Clear login
                                txtLoginCorreo.setText("");
                                txtLoginContrasenia.setText("");
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