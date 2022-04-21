package com.example.sistemapeluqueria.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.sistemapeluqueria.RegistrarClienteActivity;
import com.example.sistemapeluqueria.adapter.InicioAdapter;
import com.example.sistemapeluqueria.adapter.MostrarEspecialistaAdapter;
import com.example.sistemapeluqueria.controladores.webServicio;
import com.example.sistemapeluqueria.model.CategoryModel;
import com.example.sistemapeluqueria.model.MostrarEspecialistaModel;
import com.example.sistemapeluqueria.model.ubicacionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MostrarEspecialistasFragment extends Fragment
{

    private RecyclerView.Adapter<MostrarEspecialistaAdapter.ViewHolder> adaptador;
    private LinearLayoutManager LayoutManager;
    private   RecyclerView recyclerView;

    private int id_categoria ;
    private String nombre_categoria;
    private List<MostrarEspecialistaModel> listacEspecialistas ;

    private Spinner region ;
    private Spinner provincia;
    private Spinner distrito ;
    private ArrayAdapter<ubicacionModel>  adapterregion;
    private List<ubicacionModel> listaRegion ;

    private ArrayAdapter<ubicacionModel>  adapterprovincia;
    private List<ubicacionModel> listaProvincia ;

    private ArrayAdapter<ubicacionModel>  adapterdistrito;
    private List<ubicacionModel> listaDistrito ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_especialistas, container, false);

        region = v.findViewById(R.id.cboRegionMostrarEspecialistas);
        provincia= v.findViewById(R.id.cboProvinciaMostrarEspecialistas);
        distrito= v.findViewById(R.id.cboDistritoMostrarEspecialistas);
        listaRegion = new ArrayList<>();
       // listaRegion.add(new ubicacionModel(1,"Lima"));
        adapterregion = new ArrayAdapter<>(v.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listaRegion);
        region.setAdapter(adapterregion);



        RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());

        String url = webServicio.dominio_servicio+ "api/peluqueria/getRegion";
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {

            @Override public void onResponse(String response)
            {
                try
                {
                    listaRegion.clear();
                    JSONObject OB = new JSONObject(response);
                    String estado= OB.getString("status");
                    if (!estado.equals("OK")) {
                        Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    JSONArray jsonArray = OB.getJSONArray("data");



                    for (int i = 0; i<jsonArray.length(); i++)
                    {

                        JSONObject object = jsonArray.getJSONObject(i);
                        ubicacionModel reg =  new ubicacionModel(object.getInt("id"), object.getString("nombre"));
                        listaRegion.add(reg);
                        //Log.i("servicio", reg.getNombre());
                    }

                    adapterregion.notifyDataSetChanged();

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

        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  ubicacionModel ub=  (ubicacionModel) adapterView.getSelectedItem();
                  if (ub.getId()==-1){
                      listaProvincia = new ArrayList<>();
                      listaProvincia.add(new ubicacionModel(-1,"Todos"));
                      adapterprovincia = new ArrayAdapter<>(v.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listaProvincia);
                      provincia.setAdapter(adapterprovincia);
                  }
                  else{
                      RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());

                      String url = webServicio.dominio_servicio+ "api/peluqueria/getProvince?id_region="+ub.getId();
                      StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
                      {

                          @Override public void onResponse(String response)
                          {
                              try
                              {
                                  listaProvincia = new ArrayList<>();

                                  JSONObject OB = new JSONObject(response);
                                  String estado= OB.getString("status");
                                  if (!estado.equals("OK")) {
                                      Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                                      toast.show();
                                  }
                                  JSONArray jsonArray = OB.getJSONArray("data");



                                  for (int i = 0; i<jsonArray.length(); i++)
                                  {

                                      JSONObject object = jsonArray.getJSONObject(i);
                                      ubicacionModel reg =  new ubicacionModel(object.getInt("id"), object.getString("nombre"));
                                      listaProvincia.add(reg);

                                  }

                                  adapterprovincia = new ArrayAdapter<>(v.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listaProvincia);
                                  provincia.setAdapter(adapterprovincia);


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
                  }
                  Log.i("log", ub.getNombre() + " "+ ub.getId() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        provincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubicacionModel ub=  (ubicacionModel) adapterView.getSelectedItem();
                if (ub.getId()==-1){
                    listaDistrito = new ArrayList<>();
                    listaDistrito.add(new ubicacionModel(-1,"Todos"));
                    adapterdistrito = new ArrayAdapter<>(v.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listaProvincia);
                    distrito.setAdapter(adapterdistrito);
                }
                else{
                    RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());

                    String url = webServicio.dominio_servicio+ "api/peluqueria/getDistrict?id_province="+ub.getId();
                    StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
                    {

                        @Override public void onResponse(String response)
                        {
                            try
                            {
                                listaDistrito = new ArrayList<>();

                                JSONObject OB = new JSONObject(response);
                                String estado= OB.getString("status");
                                if (!estado.equals("OK")) {
                                    Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                JSONArray jsonArray = OB.getJSONArray("data");



                                for (int i = 0; i<jsonArray.length(); i++)
                                {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    ubicacionModel reg =  new ubicacionModel(object.getInt("id"), object.getString("nombre"));
                                    listaDistrito.add(reg);

                                }

                                adapterdistrito = new ArrayAdapter<>(v.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listaDistrito);
                                distrito.setAdapter(adapterdistrito);


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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listacEspecialistas = new ArrayList<>();
        listacEspecialistas.add(new MostrarEspecialistaModel(1, "Luisa Ramirez",100, "Av. Javier prado 400", -12.4000, -10.300, 2,1));
        listacEspecialistas.add(new MostrarEspecialistaModel(2, "Martha Perez",100, "Av. via expresa ", -12.4000, -10.300, 3,1));
        adaptador = new MostrarEspecialistaAdapter(listacEspecialistas);
        recyclerView = v.findViewById(R.id.recycleMostrarEspecialistas);

        LayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(LayoutManager);

        recyclerView.setAdapter(adaptador);

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            nombre_categoria = getArguments().getString("nombre");
            id_categoria= getArguments().getInt("id_categoria");
        }
    }
}