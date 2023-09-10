package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.List;

import frgp.utn.edu.ar.DAO.ParqueoDAO;
import frgp.utn.edu.ar.DAOImpl.ParqueoDAOImpl;
import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.Parqueo;

public class ParqueosActivity extends AppCompatActivity {

    private Button addParqueo;
    ParqueoDAO DaoPar = new ParqueoDAOImpl();
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
                        Parqueo nuevo = new Parqueo("ABC 123",45);
                        if(escribirDB(nuevo)){
                            listarParqueos();
                            buscarParqueo(nuevo.getPatente());
                        }else{
                            Log.e("ERROR", "NO SE AGREGO PARQUEO A DB");
                        }
                    }
                });

                alertDialog.show();
            }
        });
    }

    private boolean escribirDB(Parqueo nuevo){
        return DaoPar.insertarParqueo(this,nuevo);
    }

    private void listarParqueos(){
        List<Parqueo> listado = DaoPar.listarParqueos(this);
        for (Parqueo park: listado) {
            Log.i("Parqueo Listado " + park.getId(), "Patente: " + park.getPatente() + " | Tiempo: " + park.getTiempo());
        }
    }

    private void buscarParqueo(String patente){
        Parqueo buscar = DaoPar.obtenerParqueo(this, patente);
        Log.i("Parqueo Buscado " + buscar.getId(), "Patente: " + buscar.getPatente() + " | Tiempo: " + buscar.getTiempo());

    }

}