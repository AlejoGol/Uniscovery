package com.example.uniscovery;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentMostrarInformacionUnaCarrera extends Fragment {
    View VistaAUsar;
    int idCarrera;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos) {

        VistaAUsar=inflater.inflate(R.layout.activity_carrera_de_facultad,null,true);
        TextView NombreCarrera,DescripcionCarrera;
        ImageView imagen;
        NombreCarrera=VistaAUsar.findViewById(R.id.NombreDeLaCarreraViewPrivada);
        DescripcionCarrera=VistaAUsar.findViewById(R.id.DescripcionCarreraViewPrivada);
        imagen=VistaAUsar.findViewById(R.id.ImagenDeLaFacultadCarreraViewPrivada);
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
            ArrayList<Materia> Materias=new ArrayList<>();
            Materias=TraerMaterias();
            descipcion.setText((DatosAUsar.Descipcion));
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
}
