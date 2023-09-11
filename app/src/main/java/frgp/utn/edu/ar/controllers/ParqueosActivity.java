package frgp.utn.edu.ar.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

import com.google.android.material.navigation.NavigationView;

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

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    private Button addParqueo;
    private String user;
    private TextView tvUserName,tvEmail, textUserParqueos;
    private IParqueoNegocio ParNeg = new ParqueoNegocio();
    private List<String> parqueos = new ArrayList<>();

    private GridView gvParqueos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parqueos);
        setUpToolbar();

        String userName = getIntent().getStringExtra("userName");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("pass");

        // NAVBAR
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        View headerView = navigationView.getHeaderView(0);

        // CONTROLES DEL HEADER
        tvUserName = (TextView) headerView.findViewById(R.id.textUser);
        tvEmail = (TextView) headerView.findViewById(R.id.textMail);
        tvEmail.setText(email);
        tvUserName.setText(userName);

        // CONTROLES DEL LAYOUT
        addParqueo = (Button) findViewById(R.id.addParqueoBtn);
        textUserParqueos = (TextView) findViewById(R.id.textUserParqueos);
        user = userName;
        textUserParqueos.setText("Parqueos de " + userName);
        listarParqueos();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.nav_Parqueos){
                    Intent parqueos = new Intent(ParqueosActivity.this, ParqueosActivity.class);
                    parqueos.putExtra("userName", userName);
                    parqueos.putExtra("email", email);
                    parqueos.putExtra("pass", password);
                    startActivity(parqueos);
                }
                if(id==R.id.nav_user){
                    //open modal on click
                    AlertDialog.Builder contact = new AlertDialog.Builder(ParqueosActivity.this);
                    contact.setTitle("INFORMACION DEL USUARIO");
                    contact.setMessage("Nombre de Usuario: " + userName + "\n" + "Email: " + email + "\n" + "Contraseña: " + password);

                    contact.setCancelable(false)
                            .setPositiveButton("OK", null);
                    contact.show();
                }
                if(id==R.id.nav_logout){
                    CerrarSesion();
                }
                return false;
            }
        });

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

    public void CerrarSesion(){
        Intent intent = new Intent(ParqueosActivity.this, MainActivity.class);
        intent.putExtra("userName", "");
        intent.putExtra("email", "");
        intent.putExtra("pass", "");
        startActivity(intent);
    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.syncState();

    }

}