package com.example.vitu.projetotese.model;

public class Chat {

    private String ID_CHAT_CONVERSAS;
    private PropostaSubmetida proposta;

    public Chat(String ID_CHAT_CONVERSAS, PropostaSubmetida proposta) {
        this.ID_CHAT_CONVERSAS = ID_CHAT_CONVERSAS;
        this.proposta = proposta;

    }

    public String getID_CHAT_CONVERSAS() {
        return ID_CHAT_CONVERSAS;
    }

    public void setID_CHAT_CONVERSAS(String ID_CHAT_CONVERSAS) {
        this.ID_CHAT_CONVERSAS = ID_CHAT_CONVERSAS;
    }

    public PropostaSubmetida getProposta() {
        return proposta;
    }

    public void setProposta(PropostaSubmetida proposta) {
        this.proposta = proposta;
    }

}
