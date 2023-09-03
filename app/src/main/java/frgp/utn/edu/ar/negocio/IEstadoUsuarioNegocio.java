package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidades.EstadoUsuario;

public interface IEstadoUsuarioNegocio {
    public boolean guardarEstadoUsuario(EstadoUsuario nuevo);
    public EstadoUsuario cargarEstadoUsuario(int id);
    public List<EstadoUsuario> listarEstados();
}
