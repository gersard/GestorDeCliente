package com.example.gerardo.gestordeclientes;

import com.example.gerardo.gestordeclientes.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;

/**
 * Created by Gerardo on 23/07/2016.
 */
public final class Funciones {


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

}
