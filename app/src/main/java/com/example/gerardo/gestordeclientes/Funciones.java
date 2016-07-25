package com.example.gerardo.gestordeclientes;

import com.example.gerardo.gestordeclientes.model.Cliente;
import com.example.gerardo.gestordeclientes.model.Moto;
import com.example.gerardo.gestordeclientes.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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
    public static RealmResults<Cliente> getClientes(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Cliente> listaClientes = realm.where(Cliente.class).findAll();
        listaClientes.sort("nombre", Sort.ASCENDING);
        return listaClientes;
    }
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
    public static void registrarCliente(final Cliente clienteExt){
        final String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cliente cliente = realm.createObject(Cliente.class);
                cliente.setRut(clienteExt.getRut());
                cliente.setNombre(clienteExt.getNombre());
                cliente.setApellido(clienteExt.getApellido());
                cliente.setTelefono(clienteExt.getTelefono());
                cliente.setCorreo(clienteExt.getCorreo());
                cliente.setComuna(clienteExt.getComuna());

                Moto moto = realm.createObject(Moto.class);
                moto.setIdMoto(fecha);
                moto.setMarca(clienteExt.getMoto().getMarca());
                moto.setModelo(clienteExt.getMoto().getModelo());
                moto.setAño(clienteExt.getMoto().getAño());

                cliente.setMoto(moto);
            }
        });
    }

}
