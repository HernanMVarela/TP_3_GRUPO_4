package frgp.utn.edu.ar.DAOImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.DAO.UsuarioDAO;
import frgp.utn.edu.ar.OpenHelper.OpenHelper;
import frgp.utn.edu.ar.entidades.EstadoUsuario;
import frgp.utn.edu.ar.entidades.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    private OpenHelper DB;

    @Override
    public boolean insertarUsuario(Context context, Usuario nuevo) {
        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
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
    public Usuario obtenerUsuarioPorUsername(Context context, String username) {
        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
            DB.openDB();
            SQLiteDatabase base = DB.getWritableDatabase();

            Cursor fila = base.rawQuery("SELECT * FROM usuarios WHERE Username =?", new String [] {username});
            if (fila.moveToFirst()) {

                Usuario user = new Usuario(
                        fila.getInt(0),
                        fila.getString(1),
                        fila.getString(2),
                        fila.getString(3),
                        new EstadoUsuario(fila.getInt(4), ""));
                fila.close();
                DB.closeDB();
                return user;
            }else {
                fila.close();
                DB.closeDB();
                return null;
            }
        }
        catch (Exception e) {
            DB.closeDB();
            return null;
        }
    }

    @Override
    public Usuario existeUsuario(Context context, String username) {
        try {
            DB = new OpenHelper(context, "tp3g4", null, 1);
            DB.openDB();
            SQLiteDatabase base = DB.getWritableDatabase();
            Cursor fila = base.rawQuery("SELECT * FROM usuarios WHERE USERNAME =?", new String [] {username});
            if (fila.moveToFirst()) {

                    Usuario user = new Usuario(
                            fila.getInt(0),
                            fila.getString(1),
                            fila.getString(2),
                            fila.getString(3),
                            new EstadoUsuario(fila.getInt(3), ""));
                    fila.close();
                    DB.closeDB();
                    return user;

            } else {
                fila.close();
                DB.closeDB();
                return null;
            }
        }
        catch (Exception e) {
            DB.closeDB();
            return null;
        }
    }

    @Override
    public Usuario existeCorreo(Context context, String mail) {
        try {
            DB = new OpenHelper(context, "tp3g4", null, 1);
            DB.openDB();
            SQLiteDatabase base = DB.getWritableDatabase();
            Cursor fila = base.rawQuery("SELECT * FROM usuarios WHERE CORREO =?", new String [] {mail});
            if (fila.moveToFirst()) {

                Usuario user = new Usuario(
                        fila.getInt(0),
                        fila.getString(1),
                        fila.getString(2),
                        fila.getString(3),
                        new EstadoUsuario(fila.getInt(3), ""));
                fila.close();
                DB.closeDB();
                return user;

            } else {
                fila.close();
                DB.closeDB();
                return null;
            }
        }
        catch (Exception e) {
            DB.closeDB();
            return null;
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios(Context context) {
        List<Usuario> listaUsers = new ArrayList<>();
        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
            DB.openDB();
            SQLiteDatabase base = DB.getWritableDatabase();

            Cursor fila = base.rawQuery("SELECT * FROM usuarios", null);
            if (fila.moveToFirst()) {
                do {
                    // on below line we are adding the data from
                    // cursor to our array list.
                    listaUsers.add(new Usuario(
                            fila.getInt(0),
                            fila.getString(1),
                            fila.getString(2),
                            fila.getString(3),
                            new EstadoUsuario(fila.getInt(4),"")));
                } while (fila.moveToNext());
                fila.close();
                return listaUsers;
            }else {
                fila.close();
                return null;
            }
        }catch (Exception e){
            DB.closeDB();
            return null;
        }
    }

    @Override
    public void eliminarUsuario(int ID) {

    }

    @Override
    public boolean actualizarUsuario(Usuario modificar) {
        return false;
    }
}
