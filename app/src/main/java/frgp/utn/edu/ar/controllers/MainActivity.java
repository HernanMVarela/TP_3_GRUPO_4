package frgp.utn.edu.ar.controllers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.EstadoUsuario;
import frgp.utn.edu.ar.entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    UsuarioDAOImpl DaoUs = new UsuarioDAOImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void escribir(View view){
        EstadoUsuario Euser = new EstadoUsuario(1,"Activo");
        Usuario nuevo = new Usuario(1,"UserPrueba","ContraPrueba","Prueba@Prueba.com",Euser);
        boolean N = DaoUs.insertarUsuario(this, nuevo);
    }

    public void leerUsuario(View view){
        List<Usuario> lista = DaoUs.obtenerUsuarios(this);

        if (lista != null){
            for (Usuario item: lista ) {
                Log.i(String.valueOf(item.getId()),item.toString());
            }
        }else{
            Log.e("ERROR","NO SE ENCONTRÓ USUARIO");
        }
    }

}