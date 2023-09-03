package frgp.utn.edu.ar.DAO;

import java.util.ArrayList;

import frgp.utn.edu.ar.OpenHelper.OpenHelper;
import frgp.utn.edu.ar.entidades.Usuario;

public interface UsuarioDAO {
    public boolean insertarUsuario(OpenHelper DB, Usuario nuevo);
    public Usuario obtenerUsuarioPorUsername(String username);
    public boolean existeUsuario(String correo);
    public ArrayList<Usuario> obtenerUsuarios();
    public void eliminarUsuario(int ID);
    public boolean actualizarUsuario(Usuario modificar);
}
