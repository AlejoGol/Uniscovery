package com.example.uniscovery;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentMostrarListaCarreras extends Fragment {
    View VistaDevolver;
    SearchView searchView;
    ListView lista;
    String [] NombreCarreras;
    String [] Facultades;
    MainActivity actividadPrincipal=(MainActivity)getActivity();
    int[] imgs={R.drawable.uba,R.drawable.utn,R.drawable.uca,R.drawable.belgrano,R.drawable.moron,R.drawable.emba,R.drawable.untref};
    ArrayList<Carrera> listaDeCarreras=new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos)
    {
        Log.d("Entro","Al create");
        VistaDevolver=inflater.inflate(R.layout.mostrar_listado_carreras,grupoView,false);
        Log.d("Mostrar","Logro inflar");
        searchView=VistaDevolver.findViewById(R.id.SearchBuscar);
        Resources res=getResources();
        Log.d("Mostrar","Pre cambiar contenido 1");
        NombreCarreras=res.getStringArray(R.array.ArrayDeString);
        Facultades=res.getStringArray(R.array.Facultades);
        lista=VistaDevolver.findViewById(R.id.ListaDeInformacion);
        Log.d("Mostrar","Pre cambiar contenido 2");
        listaDeCarreras = getItemEnElArray(NombreCarreras, Facultades, imgs);
        searchView=VistaDevolver.findViewById(R.id.SearchBuscar);
        Log.d("Mostrar","Pre cambiar contenido 3"+listaDeCarreras.size());
        ModificarLista(listaDeCarreras);
        Log.d("Mostrar","Paso el cambiar contenido");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Carrera> ListaFiltrada=new ArrayList<>();
                query=query.toUpperCase();
                for (Carrera carrera:listaDeCarreras) {

                    if (carrera.NombreCarrera.contains(query)){
                        ListaFiltrada.add(carrera);
                    }
                }
                if (query.length()==0)
                {
                    Log.d("entro","SI");
                    ListaFiltrada.addAll(listaDeCarreras);
                    ModificarLista(ListaFiltrada);
                }
                ModificarLista(ListaFiltrada);
                return true; }
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Carrera> ListaFiltrada=new ArrayList<>();
                newText=newText.toUpperCase();
                for (Carrera carrera:listaDeCarreras) {

                    if (carrera.NombreCarrera.contains(newText)){
                        ListaFiltrada.add(carrera);
                    }
                }
                if (newText.length()==0)
                {
                    Log.d("entro","SI");
                    ListaFiltrada.addAll(listaDeCarreras);
                }
                ModificarLista(ListaFiltrada);



                return true;
            }

        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("entro","si");
                ArrayList<Carrera> ListaFiltrada=new ArrayList<>();
                ListaFiltrada.addAll(listaDeCarreras);
                ModificarLista(ListaFiltrada);
                return false;
            }
        });

        return VistaDevolver;
    }
    public void ModificarLista(ArrayList<Carrera> ListaFiltrada){

        if(ListaFiltrada.isEmpty()) {
            Toast toast1 =Toast.makeText(actividadPrincipal.getApplicationContext(), "esta vacia", Toast.LENGTH_SHORT);
            toast1.show();
        }
        ListView lista =VistaDevolver.findViewById(R.id.ListaDeInformacion);
        Log.d("Mostrar","Cantidad: "+ListaFiltrada.size());
        Adaptador_Carrera adapter=new Adaptador_Carrera(this,ListaFiltrada);
        lista.setAdapter(adapter);

    }
    public ArrayList<Carrera> getListaDeCarreras()
    {
        return listaDeCarreras;
    }
    public ListView getLista(){
        return lista;
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
