package com.example.vitu.projetotese.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.activitys.PropostaActivity;
import com.example.vitu.projetotese.activitys.WebViewActivity;
import com.example.vitu.projetotese.adapters.TesesRecyclerViewAdapter;
import com.example.vitu.projetotese.endpoints.PropostasEndpoint;
import com.example.vitu.projetotese.model.Proposta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TesesFragment extends Fragment {

    private ArrayList<Proposta> propostas;

    public TesesFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teses, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView_teses);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PropostasEndpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PropostasEndpoint service = retrofit.create(PropostasEndpoint.class);
        Call<List<Proposta>> requestPropostas = service.listarPropostas();

        requestPropostas.enqueue(new Callback<List<Proposta>>() {
            @Override
            public void onResponse(Call<List<Proposta>> call, Response<List<Proposta>> response) {
                if(!response.isSuccessful()){
                    //COLOCAR IMAGEM TRISTE NO FUNDO DA TELA CASO NÃO TENHA NET
                    Log.i("TAG", "Erro " + response.code());
                }else {

                    List<Proposta> teses = response.body();
                    propostas = new ArrayList<>();
                    propostas.addAll(teses);

                    RecyclerView.Adapter adapter = new TesesRecyclerViewAdapter(propostas);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Proposta>> call, Throwable t) {
                //COLOCAR IMAGEM TRISTE NO FUNDO DA TELA CASO NÃO TENHA NET
                Log.i("TAG_ERRO", t.getMessage());
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
                    Intent intent = new Intent(getActivity(), PropostaActivity.class);
                    intent.putExtra("idProposta", propostas.get(position).getID_PROPOSTA());
                    intent.putExtra("idInstituicao", propostas.get(position).getINSTITUICAO_TRABALHO());
                    intent.putExtra("idTitulo", propostas.get(position).getTITULO());
                    intent.putExtra("idPalavraChave", propostas.get(position).getPALAVRA_CHAVE());
                    intent.putExtra("idObjetivo", propostas.get(position).getOBJETIVO());
                    intent.putExtra("idDescricao", propostas.get(position).getDESCRICAO_ADICIONAL());
                    intent.putExtra("idMetodologia", propostas.get(position).getMETODOLOGIA());
                    intent.putExtra("idRecursos", propostas.get(position).getRECURSOS_NECESSARIOS());

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
}
