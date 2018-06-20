package com.example.vitu.projetotese.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "User")
public class Aluno {

    @PrimaryKey
    private String Id;
    private String nome;

    public String getNOME() {
        return nome;
    }


}
