package com.example.vitu.projetotese.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.adapters.TesesRecyclerViewAdapter;
import com.example.vitu.projetotese.model.Proposta;

import java.util.ArrayList;

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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_teses);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        propostas = new ArrayList<>();
        propostas.add(new Proposta("Projeto1", "Orientador1", "Objetivo1"));
        propostas.add(new Proposta("Projeto2", "Orientador2", "Objetivo2"));
        propostas.add(new Proposta("Projeto3", "Orientador3", "Objetivo3"));
        propostas.add(new Proposta("Projeto4", "Orientador4", "Objetivo4"));
        propostas.add(new Proposta("dasjdiasjdijasodjaoisd", "Orienasddasasdasador4", "Objasdasdasdasdetivo4"));
        propostas.add(new Proposta("Projeto4", "Orientador4", "Objetiasdasdasvo4"));
        propostas.add(new Proposta("Projasdaseto4", "Orienasdasdasdastador4", "Objeasdasdastivo4"));
        propostas.add(new Proposta("Projasdassaeto4", "Orieadsdasantador4", "aaaasddsadasdas"));

        RecyclerView.Adapter adapter = new TesesRecyclerViewAdapter(propostas);
        recyclerView.setAdapter(adapter);

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
                    Toast.makeText(getContext(), propostas.get(position).getCriado(), Toast.LENGTH_SHORT).show();
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
