package frgp.utn.edu.ar.negocioImpl;

import java.util.List;

import frgp.utn.edu.ar.entidades.EstadoUsuario;
import frgp.utn.edu.ar.negocio.IEstadoUsuarioNegocio;

public class EstadoUsuarioNegocio implements IEstadoUsuarioNegocio {
    @Override
    public boolean guardarEstadoUsuario(EstadoUsuario nuevo) {
        return false;
    }

    @Override
    public EstadoUsuario cargarEstadoUsuario(int id) {
        return null;
    }

    @Override
    public List<EstadoUsuario> listarEstados() {
        return null;
    }
}
