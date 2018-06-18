package com.example.vitu.projetotese.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vitu.projetotese.R;

public class PropostaActivity extends AppCompatActivity {

    private TextView INSTITUICAO_TRABALHO;
    private TextView TITULO;
    private TextView PALAVRA_CHAVE;
    private TextView OBJETIVO;
    private TextView DESCRICAO_ADICIONAL;
    private TextView METODOLOGIA;
    private TextView RECURSOS_NECESSARIOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_proposta_detalhes);
        INSTITUICAO_TRABALHO = (TextView) findViewById(R.id.id_proposta_intituicao);
        TITULO = (TextView) findViewById(R.id.id_proposta_titulo);
        PALAVRA_CHAVE = (TextView) findViewById(R.id.id_proposta_palavra_chave);
        OBJETIVO = (TextView) findViewById(R.id.id_proposta_objetivo);
        DESCRICAO_ADICIONAL = (TextView) findViewById(R.id.id_proposta_descrica_adicional);
        METODOLOGIA = (TextView) findViewById(R.id.id_proposta_metodologia);
        RECURSOS_NECESSARIOS = (TextView) findViewById(R.id.id_proposta_recursos_necessarios);
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            INSTITUICAO_TRABALHO.setText( extra.getString("idInstituicao"));
            TITULO.setText(extra.getString("idTitulo"));
            PALAVRA_CHAVE.setText(extra.getString("idPalavraChave"));
            OBJETIVO.setText(extra.getString("idObjetivo"));

            DESCRICAO_ADICIONAL.setText(extra.getString("idDescricao"));
            METODOLOGIA.setText(extra.getString("idMetodologia"));
            RECURSOS_NECESSARIOS.setText(extra.getString("idRecursos"));
        }
    }


}
