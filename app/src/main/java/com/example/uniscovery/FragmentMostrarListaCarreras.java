package com.example.uniscovery;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class FragmentMostrarListaCarreras extends Fragment implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener, AdapterView.OnItemSelectedListener {
    View VistaDevolver;
    Paginado paginado;
    SearchView searchView;
    Adaptador_Carrera adapter;
    boolean SearchAbierto=false;
    ArrayList<Carrera> listaDeCarreras=new ArrayList<>();
    ArrayList<Carrera> ListaFiltrada=new ArrayList<>();
    public GridView gridView;
    static MainActivity main;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos)
    {
        Log.d("onCreateView","entro");
        VistaDevolver=inflater.inflate(R.layout.mostrar_listado_carreras,null,true);
        Log.d("onCreateView","se inflo");
        searchView=VistaDevolver.findViewById(R.id.SearchBuscar);
        main=(MainActivity)getActivity();
        construirRecycler();
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

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
                SearchAbierto=true;
                return true; }
            @Override
            public boolean onQueryTextChange(String newText) {
                ListaFiltrada.clear();
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
                SearchAbierto=true;
                Log.d("ListaFiltrada",""+ListaFiltrada.size());
                ModificarLista(ListaFiltrada);
                return true;
            }


        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("entro","si");
                ArrayList<Carrera> ListaFiltrada = new ArrayList<>(listaDeCarreras);
                ModificarLista(ListaFiltrada);
                SearchAbierto=false;
                return false;
            }
        });
        Log.d("onCreateView","pre-return");


        return VistaDevolver;
    }
    private void construirRecycler() {
        listaDeCarreras=new ArrayList<>();
        gridView= VistaDevolver.findViewById(R.id.ListaACargar);
        listaDeCarreras=getItemEnElArray();
        ArrayList<Carrera> Eliminar=new ArrayList<>();
        for (Carrera Actual:listaDeCarreras)
        {
            if(VerificarSiEstaEnLaLista(Actual))
            {
                Eliminar.add(Actual);
            }
        }
        ListaFiltrada.addAll(listaDeCarreras);
        paginado=new Paginado(ListaFiltrada,ListaFiltrada.size());
        ListaFiltrada=PrimerFiltro();
        Log.d("11/9"," "+ListaFiltrada.size());
        adapter=new Adaptador_Carrera(getContext(),ListaFiltrada);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        gridView.setOnScrollListener(this);


    }
    public void ActualizarLista(int first)
    {
        Log.d("ActualizarLista","entro");
        if(adapter==null){

            Log.d("ActualizarLista","esta vacio");
        }
        else
        {
            adapter.updateCarreraList(ListaFiltrada);
        }
        Log.d("ActualizarLista","elementos "+ListaFiltrada.size());

    }
    public static void ClickListeenerPartDos(int posicion)
    {
        ArrayList<Carrera> ElementoCompleto=Adaptador_Carrera.getMiListaCarreras();
        main.RemplazarPorViewPrivada(ElementoCompleto.get(posicion));
    }
    public void ModificarLista(ArrayList<Carrera> ListaFiltrad){

        if (ListaFiltrad!=null){
            if(ListaFiltrad.size()==0) {
                Toast toast1 =Toast.makeText(this.getActivity(), "esta vacia", Toast.LENGTH_SHORT);
                toast1.show();
                ListaFiltrada.clear();
                adapter=new Adaptador_Carrera(this.getActivity(),ListaFiltrada);
                gridView.setAdapter(adapter);
            }else{

                Log.d("ModificarLista","Cantidad: "+ListaFiltrada.size());
                paginado.ActualizarValores(ListaFiltrad,ListaFiltrad.size());
                ArrayList<Carrera> paginada=paginado.setPaginaActual(paginado.paginaActual);
                Log.d("ListaFiltrada","paginada:"+paginada.size());
                adapter=new Adaptador_Carrera(this.getActivity(),paginada);
                Log.d("ModificarLista","por setear");
                Log.d("ModificarLista","notificado");
                ArrayList<Carrera>eliminar=new ArrayList<>();
                for (Carrera elemento:ListaFiltrad) {
                    if(VerificarSiEstaEnLaLista(elemento)){
                        eliminar.add(elemento);
                        }
                }
                Eliminar(eliminar);
                gridView.setAdapter(adapter);
            }
        }else{
            Toast.makeText(this.getActivity(), "esta NULL", Toast.LENGTH_SHORT).show();
        }



    }
    private ArrayList<Carrera> getItemEnElArray()
    {
        Log.d("BD","getItemEnElArray");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,7);
        ArrayList<Carrera> items= new ArrayList<>();
        Cursor RegistrosLeidos;
        String SqlConsulta="select ID_carrera,Nombre_Carrera,Nombre_Facultad,LinkImagen,Descripcion from Carerras" ;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        Log.d("BD","cargo la informacion,puede ser");

        if (RegistrosLeidos.moveToFirst()) {
            do {
                Log.d("BD","entra");
                int id=RegistrosLeidos.getInt(0);
                String Nombre=RegistrosLeidos.getString(1);
                Log.d("BD",""+Nombre);
                String Facultad=RegistrosLeidos.getString(2);
                Log.d("BD",""+Facultad);
                String Link=RegistrosLeidos.getString(3);
                String descripcion=RegistrosLeidos.getString(4);
                Carrera item=new Carrera(0,Nombre,Facultad,descripcion,Link);
                item.setIDCarrera(id);
                items.add(item);
            }while(RegistrosLeidos.moveToNext());
        }

        RegistrosLeidos.close();

        return items;
    }
    public ArrayList<Carrera> PrimerFiltro()
    {
        ArrayList<Carrera> paginada;
        paginada=paginado.setPaginaActual(0);
            Log.d("PrimerFiltro",""+listaDeCarreras.size());
            Log.d("PrimerFiltro",""+paginada.size());
        Log.d("cant","cantidad paginas"+paginado.numPaginas(ListaFiltrada.size()));
            return paginada;
        }
        public void Eliminar(ArrayList<Carrera> Eliminar)
    {
        for (Carrera Actual:Eliminar)
        {
            listaDeCarreras.remove(Actual);
        }
    }
    public boolean VerificarSiEstaEnLaLista(Carrera ElementoActual)
    {

        boolean veracidad=false;
        int contador=0;
        for (Carrera Actual:ListaFiltrada)
        {
            if(Actual.equals(ElementoActual))
            {
                contador++;
            }
        }
        if(contador!=1){
            veracidad=true;
        }
        return veracidad;
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
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 7);

        Cursor RegistrosLeidos;
        String SqlConsulta="select ID_carrera, Nombre_Carrera, Nombre_Facultad,LinkImagen,Descripcion from Carerras" ;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        Log.d("Tags","Ejecuto consulta a BD");
        if (RegistrosLeidos.moveToFirst()) {
            Log.d("Tags","Pre while");
            do {
                int idTag=RegistrosLeidos.getInt(0);
                String NombreCarrera =RegistrosLeidos.getString(1);
                String NombreFacultad=RegistrosLeidos.getString(2);
                String Link=RegistrosLeidos.getString(3);
                String descripcion=RegistrosLeidos.getString(4);
                if (IdCarreras.contains(idTag))
                {
                    Carrera item=new Carrera(0,NombreCarrera,NombreFacultad,descripcion,Link);
                    item.setIDCarrera(idTag);
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
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(),  "Universidades.db", null, 7);

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
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 7);

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



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Carrera> ElementoCompleto=Adaptador_Carrera.getMiListaCarreras();
        MainActivity main=(MainActivity)getActivity();
        main.RemplazarPorViewPrivada(ElementoCompleto.get(position));
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        Log.d("Scrool","onScrollStateChanged");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("Scroll","entro al scroll");
        int ultimo=firstVisibleItem+visibleItemCount;
        Log.d("Scroll","ultimo elemento visible: "+ultimo);
        Log.d("Scroll","total de items: "+totalItemCount);
        if(firstVisibleItem+visibleItemCount>=totalItemCount/2)
        {
            if(totalItemCount+10<=listaDeCarreras.size())
            {   ListaFiltrada.clear();
                List<Carrera>sublista=listaDeCarreras.subList(0,totalItemCount+10);
                for (Carrera actual:sublista) {
                    ListaFiltrada.add(actual);
                }
            }
            if(totalItemCount+10>listaDeCarreras.size())
            {
                ListaFiltrada.clear();
                List<Carrera>sublista=listaDeCarreras.subList(0,listaDeCarreras.size());
                for (Carrera actual:sublista) {
                    ListaFiltrada.add(actual);

                }
            }
            ActualizarLista(firstVisibleItem);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Carrera> ElementoCompleto=Adaptador_Carrera.getMiListaCarreras();
        MainActivity main=(MainActivity)getActivity();
        main.RemplazarPorViewPrivada(ElementoCompleto.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
