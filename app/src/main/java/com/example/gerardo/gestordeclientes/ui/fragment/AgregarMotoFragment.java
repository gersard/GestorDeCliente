package com.example.gerardo.gestordeclientes.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarMotoFragment extends Fragment {


    @Bind(R.id.edit_agregar_moto_marca)
    EditText editMarca;
    @Bind(R.id.edit_agregar_moto_modelo)
    EditText editModelo;
    @Bind(R.id.btn_agregar_moto_agregar)
    Button btnAgregar;

    public AgregarMotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar_moto, container, false);

        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_agregar_moto_agregar)
    public void onClick() {
        if (validarCampos(editMarca.getText().toString().trim(),editModelo.getText().toString().trim())){
            Funciones.registrarMoto(editMarca.getText().toString().trim(),editModelo.getText().toString().trim());
            setearCampos();
            Toast.makeText(getActivity(), "Moto registrada con éxito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean validarCampos(String mar, String mod){
        if (mar.equals("")){
            return false;
        }
        if (mod.equals("")){
            return false;
        }
        return true;
    }
    private void setearCampos(){
        editModelo.setText("");
    }
}
