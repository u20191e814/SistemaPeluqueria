package com.example.sistemapeluqueria.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.model.MostrarEspecialistaModel;

import java.util.List;
import java.util.zip.Inflater;

public class MostrarEspecialistaAdapter extends RecyclerView.Adapter<MostrarEspecialistaAdapter.ViewHolder> {
    private View.OnClickListener listener;
    private final List<MostrarEspecialistaModel> listacEspecialistas;

    public MostrarEspecialistaAdapter(List<MostrarEspecialistaModel> listacEspecialistas) {

        this.listacEspecialistas = listacEspecialistas;
    }

    @NonNull
    @Override
    public MostrarEspecialistaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_mostrarespecialista, parent, false);

        return new ViewHolder(view);
    }



    @Override
        public void onBindViewHolder (@NonNull MostrarEspecialistaAdapter.ViewHolder holder,int position){
            Context context = holder.itemView.getContext();
            String nombre = listacEspecialistas.get(position).getNombre();
            String direccion = listacEspecialistas.get(position).getDireccion();
            int imagen=listacEspecialistas.get(position).getImagen();


            holder.nombre.setText(nombre);

            holder.imagen.setImageDrawable(ContextCompat.getDrawable(context,imagen));
            //holder.itemView.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        Log.i("Click", nombre);
            //        Navigation.findNavController(view).navigate(R.id.navmostrarEspecialista);
            //    }
            //});

            holder.itemView.setOnClickListener(v -> {
                Bundle b = new Bundle();
                b.putString("nombre", nombre);
                NavController nav = Navigation.findNavController(holder.itemView);
                nav.navigate(R.id.navmostrarEspecialista, b);

            });

        }

        @Override
        public int getItemCount () {
            return listacEspecialistas.size();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {

            private TextView nombre;
            private TextView direccion;
            private  TextView Latitud;
            private  TextView Longitud;
            private  TextView Calificacion;
            private  TextView fk_categoria;
            private ImageView imagen;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nombre = itemView.findViewById(R.id.textView2);
                direccion=itemView.findViewById(R.id.textView5);
                imagen = itemView.findViewById(R.id.imgCategoria);
                Calificacion=itemView.findViewById(R.id.ratingbar);
                fk_categoria=itemView.findViewById(R.id.textView3);
            }
    }
}
