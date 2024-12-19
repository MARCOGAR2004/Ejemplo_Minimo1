package com.example.restclientservweb;

public class Denuncia {
    private String date;
    private String nombre;
    private String message;

    public Denuncia(String date, String user, String description) {
        this.date = date;
        this.nombre= user;
        this.message = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return nombre;
    }

    public void setUser(String user) {
        this.nombre = user;
    }

    public String getDescription() {
        return message;
    }

    public void setDescription(String description) {
        this.message = description;
    }

}
