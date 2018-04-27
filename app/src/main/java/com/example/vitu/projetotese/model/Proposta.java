package com.example.vitu.projetotese.model;

/**
 * Created by vitu on 17/04/2018.
 */

public class Proposta {

    private String Titulo;
    private String Criado;
    private String Objetivo;

    public Proposta(String titulo, String criado, String objetivo) {
        Titulo = titulo;
        Criado = criado;
        Objetivo = objetivo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getCriado() {
        return Criado;
    }

    public void setCriado(String criado) {
        Criado = criado;
    }

    public String getObjetivo() {
        return Objetivo;
    }

    public void setObjetivo(String objetivo) {
        Objetivo = objetivo;
    }
}
