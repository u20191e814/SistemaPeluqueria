package com.example.sistemapeluqueria.adapter;



import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
        double total = listaServicioContratado.get(position).getTotal();
        String direccion = listaServicioContratado.get(position).getDireccion();
        String fecha = listaServicioContratado.get(position).getFecha();
        String hora = listaServicioContratado.get(position).getHora();
        String estado = listaServicioContratado.get(position).getEstado();
        int pk_ContratarServicio = listaServicioContratado.get(position).getPk_ContratarServicio();


        holder.nombrePersonal.setText(nombrePersonal);
        holder.nombreServicio.setText(nombreServicio);
        holder.total.setText(String.valueOf(total));
        holder.direccion.setText(direccion);
        holder.fecha.setText(fecha);
        holder.hora.setText(hora);
        holder.estado.setText(estado);
        if (estado.equals("Pendiente")) {
            holder.estado.setTextColor(Color.parseColor("#6A6A6A"));
            holder.itemView.setOnClickListener(v->{
                Bundle b = new Bundle();
                b.putInt("pk_ContratarServicio", pk_ContratarServicio);
                b.putString("nombrePersonal", nombrePersonal);
                b.putString("nombreServicio", nombreServicio);
                b.putDouble("total", total);
                b.putString("direccion", direccion);
                b.putString("fecha", fecha);
                b.putString("hora", hora);
                b.putString("estado", estado);
                NavController nav = Navigation.findNavController(holder.itemView);
                nav.navigate(R.id.navCancelarServicio,b);
            });
        }
        if (estado.equals("Cancelado")) {
            holder.estado.setTextColor(Color.parseColor("#EF0E13"));
        }
        if (estado.equals("Completado")) {
            holder.estado.setTextColor(Color.parseColor("#34A90D"));
        }
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
        private final TextView hora;
        private final TextView estado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrePersonal= itemView.findViewById(R.id.txtNombreEspecialista);
            nombreServicio = itemView.findViewById(R.id.txtNombreServicioContratado);
            total = itemView.findViewById(R.id.txtPrecioTotalServicioContratado);
            direccion = itemView.findViewById(R.id.txtDireccionUsuarioSolicitado);
            fecha = itemView.findViewById(R.id.txtFechaServicioContratado);
            hora = itemView.findViewById(R.id.txtHoraMostrarServicioContratado);
            estado = itemView.findViewById(R.id.txtEstadoServicioContratado);
        }



    }
}
