package frgp.utn.edu.ar.entidades;


import java.util.Calendar;

public class Parqueo {
    private int id;
    private String patente;
    private int tiempo;

    public Parqueo() {
    }

    public Parqueo(int id, String patente, int tiempo) {
        this.id = id;
        this.patente = patente;
        this.tiempo = tiempo;
    }

    public Parqueo(String patente, int minutos) {
        this.patente = patente;
        this.tiempo = minutos;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
