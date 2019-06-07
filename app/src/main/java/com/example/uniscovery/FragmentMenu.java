package com.example.uniscovery;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu  extends Fragment implements View.OnClickListener{
    View VistaDevolver;
    Button Test;
    Button ListadoDeCarreras;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos)
    {

        VistaDevolver=inflater.inflate(R.layout.menu_principal,grupoView,false);
        Test=VistaDevolver.findViewById(R.id.BotonIrAlTest);
        ListadoDeCarreras=VistaDevolver.findViewById(R.id.BotonBuscarInformacion);
        Test.setOnClickListener(this);
        ListadoDeCarreras.setOnClickListener(this);
        return VistaDevolver;
    }

    public void onClick(View v) {
        int id=v.getId();
        Log.d("llego al click","vamoss que se puede");
        MainActivity actividadPrincipal=(MainActivity)getActivity();
        if(id==ListadoDeCarreras.getId())//me fijo cual lo llamo y llamo a la funcion para cambiar el fragment correspondiente
         {
             actividadPrincipal.RemplazarPorListado();
         }
         else{
            actividadPrincipal.RemplazarPorTest();
        }
    }
}
