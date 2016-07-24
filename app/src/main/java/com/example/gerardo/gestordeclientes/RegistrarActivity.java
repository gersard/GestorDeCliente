package com.example.gerardo.gestordeclientes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.model.Usuario;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarActivity extends AppCompatActivity {

    @Bind(R.id.edit_reg_username)
    EditText editRegUsername;
    @Bind(R.id.edit_reg_password)
    EditText editRegPassword;
    @Bind(R.id.edit_reg_password2)
    EditText editRegPassword2;
    @Bind(R.id.edit_reg_nombre)
    EditText editRegNombre;
    @Bind(R.id.edit_reg_apellido)
    EditText editRegApellido;
    @Bind(R.id.edit_reg_email)
    EditText editRegEmail;
    @Bind(R.id.btn_reg_registrar)
    Button btnRegRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_reg_registrar)
    public void onClick() {
        if (validarIngreso(editRegUsername.getText().toString().trim(),editRegPassword.getText().toString().trim(),
                editRegPassword2.getText().toString().trim(),editRegNombre.getText().toString().trim()
                ,editRegApellido.getText().toString().trim(), editRegEmail.getText().toString().trim())){

            if (validarPassword(editRegPassword.getText().toString().trim(),editRegPassword2.getText().toString().trim())){
                if (!Funciones.validarUsuario(editRegUsername.getText().toString().trim())){
                    //REGISTRO EN SEGUNDO PLANO
                    new AsyncTaskRegistrar().execute(editRegUsername.getText().toString().trim(),editRegPassword.getText().toString().trim()
                            ,editRegNombre.getText().toString().trim(),editRegApellido.getText().toString().trim(),
                            editRegEmail.getText().toString().trim());


                    Intent intent = new Intent(RegistrarActivity.this,LoginActivity.class);
                    intent.putExtra("reg",true);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegistrarActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(RegistrarActivity.this, "Las contraseñas son diferentes", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validarPassword(String pass1, String pass2){
        if (pass1.equals(pass2)){
            return true;
        }else{
            return false;
        }
    }

    private boolean validarIngreso(String user, String pass, String pass2, String nombre, String apellido,
                                   String email){
        String aux = "Falta agregar: ";
        boolean isOk = true;
        if (user.equals("")){
            aux = aux+"Usuario, ";
            isOk = false;
        }
        if (pass.equals("")){
            aux = aux+"Password, ";
            isOk = false;
        }
        if (pass2.equals("")){
            isOk = false;
        }
        if (nombre.equals("")){
            aux = aux+"Nombre, ";
            isOk = false;
        }
        if (apellido.equals("")){
            aux = aux+"Apellido, ";
            isOk = false;
        }
        if (email.equals("")){
            aux = aux+"Email, ";
            isOk = false;
        }
        if (!isOk){
            Toast.makeText(RegistrarActivity.this, aux, Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }


    }

    private class AsyncTaskRegistrar extends AsyncTask<String,Void,Void>{
        ProgressDialog dialog = new ProgressDialog(RegistrarActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Registrando...");
            dialog.setCancelable(false);
            dialog.show();
        }


        @Override
        protected Void doInBackground(String... params) {
            Funciones.registrarUsuario(params[0],params[1],params[2],params[3],params[4]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }


    }

}
