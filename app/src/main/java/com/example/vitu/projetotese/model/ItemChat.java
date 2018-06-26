package com.example.vitu.projetotese.model;

import java.util.Date;

/**
 * Created by vitu on 25/06/2018.
 */

public class ItemChat {

    private String mensagem;
    private String data;
    private String idUserSerder;
    private String email;
    private String tipo;

    public ItemChat(){}

    public ItemChat(String mensagem, String data, String idUserSerder, String email, String tipo) {
        this.mensagem = mensagem;
        this.data = data;
        this.idUserSerder = idUserSerder;
        this.email = email;
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdUserSerder() {
        return idUserSerder;
    }

    public void setIdUserSerder(String idUserSerder) {
        this.idUserSerder = idUserSerder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
