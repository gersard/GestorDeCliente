package com.example.gerardo.gestordeclientes.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.adapter.ListaClientesAdapter;
import com.example.gerardo.gestordeclientes.model.Cliente;

import java.util.ArrayList;

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

    ListaClientesAdapter adapter;

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
        ArrayList<Cliente> cl = new ArrayList<>(Funciones.getClientes());
        adapter = new ListaClientesAdapter(getActivity(),cl);
        recyclerMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMain.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailClienteFragment detailClienteFragment = new DetailClienteFragment();
                Bundle args = new Bundle();
                args.putString("rut",adapter.getRutCliente(recyclerMain.getChildAdapterPosition(v)));
                detailClienteFragment.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, detailClienteFragment)
                        .commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
