package frgp.utn.edu.ar.negocioImpl;

import java.util.List;

import frgp.utn.edu.ar.entidades.Usuario;
import frgp.utn.edu.ar.negocio.IUsuarioNegocio;

public class UsuarioNegocio implements IUsuarioNegocio {
    @Override
    public boolean guardarUsuario(Usuario nuevo) {
        return false;
    }

    @Override
    public Usuario cargarUsuario(String username, String password) {
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return null;
    }

    @Override
    public boolean borrarUsuario(Usuario Eliminar) {
        return false;
    }

    @Override
    public boolean modificarUsuario(Usuario modificar) {
        return false;
    }
}
