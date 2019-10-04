package com.example.uniscovery;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.data.BarEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class FragmentMostrarResultados extends Fragment {

    GraphView MiGraficoDeBarras;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View VistaDevolver = inflater.inflate(R.layout.mostrar_listado_carreras, null, true);
        Log.d("Resultados", "Estoy por declarar las entradas de3l grafico de barras ");
        MiGraficoDeBarras=new GraphView(this.getContext());
        MiGraficoDeBarras=(GraphView) VistaDevolver.findViewById(R.id.MiGrafico);

        //List<BarEntry> entradas = new ArrayList<>();
        /*MainActivity main = (MainActivity) getActivity();
        RespuestaTest respuestaTest = main.getResultadosUltimoTest();
        entradas.add(new BarEntry(1f, (respuestaTest.getInteresC() / 10) * 100));
        entradas.add(new BarEntry(1f, (respuestaTest.getAptitudC() / 4) * 100));

        entradas.add(new BarEntry(2f,(respuestaTest.getInteresH()/10)*100));
        entradas.add(new BarEntry(2f,(respuestaTest.getAptitudH()/4)*100));

        entradas.add(new BarEntry(3f,(respuestaTest.getInteresA()/10)*100));
        entradas.add(new BarEntry(3f,(respuestaTest.getAptitudA()/4)*100));

        entradas.add(new BarEntry(4f,(respuestaTest.getInteresS()/10)*100));
        entradas.add(new BarEntry(4f,(respuestaTest.getAptitudS()/4)*100));

        entradas.add(new BarEntry(5f,(respuestaTest.getInteresI()/10)*100));
        entradas.add(new BarEntry(5f,(respuestaTest.getAptitudI()/4)*100));

        entradas.add(new BarEntry(6f,(respuestaTest.getInteresD()/10)*100));
        entradas.add(new BarEntry(6f,(respuestaTest.getAptitudD()/4)*100));

        entradas.add(new BarEntry(7f,(respuestaTest.getInteresE()/10)*100));
        entradas.add(new BarEntry(7f,(respuestaTest.getAptitudE()/4)*100));*/

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
        });
        if(MiGraficoDeBarras==null)
        {
            Log.d("Prueba","Esta null");
        }
        MiGraficoDeBarras.addSeries(series);

        // ESTILO
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(20);

        // DIBUJANDO LOS VALORES
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

        return VistaDevolver;
    }
}
