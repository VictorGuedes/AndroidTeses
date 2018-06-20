package com.example.vitu.projetotese.rest;

import com.example.vitu.projetotese.endpoints.ChatEndpoint;
import com.example.vitu.projetotese.endpoints.LoginEndpoint;
import com.example.vitu.projetotese.endpoints.PropostasEndpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vitu on 18/06/2018.
 */

public class RestClient {
    private Retrofit retrofit;

    public RestClient()
    {
        //Celular
        String URL_BASE = "http://192.168.43.249:51883/api/";

        //Casa
        //String URL_BASE = "http://192.168.1.68:51883/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PropostasEndpoint getPropostasEndpoint() {
        return retrofit.create(PropostasEndpoint.class);
    }

    public ChatEndpoint getChatEndpoint(){
        return retrofit.create(ChatEndpoint.class);
    }

    public LoginEndpoint getTokenEndpoint(){
        return retrofit.create(LoginEndpoint.class);
    }

}
