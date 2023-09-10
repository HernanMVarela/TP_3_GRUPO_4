package frgp.utn.edu.ar.DAOImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.DAO.ParqueoDAO;
import frgp.utn.edu.ar.OpenHelper.OpenHelper;
import frgp.utn.edu.ar.entidades.Parqueo;

public class ParqueoDAOImpl implements ParqueoDAO {

    private OpenHelper DB;
    @Override
    public boolean insertarParqueo(Context context, Parqueo nuevo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            DB = new OpenHelper( context, "tp3g4",null,1);
            DB.openDB();
            ContentValues parqueo = new ContentValues();
            parqueo.put("PATENTE", nuevo.getPatente());
            parqueo.put("TIEMPO", nuevo.getTiempo());
            parqueo.put("INGRESO", dateFormat.format(nuevo.getIngreso()));
            DB.insertDB("parqueos",parqueo);
            DB.closeDB();
            return true;
        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
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
            Calendar fechaActual = Calendar.getInstance();
            Cursor fila = base.rawQuery("SELECT * FROM parqueos;", null);
            if (fila.moveToFirst()) {
                do {
                    // Recupera la fecha de ingreso como cadena desde la base de datos
                    String fechaStr = fila.getString(3);

                    // Convierte la cadena de fecha nuevamente a java.util.Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fechaIngreso = dateFormat.parse(fechaStr);

                    // Convierte la fecha de ingreso a Calendar
                    Calendar calendarIngreso = Calendar.getInstance();
                    calendarIngreso.setTime(fechaIngreso);

                    // Obtiene el valor de "tiempo" desde la fila
                    int tiempo = fila.getInt(2);

                    // Suma el tiempo en minutos a la fecha de ingreso
                    calendarIngreso.add(Calendar.MINUTE, tiempo);

                    // Compara la fecha calculada con la fecha actual
                    if (calendarIngreso.after(fechaActual)) {
                        listaParqueos.add(new Parqueo(
                                fila.getInt(0),
                                fila.getString(1),
                                tiempo,
                                fechaIngreso));
                    }
                } while (fila.moveToNext());
                DB.closeDB();
                return listaParqueos;
            }else {
                DB.closeDB();
                return null;
            }
        }catch (Exception e){
            Log.e("ERROR", e.toString());
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

                String fechaStr = fila.getString(3);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date fecha = dateFormat.parse(fechaStr);

                buscado.setId(fila.getInt(0));
                buscado.setPatente(fila.getString(1));
                buscado.setTiempo(fila.getInt(2));
                buscado.setIngreso(fecha);
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
