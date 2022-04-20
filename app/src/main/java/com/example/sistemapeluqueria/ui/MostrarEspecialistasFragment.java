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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.RegistrarClienteActivity;
import com.example.sistemapeluqueria.adapter.InicioAdapter;
import com.example.sistemapeluqueria.adapter.MostrarEspecialistaAdapter;
import com.example.sistemapeluqueria.model.CategoryModel;
import com.example.sistemapeluqueria.model.MostrarEspecialistaModel;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_especialistas, container, false);

        listacEspecialistas = new ArrayList<>();
        listacEspecialistas.add(new MostrarEspecialistaModel(1, "Luisa Ramirez",100, "Av. Javier prado 400", -12.4000, -10.300, 2,1));
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