package com.example.gerardo.gestordeclientes.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.ui.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

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


    }

    public void displayView(int viewId) {
        Fragment fragment = null;
        String title = null;

        switch (viewId) {
            case R.id.nav_inicio:
                fragment = new MainFragment();
                title = "Inicio";
                break;
//            case R.id.nav_agregar:
//                fragment = new AgregarFragment();
//                title = "Agregar Digimon";
//                break;
//            case R.id.nav_digimon:
//                fragment = new DigimonFragment();
//                title = "Digimons";
//                break;
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
//        drawer.closeDrawers();
    }

}
