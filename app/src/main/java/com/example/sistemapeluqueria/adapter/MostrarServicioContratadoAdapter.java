package com.example.sistemapeluqueria.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.model.ContractedServiceModel;

import java.util.List;

public class MostrarServicioContratadoAdapter extends RecyclerView.Adapter<MostrarServicioContratadoAdapter.ViewHolder> {

    private final List<ContractedServiceModel> listaServicioContratado;

    public MostrarServicioContratadoAdapter(List<ContractedServiceModel> listaServicioContratado){
        this. listaServicioContratado = listaServicioContratado;
    }

    @NonNull
    @Override
    public MostrarServicioContratadoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_mostrarserviciocontratado,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostrarServicioContratadoAdapter.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        String nombrePersonal = listaServicioContratado.get(position).getNombrePersonal();
        String nombreServicio = listaServicioContratado.get(position).getNombreServicio();
        int total = listaServicioContratado.get(position).getTotal();
        String direccion = listaServicioContratado.get(position).getDireccion();
        String fecha = listaServicioContratado.get(position).getFecha();
        String estado = listaServicioContratado.get(position).getEstado();

        holder.nombrePersonal.setText(nombrePersonal);
        holder.nombreServicio.setText(nombreServicio);
        holder.total.setText(total);
        holder.direccion.setText(direccion);
        holder.fecha.setText(fecha);
        holder.estado.setText(estado);

    }

    @Override
    public int getItemCount() {
        return listaServicioContratado.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nombrePersonal;
        private final TextView nombreServicio;
        private final TextView total;
        private final TextView direccion;
        private final TextView fecha;
        private final TextView estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrePersonal= itemView.findViewById(R.id.txtNombreEspecialista);
            nombreServicio = itemView.findViewById(R.id.txtNombreServicioContratado);
            total = itemView.findViewById(R.id.txtPrecioTotalServicioContratado);
            direccion = itemView.findViewById(R.id.txtDireccionMostrarEspecialistas);
            fecha = itemView.findViewById(R.id.txtFechaServicioContratado);
            estado = itemView.findViewById(R.id.txtEstadoServicioContratado);
        }

    }
}
