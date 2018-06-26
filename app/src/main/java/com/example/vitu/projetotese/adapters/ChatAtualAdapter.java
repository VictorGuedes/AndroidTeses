package com.example.vitu.projetotese.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitu.projetotese.DAO.UserDAO;
import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.app.App;
import com.example.vitu.projetotese.model.ItemChat;
import com.example.vitu.projetotese.model.UserBanco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitu on 20/06/2018.
 */

public class ChatAtualAdapter extends RecyclerView.Adapter {
    private List<ItemChat> itensChat;
    private Context context;
    private final int VIEW_TYPE_MESSAGE_SENT = 1;
    private final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private UserDAO userDAO;

    public ChatAtualAdapter(Context context, List<ItemChat> itensChat){
        this.context = context;
        this.itensChat = itensChat;
        userDAO = App.getDatabase().getUserDAO();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == VIEW_TYPE_MESSAGE_SENT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_meu_bolao, parent,false);
            return new ViewHolderSent(view);

        } else if(viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_text,parent,false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        String idUser = userDAO.getLogedUser().getId();

        if (itensChat.get(position).getIdUserSerder().equals(idUser)){
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemChat itemChat = itensChat.get(position);

        switch (holder.getItemViewType()){
            case VIEW_TYPE_MESSAGE_SENT:
                ((ViewHolderSent) holder).bind(itemChat);
                break;

            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(itemChat);
        }
    }

    @Override
    public int getItemCount() {
        return itensChat.size();
    }

    private class ViewHolderSent extends RecyclerView.ViewHolder{
        TextView mensagem;
        TextView sender;

        public ViewHolderSent(View itemView) {
            super(itemView);
            mensagem = (TextView) itemView.findViewById(R.id.message_text);
            sender = (TextView) itemView.findViewById(R.id.message_text_quem_enviou);
        }

        void bind(ItemChat itemChat){
            mensagem.setText(itemChat.getMensagem());
            sender.setText(itemChat.getEmail());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView mensagem;
        TextView receiver;

        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            mensagem = (TextView) itemView.findViewById(R.id.message_text2);
            receiver = (TextView) itemView.findViewById(R.id.message_text_quem_enviou2);
        }
        void bind(ItemChat itemChat){
            mensagem.setText(itemChat.getMensagem());
            receiver.setText(itemChat.getEmail());
        }
    }

}
