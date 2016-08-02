package com.example.gerardo.gestordeclientes;

import android.content.Context;

import com.example.gerardo.gestordeclientes.model.Cliente;
import com.example.gerardo.gestordeclientes.model.Marca;
import com.example.gerardo.gestordeclientes.model.Modelo;
import com.example.gerardo.gestordeclientes.model.Moto;
import com.example.gerardo.gestordeclientes.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
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
    public static boolean validarIfClientExist(String rut){
        Realm realm = Realm.getDefaultInstance();
        Cliente cliente = new Cliente();
        cliente = realm.where(Cliente.class).equalTo("rut",rut).findFirst();
        if (cliente == null){
            return false;
        }else{
            return true;
        }
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
                Marca marca = realm.where(Marca.class).equalTo("nombreMarca",clienteExt.getMoto().getMarca().getNombreMarca()).findFirst();
                Modelo modelo = realm.where(Modelo.class).equalTo("nombreModelo",clienteExt.getMoto().getModelo().getNombreModelo()).findFirst();

                moto.setMarca(marca);
                moto.setModelo(modelo);
                moto.setAño(clienteExt.getMoto().getAño());

                cliente.setMoto(moto);
            }
        });
    }
    public static void actualizarCliente (final Cliente clienteExt){
        final String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cliente cliente = realm.where(Cliente.class).equalTo("rut",clienteExt.getRut()).findFirst();
                cliente.setNombre(clienteExt.getNombre());
                cliente.setApellido(clienteExt.getApellido());
                cliente.setTelefono(clienteExt.getTelefono());
                cliente.setCorreo(clienteExt.getCorreo());
                cliente.setComuna(clienteExt.getComuna());

                Moto moto = realm.createObject(Moto.class);
                moto.setIdMoto(fecha);

                Marca marca = realm.createObject(Marca.class);
                marca.setNombreMarca(clienteExt.getMoto().getMarca().getNombreMarca());

                Modelo modelo = realm.createObject(Modelo.class);
                modelo.setNombreModelo(clienteExt.getMoto().getModelo().getNombreModelo());

                moto.setMarca(marca);
                moto.setModelo(modelo);
                moto.setAño(clienteExt.getMoto().getAño());

                cliente.setMoto(moto);

            }
        });
    }
    public static void eliminarCliente(String rut){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Cliente> cliente = realm.where(Cliente.class).equalTo("rut",rut).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cliente.deleteAllFromRealm();
            }
        });
    }
    public static Cliente getClienteByRut(String rut){
        Realm realm = Realm.getDefaultInstance();
        Cliente cliente = realm.where(Cliente.class).equalTo("rut",rut).findFirst();
        return cliente;
    }
    public static RealmResults<Cliente> getClientes(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Cliente> listaClientes = realm.where(Cliente.class).findAll();
        listaClientes.sort("nombre", Sort.ASCENDING);
        return listaClientes;
    }
    public static RealmResults<Cliente> getClientesPorParametro(String parametro, String valor){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Cliente> listaClientes = realm.where(Cliente.class).equalTo(parametro,valor).findAll();
        listaClientes.sort("nombre",Sort.ASCENDING);
        return listaClientes;
    }

    //METODOS PARA LA MOTO
    public static ArrayList<Marca> getMarcasMoto(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Marca> listaMarcas = realm.where(Marca.class).findAllSorted("nombreMarca",Sort.DESCENDING);
        ArrayList<Marca> marcas = new ArrayList<>(listaMarcas);
        return marcas;
    }
    public static Marca getModelosByMarca( String marca){
        Realm realm = Realm.getDefaultInstance();
        Marca listaModelos = realm.where(Marca.class).equalTo("nombreMarca",marca).findFirst();
        return listaModelos;
    }
    public static void registrarMoto(final String nMarca, final String nModelo){
        final String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                String nombreMarca = nMarca.substring(0, 1).toUpperCase() + nMarca.substring(1);
                String nombreModelo = nModelo.substring(0, 1).toUpperCase() + nModelo.substring(1);

                Marca marca = realm.where(Marca.class).equalTo("nombreMarca",nombreMarca).findFirst();

                Modelo modelo = realm.createObject(Modelo.class);
                modelo.setIdModelo(fecha);
                modelo.setNombreModelo(nombreModelo);

                RealmList<Modelo> lModelo;
                if (marca == null){
                    Marca marca1 = realm.createObject(Marca.class);
                    marca1.setIdMarca(fecha);
                    marca1.setNombreMarca(nMarca);

                    lModelo = new RealmList<>();
                    lModelo.add(modelo);
                    marca1.setModelos(lModelo);
                }else{
                    lModelo = marca.getModelos();
                    lModelo.add(modelo);
                    marca.setModelos(lModelo);
                }

            }
        });
    }


    //METODOS GENERALES
    public static void eliminarDatos(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }


//    public static void addMarca(){
//        final String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
//        Realm realm = Realm.getDefaultInstance();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Marca marca = realm.createObject(Marca.class);
//                marca.setIdMarca(fecha);
//                marca.setNombreMarca("Honda");
//                RealmList<Modelo> modelos = new RealmList<Modelo>();
//                Modelo mo = realm.createObject(Modelo.class);
//                mo.setIdModelo("aaaaaaaaaaaaabbbbbbbbbbbbbccccccccccc");
//                mo.setNombreModelo("XBR 250");
//                modelos.add(mo);
//                marca.setModelos(modelos);
//            }
//        });
//    }

}
