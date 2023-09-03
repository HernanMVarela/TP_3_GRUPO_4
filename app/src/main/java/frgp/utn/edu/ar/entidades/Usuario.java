package frgp.utn.edu.ar.entidades;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String correo;
    private EstadoUsuario estado;

    public Usuario(int id, String username, String password, String correo, EstadoUsuario estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.estado = estado;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + estado +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }
}
