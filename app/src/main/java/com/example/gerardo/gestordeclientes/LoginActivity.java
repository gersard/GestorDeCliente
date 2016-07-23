package com.example.gerardo.gestordeclientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    }

    @OnClick(R.id.btn_ingresar)
    public void ingresar() {
    }

    @OnClick(R.id.btn_registrar)
    public void registrar() {
    }

}
