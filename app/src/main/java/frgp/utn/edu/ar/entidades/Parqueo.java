package frgp.utn.edu.ar.entidades;



import java.util.Date;

public class Parqueo {
    private int id;
    private String patente;
    private int tiempo;
    private Date ingreso;
    private String user;

    public Parqueo() {
    }

    public Parqueo(int id, String patente, int tiempo, Date ingreso, String user) {
        this.id = id;
        this.patente = patente;
        this.tiempo = tiempo;
        this.ingreso = ingreso;
        this.user = user;
    }

    public Parqueo(String patente, int minutos, Date ingreso, String user) {
        this.patente = patente;
        this.tiempo = minutos;
        this.ingreso = ingreso;
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id)
          .append(" - Patente: ").append(patente)
          .append(" - Tiempo: ").append(tiempo)
          .append(" - Ingreso: ").append(ingreso.toString())
          .append(" - Usuario: ").append(user);

        return sb.toString();
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
