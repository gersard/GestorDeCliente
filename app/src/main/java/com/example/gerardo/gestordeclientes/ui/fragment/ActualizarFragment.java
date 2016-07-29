package com.example.gerardo.gestordeclientes.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.model.Cliente;
import com.example.gerardo.gestordeclientes.model.Marca;
import com.example.gerardo.gestordeclientes.model.Modelo;
import com.example.gerardo.gestordeclientes.model.Moto;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarFragment extends Fragment {


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
    MaterialSpinner spnComuna;
    @Bind(R.id.spinner_form_marca_moto)
    MaterialSpinner spnMarcaMoto;
    @Bind(R.id.spinner_form_modelo_moto)
    MaterialSpinner spnModeloMoto;
    @Bind(R.id.spinner_form_año_moto)
    MaterialSpinner spnAniooMoto;
    @Bind(R.id.btn_form_registrar)
    Button btnActualizar;

    String comunaSeleccionada;
    String marcaMotoSeleccionada;
    String modeloMotoSeleccionada;
    int añoMotoSeleccionada = 0;
    List<String> modelos;
    List<String> marcas;
    String rut;

    public ActualizarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelos = new ArrayList<>();
        modelos.add("Modelo");
        marcas = new ArrayList<>();
        marcas.add("Marca");
        Bundle args = getArguments();
        rut = args.getString("rut", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar_actualizar, container, false);
        ButterKnife.bind(this, root);
        rellenarSpinners();
        spnComuna.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                comunaSeleccionada = item.toString().trim();
            }
        });
        spnAniooMoto.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                añoMotoSeleccionada = Integer.parseInt(item.toString().trim());
            }
        });
        spnMarcaMoto.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                marcaMotoSeleccionada = item.toString().trim();
                rellenarSpinnerModelo(marcaMotoSeleccionada);
            }
        });
        spnModeloMoto.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                modeloMotoSeleccionada = item.toString().trim();
            }
        });
        btnActualizar.setText("Actualizar Cliente");
        editRut.setEnabled(false);
        cargarInformacion();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_form_registrar)
    public void onClick() {
        String rut = editRut.getText().toString();
        String nombre = editNombre.getText().toString().trim();
        String apellido = editApellido.getText().toString().trim();
        int tel = Integer.parseInt(editTelefono.getText().toString().trim());
        String correo = editCorreo.getText().toString().trim();
        String comuna = comunaSeleccionada;
        String marca = marcaMotoSeleccionada;
        String modelo = modeloMotoSeleccionada;
        int año = añoMotoSeleccionada;

        Cliente cliente = new Cliente();
        cliente.setRut(rut);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(tel);
        cliente.setCorreo(correo);
        cliente.setComuna(comuna);

        Moto moto = new Moto();
        Marca marcaMoto = new Marca();
        Modelo modeloMoto = new Modelo();

        marcaMoto.setNombreMarca(marca);
        modeloMoto.setNombreModelo(modelo);
        moto.setMarca(marcaMoto);
        moto.setModelo(modeloMoto);
        moto.setAño(año);

        cliente.setMoto(moto);

        Funciones.actualizarCliente(cliente);

        Toast.makeText(getActivity(), "Cliente actualizado correctamente", Toast.LENGTH_SHORT).show();

    }

    private void cargarInformacion(){
        Cliente cliente = Funciones.getClienteByRut(rut);
        editRut.setText(cliente.getRut());
        editNombre.setText(cliente.getNombre());
        editApellido.setText(cliente.getApellido());
        editTelefono.setText(String.valueOf(cliente.getTelefono()));
        editCorreo.setText(cliente.getCorreo());
        spnComuna.setText(cliente.getComuna());
        spnMarcaMoto.setText(cliente.getMoto().getMarca().getNombreMarca());
        spnModeloMoto.setText(cliente.getMoto().getModelo().getNombreModelo());
        spnAniooMoto.setText(String.valueOf(cliente.getMoto().getAño()));
    }

    private void rellenarSpinners() {
        //SPINNER COMUNA
        spnComuna.setItems(getActivity().getResources().getStringArray(R.array.comunas));
        //SPINEER AÑO
        List<String> years = new ArrayList<String>();
        years.add("Año");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        spnAniooMoto.setItems(years);
        //SPINNER MARCAS
        for (Marca marca : Funciones.getMarcasMoto()) {
            marcas.add(marca.getNombreMarca());
        }
        spnMarcaMoto.setItems(marcas);
        //SPINNER MODELOS
        spnModeloMoto.setItems(modelos);

    }
    private void rellenarSpinnerModelo(String marca) {

        Marca m = Funciones.getModelosByMarca(marca);

        for (Modelo n : m.getModelos()) {
            modelos.add(n.getNombreModelo());
        }
        spnModeloMoto.setItems(modelos);
    }
}
