package com.example.gerardo.gestordeclientes.ui.fragment;


import android.app.ProgressDialog;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.adapter.ListaClientesAdapter;
import com.example.gerardo.gestordeclientes.model.Cliente;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {


    @Bind(R.id.spn_buscar_parametro)
    MaterialSpinner spnParametro;
    @Bind(R.id.txt_buscar_valor)
    TextView txtValor;
    @Bind(R.id.edit_buscar_valor)
    EditText editValor;
    @Bind(R.id.btn_buscar)
    Button btnBuscar;
    @Bind(R.id.layout_scroll)
    LinearLayout layoutScroll;
    @Bind(R.id.recycler_buscar)
    RecyclerView recyclerBuscar;

    ListaClientesAdapter adapter;
    String parametro;
    @Bind(R.id.obs_scrollview)
    ObservableScrollView obsScrollview;

    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_buscar, container, false);
        ButterKnife.bind(this, root);
        parametro = "rut";

        spnParametro.setItems(getActivity().getResources().getStringArray(R.array.parametros));

        spnParametro.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                txtValor.setText(item.toString());
                parametro = item.toString().toLowerCase();
            }
        });

        recyclerBuscar.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_buscar)
    public void onClick() {
        new AsyncTaskBuscarCliente().execute(parametro, editValor.getText().toString());
    }

    private class AsyncTaskBuscarCliente extends AsyncTask<String, Void, Void> {
        ProgressDialog dialog = new ProgressDialog(getContext());
        ArrayList<Cliente> cl;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Buscando...");
            dialog.setCancelable(false);
            dialog.show();
            cl = new ArrayList<>(Funciones.getClientesPorParametro(parametro, editValor.getText().toString().trim()));
        }

        @Override
        protected Void doInBackground(String... params) {
            adapter = new ListaClientesAdapter(getContext(), cl);
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerBuscar.setAdapter(adapter);
            dialog.dismiss();
        }


    }

}
