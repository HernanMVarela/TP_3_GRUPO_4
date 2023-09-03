package frgp.utn.edu.ar.negocioImpl;

import java.util.List;

import frgp.utn.edu.ar.entidades.Parqueo;
import frgp.utn.edu.ar.negocio.IParqueoNegocio;

public class ParqueoNegocio implements IParqueoNegocio {
    @Override
    public boolean guardarParqueo(Parqueo nuevo) {
        return false;
    }

    @Override
    public Parqueo cargarParqueo(int id) {
        return null;
    }

    @Override
    public Parqueo buscarPorPatente(String patente) {
        return null;
    }

    @Override
    public boolean existePatente(String patente) {
        return false;
    }

    @Override
    public List<Parqueo> listarParqueos() {
        return null;
    }

    @Override
    public boolean eliminarParqueo(Parqueo eliminar) {
        return false;
    }
}
