package com.example.sistemapeluqueria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.model.CategoryModel;

import java.util.List;
import java.util.zip.Inflater;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder> {
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
    public void onBindViewHolder(@NonNull InicioAdapter.ViewHolder holder, int position) {
       Context context = holder.itemView.getContext();
        String nombre = listacategorias.get(position).getNombre();
        int rutaImagen = listacategorias.get(position).getRutaImagen();

        holder.nombre.setText(nombre);
        holder.imagen.setImageDrawable(ContextCompat.getDrawable(context,rutaImagen));
    }

    @Override
    public int getItemCount() {
        return listacategorias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nombre;
        private final ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.txtTextoCategoria);
            imagen = itemView.findViewById(R.id.imgCategoria);
        }
    }
}