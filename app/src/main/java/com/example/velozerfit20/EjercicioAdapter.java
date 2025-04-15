package com.example.velozerfit20;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder> {

    private List<Ejercicio> listaEjercicios;
    private Context context;

    public EjercicioAdapter(List<Ejercicio> listaEjercicios, Context context) {
        this.listaEjercicios = listaEjercicios;
        this.context = context;
    }

    @Override
    public EjercicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout de cada item en el RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.item_ejercicio, parent, false);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EjercicioViewHolder holder, int position) {
        // Obtener el ejercicio actual de la lista
        Ejercicio ejercicio = listaEjercicios.get(position);

        // Establecer los datos del ejercicio en el ViewHolder
        holder.txtNombre.setText(ejercicio.getNombre());
        holder.txtDescripcion.setText(ejercicio.getDescripcion());
        holder.icono.setImageResource(ejercicio.getIcono());  // Usar el ícono específico del ejercicio

        // Manejar el clic en el item y abrir el video de YouTube
        holder.itemView.setOnClickListener(v -> {
            String urlVideo = ejercicio.getUrlVideo();
            if (urlVideo != null && !urlVideo.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlVideo));
                context.startActivity(intent);
            } else {
                // Si no hay video, mostrar un mensaje
                Toast.makeText(context, "Video no disponible para este ejercicio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEjercicios.size();  // Devolver la cantidad de ejercicios
    }

    // ViewHolder que referencia las vistas de cada item en el RecyclerView
    public static class EjercicioViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtDescripcion;
        ImageView icono;

        public EjercicioViewHolder(View itemView) {
            super(itemView);
            // Inicializar las vistas del item
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            icono = itemView.findViewById(R.id.iconoEjercicio);
        }
    }
}
