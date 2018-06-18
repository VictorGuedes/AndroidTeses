package com.example.vitu.projetotese.endpoints;

import com.example.vitu.projetotese.model.Chat;
import com.example.vitu.projetotese.model.PropostaSubmetida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChatEndpoint {

    public static final String URL_BASE="http://192.168.1.68:55708/api/";

    @GET("chatConversa")
    Call<List<Chat>> listarChats();

}
