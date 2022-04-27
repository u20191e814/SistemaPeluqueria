package com.example.sistemapeluqueria.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.model.MostrarEspecialistaModel;

import java.util.List;

public class MostrarEspecialistaAdapter extends RecyclerView.Adapter<MostrarEspecialistaAdapter.ViewHolder> {

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
            //int imagen=listacEspecialistas.get(position).getImagen();
            int calificacion = listacEspecialistas.get(position).getCalificacion();
            String mostrarEspecialista = listacEspecialistas.get(position).getCategoria();
            String imgBase64 = listacEspecialistas.get(position).getImagen();
            int fk_categoria = listacEspecialistas.get(position).getFk_categoria();
            int pk_personal = listacEspecialistas.get(position).getId_especialista();



            holder.nombre.setText(nombre);
            holder.direccion.setText(direccion);
            holder.Calificacion.setRating(calificacion);
            holder.MostrarEspecialistas.setText(mostrarEspecialista);

            byte[] decodedString = Base64.decode(imgBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.imagen.setImageBitmap(decodedByte);

            //holder.imagen.setImageDrawable(ContextCompat.getDrawable(context,imagen));


            holder.itemView.setOnClickListener(v -> {
                Bundle b = new Bundle();
                b.putString("nombre", nombre);
                b.putInt("fk_categoria", fk_categoria);
                b.putInt("pk_personal", pk_personal);
                NavController nav = Navigation.findNavController(holder.itemView);
                nav.navigate(R.id.navContratarServicio, b);

            });

        }

        @Override
        public int getItemCount () {
            return listacEspecialistas.size();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {

            private TextView nombre;
            private TextView direccion;
            private RatingBar Calificacion;
            private  TextView MostrarEspecialistas;
            private ImageView imagen;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nombre = itemView.findViewById(R.id.txtNombreMostrarEspecialistas);
                direccion=itemView.findViewById(R.id.txtDireccionMostrarEspecialistas);
                imagen = itemView.findViewById(R.id.imgEspecialista);
                Calificacion=itemView.findViewById(R.id.ratingbarMostrarEspecialistas);
                MostrarEspecialistas=itemView.findViewById(R.id.txtEspecialidadMostrarCategoria);
            }
    }
}
