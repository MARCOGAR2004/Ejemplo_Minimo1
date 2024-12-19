package com.example.restclientservweb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PreguntaAdapter extends RecyclerView.Adapter<PreguntaAdapter.PreguntaViewHolder> {

    private List<Pregunta> preguntaList;

    public PreguntaAdapter(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @NonNull
    @Override
    public PreguntaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pregunta, parent, false);
        return new PreguntaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreguntaViewHolder holder, int position) {
        Pregunta pregunta = preguntaList.get(position);
        holder.textViewPregunta.setText(pregunta.getPregunta());
        holder.textViewRespuesta.setText(pregunta.getRespuesta());
    }

    @Override
    public int getItemCount() {
        return preguntaList.size();
    }

    public static class PreguntaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPregunta;
        TextView textViewRespuesta;

        public PreguntaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPregunta = itemView.findViewById(R.id.textViewPregunta);
            textViewRespuesta = itemView.findViewById(R.id.textViewRespuesta);
        }
    }
}
