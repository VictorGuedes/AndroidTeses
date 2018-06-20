package com.example.vitu.projetotese.endpoints;

import com.example.vitu.projetotese.model.Chat;
import com.example.vitu.projetotese.model.PropostaSubmetida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ChatEndpoint {


    @GET("Messenger/{idUser}")
    Call<List<Chat>> listarChatsUser(@Path("idUser") String idUser, @Header("Authorization") String BearerToken);

}
