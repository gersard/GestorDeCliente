package com.example.gerardo.gestordeclientes.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.ui.fragment.AgregarFragment;
import com.example.gerardo.gestordeclientes.ui.fragment.BuscarFragment;
import com.example.gerardo.gestordeclientes.ui.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        displayView(R.id.nav_inicio);
        navView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void displayView(int viewId) {
        Fragment fragment = null;
        String title = null;

        switch (viewId) {
            case R.id.nav_inicio:
                fragment = new MainFragment();
                title = "Inicio";
                break;
            case R.id.nav_agregar:
                fragment = new AgregarFragment();
                title = "Registrar Cliente";
                break;
//            case R.id.nav_actualizar:
//                fragment = new BuscarFragment();
//                title = "Buscar Cliente";
//                break;
            case R.id.nav_buscar:
                fragment = new BuscarFragment();
                title = "Buscar Cliente";
                break;
            case R.id.nav_agregar_moto:
//                fragment = new BuscarFragment();
//                title = "Buscar Cliente";
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
        drawerLayout.closeDrawers();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
    }
}
