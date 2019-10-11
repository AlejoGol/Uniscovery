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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentMostrarResultados extends Fragment {

    BarChart MiGraficoDeBarras;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList <BarEntry> barEntries;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View VistaDevolver = inflater.inflate(R.layout.pantalla_resultados_test, null, true);
        Log.d("Resultados", "Estoy por declarar las barEntries de3l grafico de barras ");
        MiGraficoDeBarras=(BarChart) VistaDevolver.findViewById(R.id.MiGrafico);




        getEntries();
        barDataSet = new BarDataSet(barEntries, "Mi grafico para el proyecto");
        barData = new BarData(barDataSet);
        barData.setBarWidth(0.3f);
        MiGraficoDeBarras.setData(barData);
        //MiGraficoDeBarras.setScaleXEnabled(true);
        MiGraficoDeBarras.setFitBars(true);
        Log.d("Scala"," "+MiGraficoDeBarras.getVisibleXRange());
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        MiGraficoDeBarras.fitScreen();
        MiGraficoDeBarras.invalidate();
        MiGraficoDeBarras.setVisibleXRangeMaximum(14); // allow 20 values to be displayed at once on the x-axis, not more
        MiGraficoDeBarras.moveViewToX(0);
        return VistaDevolver;
}
    private void getEntries() {
        MainActivity main = (MainActivity) getActivity();
        RespuestaTest respuestaTest = main.getResultadosUltimoTest();
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 1));
        barEntries.add(new BarEntry(1,4f, 1));
        barEntries.add(new BarEntry(2f, 1));
        barEntries.add(new BarEntry(2,4f, 1));
        barEntries.add(new BarEntry(3f, 1));
        barEntries.add(new BarEntry(3,4f, 1));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(4,4f, 1));
        barEntries.add(new BarEntry(5f, 1));
        barEntries.add(new BarEntry(5,4f, 1));
        barEntries.add(new BarEntry(6f, 1));
        barEntries.add(new BarEntry(6,4f, 1));
        barEntries.add(new BarEntry(7f, 1));
        barEntries.add(new BarEntry(7,4f, 1));

       /* barEntries.add(new BarEntry(1f, (respuestaTest.getInteresC() / 10) * 100));
        Log.d("ValorInteres",""+respuestaTest.getInteresC());
        barEntries.add(new BarEntry(1f, (respuestaTest.getAptitudC() / 4) * 100));
        barEntries.add(new BarEntry(2f,(respuestaTest.getInteresH()/10)*100));
        barEntries.add(new BarEntry(2f,(respuestaTest.getAptitudH()/4)*100));
        barEntries.add(new BarEntry(3f,(respuestaTest.getInteresA()/10)*100));
        barEntries.add(new BarEntry(3f,(respuestaTest.getAptitudA()/4)*100));
        barEntries.add(new BarEntry(4f,(respuestaTest.getInteresS()/10)*100));
        barEntries.add(new BarEntry(4f,(respuestaTest.getAptitudS()/4)*100));
        barEntries.add(new BarEntry(5f,(respuestaTest.getInteresI()/10)*100));
        barEntries.add(new BarEntry(5f,(respuestaTest.getAptitudI()/4)*100));
        barEntries.add(new BarEntry(6f,(respuestaTest.getInteresD()/10)*100));
        barEntries.add(new BarEntry(6f,(respuestaTest.getAptitudD()/4)*100));
        barEntries.add(new BarEntry(7f,(respuestaTest.getInteresE()/10)*100));
        barEntries.add(new BarEntry(7f,(respuestaTest.getAptitudE()/4)*100));*/
    }
}
