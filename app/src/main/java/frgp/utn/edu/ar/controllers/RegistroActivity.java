package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.EstadoUsuario;
import frgp.utn.edu.ar.entidades.Usuario;

public class RegistroActivity extends AppCompatActivity {
    public EditText etNombre, etCorreo, etPassword1, etPassword2;
    UsuarioDAOImpl DaoUs = new UsuarioDAOImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.etNombreRegistro);
        etCorreo = (EditText) findViewById(R.id.etCorreoRegistro);
        etPassword1 = (EditText) findViewById(R.id.etPassUnoRegistro);
        etPassword2 = (EditText) findViewById(R.id.etPassDosRegistro);
    }

    public void Registrar(View view){
        String strNombre = etNombre.getText().toString();
        String strCorreo = etCorreo.getText().toString();
        String strPassUno = etPassword1.getText().toString();
        String strPassDos = etPassword2.getText().toString();

        if(strNombre.length() == 0 || strCorreo.length() == 0 || strPassUno.length() == 0 || strPassDos.length() == 0){
            Toast.makeText(this, "Todos los campos son Obligatorios", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Procesando...", Toast.LENGTH_LONG).show();
        }
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