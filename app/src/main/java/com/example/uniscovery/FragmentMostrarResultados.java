package com.example.uniscovery;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
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
    BarDataSet barDataSet, barDataSet2, barDataSet3, barDataSet4, barDataSet5, barDataSet6, barDataSet7,barDataSetAptitudes;
    ArrayList <BarEntry> barEntries, barEntries2, barEntries3, barEntries4, barEntries5, barEntries6, barEntries7,barEntriesAptitudes;
    RespuestaTest respuestas;
    MainActivity main;
    String[] names;
    List<IBarDataSet> bars;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View VistaDevolver = inflater.inflate(R.layout.pantalla_resultados_test, null, true);
        Log.d("Resultados", "Estoy por declarar las barEntries de3l grafico de barras ");
        main=(MainActivity)getActivity();
        names= new String[]{"Administrativas y Contables", "Humanísticas y Sociales", "Artísticas", "Medicina y Cs. de la Salud", "Ingeniería y Computación", "Defensa y Seguridad", "Ciencias Exactas y Agrarias"};
        MiGraficoDeBarras = (BarChart) VistaDevolver.findViewById(R.id.MiGrafico);
        MiGraficoDeAptitud = (BarChart) VistaDevolver.findViewById(R.id.TablaAptitudes);
        MainActivity main = (MainActivity) getActivity();
        if(MainActivity.isPlayingAudio)
        {
            main.PararMusica();
        }
        RespuestaTest respuestaTest = new RespuestaTest(main.getResultadosUltimoTest());
        main.ReemplazarInformacionUltimoTest(respuestaTest,99);
        Debug(respuestaTest);
        SetearTablaAptitudes(respuestaTest);
        SetearTablaIntereses(respuestaTest);
        //SetValoresDeInteres(respuestaTest);
        //SetValoresActitud(respuestaTest);
        return VistaDevolver;
}
    private void SetearTablaIntereses(final RespuestaTest respuestaTest) {
        respuestas = respuestaTest;
        barEntries = new ArrayList<>();
        barEntries2 = new ArrayList<>();
        barEntries3 = new ArrayList<>();
        barEntries4 = new ArrayList<>();
        barEntries5 = new ArrayList<>();
        barEntries6 = new ArrayList<>();
        barEntries7 = new ArrayList<>();
        //barEntries.add(new BarEntry(0, ))
        SetEntriesIntereses(respuestaTest);
        bars = new ArrayList<IBarDataSet>();
        barDataSet = new BarDataSet(barEntries, names[0]);
        barDataSet.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.red));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        bars.add(barDataSet);
        barDataSet2 = new BarDataSet(barEntries2, names[1]);
        barDataSet2.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.orange));
        barDataSet2.setValueTextColor(Color.BLACK);
        barDataSet2.setValueTextSize(18f);
        bars.add(barDataSet2);
        barDataSet3 = new BarDataSet(barEntries3, names[2]);
        barDataSet3.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.yellow));
        barDataSet3.setValueTextColor(Color.BLACK);
        barDataSet3.setValueTextSize(18f);
        bars.add(barDataSet3);
        barDataSet4 = new BarDataSet(barEntries4, names[3]);
        barDataSet4.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.green));
        barDataSet4.setValueTextColor(Color.BLACK);
        barDataSet4.setValueTextSize(18f);
        bars.add(barDataSet4);
        barDataSet5 = new BarDataSet(barEntries5, names[4]);
        barDataSet5.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.blue));
        barDataSet5.setValueTextColor(Color.BLACK);
        barDataSet5.setValueTextSize(18f);
        bars.add(barDataSet5);
        barDataSet6 = new BarDataSet(barEntries6, names[5]);
        barDataSet6.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.indigo));
        barDataSet6.setValueTextColor(Color.BLACK);
        barDataSet6.setValueTextSize(18f);
        bars.add(barDataSet6);
        barDataSet7 = new BarDataSet(barEntries7, names[6]);
        barDataSet7.setColor(ContextCompat.getColor(main.getApplicationContext(), R.color.violet));
        barDataSet7.setValueTextColor(Color.BLACK);
        barDataSet7.setValueTextSize(18f);
        bars.add(barDataSet7);
        barData = new BarData(bars);
        barData.setBarWidth(0.5f);
        MiGraficoDeBarras.setData(barData);
        //MiGraficoDeBarras.setScaleXEnabled(true);
        MiGraficoDeBarras.setFitBars(true);
        Log.d("Scala"," "+MiGraficoDeBarras.getVisibleXRange());
        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        /*barDataSet.setColors(ContextCompat.getColor(main.getApplicationContext(),R.color.red),
                ContextCompat.getColor(main.getApplicationContext(),R.color.orange),
                ContextCompat.getColor(main.getApplicationContext(),R.color.yellow),
                ContextCompat.getColor(main.getApplicationContext(),R.color.green),
                ContextCompat.getColor(main.getApplicationContext(),R.color.blue),
                ContextCompat.getColor(main.getApplicationContext(),R.color.indigo),
                ContextCompat.getColor(main.getApplicationContext(),R.color.violet));
        */

        //MiGraficoDeBarras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(names));
        MiGraficoDeBarras.fitScreen();
        MiGraficoDeBarras.invalidate();
        MiGraficoDeBarras.setVisibleXRangeMaximum(14); // allow 20 values to be displayed at once on the x-axis, not more
        MiGraficoDeBarras.moveViewToX(0);
        MiGraficoDeBarras.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String Seleccionado="";
                Log.d("Value"," "+e.getX());
                Log.d("Value","Posicion Y "+e.getY());
                if(e.getX()==1f)
                {
                    Seleccionado="Administrativas y Contables";
                }
                if(e.getX()==2f)
                {
                    Seleccionado="Humanísticas y Sociales";
                }
                if(e.getX()==3f)
                {
                    Seleccionado=" Artísticas";
                }
                if(e.getX()==4f)
                {
                    Seleccionado="Medicina y Cs. de la Salud";
                }
                if(e.getX()==5f)
                {
                    Seleccionado="Ingeniería y Computación";
                }
                if(e.getX()==6f)
                {
                    Seleccionado="Defensa y Seguridad";
                }
                if(e.getX()==7f)
                {
                    Seleccionado="Ciencias Exactas y Agrarias";
                }
                Log.d("ValorSeleccionado"," valor "+Seleccionado);
                if(!Seleccionado.equals(""))
                {
                    MainActivity main=(MainActivity)getActivity();
                    main.setSeleccionado(Seleccionado);
                    main.ReemplazarDeGraficoALista();
                }
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
        //barDataSetAptitudes.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSetAptitudes.setColors(ContextCompat.getColor(main.getApplicationContext(),R.color.red),
                ContextCompat.getColor(main.getApplicationContext(),R.color.orange),
                ContextCompat.getColor(main.getApplicationContext(),R.color.yellow),
                ContextCompat.getColor(main.getApplicationContext(),R.color.green),
                ContextCompat.getColor(main.getApplicationContext(),R.color.blue),
                ContextCompat.getColor(main.getApplicationContext(),R.color.indigo),
                ContextCompat.getColor(main.getApplicationContext(),R.color.violet));
        barDataSetAptitudes.setValueTextColor(Color.BLACK);
        barDataSetAptitudes.setValueTextSize(18f);
        MiGraficoDeAptitud.fitScreen();
        MiGraficoDeAptitud.invalidate();
        MiGraficoDeAptitud.setVisibleXRangeMaximum(14); // allow 20 values to be displayed at once on the x-axis, not more
        MiGraficoDeAptitud.moveViewToX(0);
        MiGraficoDeAptitud.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String Seleccionado="";
                Log.d("Value"," "+e.getX());
                Log.d("Value","Posicion Y "+e.getY());
                
                float posicionX=e.getX();
                if(e.getX() == 1f)
                {
                    Seleccionado="Administrativas y Contables";
                }
                if(e.getX()==2f)
                {
                    Seleccionado="Humanísticas y Sociales";
                }
                if(e.getX()==3f)
                {
                    Seleccionado=" Artísticas";
                }
                if(e.getX()==4f)
                {
                    Seleccionado="Medicina y Cs. de la Salud";
                }
                if(e.getX()==5f)
                {
                    Seleccionado="Ingeniería y Computación";
                }
                if(e.getX()==6f)
                {
                    Seleccionado="Defensa y Seguridad";
                }
                if(e.getX()==7f)
                {
                    Seleccionado="Ciencias Exactas y Agrarias";
                }
                Log.d("ValorSeleccionado"," valor "+Seleccionado);
                if(!Seleccionado.equals(""))
                {
                    MainActivity main=(MainActivity)getActivity();
                    main.setSeleccionado(Seleccionado);
                    main.ReemplazarDeGraficoALista();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
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
            barEntriesAptitudes.add(new BarEntry(2f,(respuestaTest.getAptitudH()*100)/4));
        }
        else
        {
            barEntriesAptitudes.add(new BarEntry(2f,1));
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
            barEntries.add(new BarEntry(1f,(respuestaTest.getInteresC()*100)/10));
        }
        else
        {
            barEntries.add(new BarEntry(1f,1));
        }
        if(respuestaTest.getInteresH()!=0)
        {
            barEntries2.add(new BarEntry(2f,(respuestaTest.getInteresH()*100)/10));
        }
        else
        {
            barEntries2.add(new BarEntry(2f,1));
        }
        if(respuestaTest.getInteresA()!=0)
        {
            barEntries3.add(new BarEntry(3f,(respuestaTest.getInteresA()*100)/10));
        }
        else
        {
            barEntries3.add(new BarEntry(3f,1));
        }
        if(respuestaTest.getInteresS()!=0)
        {
            barEntries4.add(new BarEntry(4f,(respuestaTest.getInteresS()*100)/10));
        }
        else
        {
            barEntries4.add(new BarEntry(4f,1));
        }
        if(respuestaTest.getInteresI()!=0)
        {
            barEntries5.add(new BarEntry(5f,(respuestaTest.getInteresI()*100)/10));
        }
        else
        {
            barEntries5.add(new BarEntry(5f,1));
        }
        if(respuestaTest.getInteresD()!=0)
        {
            barEntries6.add(new BarEntry(6f,(respuestaTest.getInteresD()*100)/10));
        }
        else
        {
            barEntries6.add(new BarEntry(6f,1));
        }
        if(respuestaTest.getInteresE()!=0)
        {
            barEntries7.add(new BarEntry(7f,(respuestaTest.getInteresE()*100)/10));
        }
        else
        {
            barEntries7.add(new BarEntry(7f,1));
        }
    }

}
