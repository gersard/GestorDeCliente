package com.example.gerardo.gestordeclientes.ui.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.model.Cliente;
import com.example.gerardo.gestordeclientes.model.Moto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarFragment extends Fragment {


    @Bind(R.id.edit_form_rut)
    EditText editRut;
    @Bind(R.id.edit_form_nombre)
    EditText editNombre;
    @Bind(R.id.edit_form_apellido)
    EditText editApellido;
    @Bind(R.id.edit_form_telefono)
    EditText editTelefono;
    @Bind(R.id.edit_form_correo)
    EditText editCorreo;
    @Bind(R.id.spinner_form_comuna)
    Spinner spnComuna;
    @Bind(R.id.spinner_form_marca_moto)
    Spinner spnMarcaMoto;
    @Bind(R.id.spinner_form_modelo_moto)
    Spinner spnModeloMoto;
    @Bind(R.id.spinner_form_año_moto)
    Spinner spnAnioMoto;
    @Bind(R.id.btn_form_registrar)
    Button btnRegistrar;

    public AgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar_actualizar, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_form_registrar)
    public void onClick() {
        String rut = editRut.getText().toString().trim();
        String nombre = editNombre.getText().toString().trim();
        String apellido = editApellido.getText().toString().trim();
        int telefono = Integer.parseInt(editTelefono.getText().toString().trim());
        String correo = editCorreo.getText().toString().trim();
        String comuna = spnComuna.getSelectedItem().toString().trim();
        String marca = spnMarcaMoto.getSelectedItem().toString().trim();
        String modelo = spnModeloMoto.getSelectedItem().toString().trim();
        int año = Integer.parseInt(spnAnioMoto.getSelectedItem().toString().trim());

        if (validarIngreso(nombre,rut,apellido,telefono,correo,comuna,marca,modelo,año)){
            if (Funciones.validarRut(rut)){
                Cliente cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setRut(rut);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setCorreo(correo);
                cliente.setComuna(comuna);

                Moto moto = new Moto();
                moto.setMarca(marca);
                moto.setModelo(modelo);
                moto.setAño(año);

                cliente.setMoto(moto);

                new AsyncTaskRegistrarCliente().execute(cliente);

            }else{
                Toast.makeText(getActivity(), "Rut inválido", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        }



    }

    private boolean validarIngreso(String nombre,String rut,String apellido,int telefono,
                                   String correo,String comuna,String marca,String modelo,int año){
        if (nombre.equals("")){
            return false;
        }
        if (rut.equals("")){
            return false;
        }
        if (apellido.equals("")){
            return false;
        }
        if (telefono == 0){
            return false;
        }
        if (correo.equals("")){
            return false;
        }
        if (comuna.equals("Comuna")){
            return false;
        }
        if (marca.equals("Marca")){
            return false;
        }
        if (modelo.equals("Modelo")){
            return false;
        }
        if (año == 0){
            return false;
        }
        return true;

    }

    private void rellenarSpinnerAño(){
        List<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);

        spnAnioMoto.setAdapter(adapter);
    }

    private class AsyncTaskRegistrarCliente extends AsyncTask<Cliente,Void,Void> {
        ProgressDialog dialog = new ProgressDialog(getActivity());


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Registrando...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Cliente... params) {
            Funciones.registrarCliente(params[0]);
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }


    }


}
