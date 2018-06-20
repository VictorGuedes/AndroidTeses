package com.example.vitu.projetotese.model;

/**
 * Created by vitu on 17/04/2018.
 */

public class PropostaSubmetida {

    private String id_proposta_submetida;
    private String titulo;
    private String objetivo;
    private String orientador;
    private String isRejected;

    public String getId_proposta_submetida() {
        return id_proposta_submetida;
    }

    public void setId_proposta_submetida(String id_proposta_submetida) {
        this.id_proposta_submetida = id_proposta_submetida;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public String getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(String isRejected) {
        this.isRejected = isRejected;
    }
}
