package frgp.utn.edu.ar.entidades;


import java.util.Calendar;

public class Parqueo {
    private int id;
    private String patente;
    private Calendar tiempo;

    public Parqueo() {
    }

    public Parqueo(int id, String patente, Calendar tiempo) {
        this.id = id;
        this.patente = patente;
        this.tiempo = tiempo;
    }

    public void getTiempoFromInt(int minutos){
        tiempo.set(Calendar.HOUR,minutos%60);
        tiempo.set(Calendar.MINUTE,(minutos - (minutos%60)*60));
        tiempo.set(Calendar.SECOND,0);
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

    public Calendar getTiempo() {
        return tiempo;
    }

    public void setTiempo(Calendar tiempo) {
        this.tiempo = tiempo;
    }
}
