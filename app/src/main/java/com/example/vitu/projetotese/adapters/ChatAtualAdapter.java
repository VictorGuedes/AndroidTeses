package com.example.vitu.projetotese.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vitu.projetotese.R;

import java.util.ArrayList;

/**
 * Created by vitu on 20/06/2018.
 */

public class ChatAtualAdapter extends RecyclerView.Adapter<ChatAtualAdapter.ViewHolder> {
    private ArrayList<String> itensChat;

    public ChatAtualAdapter(ArrayList<String> itensChat){
        this.itensChat = itensChat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_meu_bolao, parent, false);
        return new ChatAtualAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itensChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

}
