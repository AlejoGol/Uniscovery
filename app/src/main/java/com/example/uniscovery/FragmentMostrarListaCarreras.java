package com.example.uniscovery;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class FragmentMostrarListaCarreras extends Fragment implements View.OnClickListener{
    View VistaDevolver;
    Paginado paginado;
    SearchView searchView;
    Adaptador_Carrera adapter;
    Button Anterior;
    Button siguiente;
    int PaginaActual=0;
    boolean SearchAbierto=false;
    //int[] imgs={R.drawable.uba,R.drawable.utn,R.drawable.uca,R.drawable.belgrano,R.drawable.moron,R.drawable.emba,R.drawable.untref};
    ArrayList<Carrera> listaDeCarreras=new ArrayList<>();
    ArrayList<Carrera> ListaFiltrada=new ArrayList<>();
    public GridView gridView;

    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos)
    {
        Log.d("onCreateView","entro");
        VistaDevolver=inflater.inflate(R.layout.mostrar_listado_carreras,null,true);
        Log.d("onCreateView","se inflo");
        searchView=VistaDevolver.findViewById(R.id.SearchBuscar);
        Anterior=VistaDevolver.findViewById(R.id.anterior);
        siguiente=VistaDevolver.findViewById(R.id.Siguiente);

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
                if(ListaFiltrada.size()<=10)
                {
                    siguiente.setEnabled(false);
                }
                else
                {
                    siguiente.setEnabled(true);
                }
                Log.d("ListaFiltrada",""+ListaFiltrada.size());
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
                siguiente.setEnabled(false);
                SearchAbierto=false;
                return false;
            }
        });
        Log.d("onCreateView","pre-return");


        return VistaDevolver;
    }
    public void onClick(View v) {
        ArrayList<Carrera> paginada;
        Log.d("OnClick","entro");
        if(ListaFiltrada.size()<=10)
        {
            Anterior.setEnabled(false);
            siguiente.setEnabled(false);
        }
        if(v.getId()==R.id.anterior)
        {
            Log.d("OnClick","anterior");
            paginado.paginaActual--;
            paginada=paginado.setPaginaActual(paginado.paginaActual);
            if(paginado.paginaActual==0) {
                Anterior.setEnabled(false);
            }
            else{
                Anterior.setEnabled(true);
            }
            if(paginado.paginaActual==paginado.numPaginas(ListaFiltrada.size())-1)
            {
                siguiente.setEnabled(false);
            }
            if(paginado.paginaActual!=paginado.numPaginas(ListaFiltrada.size()))
            {
                siguiente.setEnabled(true);
            }
        }
        else
        {
            Log.d("OnClick","siguiente");
            paginado.paginaActual=paginado.paginaActual+1;
            Log.d("OnClick",""+paginado.paginaActual);
            paginada=paginado.setPaginaActual(paginado.paginaActual);
            if(paginado.paginaActual==0) {
                Anterior.setEnabled(false);
            }
            else{
                Anterior.setEnabled(true);
            }
            Log.d("cant",""+paginado.numPaginas(ListaFiltrada.size()));
            int pag=paginado.paginaActual+1;
            Log.d("paginado",""+pag);
            if(pag==paginado.numPaginas(ListaFiltrada.size()))
            {
                siguiente.setEnabled(true);
            }
            if(paginado.paginaActual!=paginado.numPaginas(ListaFiltrada.size()))
            {
                siguiente.setEnabled(false);
            }
        }
        ModificarLista(paginada);
    }
    private void construirRecycler() {
        listaDeCarreras=new ArrayList<>();
        gridView= (GridView) VistaDevolver.findViewById(R.id.ListaACargar);
        listaDeCarreras=getItemEnElArray();
        ArrayList<Carrera> Eliminar=new ArrayList<>();
        for (Carrera Actual:listaDeCarreras)
        {
            if(VerificarSiEstaEnLaLista(Actual)==true)
            {
                Eliminar.add(Actual);
            }
        }
        Eliminar(Eliminar);
        ListaFiltrada.addAll(listaDeCarreras);
        paginado=new Paginado(ListaFiltrada,ListaFiltrada.size());
        ListaFiltrada=PrimerFiltro();

        Adaptador_Carrera adapter=new Adaptador_Carrera(getContext(),ListaFiltrada);
        Anterior.setOnClickListener(this);
        siguiente.setOnClickListener(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Carrera> ElementoCompleto=Adaptador_Carrera.getMiListaCarreras();
                MainActivity main=(MainActivity)getActivity();
                main.RemplazarPorViewPrivada(ElementoCompleto.get(position));
            }
        });
    }
    public void ModificarLista(ArrayList<Carrera> ListaFiltrada){

        if (ListaFiltrada!=null){
            if(ListaFiltrada.size()==0) {
                Toast toast1 =Toast.makeText(this.getActivity(), "esta vacia", Toast.LENGTH_SHORT);
                toast1.show();
                ListaFiltrada.clear();
                adapter=new Adaptador_Carrera(this.getActivity(),ListaFiltrada);
                gridView.setAdapter(adapter);
            }else{

                Log.d("ModificarLista","Cantidad: "+ListaFiltrada.size());
                paginado.ActualizarValores(ListaFiltrada,ListaFiltrada.size());
                ArrayList<Carrera> paginada=paginado.setPaginaActual(paginado.paginaActual);
                Log.d("ListaFiltrada","paginada:"+paginada.size());
                adapter=new Adaptador_Carrera(this.getActivity(),paginada);
                Log.d("ModificarLista","por setear");

                Log.d("ModificarLista","notificado");
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
    public ArrayList<Carrera> PrimerFiltro()
    {
        ArrayList<Carrera> paginada;
        paginada=paginado.setPaginaActual(PaginaActual);
            Anterior.setEnabled(false);
            Log.d("PrimerFiltro",""+listaDeCarreras.size());
            Log.d("PrimerFiltro",""+paginada.size());
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
        for (Carrera Actual:listaDeCarreras)
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


}
