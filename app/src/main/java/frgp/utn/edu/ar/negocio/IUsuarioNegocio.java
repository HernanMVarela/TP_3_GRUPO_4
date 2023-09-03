package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidades.Usuario;

public interface IUsuarioNegocio {

    public boolean guardarUsuario(Usuario nuevo);
    public Usuario cargarUsuario(String username, String password);
    public List<Usuario> listarUsuarios();
    public boolean borrarUsuario(Usuario Eliminar);
    public boolean modificarUsuario(Usuario modificar);
}
