package com.example.sistemapeluqueria.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sistemapeluqueria.R;


public class MiCuentaFragment extends Fragment {





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_mi_cuenta, container, false    );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}