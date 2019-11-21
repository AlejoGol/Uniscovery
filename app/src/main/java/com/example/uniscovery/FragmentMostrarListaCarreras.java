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

public class FragmentMostrarListaCarreras extends Fragment implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
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
                ListaFiltrada.clear();
                query=query.toUpperCase();
                for (Carrera carrera:listaDeCarreras) {
                    if (carrera.NombreCarrera.contains(query)){
                        Log.d("onQueryTextChange", carrera.NombreCarrera);
                        Log.d("onQueryTextChange", ""+carrera.getIDCarrera());
                        ListaFiltrada.add(carrera);
                    }
                }
                if (query.length()==0)
                {
                    Log.d("onQueryTextChange",""+ listaDeCarreras.size());
                    ListaFiltrada.addAll(listaDeCarreras);
                }
                ArrayList<Carrera> ConTags=TraerCosasDeLaBD(query);
                boolean esta=false;
                for (Carrera Tags:ConTags) {
                    for(Carrera uno:ListaFiltrada){
                        if(uno.NombreCarrera.equals(Tags.NombreCarrera)){
                            esta=true;
                        }
                    }
                    //if(!ListaFiltrada.contains(Tags))
                    if(!esta)
                    {
                        Log.d("onQueryTextChange", Tags.NombreCarrera);
                        Log.d("onQueryTextChange", ""+Tags.getIDCarrera());
                        ListaFiltrada.add(Tags);
                        esta=false;
                    }
                }
                SearchAbierto=true;
                Log.d("ListaFiltrada",""+ListaFiltrada.size());
                ModificarLista(ListaFiltrada);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                ListaFiltrada.clear();
                newText=newText.toUpperCase();
                for (Carrera carrera:listaDeCarreras) {
                    if (carrera.NombreCarrera.contains(newText)){
                        Log.d("onQueryTextChange", carrera.NombreCarrera);
                        Log.d("onQueryTextChange", ""+carrera.getIDCarrera());
                        ListaFiltrada.add(carrera);
                    }
                }
                if (newText.length()==0)
                {
                    Log.d("onQueryTextChange",""+ listaDeCarreras.size());
                    ListaFiltrada.addAll(listaDeCarreras);
                }
                ArrayList<Carrera> ConTags=TraerCosasDeLaBD(newText);
                boolean esta=false;
                for (Carrera Tags:ConTags) {
                    for(Carrera uno:ListaFiltrada){
                        if(uno.NombreCarrera.equals(Tags.NombreCarrera)){
                            esta=true;
                        }
                    }
                    //if(!ListaFiltrada.contains(Tags))
                    if(!esta)
                    {
                        Log.d("onQueryTextChange", Tags.NombreCarrera);
                        Log.d("onQueryTextChange", ""+Tags.getIDCarrera());
                        ListaFiltrada.add(Tags);
                        esta=false;
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
                //ArrayList<Carrera> ListaFiltrada = new ArrayList<>();
                ListaFiltrada.addAll(listaDeCarreras);
                ModificarLista(ListaFiltrada);
                SearchAbierto=false;
                return false;
            }
        });
        Log.d("onCreateView","pre-return");


        return VistaDevolver;
    }
    private void construirRecycler() {
        //listaDeCarreras=new ArrayList<>();
        gridView= VistaDevolver.findViewById(R.id.ListaACargar);
        listaDeCarreras=getItemEnElArray();
        ArrayList<Carrera> eliminar=new ArrayList<>();
        for (Carrera Actual:listaDeCarreras)
        {
            if(VerificarSiEstaEnLaLista(Actual))
            {
                eliminar.add(Actual);
            }
        }
        String seleccionado=main.getSeleccionado();
        Log.d("ValorSeleccionado"," valor: "+seleccionado);
        if(seleccionado.equals(""))
        {
            ListaFiltrada.addAll(listaDeCarreras);
            paginado=new Paginado(ListaFiltrada,ListaFiltrada.size());
            ListaFiltrada=PrimerFiltro();
            Log.d("11/9"," "+ListaFiltrada.size());
            adapter=new Adaptador_Carrera(getContext(),ListaFiltrada);
        }else{
            Log.d("ValorSeleccionado"," Entro por lo que funciona a medias");
            Log.d("ValorSeleccionado"," "+TraerCosasDeLaBD(seleccionado).size());
            searchView.setQuery(seleccionado,true);
            ListaFiltrada.clear();
            ListaFiltrada.addAll(CargarListaConRelaciones(seleccionado));
            listaDeCarreras.clear();
            if(ListaFiltrada.size()==0)
            {
                Toast toast1 =Toast.makeText(this.getActivity(), "No pusimos todavia carreras de esta categoria", Toast.LENGTH_SHORT);
                toast1.show();
            }
            else {

                listaDeCarreras.addAll(ListaFiltrada);
            }
            Log.d("LargoDeListaParaSegundaVuelta"," largo "+ListaFiltrada.size());
            adapter=new Adaptador_Carrera(getContext(),ListaFiltrada);
            paginado=new Paginado(ListaFiltrada,ListaFiltrada.size());
        }

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
    private ArrayList<Carrera> CargarListaConRelaciones(String Query)
    {
        Log.d("BD","CargarListaConRelaciones");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,20);
        ArrayList<Carrera> items= new ArrayList<>();
        Cursor RegistrosLeidos;
        String SqlConsulta="select ca.ID_carrera,ca.Nombre_Carrera,ca.Nombre_Facultad,ca.LinkImagen,ca.Descripcion,R.ID_carrera,R.ID_Tag,ta.ID_Tag,ta.Nombre_Tag from Carerras AS ca INNER JOIN Relacion_Carrera_Tag As R ON ca.ID_carrera = R.ID_carrera inner join  Tags as ta On R.ID_Tag = ta.ID_Tag where ta.Nombre_Tag=\""+Query.toUpperCase()+"\"" ;
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
        Log.d("CargarListaConRelaciones","Tamaño del Array: "+items.size());
        RegistrosLeidos.close();

        return items;
    }
    private ArrayList<Carrera> getItemEnElArray()
    {
        Log.d("BD","getItemEnElArray");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,20);
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
            if(Actual.NombreCarrera.equals(ElementoActual.NombreCarrera))
            {
                contador++;
            }
        }
        if(contador!=1){
            veracidad=true;
        }
        return veracidad;
    }
    private ArrayList<Carrera> TraerCosasDeLaBD(String Query)
    {
        Log.d("BD","TraerCosasDeLaBD");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,20);
        ArrayList<Carrera> items= new ArrayList<>();
        Cursor RegistrosLeidos;
        String SqlConsulta="select ca.ID_carrera,ca.Nombre_Carrera,ca.Nombre_Facultad,ca.LinkImagen,ca.Descripcion,R.ID_carrera,R.ID_Tag,ta.ID_Tag,ta.Nombre_Tag from Carerras AS ca INNER JOIN Relacion_Carrera_Tag As R ON ca.ID_carrera = R.ID_carrera inner join  Tags as ta On R.ID_Tag = ta.ID_Tag where ta.Nombre_Tag LIKE \'%"+Query+"%\'" ;
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
        Log.d("TraerCosasDeLaBD","Tamaño del Array: "+items.size());
        RegistrosLeidos.close();

        return items;
    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Carrera> ElementoCompleto=Adaptador_Carrera.getMiListaCarreras();
        MainActivity main=(MainActivity)getActivity();
        main.RemplazarPorViewPrivada(ElementoCompleto.get(position));
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        adapter.notifyDataSetChanged();
        Log.d("Scrool","onScrollStateChanged");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("Scroll","entro al scroll");
        int ultimo=firstVisibleItem+visibleItemCount;
        Log.d("Scroll","ultimo elemento visible: "+ultimo);
        Log.d("Scroll","total de items: "+totalItemCount);
        if(listaDeCarreras.isEmpty())
        {

            listaDeCarreras=getItemEnElArray();
        }
        Log.d("Scroll","total de items:  sf"+listaDeCarreras.size());

            if(firstVisibleItem+visibleItemCount>=totalItemCount/2)
            {
                if(totalItemCount+10<=listaDeCarreras.size())
                {   ListaFiltrada.clear();
                    List<Carrera>sublista=listaDeCarreras.subList(0,totalItemCount+10);
                    ListaFiltrada.addAll(sublista);
                }
                if(totalItemCount+10>listaDeCarreras.size())
                {
                    ListaFiltrada.clear();
                    List<Carrera>sublista=listaDeCarreras.subList(0,listaDeCarreras.size());
                    ListaFiltrada.addAll(sublista);
                }
                ActualizarLista(firstVisibleItem);

        }

    }
}
