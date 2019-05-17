package com.example.uniscovery;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class BuscarInformacion extends AppCompatActivity {

    ListView lista;
    String [] NombreCarreras;
    String [] Facultades;
    int[] imgs={R.drawable.uba,R.drawable.utn,R.drawable.uca,R.drawable.belgrano,R.drawable.moron,R.drawable.emba,R.drawable.untref};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_informacion);
        Resources res=getResources();
        NombreCarreras=res.getStringArray(R.array.ArrayDeString);
        Facultades=res.getStringArray(R.array.Facultades);
        lista=findViewById(R.id.ListaDeInformacion);
    }
}
