package com.example.gerardo.gestordeclientes.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.model.Cliente;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailClienteFragment extends Fragment {


    @Bind(R.id.txt_client_detail_nombre_apellido)
    TextView txtClientDetailNombreApellido;
    @Bind(R.id.txt_client_detail_rut)
    TextView txtClientDetailRut;
    @Bind(R.id.txt_client_detail_telefono)
    TextView txtClientDetailTelefono;
    @Bind(R.id.txt_client_detail_correo)
    TextView txtClientDetailCorreo;
    @Bind(R.id.txt_client_detail_comuna)
    TextView txtClientDetailComuna;
    @Bind(R.id.txt_client_detail_marca)
    TextView txtClientDetailMarca;
    @Bind(R.id.txt_client_detail_modelo)
    TextView txtClientDetailModelo;
    @Bind(R.id.txt_client_detail_año)
    TextView txtClientDetailAnio;

    String rut;

    public DetailClienteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        rut = args.getString("rut", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_cliente, container, false);

        if (!rut.equals("")){
            cargarDatos(rut);
        }else{
            txtClientDetailNombreApellido.setText("Sin información del cliente");
        }

        ButterKnife.bind(this, root);
        return root;
    }

    private void cargarDatos(String rut) {
        Cliente cliente = Funciones.getClienteByRut(rut);
        txtClientDetailNombreApellido.setText(cliente.getNombre() +" "+ cliente.getApellido());
        txtClientDetailRut.setText(getActivity().getResources().getString(R.string.item_cliente_rut,cliente.getRut()));
        txtClientDetailTelefono.setText("+569"+cliente.getTelefono());
        txtClientDetailCorreo.setText(cliente.getCorreo());
        txtClientDetailComuna.setText(cliente.getComuna());
        txtClientDetailMarca.setText(cliente.getMoto().getMarca().getNombreMarca());
        txtClientDetailModelo.setText(cliente.getMoto().getModelo().getNombreModelo());
        txtClientDetailAnio.setText("Año: "+cliente.getMoto().getAño());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
