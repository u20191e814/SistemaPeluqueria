package com.example.sistemapeluqueria.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
=======

import androidx.annotation.Nullable;
>>>>>>> 91676e26f7a822c0d0056fb60405a5030a9d7fc6
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.sistemapeluqueria.databinding.ActivityMainBinding;


public class MostrarEspecialistasFragment extends Fragment {
<<<<<<< HEAD
    //private Spinner spinnerDepartamento;

=======

private int id_categoria ;
private String nombre_categoria;
>>>>>>> 91676e26f7a822c0d0056fb60405a5030a9d7fc6
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_especialistas, container, false);


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