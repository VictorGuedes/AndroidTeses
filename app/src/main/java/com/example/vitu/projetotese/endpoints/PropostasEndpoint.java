package com.example.vitu.projetotese.endpoints;
import com.example.vitu.projetotese.model.PropostaSubmetida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by vitu on 04/05/2018.
 */

public interface PropostasEndpoint {

    @GET("PropostaSubmetidas")
    Call<List<PropostaSubmetida>> listarPropostas(@Header("Authorization") String BearerToken);

}
