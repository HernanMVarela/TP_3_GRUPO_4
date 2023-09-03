package frgp.utn.edu.ar.DAOImpl;

import android.content.ContentValues;

import java.util.ArrayList;

import OpenHelper.OpenHelper;
import frgp.utn.edu.ar.DAO.UsuarioDAO;
import frgp.utn.edu.ar.entidades.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean insertarUsuario(OpenHelper DB, Usuario nuevo) {
        try {
            DB.openDB();
            ContentValues user = new ContentValues();
            user.put("Username", nuevo.getUsername());
            user.put("Password", nuevo.getPassword());
            user.put("Correo", nuevo.getCorreo());
            user.put("Estado", nuevo.getEstado().getId());
            DB.insertDB("usuarios",user);
            DB.closeDB();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        return null;
    }

    @Override
    public boolean existeUsuario(String correo) {
        return false;
    }

    @Override
    public ArrayList<Usuario> obtenerUsuarios() {
        return null;
    }

    @Override
    public void eliminarUsuario(int ID) {

    }

    @Override
    public boolean actualizarUsuario(Usuario modificar) {
        return false;
    }
}
