package com.example.sistemapeluqueria.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.sistemapeluqueria.MainActivity;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.RegistrarClienteActivity;


public class InicioFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View s = inflater.inflate(R.layout.fragment_inicio,container, false);

        //ImageView img = s.findViewById(R.id.imgmostrarcategoriasPeinado);
       //img.setOnClickListener(c-> {

          // Fragment nuevoFragmento = new MostrarEspecialistasFragment();
          // FragmentTransaction transaction = getFragmentManager().beginTransaction();
         //  transaction.replace(R.id.nav_host_fragment_content_main, nuevoFragmento);

       //    transaction.commit();

     //  });




        return  s;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}