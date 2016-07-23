package com.example.gerardo.gestordeclientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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
        if (Funciones.validarLogin(editUsuario.getText().toString(),editPass.getText().toString())){
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
