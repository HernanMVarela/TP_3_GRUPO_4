package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import frgp.utn.edu.ar.DAO.UsuarioDAO;
import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.EstadoUsuario;
import frgp.utn.edu.ar.entidades.Usuario;

public class RegistroActivity extends AppCompatActivity {
    public EditText etUser, etCorreo, etPassword1, etPassword2;
    UsuarioDAO DaoUs = new UsuarioDAOImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUser = (EditText) findViewById(R.id.etNombreRegistro);
        etCorreo = (EditText) findViewById(R.id.etCorreoRegistro);
        etPassword1 = (EditText) findViewById(R.id.etPassUnoRegistro);
        etPassword2 = (EditText) findViewById(R.id.etPassDosRegistro);
    }

    public Boolean Registrar(View view){

        String userName = etUser.getText().toString();
        ///validate that userName doesnt contain whitespaces
        if(userName.contains(" ")){
            Toast.makeText(this, "El nombre de usuario no puede contener espacios", Toast.LENGTH_LONG).show();
            return false;
        }

        if(etUser.getText().toString().isEmpty()
        || etCorreo.getText().toString().isEmpty()
        || etPassword1.getText().toString().isEmpty()
        || etPassword2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!etCorreo.getText().toString().contains("@")){
            Toast.makeText(this, "Ingrese una direccion de mail valida", Toast.LENGTH_LONG).show();
            return false;
        }

        if(etPassword1.getText().toString().length() < 6 || etPassword2.getText().toString().length() < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!etPassword1.getText().toString().equals(etPassword2.getText().toString())){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!checkUserName(view)){
            return false;
        }

        escribir(etUser.getText().toString(),etPassword1.getText().toString(),etCorreo.getText().toString());
        return true;
    }

    public void escribir( String userName, String password, String correo){
        EstadoUsuario eUser = new EstadoUsuario(1,"Activo");
        Usuario nuevo = new Usuario(1,userName,password,correo,eUser);
        boolean response = DaoUs.insertarUsuario(this, nuevo);
        if (response){
            Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_LONG).show();
            Intent login = new Intent(this, MainActivity.class);
            startActivity(login);
        }else{
            Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_LONG).show();
        }
    }


    public boolean checkUserName(View view){
        String userName = etUser.getText().toString();
        if(DaoUs.obtenerUsuarioPorUsername(this, userName) != null){
            Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
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