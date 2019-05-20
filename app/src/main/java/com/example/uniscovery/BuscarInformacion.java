package com.example.uniscovery;

import android.content.res.Resources;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class BuscarInformacion extends AppCompatActivity {

    ListView lista;
    String [] NombreCarreras;
    String [] Facultades;
    Adapter adapter;
    int[] imgs={R.drawable.uba,R.drawable.utn,R.drawable.uca,R.drawable.belgrano,R.drawable.moron,R.drawable.emba,R.drawable.untref};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_informacion);
        Resources res=getResources();
        NombreCarreras=res.getStringArray(R.array.ArrayDeString);
        Facultades=res.getStringArray(R.array.Facultades);
        lista=findViewById(R.id.ListaDeInformacion);
        adapter=new AdaptadorCarrera(this,getItemEnElArray(NombreCarreras,Facultades,imgs));
        lista.setAdapter((ListAdapter) adapter);
    }

    private ArrayList<Carrera> getItemEnElArray(String[] nombreCarreras, String[] facultades, int[] imgs)
    {
        ArrayList<Carrera> items=new ArrayList<Carrera>();
        items.add(new Carrera(imgs[0],nombreCarreras[0],facultades[0],null));
        items.add(new Carrera(imgs[1],nombreCarreras[1],facultades[1],null));
        items.add(new Carrera(imgs[2],nombreCarreras[2],facultades[2],null));
        items.add(new Carrera(imgs[3],nombreCarreras[3],facultades[3],null));
        items.add(new Carrera(imgs[4],nombreCarreras[4],facultades[4],null));
        items.add(new Carrera(imgs[5],nombreCarreras[5],facultades[5],null));
        items.add(new Carrera(imgs[6],nombreCarreras[6],facultades[6],null));
        return items;
    }
}
