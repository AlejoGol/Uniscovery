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
        this.paginaActual = valorPagina;
        if(paginaActual==-1)
        {
            paginaActual=0;
        }
        Log.d("setPaginaActual","valor de pagina actual "+valorPagina);

        for(int i=0;i<10;i++){
            if(i+(paginaActual*10)<ListaDeElementos.size()){
                Log.d("setPaginaActual",""+ListaDeElementos.get(i+(paginaActual*10)).NombreCarrera);
                paginada.add(ListaDeElementos.get(i+(paginaActual*10)));
            }
        }
        Log.d("paginada",""+paginada.size());
        return paginada;
    }


}
