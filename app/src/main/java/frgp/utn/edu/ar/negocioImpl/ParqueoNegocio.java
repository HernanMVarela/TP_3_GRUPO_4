package frgp.utn.edu.ar.negocioImpl;

import android.content.Context;

import java.util.List;

import frgp.utn.edu.ar.DAO.ParqueoDAO;
import frgp.utn.edu.ar.DAOImpl.ParqueoDAOImpl;
import frgp.utn.edu.ar.entidades.Parqueo;
import frgp.utn.edu.ar.negocio.IParqueoNegocio;

public class ParqueoNegocio implements IParqueoNegocio {
    ParqueoDAO ParDao = new ParqueoDAOImpl();
    @Override
    public boolean guardarParqueo(Context context, Parqueo nuevo) {
        return ParDao.insertarParqueo(context, nuevo);
    }
    @Override
    public Parqueo cargarParqueo(Context context, int id) {
        return null;
    }

    @Override
    public Parqueo buscarPorPatente(Context context, String patente) {
        return ParDao.obtenerParqueo(context, patente);
    }

    @Override
    public boolean existePatente(Context context, String patente) {
        return false;
    }

    @Override
    public List<Parqueo> listarParqueos(Context context) {
        return ParDao.listarParqueos(context);
    }

    @Override
    public List<Parqueo> listarParqueosPorUser(Context context, String user) {
        return ParDao.listarParqueosPorUser(context, user);
    }

    @Override
    public boolean eliminarParqueo(Context context, Parqueo eliminar) {
        return false;
    }
}
