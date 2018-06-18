package com.example.vitu.projetotese.endpoints;
import com.example.vitu.projetotese.model.PropostaSubmetida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vitu on 04/05/2018.
 */

public interface PropostasEndpoint {

    @GET("PropostaSubmetidas")
    Call<List<PropostaSubmetida>> listarPropostas();

}
