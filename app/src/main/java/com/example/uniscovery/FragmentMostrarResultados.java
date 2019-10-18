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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentMostrarResultados extends Fragment {

    BarChart MiGraficoDeBarras,MiGraficoDeAptitud;
    BarData barData,BarDataAptitud;
    BarDataSet barDataSet,barDataSetAptitudes;
    ArrayList <BarEntry> barEntries,barEntriesAptitudes;
    RespuestaTest respuestas;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View VistaDevolver = inflater.inflate(R.layout.pantalla_resultados_test, null, true);
        Log.d("Resultados", "Estoy por declarar las barEntries de3l grafico de barras ");
        MiGraficoDeBarras = (BarChart) VistaDevolver.findViewById(R.id.MiGrafico);
        MiGraficoDeAptitud = (BarChart) VistaDevolver.findViewById(R.id.TablaAptitudes);
        MainActivity main = (MainActivity) getActivity();
        RespuestaTest respuestaTest = new RespuestaTest(main.getResultadosUltimoTest());
        Debug(respuestaTest);
        SetearTablaAptitudes(respuestaTest);
        SetearTablaIntereses(respuestaTest);
        //SetValoresDeInteres(respuestaTest);
        //SetValoresActitud(respuestaTest);
        return VistaDevolver;
}
    private void SetearTablaIntereses(final RespuestaTest respuestaTest)
    {
        respuestas=respuestaTest;
        barEntries=new ArrayList<>();
        SetEntriesIntereses(respuestaTest);
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
        MiGraficoDeBarras.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String Seleccionado="";
                if(e.getX()>1f&&e.getX()<1.3f&&e.getY()<=(respuestas.getInteresC()*100)/10)
                {
                    Seleccionado="Administrativas y Contables";
                }
                if(e.getX()>2f&&e.getX()<2.3f&&e.getY()<=(respuestas.getInteresH()*100)/10)
                {
                    Seleccionado="Humanísticas y Sociales";
                }
                if(e.getX()>3f&&e.getX()<3.3f&&e.getY()<=(respuestas.getInteresA()*100)/10)
                {
                    Seleccionado=" Artísticas";
                }
                if(e.getX()>4f&&e.getX()<4.3f&&e.getY()<=(respuestas.getInteresS()*100)/10)
                {
                    Seleccionado="Medicina y Cs. de la Salud";
                }
                if(e.getX()>5f&&e.getX()<5.3f&&e.getY()<=(respuestas.getInteresI()*100)/10)
                {
                    Seleccionado="Ingeniería y Computación";
                }
                if(e.getX()>6f&&e.getX()<6.3f&&e.getY()<=(respuestas.getInteresD()*100)/10)
                {
                    Seleccionado="Defensa y Seguridad";
                }
                if(e.getX()>7f&&e.getX()<7.3f&&e.getY()<=(respuestas.getInteresE()*100)/10)
                {
                    Seleccionado="Ciencias Exactas y Agrarias";
                }
                MainActivity main=(MainActivity)getActivity();
                main.setSeleccionado(Seleccionado);
            }

            @Override
            public void onNothingSelected() {

            }
        });

}
    private void SetValoresActitud(RespuestaTest respuesta) {

    }
    private void SetearTablaAptitudes(RespuestaTest respuestaTest)
    {
        barEntriesAptitudes=new ArrayList<>();
        SetEntriesAptitudes(respuestaTest);
        barDataSetAptitudes = new BarDataSet(barEntriesAptitudes, "Mi grafico para el proyecto");
        BarDataAptitud = new BarData(barDataSetAptitudes);
        BarDataAptitud.setBarWidth(0.3f);
        MiGraficoDeAptitud.setData(BarDataAptitud);
        //MiGraficoDeBarras.setScaleXEnabled(true);
        MiGraficoDeAptitud.setFitBars(true);
        Log.d("Scala"," "+MiGraficoDeAptitud.getVisibleXRange());
        barDataSetAptitudes.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSetAptitudes.setValueTextColor(Color.BLACK);
        barDataSetAptitudes.setValueTextSize(18f);
        MiGraficoDeAptitud.fitScreen();
        MiGraficoDeAptitud.invalidate();
        MiGraficoDeAptitud.setVisibleXRangeMaximum(14); // allow 20 values to be displayed at once on the x-axis, not more
        MiGraficoDeAptitud.moveViewToX(0);
    }
    private void Debug(RespuestaTest respuestaTest)
    {
        Log.d("ValoresDeTest","Aptitud C : "+respuestaTest.getAptitudC());
        Log.d("ValoresDeTest","Aptitud H : "+respuestaTest.getAptitudH());
        Log.d("ValoresDeTest","Aptitud A : "+respuestaTest.getAptitudC());
        Log.d("ValoresDeTest","Aptitud S : "+respuestaTest.getAptitudS());
        Log.d("ValoresDeTest","Aptitud I : "+respuestaTest.getAptitudI());
        Log.d("ValoresDeTest","Aptitud D : "+respuestaTest.getAptitudD());
        Log.d("ValoresDeTest","Aptitud E : "+respuestaTest.getAptitudE());
        Log.d("ValoresDeTest","Interes C : "+respuestaTest.getInteresC());
        Log.d("ValoresDeTest","Interes H : "+respuestaTest.getInteresH());
        Log.d("ValoresDeTest","Interes A : "+respuestaTest.getInteresA());
        Log.d("ValoresDeTest","Interes S : "+respuestaTest.getInteresS());
        Log.d("ValoresDeTest","Interes I : "+respuestaTest.getAptitudI());
        Log.d("ValoresDeTest","Interes D : "+respuestaTest.getInteresD());
        Log.d("ValoresDeTest","Interes E : "+respuestaTest.getInteresE());
    }
    private void SetEntriesAptitudes(RespuestaTest respuestaTest) {
        if(respuestaTest.getAptitudC()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(1f,(respuestaTest.getAptitudC()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(1f,1));
        }
        if(respuestaTest.getAptitudH()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(1f,(respuestaTest.getAptitudH()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(1f,1));
        }
        if(respuestaTest.getAptitudA()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(3f,(respuestaTest.getAptitudA()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(3f,1));
        }
        if(respuestaTest.getAptitudS()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(4f,(respuestaTest.getAptitudS()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(4f,1));
        }
        if(respuestaTest.getAptitudI()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(5f,(respuestaTest.getAptitudI()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(5f,1));
        }
        if(respuestaTest.getAptitudD()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(6f,(respuestaTest.getAptitudD()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(6f,1));
        }
        if(respuestaTest.getAptitudE()!=0)
        {
            barEntriesAptitudes.add(new BarEntry(7f,(respuestaTest.getAptitudE()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(7f,1));
        }
    }
    private void SetEntriesIntereses(RespuestaTest respuestaTest) {
        if(respuestaTest.getInteresC()!=0)
        {
            barEntries.add(new BarEntry(1f,(respuestaTest.getInteresC()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(1f,1));
        }
        if(respuestaTest.getInteresH()!=0)
        {
            barEntries.add(new BarEntry(1f,(respuestaTest.getInteresH()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(1f,1));
        }
        if(respuestaTest.getInteresA()!=0)
        {
            barEntries.add(new BarEntry(3f,(respuestaTest.getInteresA()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(3f,1));
        }
        if(respuestaTest.getAptitudS()!=0)
        {
            barEntries.add(new BarEntry(4f,(respuestaTest.getAptitudS()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(4f,1));
        }
        if(respuestaTest.getInteresI()!=0)
        {
            barEntries.add(new BarEntry(5f,(respuestaTest.getInteresI()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(5f,1));
        }
        if(respuestaTest.getInteresD()!=0)
        {
            barEntries.add(new BarEntry(6f,(respuestaTest.getInteresD()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(6f,1));
        }
        if(respuestaTest.getInteresE()!=0)
        {
            barEntries.add(new BarEntry(7f,(respuestaTest.getInteresE()*100)/4));
        }
        else
        {
            barEntries.add(new BarEntry(7f,1));
        }
    }

}
