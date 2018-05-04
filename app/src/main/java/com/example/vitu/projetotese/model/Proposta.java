package com.example.vitu.projetotese.model;

/**
 * Created by vitu on 17/04/2018.
 */

public class Proposta {

    private String ID_PROPOSTA;
    private String TITULO;
    private String OBJETIVO;
    private Professor Professor;


    public Proposta(String id, String titulo, String objetivo, Professor professor) {
        ID_PROPOSTA = id;
        TITULO = titulo;
        OBJETIVO = objetivo;
        Professor = professor;
    }

    public String getID_PROPOSTA() {
        return ID_PROPOSTA;
    }

    public void setID_PROPOSTA(String ID_PROPOSTA) {
        this.ID_PROPOSTA = ID_PROPOSTA;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getOBJETIVO() {
        return OBJETIVO;
    }

    public void setOBJETIVO(String OBJETIVO) {
        this.OBJETIVO = OBJETIVO;
    }

    public com.example.vitu.projetotese.model.Professor getProfessor() {
        return Professor;
    }

    public void setProfessor(com.example.vitu.projetotese.model.Professor professor) {
        Professor = professor;
    }
}
