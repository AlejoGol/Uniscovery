package com.example.uniscovery;

import android.util.Log;

import java.util.ArrayList;

public class Paginado {
    public ArrayList<Carrera> ListaDeElementos;
    public int cantidadItems;
    public int paginaActual;

    public Paginado(ArrayList<Carrera> listaDeElementos, int cantidadItems) {
        ListaDeElementos = listaDeElementos;
        this.cantidadItems = cantidadItems;
        paginaActual=0;
    }
    public int numPaginas(int cantidadItems){
        int numPaginas = cantidadItems /10;
        cantidadItems-=numPaginas*10;
        if(cantidadItems!=0){
            numPaginas++;
        }
        return numPaginas;
    }
    public void ActualizarValores(ArrayList<Carrera> nueva,int nuevolargo)
    {
        ListaDeElementos.clear();
        ListaDeElementos.addAll(nueva);
        cantidadItems=nuevolargo;
        paginaActual=0;
    }
    public ArrayList<Carrera> setPaginaActual(int valorPagina){
        ArrayList<Carrera> paginada=new ArrayList<>();

        if(2==valorPagina)
        {
            Log.d("valorpagina","estamos en problemas dios");
            valorPagina=1;
            Log.d("valorpagina","veamos");
        }
        Log.d("setPaginaActual","valor de pagina actual "+valorPagina);

        int i=0,TamañoLista=ListaDeElementos.size();
        while(i<10 &&i+(valorPagina*10)<TamañoLista)
        {
            Log.d("setPaginaActual",""+ListaDeElementos.get(i+(valorPagina*10)).NombreCarrera);
            paginada.add(ListaDeElementos.get(i+(valorPagina*10)));
            i++;
        }
        Log.d("paginada",""+paginada.size());
        return paginada;
    }
    public ArrayList<Carrera> TraerListaPaginada(int pagina)
    {
        int i=0,TamañoLista=ListaDeElementos.size();
        ArrayList<Carrera> paginada=new ArrayList<>();
        while(i<10 &&i+(pagina*10)<TamañoLista)
        {
            Log.d("setPaginaActual",""+ListaDeElementos.get(i+(pagina*10)).NombreCarrera);
            paginada.add(ListaDeElementos.get(i+(pagina*10)));
            i++;
        }
        Log.d("paginada",""+paginada.size());
        return paginada;
    }

}
