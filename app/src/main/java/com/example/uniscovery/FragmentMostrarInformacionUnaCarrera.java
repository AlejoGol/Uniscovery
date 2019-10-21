package com.example.uniscovery;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.FontRequest;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentMostrarInformacionUnaCarrera extends Fragment implements View.OnClickListener {
    View VistaAUsar;
    int idCarrera;
    Spinner Año;
    Spinner materias;
    Context ContextoGeneral;
    TextView DescripcionMateria;
    ArrayList<Materia> Materias;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos) {

        VistaAUsar=inflater.inflate(R.layout.activity_carrera_de_facultad,null,true);
        TextView NombreCarrera,DescripcionCarrera;
        ImageView imagen;
        ContextoGeneral=this.getContext();
        Materias=new ArrayList<>();
        DescripcionMateria=VistaAUsar.findViewById(R.id.DescripcionMateria);
        DescripcionMateria.setMovementMethod(new ScrollingMovementMethod());
        NombreCarrera=VistaAUsar.findViewById(R.id.NombreDeLaCarreraViewPrivada);
        DescripcionCarrera=VistaAUsar.findViewById(R.id.DescripcionCarreraViewPrivada);
        DescripcionCarrera.setMovementMethod(new ScrollingMovementMethod());
        imagen=VistaAUsar.findViewById(R.id.ImagenDeLaFacultadCarreraViewPrivada);
        Año=VistaAUsar.findViewById(R.id.SpinnerAñoDeMateriasViewPrivada);
        materias=VistaAUsar.findViewById(R.id.SpinnerMateriasViewPrivada);
        LlenarCampos(NombreCarrera,DescripcionCarrera,imagen,DescripcionMateria);

        return VistaAUsar;
    }
        private void LlenarCampos(TextView nombre,TextView descipcion,ImageView LogoCarrera,TextView descripcionMateria)
        {
            MainActivity actividadPrincipal=(MainActivity)getActivity();
            Carrera DatosAUsar=actividadPrincipal.getSeleccion();
            LogoCarrera.setImageResource(ObtenerImagenFacultad(DatosAUsar.NombreFacultad));
            nombre.setText(DatosAUsar.NombreCarrera);
            idCarrera=DatosAUsar.getIDCarrera();
            Materias=TraerMaterias();
            ArrayList<Integer> Años=CalcularMaximoAño(Materias);
            ArrayAdapter adapterAños=new ArrayAdapter(this.getContext(),android.R.layout.simple_spinner_item,Años);
            adapterAños.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            Año.setAdapter(adapterAños);
            Año.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> spn,View v, int posicion, long id) {
                    ArrayList<String> Listmaterias;
                    Listmaterias=LlenarInformacion(posicion);
                    DescripcionMateria.setText(Materias.get(posicion).getDescripcion());
                    ArrayAdapter adapterAños=new ArrayAdapter(ContextoGeneral,android.R.layout.simple_spinner_item,Listmaterias);
                    adapterAños.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                    materias.setAdapter(adapterAños);
                }
                public void onNothingSelected(AdapterView<?> spn) {

                }
            });
            materias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 LlenarDescripcion(position);
              }
//
              @Override
              public void onNothingSelected(AdapterView<?> parent) {
//
              }
            });
            descipcion.setText((DatosAUsar.Descipcion));

        }
        public void LlenarDescripcion(int position)
        {
            DescripcionMateria.setText(Materias.get(position).getDescripcionMateria());
        }
        private ArrayList CalcularMaximoAño(ArrayList<Materia> Materias)
        {
            ArrayList<Integer> Años=new ArrayList<>();
            int mayor=0;
            for (int n=0;n<Materias.size();n++)
            {
                if(mayor<Materias.get(n).getAño())
                {
                    mayor=Materias.get(n).getAño();
                }
            }
            for (int v=0;v<mayor;v++)
            {
                Años.add(v+1);
            }
            return Años;
        }
        private ArrayList<Materia> TraerMaterias()
        {
            ArrayList<Materia> Materias=new ArrayList<>();
            ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,16);

            Cursor RegistrosLeidos;
            String SqlConsulta="select ID_Materia,Anio,Nombre_Materia,Descripcion_Materia from Materias where ID_Carrera = "+idCarrera + " ORDER BY Anio";
            RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
            Log.d("Tags","Ejecuto consulta a BD");
            if (RegistrosLeidos.moveToFirst()) {
                Log.d("Tags","Pre while");
                do {
                    Materia mate=new Materia();
                    mate.setIDCarrera(RegistrosLeidos.getInt(0));
                    mate.setAño(RegistrosLeidos.getInt(1));
                    mate.setNombreMateria(RegistrosLeidos.getString(2));
                    mate.setDescripcionMateria(RegistrosLeidos.getString(3));
                    mate.setDescripcion(RegistrosLeidos.getString(3));
                    if(Materias.size()==0)
                    {
                        Materias.add(mate);
                    }else {
                        boolean noesta=false;
                        for (Materia m : Materias) {
                            Log.d("Nombre Materia m: ", m.getNombreMateria());
                            Log.d("Nombre Materia mate: ", mate.getNombreMateria());
                            if (!m.getNombreMateria().equals(mate.getNombreMateria())) {
                                noesta=true;
                            }
                        }
                        if(noesta)
                        {
                            Materias.add(mate);
                        }
                    }
                }while(RegistrosLeidos.moveToNext());
                Log.d("Tags","Salio del while");
            }
            RegistrosLeidos.close();
            return Materias;
        }
        public void onClick(View v) {
        int position=Año.getSelectedItemPosition();

    }
    private ArrayList LlenarInformacion(int position)
    {
        ArrayList<String> Listmaterias= new ArrayList<>();
        Log.d("LlenarInformacion","Tamaño lista: "+Materias.size());
        for (Materia MateriaActual: Materias) {
            if(MateriaActual.getAño()==position+1)
            {
                Listmaterias.add(MateriaActual.getNombreMateria());
            }
        }
        //ModificarLista(Listmaterias);
        return  Listmaterias;
    }
    public void ModificarLista(ArrayList<String> ListaFiltrada)
    {
        ArrayList<String>eliminar=new ArrayList<>();
        ArrayList<String>nueva=new ArrayList<>();
        nueva.addAll(ListaFiltrada);
        for (String elemento:ListaFiltrada) {
            if(VerificarSiEstaEnLaLista(elemento, nueva)){
                Log.d("ModificarLista: ",elemento);
                eliminar.add(elemento);
                Eliminar(eliminar, nueva);
            }
        }
        ListaFiltrada.removeAll(ListaFiltrada);
        ListaFiltrada.addAll(nueva);
    }
    public void Eliminar(ArrayList<String> Eliminar, ArrayList<String> ListaFiltrada)
    {
        ListaFiltrada.removeAll(Eliminar);
        /*
        for (String Actual:Eliminar)
        {
            Log.d("Eliminar: ",Actual);
            ListaFiltrada.remove(Actual);
        }*/
    }
    public boolean VerificarSiEstaEnLaLista(String ElementoActual, ArrayList<String> ListaFiltrada)
    {

        boolean veracidad=false;
        int contador=0;
        for (String Actual:ListaFiltrada)
        {
            if(Actual.equals(ElementoActual))
            {
                Log.d("VerificarSiEstaEnLaLista: ",Actual);
                contador++;
                Log.d("VerificarSiEstaEnLaLista: ",""+contador);

            }
        }
        if(contador!=1){
            veracidad=true;
        }
        return veracidad;
    }
    private  int ObtenerImagenFacultad(String Facultad)
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
