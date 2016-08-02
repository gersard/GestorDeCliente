package com.example.gerardo.gestordeclientes.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.model.Cliente;
import com.example.gerardo.gestordeclientes.model.Marca;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.realm.implementation.RealmBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticaFragment extends Fragment {


    @Bind(R.id.chart)
    BarChart chart;

    public EstadisticaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_estadistica, container, false);
        ButterKnife.bind(this, root);

        float yValues [] = {10,20,30,40,50};
        String xValues [] = {"x1","x2","x3","x4","x5"};

        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Float> yVal = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();

        for (int i = 0; i < Funciones.getClientes().size(); i++) {
            nombres.add(Funciones.getClientes().get(i).getMoto().getMarca().getNombreMarca());
            yVal.add((float) realm.where(Marca.class).equalTo("nombreMarca",nombres.get(i)).count());
        }

//        drawBarChart(yValues,xValues);
        drawBarChart(yVal,nombres);
        return root;
    }

    private void drawBarChart(ArrayList<Float> yValues,ArrayList<String> xValues){
        chart.setDescription("Marcas");


        ArrayList<BarEntry> yData = new ArrayList<>();
        for (int i = 0; i < yValues.size(); i++) {
            yData.add(new BarEntry(yValues.get(i),i));
        }

        ArrayList<String> xData = new ArrayList<>();
        for (int i = 0; i < xValues.size(); i++) {
            xData.add(xValues.get(i));
        }

        BarDataSet barDataSet = new BarDataSet(yData,"");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(xData,barDataSet);
        barData.setValueTextSize(13f);
        barData.setValueTextColor(Color.BLACK);

        chart.setData(barData);
        chart.invalidate();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
