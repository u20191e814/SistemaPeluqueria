package com.example.sistemapeluqueria.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemapeluqueria.MainActivity;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.RegistrarClienteActivity;
import com.example.sistemapeluqueria.adapter.InicioAdapter;
import com.example.sistemapeluqueria.controladores.webServicio;
import com.example.sistemapeluqueria.model.CategoryModel;
import com.example.sistemapeluqueria.model.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InicioFragment extends Fragment {

    NavController navController;


    private List<CategoryModel> listaCategorias ;
    private RecyclerView.Adapter<InicioAdapter.ViewHolder> adaptador;
    private LinearLayoutManager LayoutManager;
    private   RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View s = inflater.inflate(R.layout.fragment_inicio,container, false);
        listaCategorias = new ArrayList<>();
        recyclerView = s.findViewById(R.id.recycleInicio);


        adaptador = new InicioAdapter(listaCategorias);
        LayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(LayoutManager);

        recyclerView.setAdapter(adaptador);


        //Log.i("Peinado", R.mipmap.imagenpeinado +"");
        //Log.i("Cortes", R.mipmap.imagencortes +"");
        //Log.i("maquillaje", R.mipmap.imagenmaquillaje +"");
        //Log.i("Cejas", R.mipmap.imagencejas +"");
        return  s;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());

         String url = webServicio.dominio_servicio+ "api/peluqueria/categoria";
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {

            @Override public void onResponse(String response)
            {
            try
            {
                listaCategorias.clear();
                JSONObject OB = new JSONObject(response);
                String estado= OB.getString("status");
                if (!estado.equals("OK")) {
                    Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                    toast.show();
                }
                JSONArray jsonArray = OB.getJSONArray("data");
                //Log.i("Json", OB.getString("data"));
               for (int i = 0; i<jsonArray.length(); i++)
                {

                    JSONObject object = jsonArray.getJSONObject(i);
                    CategoryModel categoria =  new CategoryModel(object.getInt("pk_categoria"), object.getString("nombre"), object.getString("imagenCat"));
                    listaCategorias.add(categoria);
                    //Log.i("servicio", categoria.getId_categoria() +" " + categoria.getNombre() + "  " + categoria.getRutaImagen());
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

        requestQueue.add(stringRequest);


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
