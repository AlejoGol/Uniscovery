package com.example.uniscovery;

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
    public ArrayList<Carrera> setPaginaActual(int paginaActual){
        ArrayList<Carrera> paginada=new ArrayList<>();
        if(paginaActual==-1)
        {
            paginaActual=0;
        }
        else
        {
            this.paginaActual = paginaActual;
        }
        for(int i=0;i<10;i++){
            if(i+(paginaActual*10)<ListaDeElementos.size()){
                paginada.add(ListaDeElementos.get(i+(paginaActual*10)));
            }
        }
        return paginada;
    }


}
