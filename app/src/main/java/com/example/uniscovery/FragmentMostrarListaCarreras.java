package com.example.uniscovery;


import android.app.Fragment;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class FragmentMostrarListaCarreras extends Fragment {
    View VistaDevolver;
    SearchView searchView;
    Adaptador_Carrera adapter;
    //int[] imgs={R.drawable.uba,R.drawable.utn,R.drawable.uca,R.drawable.belgrano,R.drawable.moron,R.drawable.emba,R.drawable.untref};
    ArrayList<Carrera> listaDeCarreras=new ArrayList<>();
    public RecyclerView recyclerView;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos)
    {
        Log.d("onCreateView","entro");
        VistaDevolver=inflater.inflate(R.layout.mostrar_listado_carreras,grupoView,true);
        Log.d("onCreateView","se inflo");
        recyclerView=VistaDevolver.findViewById(R.id.ListaCarreras);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchView=VistaDevolver.findViewById(R.id.SearchBuscar);
        listaDeCarreras = getItemEnElArray();
        Log.d("onCreateView","array con informacion :"+listaDeCarreras.size());
        adapter=new Adaptador_Carrera(getContext(),listaDeCarreras);
        recyclerView.setAdapter(adapter);
        Log.d("onCreateView","adapter set");
        //ModificarLista(listaDeCarreras);
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
                ArrayList<Carrera> ConTags=VerificarTags(newText);
                for (Carrera Tags:ConTags) {
                    if(!ListaFiltrada.contains(Tags))
                    {
                        ListaFiltrada.add(Tags);
                    }
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
        Log.d("onCreateView","pre-return");
        return VistaDevolver;
    }
    public void ModificarLista(ArrayList<Carrera> ListaFiltrada){

        if (ListaFiltrada!=null){
            if(ListaFiltrada.size()==0) {
                Toast toast1 =Toast.makeText(this.getActivity(), "esta vacia", Toast.LENGTH_SHORT);
                toast1.show();
            }else{

                Log.d("ModificarLista","Cantidad: "+ListaFiltrada.size());
                adapter=new Adaptador_Carrera(getContext(),ListaFiltrada);
                Log.d("ModificarLista","por setear");
                adapter.notifyDataSetChanged();
                Log.d("ModificarLista","notificado");
                recyclerView.setAdapter(adapter);
            }
        }else{
            Toast.makeText(this.getActivity(), "esta NULL", Toast.LENGTH_SHORT).show();
        }



    }
    private ArrayList<Carrera> getItemEnElArray()
    {
        Log.d("BD","getItemEnElArray");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 3);
        ArrayList<Carrera> items= new ArrayList<>();
        Cursor RegistrosLeidos;
        String SqlConsulta="select Nombre_Carrera,Nombre_Facultad from Carerras" ;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        // Recorrosad
        Log.d("BD","cargo la informacion,puede ser");

        if (RegistrosLeidos.moveToFirst()) {
            do {
                String Nombre=RegistrosLeidos.getString(0);
                String Facultad=RegistrosLeidos.getString(1);
                Carrera item=new Carrera(0,Nombre,Facultad);
                items.add(item);
            }while(RegistrosLeidos.moveToNext());
        }

        RegistrosLeidos.close();

        return items;
    }
    private ArrayList<Carrera> VerificarTags(String query)//me fijo en la tabla de tags cuales contienen la query y con el id de esos me fijo en la tabla cuales carreras tienen ese como tag asignado y los cargo
    {
        ArrayList<Integer> IDTags=BuscarPorIdTag(query);
        Log.d("Tags","Cargo Primera Lista");
        ArrayList<Integer> IdCarreras=BuscarCarrerasPorTag(IDTags);
        Log.d("Tags","Cargo Segunda Lista");
        ArrayList<Carrera> CarrerasConTags =BuscarCarrerasPorId(IdCarreras);
        Log.d("Tags","Cargo Ultima Lista");
        return CarrerasConTags;
    }
    private ArrayList<Carrera>  BuscarCarrerasPorId(ArrayList<Integer>IdCarreras)
    {
        ArrayList<Carrera> CarrerasConTags=new ArrayList<>();
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 3);

        Cursor RegistrosLeidos;
        String SqlConsulta="select ID_carrera, Nombre_Carrera, Nombre_Facultad from Carerras" ;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        Log.d("Tags","Ejecuto consulta a BD");
        if (RegistrosLeidos.moveToFirst()) {
            Log.d("Tags","Pre while");
            do {
                int idTag=RegistrosLeidos.getInt(0);
                String NombreCarrera =RegistrosLeidos.getString(1);
                String NombreFacultad=RegistrosLeidos.getString(2);
                if (IdCarreras.contains(idTag))
                {
                    Carrera item=new Carrera(0,NombreCarrera,NombreFacultad);
                    CarrerasConTags.add(item);
                }
            }while(RegistrosLeidos.moveToNext());
            Log.d("Tags","Salio del while");
        }
        RegistrosLeidos.close();
        return CarrerasConTags;
    }
    private ArrayList<Integer> BuscarCarrerasPorTag(ArrayList<Integer> IDTags)
    {
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 3);

        Cursor RegistrosLeidos;
        String SqlConsulta="select * from Relacion_Carrera_Tag" ;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        // Recorrosad
        Log.d("BD","cargo la informacion,puede ser");
        ArrayList<Integer>ListaCarrera=new ArrayList<>();
        if (RegistrosLeidos.moveToFirst()) {
            do {
                int idCarrera =RegistrosLeidos.getInt(0);
                int idTag=RegistrosLeidos.getInt(1);
                if (IDTags.contains(idTag))
                {
                    ListaCarrera.add(idCarrera);
                }
            }while(RegistrosLeidos.moveToNext());
        }
        RegistrosLeidos.close();
       return  ListaCarrera;
    }
    private ArrayList<Integer> BuscarPorIdTag(String query)
    {
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 3);

        Cursor RegistrosLeidos;
        String SqlConsulta="select * from Tags" ;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        // Recorrosad
        Log.d("BD","cargo la informacion,puede ser");
        ArrayList<Integer> IDTags=new ArrayList<>();
        if (RegistrosLeidos.moveToFirst()) {
            do {
                int id =RegistrosLeidos.getInt(0);
                String nombre=RegistrosLeidos.getString(1);
                if(nombre.contains(query))
                {
                    IDTags.add(id);
                }
            }while(RegistrosLeidos.moveToNext());
        }
        RegistrosLeidos.close();
        return IDTags;
    }
}
