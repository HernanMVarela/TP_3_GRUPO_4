package frgp.utn.edu.ar.entidades;

public class EstadoUsuario {
    private int id;
    private String nombre;

    public EstadoUsuario() {
    }

    public EstadoUsuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
