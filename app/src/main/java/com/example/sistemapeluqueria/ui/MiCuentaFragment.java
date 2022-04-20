package com.example.sistemapeluqueria.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.sistemapeluqueria.InicioSesionActivity;
import com.example.sistemapeluqueria.MainActivity;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.controladores.webServicio;
import com.example.sistemapeluqueria.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MiCuentaFragment extends Fragment {

    public int id_cliente ;
public View v;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity lectura = (MainActivity)getActivity();
        String dta= lectura.data_Login;
         v =inflater.inflate(R.layout.fragment_mi_cuenta, container, false    );
        Button actualizar = v.findViewById(R.id.btnActualizarCuenta);
        Button eliminar = v.findViewById(R.id.btnEliminarCuenta);
        actualizar.setOnClickListener(view -> {

            EditText nombre = v.findViewById(R.id.txtNombreMiCuenta);

            EditText apellido = v.findViewById(R.id.txtApellidoMiCuenta);

            EditText telefono = v.findViewById(R.id.txtTelefonoMiCuenta);

            EditText email = v.findViewById(R.id.txtEmailMiCuenta);

            EditText clave = v.findViewById(R.id.txtClaveMiCuenta);


            if (nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() || telefono.getText().toString().isEmpty() || email.getText().toString().isEmpty() || clave.getText().toString().isEmpty() ){
                Toast.makeText(view.getContext(), "Los campos son obligatorios", Toast.LENGTH_LONG).show();
            }
            else{
                JSONObject jsonobject = new JSONObject();
                try {

                    jsonobject.put("id_cliente", id_cliente);
                    jsonobject.put("nombre", nombre.getText().toString());
                    jsonobject.put("apellido", apellido.getText().toString());
                    jsonobject.put("correo", email.getText().toString());
                    jsonobject.put("telefono", telefono.getText().toString());
                    jsonobject.put("clave", clave.getText().toString());

                } catch (JSONException e) {
                    Log.i("Json error ======>", e.getMessage());
                }

                String ruta = webServicio.dominio_servicio+"api/peluqueria/updateClient";
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,ruta, jsonobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONObject obj = new JSONObject(response.toString());
                                    String estado= obj.getString("status");
                                    if (!estado.equals("OK")) {
                                        Toast toast = Toast.makeText(getContext(), obj.getString("statusMessage"), Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                    else{
                                        Intent intent =new Intent(getContext(), InicioSesionActivity.class);
                                        startActivity(intent);
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Actualizar perfil", Toast.LENGTH_SHORT).show();
                                Log.i("error======>", error.getMessage());
                            }
                        }
                );

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(jsonObjReq);
            }
        });
        eliminar.setOnClickListener(view -> {
            new AlertDialog.Builder(getContext()).setTitle("¿Desea eliminar su cuenta?")

                    .setPositiveButton("SI",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());


                                    String ruta = webServicio.dominio_servicio+"api/peluqueria/removeClient?id_cliente="+id_cliente;
                                    StringRequest stringRequest= new StringRequest(Request.Method.DELETE, ruta, new Response.Listener<String>()
                                    {

                                        @Override public void onResponse(String response)
                                        {
                                            try
                                            {

                                                JSONObject OB = new JSONObject(response);
                                                String estado= OB.getString("status");
                                                if (!estado.equals("OK")) {
                                                    Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                                                    toast.show();
                                                }
                                                else {
                                                    Intent intent =new Intent(getContext(), InicioSesionActivity.class);
                                                    startActivity(intent);
                                                }


                                            }
                                            catch (JSONException e)
                                            {
                                                Log.i("======> e", e.getMessage());
                                            }
                                        }
                                    }, new Response.ErrorListener()
                                    {
                                        @Override public void onErrorResponse(VolleyError error)
                                        {
                                            Log.i("====> e", error.toString());
                                        }
                                    } );

                                    requestQueue.add(stringRequest);
                                    dialog.dismiss();
                                }
                            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                            Log.i("Nio","No");
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();

        });
        try {

            JSONObject obj = new JSONObject(dta);
            EditText nombre = v.findViewById(R.id.txtNombreMiCuenta);
            nombre.setText(obj.get("nombre").toString());
            EditText apellido = v.findViewById(R.id.txtApellidoMiCuenta);
            apellido.setText(obj.get("apellido").toString());
            EditText telefono = v.findViewById(R.id.txtTelefonoMiCuenta);
            telefono.setText(obj.get("telefono").toString());
            EditText email = v.findViewById(R.id.txtEmailMiCuenta);
            email.setText(obj.get("correo").toString());
            EditText clave = v.findViewById(R.id.txtClaveMiCuenta);
            clave.setText(obj.get("clave").toString());
            id_cliente= obj.getInt("id_cliente");

        } catch (Throwable tx) {
            Log.e("Mi cuenta", "Error en parsear json ");
        }
        return  v;
    }

    public void click_ActualizarCuenta(View v){
        Toast.makeText(v.getContext(), "Actualizar", Toast.LENGTH_SHORT).show();
    }

    public void click_EliminarCuenta(View v){
        Toast.makeText(v.getContext(), "Eliminar", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}