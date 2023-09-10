package frgp.utn.edu.ar.entidades;



import java.util.Date;

public class Parqueo {
    private int id;
    private String patente;
    private int tiempo;
    private Date ingreso;

    public Parqueo() {
    }

    public Parqueo(int id, String patente, int tiempo, Date ingreso) {
        this.id = id;
        this.patente = patente;
        this.tiempo = tiempo;
        this.ingreso = ingreso;
    }

    public Parqueo(String patente, int minutos, Date ingreso) {
        this.patente = patente;
        this.tiempo = minutos;
        this.ingreso = ingreso;
    }

    @Override
    public String toString() {
        return "id: " + id + " - Patente: " + patente + " - Tiempo: " + tiempo + " - Ingreso: " + ingreso.toString();
    }

    public Date getIngreso() {
        return ingreso;
    }
    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
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
