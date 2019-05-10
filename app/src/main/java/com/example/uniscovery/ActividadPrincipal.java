package com.example.uniscovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActividadPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
    }
    public void IrANuevaPantalla(View vista)
    {
        int id =vista.getId();
        Intent PasarDePantalla;
        if(id==R.id.BotonBuscarInformacion)
        {
            PasarDePantalla=new Intent(this,BuscarInformacion.class);
            startActivity(PasarDePantalla);
        }
        else{
            PasarDePantalla=new Intent(this,GenerarTest.class);
            startActivity(PasarDePantalla);

        }
    }
}
