package frgp.utn.edu.ar.DAO;

import android.content.Context;

import java.util.List;

import frgp.utn.edu.ar.entidades.Parqueo;

public interface ParqueoDAO {
    public boolean insertarParqueo(Context context, Parqueo nuevo);
    public boolean existeParqueo(Context context, String patente);
    public List<Parqueo> listarParqueos(Context context);
    public List<Parqueo> listarParqueosPorUser(Context context, String user);

    public Parqueo obtenerParqueo(Context context, String patente);
}
