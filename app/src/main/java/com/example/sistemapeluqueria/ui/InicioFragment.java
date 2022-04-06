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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemapeluqueria.MainActivity;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.RegistrarClienteActivity;
import com.example.sistemapeluqueria.adapter.InicioAdapter;
import com.example.sistemapeluqueria.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;


public class InicioFragment extends Fragment {

    NavController navController;
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

        RecyclerView rec = s.findViewById(R.id.recycleInicio);
        rec.setOnClickListener(c->{
            navController.navigate(R.id.navmostrarEspecialista);
        });


        return  s;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        //navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
        RecyclerView recyclerView = view.findViewById(R.id.recycleInicio);
        List<CategoryModel> listaCategorias = new ArrayList<>();


        listaCategorias.add(new CategoryModel("Peinados", R.mipmap.imagenpeinado));
        listaCategorias.add(new CategoryModel("Cortes", R.mipmap.imagencortes));
        listaCategorias.add(new CategoryModel("Maquillaje", R.mipmap.imagenmaquillaje));
        listaCategorias.add(new CategoryModel("Cejas", R.mipmap.imagencejas));

        RecyclerView.Adapter<InicioAdapter.ViewHolder> adaptador = new InicioAdapter(listaCategorias);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}