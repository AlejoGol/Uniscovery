package com.example.uniscovery;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentMostrarInformacionUnaCarrera extends Fragment {
    View VistaAUsar;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos) {

        VistaAUsar=inflater.inflate(R.layout.activity_carrera_de_facultad,null,true);
        TextView NombreCarrera,DescripcionCarrera;
        NombreCarrera=VistaAUsar.findViewById(R.id.NombreDeLaCarreraViewPrivada);
        DescripcionCarrera=VistaAUsar.findViewById(R.id.DescripcionCarreraViewPrivada);
        LlenarCampos(NombreCarrera,DescripcionCarrera);

        return VistaAUsar;
    }
private void LlenarCampos(TextView nombre,TextView descipcion)
{
    MainActivity actividadPrincipal=(MainActivity)getActivity();
    Carrera DatosAUsar=actividadPrincipal.getSeleccion();
    nombre.setText(DatosAUsar.NombreCarrera);
    descipcion.setText((DatosAUsar.Descipcion));
}

}
