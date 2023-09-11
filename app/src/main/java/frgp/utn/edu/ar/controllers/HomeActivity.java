package frgp.utn.edu.ar.controllers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    TextView tvUserName, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpToolbar();

        String userName = getIntent().getStringExtra("userName");
        String email = getIntent().getStringExtra("email");

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        View headerView = navigationView.getHeaderView(0);
        tvUserName = (TextView) headerView.findViewById(R.id.textUser);
        tvEmail = (TextView) headerView.findViewById(R.id.textMail);
        tvUserName.setText(userName);
        tvEmail.setText(email);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.nav_Parqueos){
                    Intent parqueos = new Intent(HomeActivity.this, ParqueosActivity.class);
                    parqueos.putExtra("userName", userName);
                    startActivity(parqueos);
                }
                if(id==R.id.nav_user){
                    //open modal on click
                    AlertDialog.Builder contact = new AlertDialog.Builder(HomeActivity.this);
                    contact.setTitle("INFORMACION DEL USUARIO");
                    contact.setMessage("Nombre de Usuario: " + userName + "\n" + "Email: " + email + "\n" + "Contraseña: " + getIntent().getStringExtra("pass"));

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
    }

    public void CerrarSesion(){
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
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