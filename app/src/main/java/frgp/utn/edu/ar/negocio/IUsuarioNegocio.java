package frgp.utn.edu.ar.negocio;

import android.content.Context;

import java.util.List;

import frgp.utn.edu.ar.entidades.Usuario;

public interface IUsuarioNegocio {

    public boolean guardarUsuario(Context context, Usuario nuevo);
    public Usuario cargarUsuario(Context context, String username, String password);
    public List<Usuario> listarUsuarios(Context context);
    boolean existeUsuario(Context context, String username);
    boolean existeCorreo(Context context, String mail);

    public boolean borrarUsuario(Context context, Usuario Eliminar);
    public boolean modificarUsuario(Context context, Usuario modificar);
}
