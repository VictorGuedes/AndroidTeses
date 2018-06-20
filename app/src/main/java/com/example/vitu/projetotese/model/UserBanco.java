package com.example.vitu.projetotese.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by vitu on 20/06/2018.
 */

@Entity(tableName = "User")
public class UserBanco {
    @PrimaryKey
    @NonNull
    private String Id;
    private String EMAIL;
    private String dataExpiracaoToken;
    private String token;


    @NonNull
    public String getId() {
        return Id;
    }

    public void setId(@NonNull String id) {
        Id = id;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }


    public String getDataExpiracaoToken() {
        return dataExpiracaoToken;
    }

    public void setDataExpiracaoToken(String dataExpiracaoToken) {
        this.dataExpiracaoToken = dataExpiracaoToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
