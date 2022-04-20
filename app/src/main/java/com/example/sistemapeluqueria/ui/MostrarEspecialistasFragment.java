package com.example.sistemapeluqueria.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.sistemapeluqueria.databinding.ActivityMainBinding;
import com.example.sistemapeluqueria.model.CategoryModel;
import com.example.sistemapeluqueria.model.MostrarEspecialistaModel;

import java.util.ArrayList;
import java.util.List;


public class MostrarEspecialistasFragment extends Fragment {

    //private Spinner spinnerDepartamento;



private int id_categoria ;
private String nombre_categoria;
private List<MostrarEspecialistaModel> listacEspecialistas ;
private RecyclerView.Adapter<MostrarEspecialistaAdapter.ViewHolder> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_especialistas, container, false);

        listacEspecialistas = new ArrayList<>();
        adaptador = new MostrarEspecialistaAdapter(listacEspecialistas);


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