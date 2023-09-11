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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import frgp.utn.edu.ar.DAO.ParqueoDAO;
import frgp.utn.edu.ar.DAOImpl.ParqueoDAOImpl;
import frgp.utn.edu.ar.DAOImpl.UsuarioDAOImpl;
import frgp.utn.edu.ar.entidades.Parqueo;
import frgp.utn.edu.ar.entidades.ParqueoAdapter;
import frgp.utn.edu.ar.negocio.IParqueoNegocio;
import frgp.utn.edu.ar.negocioImpl.ParqueoNegocio;

public class ParqueosActivity extends AppCompatActivity {

    private Button addParqueo;
    private String user;
    private TextView tvUserName;
    private IParqueoNegocio ParNeg = new ParqueoNegocio();
    private List<String> parqueos = new ArrayList<>();

    private GridView gvParqueos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parqueos);
        addParqueo = (Button) findViewById(R.id.addParqueoBtn);
        tvUserName = (TextView) findViewById(R.id.textUserParqueos);
        String userName = getIntent().getStringExtra("userName");
        user = userName;
        tvUserName.setText("Parqueos de " + userName);
        listarParqueos();
        addParqueo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { showCustomDialog();
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
                if(!matricula.getText().toString().isEmpty() && !tiempo.getText().toString().isEmpty())
                {
                    Parqueo parq = new Parqueo(matricula.getText().toString(),Integer.parseInt(tiempo.getText().toString()), Calendar.getInstance().getTime(), user, true);
                    if(escribirDB(parq))
                    {
                        Toast.makeText(ParqueosActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                        listarParqueos();
                    }
                    else
                    {
                        Toast.makeText(ParqueosActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(ParqueosActivity.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private boolean escribirDB(Parqueo nuevo){
        return ParNeg.guardarParqueo(this,nuevo);
    }

    private void listarParqueos(){

        parqueos.clear();

        List<Parqueo> listado = ParNeg.listarParqueos(this);

        if(listado!=null) {
            for (Parqueo park : listado) {
                Log.i("Parqueo " + park.getId(), "Patente: " + park.getPatente() + " | Tiempo: " + park.getTiempo() + " Minutos");
                parqueos.add("Patente: " + park.getPatente() + " | Tiempo: " + park.getTiempo()+ " Minutos");
                ParqueoAdapter adapter = new ParqueoAdapter(this,listado);
                gvParqueos = (GridView) findViewById(R.id.gvParqueos);
                gvParqueos.setAdapter(adapter);
            }
        } else{
            Log.i("Parqueo ", "No tienes parqueos guardados.");
            parqueos.add("No tienes parqueos guardados.");
        }
    }

    private void buscarParqueo(String patente){
        Parqueo buscar = ParNeg.buscarPorPatente(this, patente);
        Log.i("Parqueo Buscado " + buscar.getId(), "Patente: " + buscar.getPatente() + " | Tiempo: " + buscar.getTiempo());

    }

}