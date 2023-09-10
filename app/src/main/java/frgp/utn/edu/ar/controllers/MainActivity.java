package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    public EditText etNombre, etPassword;
    UsuarioDAOImpl DaoUs = new UsuarioDAOImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public Boolean IniciarSesion(View view){
        String et1 = etNombre.getText().toString();
        String et2 = etPassword.getText().toString();

        if(et1.length() == 0 || et2.length() == 0){
            Toast.makeText(this, "Todos los campos son Obligatorios", Toast.LENGTH_LONG).show();
            return false;
        }

        if(DaoUs.existeUsuario(this, et1, et2) == null){
            Toast.makeText(this, "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
            return true;
        }
    }

    public void IrRegistro(View view){
        Intent registro = new Intent(this, RegistroActivity.class);
        startActivity(registro);
    }


}