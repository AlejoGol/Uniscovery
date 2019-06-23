package com.example.uniscovery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_Carrera extends BaseAdapter {
    private Context Contexto;
    public ArrayList<Carrera> MiListaCarreras;

    public Adaptador_Carrera(Context context, ArrayList<Carrera> miListaCarreras) {
        this.MiListaCarreras = miListaCarreras;
        Contexto=context;
    }

    public Adaptador_Carrera(FragmentMostrarListaCarreras fragmentMostrarListaCarreras, ArrayList<Carrera> listaFiltrada) {
        Contexto=fragmentMostrarListaCarreras.getContext();
        MiListaCarreras=listaFiltrada;
    }

    public int getCount(){
        return MiListaCarreras.size();
    }

    @Override
    public Object getItem(int position) {
        return MiListaCarreras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Carrera item=(Carrera) getItem(position);

        convertView=LayoutInflater.from(Contexto).inflate(R.layout.diseniolistview,null);
        ImageView imagen=convertView.findViewById(R.id.ImagenFacultad);
        TextView NombreCarrera=convertView.findViewById(R.id.NombreCarrera);
        TextView NombreFacultad=convertView.findViewById(R.id.NombreFacultad);
        if(validarImagen(item.NombreFacultad)!=-1)
        {
            imagen.setImageResource(item.getIDImagen());
        }
        else{
                imagen.setImageResource(R.drawable.sinimagen);
        }
        NombreCarrera.setText(item.getNombreCarrera());
        NombreFacultad.setText(item.getNombreFacultad());

        return convertView;
    }
    private int validarImagen(String Facultad)
    {   int valorADevolver=-1;
        switch(Facultad.toLowerCase())
        {
            case "utn":
                valorADevolver=R.drawable.utn;
                break;
            case "uba":
                valorADevolver=R.drawable.uba;
                break;
            case "emba":
                valorADevolver=R.drawable.emba;
                break;
            case "di tella":
                valorADevolver=R.drawable.ditella;
                break;
            case "uces":
                valorADevolver=R.drawable.uces;
                break;
            case "umai":
                valorADevolver=R.drawable.umai;
                break;
            case "uade":
                valorADevolver=R.drawable.uade;
                break;
            case "udesa":
                valorADevolver=R.drawable.udesa;
                break;
            case "up":
                valorADevolver=R.drawable.up;
                break;
            case "caece":
                valorADevolver=R.drawable.caece;
                break;
            case "itba":
                valorADevolver=R.drawable.itba;
                break;
            case "unq":
                valorADevolver=R.drawable.unq;
                break;
            case "ub":
                valorADevolver=R.drawable.belgrano;
                break;
            case "unlam":
                valorADevolver=R.drawable.unlam;
                break;
            case "uca":
                valorADevolver=R.drawable.uca;
                break;
            case "austral":
                valorADevolver=R.drawable.austral;
                break;
            case "image campus":
                valorADevolver=R.drawable.imagecampus;
                break;
            case "favaloro":
                valorADevolver=R.drawable.favaloro;
                break;
            case "ucema":
                valorADevolver=R.drawable.ucema;
                break;
            case "um":
                valorADevolver=R.drawable.moron;
                break;
            case "untref":
                valorADevolver=R.drawable.untref;
                break;
            case "una":
                valorADevolver=R.drawable.una;
                break;
            default:
            break;
        }

            return valorADevolver;
    }
}
