package com.example.gerardo.gestordeclientes.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.edit_usuario)
    EditText editUsuario;
    @Bind(R.id.edit_pass)
    EditText editPass;
    @Bind(R.id.btn_ingresar)
    Button btnIngresar;
    @Bind(R.id.btn_registrar)
    TextView btnRegistrar;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        prefs = getSharedPreferences("prefsTDM",MODE_PRIVATE);

        if (prefs.getBoolean("keepLogin",false)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

        try{
            Bundle extras = getIntent().getExtras();
            if (extras.getBoolean("reg")){
                Toast.makeText(LoginActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_ingresar)
    public void ingresar() {
        SharedPreferences.Editor editor = prefs.edit();
        if (Funciones.validarLogin(editUsuario.getText().toString().trim(),editPass.getText().toString().trim())){
            editor.putString("user",editUsuario.getText().toString().trim());
            editor.putString("pass",editPass.getText().toString().trim());
            editor.putBoolean("keepLogin",true);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "Datos inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_registrar)
    public void registrar() {
        Intent intent = new Intent(LoginActivity.this,RegistrarActivity.class);
        startActivity(intent);
    }

}
