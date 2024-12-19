package edu.upc.dsa.models;

public class Denuncia {
    String date;
    String nombre;
    String message;

    public Denuncia() {
    }
    public Denuncia(String date, String nombre, String message) {
        this.date = date;
        this.nombre = nombre;
        this.message = message;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
