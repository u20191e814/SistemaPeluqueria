package com.example.sistemapeluqueria.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.RegistrarClienteActivity;
import com.example.sistemapeluqueria.databinding.ActivityMainBinding;


public class MostrarEspecialistasFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_especialistas, container, false);

        return v;

    }
}