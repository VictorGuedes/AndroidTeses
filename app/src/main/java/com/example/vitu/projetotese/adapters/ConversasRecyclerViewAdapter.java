package com.example.vitu.projetotese.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.model.Chat;

import java.util.ArrayList;


public class ConversasRecyclerViewAdapter extends RecyclerView.Adapter<ConversasRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Chat> chats;

    public ConversasRecyclerViewAdapter(ArrayList<Chat> chats){
        this.chats = chats;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversas_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titutoConversa.setText(chats.get(position).getTitulo());
        holder.alunoResponsavel.setText("Aluno: " + chats.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titutoConversa;
        private TextView alunoResponsavel;

        public ViewHolder(View itemView) {
            super(itemView);
            titutoConversa = (TextView) itemView.findViewById(R.id.titulo_tese_card_row);
            alunoResponsavel = (TextView) itemView.findViewById(R.id.aluno_tese_card_row);
        }
    }

}
