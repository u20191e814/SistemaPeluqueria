package com.example.sistemapeluqueria.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sistemapeluqueria.MainActivity;
import com.example.sistemapeluqueria.R;

import org.json.JSONObject;


public class MiCuentaFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity lectura = (MainActivity)getActivity();
        String dta= lectura.data_Login;
        View v =inflater.inflate(R.layout.fragment_mi_cuenta, container, false    );


        try {

            JSONObject obj = new JSONObject(dta);
            EditText nombre = v.findViewById(R.id.txtNombreMiCuenta);
            nombre.setText(obj.get("nombre").toString());
            EditText apellido = v.findViewById(R.id.txtApellidoMiCuenta);
            apellido.setText(obj.get("apellido").toString());
            EditText telefono = v.findViewById(R.id.txtTelefonoMiCuenta);
            telefono.setText(obj.get("telefono").toString());
            EditText email = v.findViewById(R.id.txtEmailMiCuenta);
            email.setText(obj.get("correo").toString());

            EditText clave = v.findViewById(R.id.txtClaveMiCuenta);
            clave.setText(obj.get("clave").toString());






        } catch (Throwable tx) {
            Log.e("Mi cuenta", "Error en parsear json ");
        }
        return  v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}