package frgp.utn.edu.ar.controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import OpenHelper.OpenHelper;
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
        OpenHelper DB = new OpenHelper(this,"tp3g4",null,1);
        EstadoUsuario Euser = new EstadoUsuario(1,"Activo");
        Usuario nuevo = new Usuario(1,"UserPrueba","ContraPrueba","Prueba@Prueba.com",Euser);
        boolean N = DaoUs.insertarUsuario(DB, nuevo);
        if (N==true){
            Toast.makeText(this,"Si Funciona",Toast.LENGTH_LONG).show();}
    }

}