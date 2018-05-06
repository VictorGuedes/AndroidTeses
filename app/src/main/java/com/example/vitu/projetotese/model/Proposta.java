package com.example.vitu.projetotese.model;

/**
 * Created by vitu on 17/04/2018.
 */

public class Proposta {

    private String ID_PROPOSTA;
    private String TITULO;
    private String OBJETIVO;
    private Professor Professor;
    private String INSTITUICAO_TRABALHO;
    private String PALAVRA_CHAVE;
    private String DESCRICAO_ADICIONAL;
    private String METODOLOGIA;
    private String RECURSOS_NECESSARIOS;

    public String getDESCRICAO_ADICIONAL() {
        return DESCRICAO_ADICIONAL;
    }

    public void setDESCRICAO_ADICIONAL(String DESCRICAO_ADICIONAL) {
        this.DESCRICAO_ADICIONAL = DESCRICAO_ADICIONAL;
    }

    public String getMETODOLOGIA() {
        return METODOLOGIA;
    }

    public void setMETODOLOGIA(String METODOLOGIA) {
        this.METODOLOGIA = METODOLOGIA;
    }

    public String getRECURSOS_NECESSARIOS() {
        return RECURSOS_NECESSARIOS;
    }

    public void setRECURSOS_NECESSARIOS(String RECURSOS_NECESSARIOS) {
        this.RECURSOS_NECESSARIOS = RECURSOS_NECESSARIOS;
    }

    public String getPALAVRA_CHAVE() {
        return PALAVRA_CHAVE;
    }

    public void setPALAVRA_CHAVE(String PALAVRA_CHAVE) {
        this.PALAVRA_CHAVE = PALAVRA_CHAVE;
    }

    public String getINSTITUICAO_TRABALHO() {
        return INSTITUICAO_TRABALHO;
    }

    public void setINSTITUICAO_TRABALHO(String INSTITUICAO_TRABALHO) {
        this.INSTITUICAO_TRABALHO = INSTITUICAO_TRABALHO;
    }

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
