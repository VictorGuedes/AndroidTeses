package com.example.vitu.projetotese.model;

public class User {

    private String Id;
    private String EMAIL;
    private String PASSWORD;
    private String TOKEN;

    public User(String EMAIL, String PASSWORD, String TOKEN) {
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.TOKEN = TOKEN;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }
}
