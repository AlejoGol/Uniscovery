package com.example.uniscovery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity   {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentMenu fragmentMenu=new FragmentMenu();
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutPrincipal,fragmentMenu);
        fragmentTransaction.commit();
    }
    public void RemplazarPorListado()
    {
        FragmentMostrarListaCarreras mostrarListaCarreras=new FragmentMostrarListaCarreras();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutPrincipal,mostrarListaCarreras);
        fragmentTransaction.commit();
    }
    public void RemplazarPorTest()
    {

    }

}
