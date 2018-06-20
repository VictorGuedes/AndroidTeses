package com.example.vitu.projetotese.model;

public class User {

    private String Id;
    private String Email;
    private Aluno Aluno;
    private Professor Professor;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Aluno getAluno() {
        return Aluno;
    }

    public Professor getProfessor() {
        return Professor;
    }
}
