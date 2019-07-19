package com.example.uniscovery;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
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
    ArrayList<Materia> Materias;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos) {

        VistaAUsar=inflater.inflate(R.layout.activity_carrera_de_facultad,null,true);
        TextView NombreCarrera,DescripcionCarrera;
        ImageView imagen;
        ContextoGeneral=this.getContext();
        Materias=new ArrayList<>();
        NombreCarrera=VistaAUsar.findViewById(R.id.NombreDeLaCarreraViewPrivada);
        DescripcionCarrera=VistaAUsar.findViewById(R.id.DescripcionCarreraViewPrivada);
        imagen=VistaAUsar.findViewById(R.id.ImagenDeLaFacultadCarreraViewPrivada);
        Año=VistaAUsar.findViewById(R.id.SpinnerAñoDeMateriasViewPrivada);
        materias=VistaAUsar.findViewById(R.id.SpinnerMateriasViewPrivada);
        LlenarCampos(NombreCarrera,DescripcionCarrera,imagen);

        return VistaAUsar;
    }
        private void LlenarCampos(TextView nombre,TextView descipcion,ImageView LogoCarrera)
        {

            MainActivity actividadPrincipal=(MainActivity)getActivity();
            Carrera DatosAUsar=actividadPrincipal.getSeleccion();
            LogoCarrera.setImageResource(DatosAUsar.IDImagen);
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
                    ArrayAdapter adapterAños=new ArrayAdapter(ContextoGeneral,android.R.layout.simple_spinner_item,Listmaterias);
                    adapterAños.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    materias.setAdapter(adapterAños);
                }
                public void onNothingSelected(AdapterView<?> spn) {

                }
            });
            descipcion.setText((DatosAUsar.Descipcion));

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
            ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null, 5);

            Cursor RegistrosLeidos;
            String SqlConsulta="select ID_Materia,Anio,Nombre_Materia from Materias where ID_Carrera = "+idCarrera ;
            RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
            Log.d("Tags","Ejecuto consulta a BD");
            if (RegistrosLeidos.moveToFirst()) {
                Log.d("Tags","Pre while");
                do {
                    Materia mate=new Materia();
                    mate.setIDCarrera(RegistrosLeidos.getInt(0));
                    mate.setAño(RegistrosLeidos.getInt(1));
                    mate.setNombreMateria(RegistrosLeidos.getString(2));
                    Materias.add(mate);
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
        for (Materia MateriaActual: Materias) {
            if(MateriaActual.getAño()==position+1)
            {
                Listmaterias.add(MateriaActual.getNombreMateria());
            }
        }
        return  Listmaterias;
    }
}
