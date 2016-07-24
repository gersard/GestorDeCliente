package com.example.gerardo.gestordeclientes;

import com.example.gerardo.gestordeclientes.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

/**
 * Created by Gerardo on 23/07/2016.
 */
public final class Funciones {

    //METODOS PARA EL USUARIO
    public static void registrarUsuario(final String user, final String pass, final String nombre, final String apellido, final String email){
        final String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Usuario usuario = realm.createObject(Usuario.class);
                usuario.setUsername(user);
                usuario.setPassword(pass);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setFechaRegistro(fecha);
                usuario.setEmail(email);
            }
        });

    }
    public static boolean validarUsuario(String user){
        Realm realm = Realm.getDefaultInstance();
        Usuario usuario = new Usuario();
        usuario = realm.where(Usuario.class).equalTo("username",user).findFirst();
        if (usuario == null){
            return false;
        }else{
            return true;
        }
    }
    public static boolean validarLogin(String user, String pass){
        Realm realm = Realm.getDefaultInstance();
        Usuario usuario = new Usuario();
        usuario = realm.where(Usuario.class).equalTo("username",user).equalTo("password",pass).findFirst();
        if (usuario == null){
            return false;
        }else{
            return true;
        }
    }

    //METODOS PARA EL CLIENTE

}
