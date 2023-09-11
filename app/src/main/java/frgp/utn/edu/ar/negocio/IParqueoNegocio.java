package frgp.utn.edu.ar.negocio;

import android.content.Context;

import java.util.List;

import frgp.utn.edu.ar.entidades.Parqueo;

public interface IParqueoNegocio {
    public boolean guardarParqueo(Context context, Parqueo nuevo);
    public Parqueo cargarParqueo(Context context, int id);
    public Parqueo buscarPorPatente(Context context, String patente);
    public boolean existePatente(Context context, String patente);
    public List<Parqueo> listarParqueos(Context context);
    public List<Parqueo> listarParqueosPorUser(Context context, String user);
    public boolean eliminarParqueo(Context context, Parqueo eliminar);
}
