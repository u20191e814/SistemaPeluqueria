package com.example.sistemapeluqueria.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.controladores.webServicio;
import com.example.sistemapeluqueria.model.subcategoria;
import com.example.sistemapeluqueria.model.ubicacionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Contratar_ServicioFragmento extends Fragment {


    private String nombre ;
    private int fk_categoria;
    private int pk_personal;
    private Spinner cbosubcategoria;
    private List<subcategoria> listasubcategoria ;
    private ArrayAdapter<subcategoria>  adapterSubCategoria;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString("nombre");
            fk_categoria = getArguments().getInt("fk_categoria");
            pk_personal= getArguments().getInt("pk_personal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View r = inflater.inflate(R.layout.fragment_contratar__servicio, container, false);
        TextView nombrePersonal = r.findViewById(R.id.txtNombrePersonalContratarServicio);
        cbosubcategoria= r.findViewById(R.id.cboSubCategoria);
        listasubcategoria = new ArrayList<>();

        adapterSubCategoria = new ArrayAdapter<>(r.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listasubcategoria);
        cbosubcategoria.setAdapter(adapterSubCategoria);
        RequestQueue requestQueue= Volley.newRequestQueue(r.getContext());

        String url = webServicio.dominio_servicio+ "api/peluqueria/getsubCategoria?fk_categoria="+fk_categoria;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {

            @Override public void onResponse(String response)
            {
                try
                {
                    listasubcategoria.clear();
                    JSONObject OB = new JSONObject(response);
                    String estado= OB.getString("status");
                    if (!estado.equals("OK")) {
                        Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    JSONArray jsonArray = OB.getJSONArray("data");

                    String data = OB.getString("data");
                    for (int i = 0; i<jsonArray.length(); i++)
                    {

                        JSONObject object = jsonArray.getJSONObject(i);
                        subcategoria reg =  new subcategoria(object.getInt("pk_subCategoria"), object.getString("nombre"), object.getInt("fk_categoria"), object.getDouble("precio"));
                        listasubcategoria.add(reg);

                    }

                    adapterSubCategoria.notifyDataSetChanged();

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


        nombrePersonal.setText(nombre);

        return  r;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}