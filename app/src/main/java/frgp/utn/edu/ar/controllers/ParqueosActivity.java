package frgp.utn.edu.ar.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            public void onClick(View view) { showCustomDialog();
                /*AlertDialog alertDialog = new AlertDialog.Builder(ParqueosActivity.this).create(); //Read Update
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

                alertDialog.show();*/
            }
        });
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog (ParqueosActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        final EditText matricula = dialog.findViewById(R.id.etNumeroMatricula);
        final EditText tiempo = dialog.findViewById(R.id.etTiempo);
        final Button registrar = dialog.findViewById(R.id.btnRegistrarRP);
        final Button cancelar = dialog.findViewById(R.id.btnCancelarRP);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ParqueosActivity.this, matricula.getText().toString() + " & " + tiempo.getText().toString(), Toast.LENGTH_LONG).show();
                if(matricula.getText().toString()!=null&&tiempo.getText().toString()!=null)
                {
                    Parqueo parq = new Parqueo(matricula.getText().toString(),Integer.parseInt(tiempo.getText().toString()));
                    if(escribirDB(parq))
                    {
                        Toast.makeText(ParqueosActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ParqueosActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
                dialog.dismiss();
            }
        });

        dialog.show();
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