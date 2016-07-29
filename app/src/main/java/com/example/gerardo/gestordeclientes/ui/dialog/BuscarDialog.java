package com.example.gerardo.gestordeclientes.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.ui.activity.MainActivity;
import com.example.gerardo.gestordeclientes.ui.fragment.DetailClienteFragment;
import com.example.gerardo.gestordeclientes.ui.fragment.MainFragment;
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
        parametro = "rut";
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

    private boolean validarDatos(String param, String valor){
        if (param == null){
            return false;
        }
        if (valor.equals("")){
            return false;
        }
        return true;
    }

    @OnClick(R.id.dialog_btn_buscar)
    public void onClick() {
        if (validarDatos(parametro,editValor.getText().toString())){

            if (Funciones.getClientesPorParametro(parametro.toLowerCase(),editValor.getText().toString()).size()>0){
                if (origen.equals("buscar")){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("parametro",parametro);
                    intent.putExtra("valor",editValor.getText().toString().trim());
                    getContext().startActivity(intent);
                }else{

                }
            }else{
                Toast.makeText(getContext(), "No existen clientes", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getContext(), "Rellene todos los campos antes de buscar", Toast.LENGTH_SHORT).show();
        }

    }
}
