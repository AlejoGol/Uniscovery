package com.example.uniscovery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;

public class ActividadPrincipal extends Activity {

    public Button Boton1;
    public Button Boton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        Boton1=findViewById(R.id.BotonIrAlTest);
        Boton2=findViewById(R.id.BotonBuscarInformacion);
    }
    public void IrAAboutUS(View vista)
    {
        Intent AboutUs=new Intent(this,AboutUs.class);
        startActivity(AboutUs);
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
