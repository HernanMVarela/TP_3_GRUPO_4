package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import frgp.utn.edu.ar.entidades.Usuario;
import frgp.utn.edu.ar.negocio.IUsuarioNegocio;
import frgp.utn.edu.ar.negocioImpl.UsuarioNegocio;

public class MainActivity extends AppCompatActivity {

    public EditText etNombre, etPassword;
    IUsuarioNegocio UserNeg = new UsuarioNegocio();
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

        Usuario user = UserNeg.cargarUsuario(this, et1, et2);
        if(user == null){
            Toast.makeText(this, "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Intent home = new Intent(this, HomeActivity.class);
            home.putExtra("userName", user.getUsername());
            home.putExtra("email", user.getCorreo());
            home.putExtra("pass", user.getPassword());
            startActivity(home);
            return true;
        }
    }

    public void IrRegistro(View view){
        Intent registro = new Intent(this, RegistroActivity.class);
        startActivity(registro);
    }

}