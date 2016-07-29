package com.example.gerardo.gestordeclientes.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gerardo on 29/07/2016.
 */
public class BuscarDialog extends Dialog {

    String origen;
    @Bind(R.id.dialog_spinner_parametro)
    MaterialSpinner spnParametro;
    @Bind(R.id.dialog_edit_valor)
    EditText editValor;
    @Bind(R.id.dialog_btn_buscar)
    Button btnBuscar;
    @Bind(R.id.dialog_txt_valor)
    TextView txtValor;

    String parametro;

    public BuscarDialog(Context context, String origen) {
        super(context);
        this.origen = origen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDialog();
    }

    private void configureDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_buscar);
        ButterKnife.bind(this);

        spnParametro.setItems(getContext().getResources().getStringArray(R.array.parametros));

        spnParametro.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                parametro = item.toString();
                txtValor.setText(parametro);
            }
        });

    }



    @OnClick(R.id.dialog_btn_buscar)
    public void onClick() {
        Toast.makeText(getContext(), String.valueOf(Funciones.getClientesPorParametro(parametro.toLowerCase(),editValor.getText().toString()).size()), Toast.LENGTH_SHORT).show();
    }
}
