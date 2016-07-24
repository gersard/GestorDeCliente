package com.example.gerardo.gestordeclientes.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gerardo.gestordeclientes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarFragment extends Fragment {


    public AgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_agregar_actualizar, container, false);
        return root;
    }

}
