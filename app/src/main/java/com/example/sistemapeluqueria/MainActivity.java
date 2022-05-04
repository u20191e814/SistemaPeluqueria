package com.example.sistemapeluqueria;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sistemapeluqueria.ui.InicioFragment;
import com.example.sistemapeluqueria.ui.MiCuentaFragment;
import com.example.sistemapeluqueria.ui.MostrarEspecialistasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.ToolbarKt;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//implements NavigationView.OnNavigationItemSelectedListener
    private AppBarConfiguration mAppBarConfiguration;
    public String data_Login ;
    public int id_cliente ;
    private DrawerLayout drawer;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        data_Login = intent.getStringExtra("data");
        try {
            JSONObject obj = new JSONObject(data_Login);

            id_cliente = obj.getInt("id_cliente");
        }
        catch (Throwable tx) {
            Log.e("Main", "Error en parsear json ");
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.navMiCuenta, R.id.nav_servicioscontratados )
                .setOpenableLayout(drawer)
                .build();


        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i ++){
            MenuItem menuItem = menu.getItem(i);

            if (menuItem.getTitle().equals("Cerrar Sesión")){

                SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
                spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.estadoCancelado)), 0, spanString.length(), 0);
                menuItem.setTitle(spanString);

            }

        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


          item.setChecked(true);
        switch (id){

            case R.id.nav_inicio:
                navController.navigate(R.id.nav_inicio);
                break;
            case R.id.navMiCuenta:
                navController.navigate(R.id.navMiCuenta);
                break;
            case R.id.nav_servicioscontratados:
               navController.navigate(R.id.nav_servicioscontratados);
               break;
            case R.id.navcerrarsesion:
                finish();
                System.exit(0);
        }



         return  true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}