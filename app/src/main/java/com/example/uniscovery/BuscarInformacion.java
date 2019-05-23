package com.example.uniscovery;

import android.content.res.Resources;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Iterator;

public class BuscarInformacion extends AppCompatActivity {
    SearchView searchView;
    ListView lista;
    String [] NombreCarreras;
    String [] Facultades;
    Adapter adapter;
    int[] imgs={R.drawable.uba,R.drawable.utn,R.drawable.uca,R.drawable.belgrano,R.drawable.moron,R.drawable.emba,R.drawable.untref};
    ArrayList<Carrera> listaDeCarreras=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_informacion);
        Resources res=getResources();
        NombreCarreras=res.getStringArray(R.array.ArrayDeString);
        Facultades=res.getStringArray(R.array.Facultades);
        lista=findViewById(R.id.ListaDeInformacion);
        listaDeCarreras = getItemEnElArray(NombreCarreras, Facultades, imgs);
        adapter=new AdaptadorCarrera(this,listaDeCarreras);
        searchView=findViewById(R.id.SearchBuscar);
        lista.setAdapter((ListAdapter) adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return true; }
            @Override
            public boolean onQueryTextChange(String newText) {
                /*int cantidad =listaDeCarreras.size();
                for(int i=0;i<=cantidad;i++)
                {


                    if (!listaDeCarreras.get(i).NombreCarrera.contains(newText))
                    {
                        listaDeCarreras.remove(i);
                    }
                }
                adapter.notify();

                */
                ArrayList<Carrera> Eliminar=new ArrayList<>();
                Iterator<Carrera> it = listaDeCarreras.iterator();
                while (it.hasNext()) {
                    Log.d("Que tienes",it.next().NombreCarrera);
                    if (!it.next().NombreCarrera.contains(newText))
                    {
                        Eliminar.add(it.next());
                    }
                }
                //adapter.
                return true;
            }

        });




    }
    private ArrayList<Carrera> getItemEnElArray(String[] nombreCarreras, String[] facultades, int[] imgs)
    {
        ArrayList<Carrera> items=new ArrayList<Carrera>();
        items.add(new Carrera(imgs[0],nombreCarreras[0],facultades[0]));
        items.add(new Carrera(imgs[1],nombreCarreras[1],facultades[1]));
        items.add(new Carrera(imgs[2],nombreCarreras[2],facultades[2]));
        items.add(new Carrera(imgs[3],nombreCarreras[3],facultades[3]));
        items.add(new Carrera(imgs[4],nombreCarreras[4],facultades[4]));
        items.add(new Carrera(imgs[5],nombreCarreras[5],facultades[5]));
        items.add(new Carrera(imgs[6],nombreCarreras[6],facultades[6]));
        return items;
    }
}
