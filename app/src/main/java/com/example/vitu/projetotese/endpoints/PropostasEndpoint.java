package com.example.vitu.projetotese.endpoints;
import com.example.vitu.projetotese.model.Proposta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vitu on 04/05/2018.
 */

public interface PropostasEndpoint {

    public static final String URL_BASE="http://192.168.1.68:55708/api/";

    @GET("Proposta")
    Call<List<Proposta>> listarPropostas();
}
