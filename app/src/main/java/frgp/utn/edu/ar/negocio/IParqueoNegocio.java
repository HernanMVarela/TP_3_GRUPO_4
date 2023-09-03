package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidades.Parqueo;

public interface IParqueoNegocio {
    public boolean guardarParqueo(Parqueo nuevo);
    public Parqueo cargarParqueo(int id);
    public Parqueo buscarPorPatente(String patente);
    public boolean existePatente(String patente);
    public List<Parqueo> listarParqueos();
    public boolean eliminarParqueo(Parqueo eliminar);
}
