package frgp.utn.edu.ar.negocioImpl;

import android.content.Context;
import android.util.Log;

import java.util.List;

import frgp.utn.edu.ar.DAO.UsuarioDAO;
import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.Usuario;
import frgp.utn.edu.ar.negocio.IUsuarioNegocio;

public class UsuarioNegocio implements IUsuarioNegocio {

    UsuarioDAO UserDao = new UsuarioDAOImpl();
    @Override
    public boolean guardarUsuario(Context context, Usuario nuevo) {
        return UserDao.insertarUsuario(context, nuevo);
    }

    @Override
    public Usuario cargarUsuario(Context context, String username, String password) {
        Usuario user = UserDao.obtenerUsuarioPorUsername(context, username);
        if(user == null){
            return null;
        }
        if(user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public List<Usuario> listarUsuarios(Context context) {
        return UserDao.obtenerUsuarios(context);
    }

    @Override
    public boolean existeUsuario(Context context, String username) {
        if(UserDao.existeUsuario(context,username) != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean existeCorreo(Context context, String mail) {
        if(UserDao.existeCorreo(context,mail) != null){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean borrarUsuario(Context context, Usuario Eliminar) {
        return false;
    }

    @Override
    public boolean modificarUsuario(Context context, Usuario modificar) {
        return false;
    }
}
