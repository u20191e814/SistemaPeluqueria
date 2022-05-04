 package com.example.sistemapeluqueria.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.controladores.webServicio;

import org.json.JSONException;
import org.json.JSONObject;


 public class ActualizarServicioFragment extends Fragment {
    private String nombrePersonal ;
    private String nombreServicio ;
    private String direccion ;
    private String estado ;
    private String fecha ;
    private String hora ;
    private int pk_ContratarServicio;
    private double precio;
    private double total;
    private int cantidad;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombrePersonal = getArguments().getString("nombrePersonal");
            nombreServicio = getArguments().getString("nombreServicio");
            direccion = getArguments().getString("direccion");
            estado = getArguments().getString("estado");
            fecha = getArguments().getString("fecha");
            hora = getArguments().getString("hora");
            pk_ContratarServicio = getArguments().getInt("pk_ContratarServicio");
            precio = getArguments().getDouble("precio");
            total = getArguments().getDouble("total");
            cantidad = getArguments().getInt("cantidad");
            Log.i("pk_ContratarServicio",pk_ContratarServicio+"");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View r = inflater.inflate(R.layout.fragment_actualizar_servicio, container, false);

        TextView txtNombreEspecialistaCancelar = r.findViewById(R.id.txtNombreEspecialistaCancelar);
        EditText editNombreServicioCancelar = r.findViewById(R.id.editNombreServicioCancelar);
        EditText editPrecioCancelar = r.findViewById(R.id.editPrecioCancelar);
        EditText editCantidadCancelar = r.findViewById(R.id.editCantidadCancelar);
        EditText editTotalCancelar = r.findViewById(R.id.editTotalCancelar);
        EditText editFechaCancelar = r.findViewById(R.id.editFechaCancelar);
        EditText editHoraCancelar = r.findViewById(R.id.editHoraCancelar);
        EditText editDireccionCancelar = r.findViewById(R.id.editDireccionCancelar);
        EditText editEstadoCancelar = r.findViewById(R.id.editEstadoCancelar);
        Button btnCancelarServicio = r.findViewById(R.id.btnCancelarServicio);
        Button btncompletarServicio = r.findViewById(R.id.btnCompletarServicio);
        txtNombreEspecialistaCancelar.setText(nombrePersonal);
        editNombreServicioCancelar.setText(nombreServicio);
        editPrecioCancelar.setText(precio+"");
        editCantidadCancelar.setText(cantidad+"");
        editTotalCancelar.setText(total+"");
        editFechaCancelar.setText(fecha);
        editHoraCancelar.setText(hora);
        editDireccionCancelar.setText(direccion);
        editEstadoCancelar.setText(estado);

        //Evento click button
        btnCancelarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = webServicio.dominio_servicio + "api/peluqueria/updateService?id_service="+pk_ContratarServicio+ "&estado=Cancelado";
                StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Success Callback
                                Log.i("actualizar servicio", response.toString());

                                try {
                                    JSONObject obj = new JSONObject(response.toString());
                                    boolean data = obj.getBoolean("data");
                                    if (data == false){
                                        Toast.makeText(getContext(), "No se pudo cancelar el servicio", Toast.LENGTH_LONG).show();
                                    }
                                    if (data ){
                                        Toast.makeText(getContext(), "Se canceló correctamente", Toast.LENGTH_LONG).show();
                                        NavController nav = Navigation.findNavController(view);
                                        nav.popBackStack();}


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
        btncompletarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = webServicio.dominio_servicio + "api/peluqueria/updateService?id_service="+pk_ContratarServicio+ "&estado=Completado";
                StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject obj = new JSONObject(response.toString());
                                    boolean data = obj.getBoolean("data");
                                    if (data == false){
                                        Toast.makeText(getContext(), "No se pudo completar el servicio", Toast.LENGTH_LONG).show();
                                    }
                                    if (data ){
                                        Toast.makeText(getContext(), "Se completó correctamente", Toast.LENGTH_LONG).show();
                                        NavController nav = Navigation.findNavController(view);
                                        Bundle b = new Bundle();
                                        b.putInt("Pk_servicio", pk_ContratarServicio);
                                        nav.navigate(R.id.navCalificarServicio,b);
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


        return  r;
    }
     @Override
     public void onDestroyView() {
         super.onDestroyView();
     }
}