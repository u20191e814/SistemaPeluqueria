package com.example.sistemapeluqueria.ui;

import android.media.Rating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.controladores.webServicio;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class CalificarServicioFragment extends Fragment {





    private int Pk_servicio ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Pk_servicio = getArguments().getInt("Pk_servicio");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calificar_servicio, container, false);
        RatingBar rating = v.findViewById(R.id.ratingBarCalificacion);
        TextView comentario = v.findViewById(R.id.txtComentarioCalificacion);
        Button btnEnviarCalificacion = v.findViewById(R.id.btnEnviarCalificacion);

        btnEnviarCalificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rating==null ){
                    Toast.makeText(getContext(), "Debe elegir una calificación ", Toast.LENGTH_SHORT).show();
                    return;
                }
                float ratting= rating.getRating();
                int cantidad = (int)(ratting);
                if (cantidad==0){
                    Toast.makeText(getContext(), "La cantidad no puede ser 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = webServicio.dominio_servicio+ "api/peluqueria/rateService";

                JSONObject jsonobject = new JSONObject();
                try {

                    jsonobject.put("id_service", Pk_servicio);
                    jsonobject.put("calificacion", cantidad);
                    jsonobject.put("comentario", comentario.getText().toString());

                } catch (JSONException e) {
                    Log.i("errorjon======>", e.getMessage());
                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, jsonobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONObject obj = new JSONObject(response.toString());
                                    boolean data = obj.getBoolean("data");
                                    if (data == false){
                                        Toast.makeText(getContext(), "No se pudo registrar la calificación", Toast.LENGTH_LONG).show();
                                    }
                                    if (data ){
                                        Toast.makeText(getContext(), "Se registró la calificación", Toast.LENGTH_LONG).show();
                                        NavController nav = Navigation.findNavController(view);
                                        nav.navigate(R.id.nav_servicioscontratados);
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                                Log.i("error======>", error.getMessage());
                            }
                        }
                );

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(jsonObjReq);


            }
        });

        return  v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}