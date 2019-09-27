package com.example.uniscovery;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ArrayList<Fragment> ListaFragment;
    private Carrera Seleccion=null;
    public boolean VioAdvertencia;
    public static int VersionBD;

    public Carrera getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(Carrera seleccion) {
        Seleccion = seleccion;
    }

    public List<Fragment> getListaFragment() { return ListaFragment;  }

    public void setListaFragment(ArrayList<Fragment> listaFragment) { ListaFragment = listaFragment;  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VioAdvertencia=true;
        FragmentMenu fragmentMenu=new FragmentMenu();
        fragmentManager=getFragmentManager();
        ReemplazarFragment(fragmentMenu);
        VersionBD=11;
        ListaFragment=new ArrayList<>();
    }

    public void RemplazarPorViewPrivada(Carrera seleccionada)
    {
        Fragment ListaCarre=new FragmentMostrarListaCarreras();
        ListaFragment.add(ListaCarre);
        Seleccion=seleccionada;
        FragmentMostrarInformacionUnaCarrera fragmentMostrarInformacionUnaCarrera=new FragmentMostrarInformacionUnaCarrera();
        ReemplazarFragment(fragmentMostrarInformacionUnaCarrera);
    }
    public void RemplazarPorListado()
    {
        FragmentMenu menu=new FragmentMenu();
        ListaFragment.add(menu);
        FragmentMostrarListaCarreras mostrarListaCarreras=new FragmentMostrarListaCarreras();
        ReemplazarFragment(mostrarListaCarreras);

    }
    public void RemplazarPorTest()
    {
        ListaFragment.add(new FragmentMenu());
        Fragment Chaside=new FragmentPreguntaChaside();
        ReemplazarFragment(Chaside);


    }
    public void ReemplazarFragment(Fragment FragmentAUsar)
    {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutPrincipal,FragmentAUsar);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutPrincipal,ListaFragment.get(ListaFragment.size()-1));
        fragmentTransaction.commit();
        ListaFragment.remove(ListaFragment.size()-1);
        Log.d("LLego","al final");
        /*super.onBackPressed();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }*/
    }
    //TODO
}
