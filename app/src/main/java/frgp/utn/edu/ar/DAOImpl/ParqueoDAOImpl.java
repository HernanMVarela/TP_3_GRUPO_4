package frgp.utn.edu.ar.DAOImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import frgp.utn.edu.ar.DAO.ParqueoDAO;
import frgp.utn.edu.ar.OpenHelper.OpenHelper;
import frgp.utn.edu.ar.entidades.EstadoUsuario;
import frgp.utn.edu.ar.entidades.Parqueo;
import frgp.utn.edu.ar.entidades.Usuario;

public class ParqueoDAOImpl implements ParqueoDAO {

    private OpenHelper DB;
    @Override
    public boolean insertarParqueo(Context context, Parqueo nuevo) {
        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
            DB.openDB();
            ContentValues parqueo = new ContentValues();
            parqueo.put("PATENTE", nuevo.getPatente());
            parqueo.put("TIEMPO", nuevo.getTiempo());
            DB.insertDB("parqueos",parqueo);
            DB.closeDB();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existeParqueo(Context context, String patente) {
        return false;
    }

    @Override
    public List<Parqueo> listarParqueos(Context context) {
        List<Parqueo> listaParqueos = new ArrayList<Parqueo>();
        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
            DB.openDB();
            SQLiteDatabase base = DB.getWritableDatabase();

            Cursor fila = base.rawQuery("SELECT * FROM PARQUEOS", null);
            if (fila.moveToFirst()) {
                do {
                    listaParqueos.add(new Parqueo(
                            fila.getInt(0),
                            fila.getString(1),
                            fila.getInt(2)));
                } while (fila.moveToNext());
                return listaParqueos;
            }else {
                return null;
            }
        }catch (Exception e){
            DB.closeDB();
            return null;
        }
    }
    @Override
    public Parqueo obtenerParqueo(Context context, String patente) {
        Parqueo buscado = new Parqueo();
        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
            DB.openDB();
            SQLiteDatabase base = DB.getWritableDatabase();
            Cursor fila = base.rawQuery("SELECT * FROM parqueos WHERE PATENTE =?", new String [] {patente});
            if (fila.moveToFirst()) {
                buscado.setId(fila.getInt(0));
                buscado.setPatente(fila.getString(1));
                buscado.setTiempo(fila.getInt(2));
                DB.closeDB();
                return buscado;
            }else {
                DB.closeDB();
                return null;
            }
        }catch (Exception e){
            DB.closeDB();
            return null;
        }
    }
}
