package com.example.vitu.projetotese.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.vitu.projetotese.DAO.UserDAO;
import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.activitys.ConversaActivity;
import com.example.vitu.projetotese.adapters.ConversasRecyclerViewAdapter;
import com.example.vitu.projetotese.app.App;
import com.example.vitu.projetotese.endpoints.ChatEndpoint;
import com.example.vitu.projetotese.model.Chat;
import com.example.vitu.projetotese.model.PropostaSubmetida;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConversasFragment extends Fragment {

    private ArrayList<Chat> conversas;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ConversasFragment(){  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_conversas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView_lista_conversas_pessoas);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutChats);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setRefreshing(true);

        UserDAO userDAO = App.getDatabase().getUserDAO();
        final String userId = userDAO.getLogedUser().getId();
        final String userToken = userDAO.getLogedUser().getToken();


        final Call<List<Chat>> requestChats = App.getRestClient().getChatEndpoint().listarChatsUser(userId, userToken);
        getChats(requestChats, recyclerView);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getChats(requestChats, recyclerView);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Intent intent = new Intent(getActivity(), ConversaActivity.class);
                    intent.putExtra("idUser", userId);
                    intent.putExtra("tokenUser", userToken);
                    intent.putExtra("idChat", conversas.get(position).getId_messsenger());
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void getChats(Call<List<Chat>> requestChats, final RecyclerView recyclerView){

        requestChats.clone().enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Chat>> call, @NonNull Response<List<Chat>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Algo deu errado :/", Toast.LENGTH_SHORT).show();
                }else {
                    List<Chat> chats = response.body();
                    conversas = new ArrayList<>();
                    conversas.addAll(chats);

                    RecyclerView.Adapter adapter = new ConversasRecyclerViewAdapter(conversas);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Chat>> call, @NonNull Throwable t) {
                Log.i("TAG_ERRO", t.getMessage());
            }
        });

    }
}
