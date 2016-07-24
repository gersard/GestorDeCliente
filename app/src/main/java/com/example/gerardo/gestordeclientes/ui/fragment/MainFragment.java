package com.example.gerardo.gestordeclientes.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.model.Cliente;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.txt_titulo_main_fragment)
    TextView txtTitulo;
    @Bind(R.id.recycler_main_fragment)
    RecyclerView recyclerMain;
    RealmResults<Cliente> listaClientes;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        listaClientes = Funciones.getClientes();
        if (listaClientes.size()==0){
            txtTitulo.setText("Sin clientes");
        }else{
            txtTitulo.setText("Clientes");
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
