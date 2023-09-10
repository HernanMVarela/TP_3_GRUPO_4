package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText etNombre, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }


    public void InisiarSesion(View view){
        String et1 = etNombre.getText().toString();
        String et2 = etPassword.getText().toString();

        if(et1.length() == 0 || et2.length() == 0){
            Toast.makeText(this, "Todos los campos son Obligatorios", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Procesando...", Toast.LENGTH_LONG).show();
        }
    }

    public void IrRegistro(View view){
        Intent registro = new Intent(this, RegistroActivity.class);
        startActivity(registro);
    }


}