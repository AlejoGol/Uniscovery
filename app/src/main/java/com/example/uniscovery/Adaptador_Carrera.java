package com.example.uniscovery;
import android.content.Context;
import android.database.Observable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/*public class Adaptador_Carrera extends RecyclerView.Adapter<Adaptador_Carrera.Holder> implements View.OnClickListener {
    public Context Contexto;
    public static ArrayList<Carrera> MiListaCarreras;

    public static ArrayList<Carrera> getMiListaCarreras() {
        return MiListaCarreras;
    }

    public Adaptador_Carrera(Context context, ArrayList<Carrera> listaFiltrada) {
        Contexto=context;
        MiListaCarreras=listaFiltrada;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("onCreateViewHolder","se creo");
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.diseniolistview, null);
        Holder vh = new Holder( v );
        CargarImagenes();
        return vh;

    }
    private void CargarImagenes()
    {
        for (int i=0;i<MiListaCarreras.size();i++) {
            int imagen=validarImagen(MiListaCarreras.get(i).NombreFacultad);
            MiListaCarreras.get(i).setIDImagen(imagen);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int i) {
       Carrera carrera=MiListaCarreras.get(i);
       Holder Holder=viewHolder;

       Holder.imagen.setImageResource(carrera.IDImagen);
       Holder.NombreCarrera.setText(carrera.NombreCarrera);
       Holder.NombreFacultad.setText(carrera.NombreFacultad);
        Log.d("Adaptador_Carrera","onBindViewHolder");
    }
    private  int validarImagen(String Facultad)
    {   int valorADevolver=-1;
        Log.d("validarImagen",Facultad);
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

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateData(ArrayList<Carrera> data) {
        this.MiListaCarreras.clear();
        this.MiListaCarreras.addAll(data);
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return MiListaCarreras.size();
    }

    @Override
    public void onClick(View v) {
        Log.d("onClick","entro al del manejador");

    }

    public  class Holder extends RecyclerView.ViewHolder  {
            ImageView imagen;
            TextView NombreCarrera;
            TextView NombreFacultad;
            Holder(@NonNull View itemView) {
                super(itemView);
                imagen=itemView.findViewById(R.id.ImagenFacultad);
                NombreCarrera=itemView.findViewById(R.id.NombreCarrera);

            }
            public void bindData(final Holder holder) {

                int image= validarImagen(holder.NombreFacultad.toString());
                imagen.setImageResource(image);
                NombreCarrera.setText(holder.NombreCarrera.toString());
                NombreFacultad.setText(holder.NombreFacultad.toString());
            }



    }

}*/
public class Adaptador_Carrera extends BaseAdapter
{
    public Context Contexto;
    public static ArrayList<Carrera> MiListaCarreras;

    ArrayList<String> ListaFacultades=new ArrayList<>();
    public Adaptador_Carrera(Context context, ArrayList<Carrera> listaFiltrada) {
        Contexto=context;
        MiListaCarreras=listaFiltrada;
    }
    public static ArrayList<Carrera> getMiListaCarreras() {
        return MiListaCarreras;
    }

    @Override
    public int getCount() {
        return MiListaCarreras.size();
    }

    @Override
    public Carrera getItem(int position) {
        return MiListaCarreras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflador=(LayoutInflater) Contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View VistaDevolver= inflador.inflate(R.layout.diseniolistview,parent,false);
        final ImageView LogoFacultad=VistaDevolver.findViewById(R.id.ImagenFacultad);
        TextView NombreCarrera=VistaDevolver.findViewById(R.id.NombreCarrera);
        NombreCarrera.setText(MiListaCarreras.get(position).NombreCarrera);

        for (Carrera Facultad:MiListaCarreras) {
            ListaFacultades.add(Facultad.NombreFacultad);
        }
        class CargarImagenes extends AsyncTask<String,Void, Bitmap>
        {
            @Override
            protected Bitmap doInBackground(String... Ruta) {

                Bitmap ImagenConvertida=null;
                try {
                    for (String ruta:Ruta) {
                        URL Rutas=new URL(ruta);
                        HttpURLConnection httpURLConnection=(HttpURLConnection)Rutas.openConnection();
                        if(httpURLConnection.getResponseCode()==200)
                        {
                            InputStream stream=httpURLConnection.getInputStream();
                            BufferedInputStream lectorEntrada=new BufferedInputStream(stream);
                            ImagenConvertida= BitmapFactory.decodeStream(lectorEntrada);
                            httpURLConnection.disconnect();
                        }
                    }

                }catch (Exception error)
                {
                    Log.d("Error","Codigo:"+error.getLocalizedMessage());
                }


                return ImagenConvertida;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if(bitmap!=null)
            {
                MiListaCarreras.get(position).IDImagen=validarImagen(MiListaCarreras.get(position).NombreFacultad);
                LogoFacultad.setImageBitmap(bitmap);
            }
            else
            {
                MiListaCarreras.get(position).IDImagen=R.drawable.sinimagen;
                LogoFacultad.setImageResource(R.drawable.sinimagen);
            }
        }
    }
        if (MiListaCarreras.get(position).IDImagen!=0)
        {
            LogoFacultad.setImageResource(getItem(position).IDImagen);
        }
        else
        {
            CargarImagenes TareaAsyncronica=new CargarImagenes();
            TareaAsyncronica.execute(MiListaCarreras.get(position).LinkImagen);
        }
        return VistaDevolver;
    }

    private  int validarImagen(String Facultad)
    {   int valorADevolver=-1;
        Log.d("validarImagen",Facultad);
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
