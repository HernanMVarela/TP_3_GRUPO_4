package frgp.utn.edu.ar.DAO;

import android.content.Context;

import java.util.List;

import frgp.utn.edu.ar.entidades.Usuario;

public interface UsuarioDAO {
    public boolean insertarUsuario(Context context, Usuario nuevo);
    public Usuario obtenerUsuarioPorUsername(Context context, String username);
    public Usuario existeUsuario(Context context, String username);
    public Usuario existeCorreo(Context context, String mail);

    public List<Usuario> obtenerUsuarios(Context context);
    public void eliminarUsuario(int ID);
    public boolean actualizarUsuario(Usuario modificar);
}
