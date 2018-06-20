package com.example.vitu.projetotese.model;

import java.util.List;

public class Chat {

    private String id_messsenger;
    private String titulo;
    private List<Aluno> Aluno;
    private List<Professor> Professor;
    private String nome;

    public String getId_messsenger() {
        return id_messsenger;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<com.example.vitu.projetotese.model.Aluno> getAluno() {
        return Aluno;
    }

    public List<com.example.vitu.projetotese.model.Professor> getProfessor() {
        return Professor;
    }

    public String getNome() {
        return nome;
    }
}
