package com.example.uniscovery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_Carrera extends RecyclerView.Adapter {
    public Context Contexto;
    public ArrayList<Carrera> MiListaCarreras;


    public Adaptador_Carrera(Context context, ArrayList<Carrera> listaFiltrada) {
        Contexto=context;
        MiListaCarreras=listaFiltrada;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("onCreateViewHolder","se creo");
        View contentView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diseniolistview,null,false);
        Log.d("onCreateViewHolder","se creo");
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       Carrera carrera=MiListaCarreras.get(i);
       Holder Holder=(Holder) viewHolder;
       Holder.imagen.setImageResource(R.drawable.sinimagen);
       Holder.NombreCarrera.setText(carrera.NombreCarrera);
       Holder.NombreFacultad.setText(carrera.NombreFacultad);
        Log.d("Adaptador_Carrera","onBindViewHolder");
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return MiListaCarreras.size();
    }
        public static class Holder extends RecyclerView.ViewHolder{
            ImageView imagen;
            TextView NombreCarrera;
            TextView NombreFacultad;
            public Holder(@NonNull View itemView) {
                super(itemView);
                imagen=itemView.findViewById(R.id.ImagenFacultad);
                NombreCarrera=itemView.findViewById(R.id.NombreCarrera);
                NombreFacultad=itemView.findViewById(R.id.NombreFacultad);

            }
            private static int validarImagen(String Facultad)
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


}
