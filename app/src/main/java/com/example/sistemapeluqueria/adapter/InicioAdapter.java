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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.model.CategoryModel;

import java.util.List;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder>

{

    private final List<CategoryModel> listacategorias;

    public InicioAdapter(List<CategoryModel> listaCategorias) {

       this. listacategorias = listaCategorias;
    }

    @NonNull
    @Override
    public InicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_categorias,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InicioAdapter.ViewHolder holder,  int position) {
       Context context = holder.itemView.getContext();
        String nombre = listacategorias.get(position).getNombre();
        String rutaImagen = listacategorias.get(position).getRutaImagen();
        int id = listacategorias.get(position).getId_categoria();


        holder.nombre.setText(nombre);

        //holder.imagen.setImageDrawable(ContextCompat.getDrawable(context,rutaImagen));
        byte[] decodedString = Base64.decode(rutaImagen, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imagen.setImageBitmap(decodedByte);

        holder.itemView.setOnClickListener(v->{
            Bundle b = new Bundle();
            b.putString("nombre", nombre);
            b.putInt("id_categoria", id);
            NavController nav = Navigation.findNavController(holder.itemView);
            nav.navigate(R.id.navmostrarEspecialista,b);

        });

    }

    @Override
    public int getItemCount() {
        return listacategorias.size();
    }





    public static class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView nombre;
        private  ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.txtTextoCategoria);
            imagen = itemView.findViewById(R.id.imgEspecialista);

        }
    }
}
