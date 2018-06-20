package com.example.vitu.projetotese.endpoints;


import com.example.vitu.projetotese.model.ResponseToken;
import com.example.vitu.projetotese.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginEndpoint {

    @FormUrlEncoded
    @POST("Token")
    Call<ResponseToken> retornarUserToken(@Field("grant_type") String grant_type,
                                          @Field("username") String username,
                                          @Field("password") String password);
    @GET("Account/UserInfo")
    Call<User> retornarUserInfo(@Header("Authorization") String BearerToken);
}
