package com.example.vitu.projetotese.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vitu on 04/05/2018.
 */

@Entity(tableName = "Professor")
public class Professor {

    @PrimaryKey
    private String Id;
    private String nome;


    public String getNome() {
        return nome;
    }
}
