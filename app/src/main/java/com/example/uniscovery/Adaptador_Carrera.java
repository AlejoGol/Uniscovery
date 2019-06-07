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
        imagen.setImageResource(item.getIDImagen());
        NombreCarrera.setText(item.getNombreCarrera());
        NombreFacultad.setText(item.getNombreFacultad());

        return convertView;
    }
}
