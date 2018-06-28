package com.example.vitu.projetotese.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.model.PropostaSubmetida;

import java.util.ArrayList;

/**
 * Created by vitu on 17/04/2018.
 */

public class TesesRecyclerViewAdapter extends RecyclerView.Adapter<TesesRecyclerViewAdapter.ViewHolder> {
    private ArrayList<PropostaSubmetida> propostas;

    public TesesRecyclerViewAdapter(ArrayList<PropostaSubmetida> propostas){
        this.propostas = propostas;
    }

    @Override
    public TesesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teses_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TesesRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.titulo.setText(propostas.get(position).getTitulo());
        holder.objetivo.setText(propostas.get(position).getObjetivo());
    }

    @Override
    public int getItemCount() {
        return propostas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo;
        private TextView objetivo;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.tituto_tese_card_row);
            objetivo = (TextView) itemView.findViewById(R.id.objetivo_tese_card_row);
        }
    }

}
