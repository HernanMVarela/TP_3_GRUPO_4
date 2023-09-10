package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class ParqueosActivity extends AppCompatActivity {

    private Button addParqueo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parqueos);

        addParqueo = (Button) findViewById(R.id.addParqueoBtn);
        addParqueo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(ParqueosActivity.this).create(); //Read Update
                alertDialog.setTitle("AGREGAR PARQUEO");
                alertDialog.setMessage("Ingrese los datos del parqueo");
                alertDialog.setButton("GUARDAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });

                alertDialog.show();  //<-- See This!
            }

        });
    }

}