package com.example.sistemapeluqueria.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.adapter.MostrarServicioContratadoAdapter;
import com.example.sistemapeluqueria.controladores.webServicio;
import com.example.sistemapeluqueria.model.ContractedServiceModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MostrarServicioContratadoFragment extends Fragment {

    NavController navController;

    private List<ContractedServiceModel>listaServicioContratado;
    private RecyclerView.Adapter<MostrarServicioContratadoAdapter.ViewHolder> adaptador;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_servicio_contratado, container, false);
        listaServicioContratado = new ArrayList<>();
        //listaServicioContratado.add(new ContractedServiceModel("Jose", "Peluqueria", 100, "jajajja", "2022-12-08","Iniciado"));
        adaptador = new MostrarServicioContratadoAdapter(listaServicioContratado);
        recyclerView = v.findViewById(R.id.recycleMostrarServicioContratado);

        layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adaptador);

        Log.i("recyclerView2","");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        String dataLogin;
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras == null) {
            dataLogin= null;
        } else {
            dataLogin= extras.getString("data");
        }

        JSONObject obj = null;
        String id = "";
        try {
            obj = new JSONObject(dataLogin);
            id = obj.getString("id_cliente");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = webServicio.dominio_servicio+ "api/peluqueria/servicio?id_cliente="+id;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {

            @Override public void onResponse(String response)
            {
                try
                {
                    listaServicioContratado.clear();
                    JSONObject OB = new JSONObject(response);

                    String estado= OB.getString("status");
                    if (!estado.equals("OK")) {
                        Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_LONG);
                        toast.show();
                    }
                    JSONArray jsonArray = OB.getJSONArray("data");
                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.i("object ==> ",object.toString());
                        ContractedServiceModel servicioContratado =  new ContractedServiceModel(object.getString("nombrePersonal"), object.getString("nombreServicio"), object.getInt("total"), object.getString("direccion"), object.getString("fecha"), object.getString("estado"));
                        listaServicioContratado.add(servicioContratado);
                    }

                    adaptador.notifyDataSetChanged();
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
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}