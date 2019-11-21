package com.example.uniscovery;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private ArrayList<Fragment> ListaFragment;
    private Carrera Seleccion=null;
    private boolean VioAdvertencia;
    private SharedPreferences preferencias;
    int numeroUltimaPregunta;
    private static MediaPlayer Reproductor;
    public static boolean isPlayingAudio,EstaEnElTest;
    private RespuestaTest ResultadosUltimoTest;
    private String seleccionado;

    public boolean getVioAdvertencia() {
        return VioAdvertencia;
    }

    public void setVioAdvertencia(boolean vioAdvertencia) {
        VioAdvertencia = vioAdvertencia;
    }
    public RespuestaTest getResultadosUltimoTest() {
        return ResultadosUltimoTest;
    }

    public void setResultadosUltimoTest(RespuestaTest resultadosUltimoTest) {
        ResultadosUltimoTest = resultadosUltimoTest;
    }
    public static void PlayAudio(Context c, int id){
        Reproductor = MediaPlayer.create(c, id);
        SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC,50);
        if (!Reproductor.isPlaying())
        {
            isPlayingAudio= true;
            Reproductor.start();
            Reproductor.setLooping(true);
        }
    }

    public void PararMusica()
    {
        isPlayingAudio=false;
        Reproductor.stop();
    }
    public void setSeleccionado(String selec)
    {
        seleccionado=selec;
    }
    public String getSeleccionado(){return seleccionado;}
    public void RemplazarPorResultados()
    {
        EstaEnElTest=false;
        Fragment FragmentAUsar=new FragmentMostrarResultados();
        ReemplazarFragment(FragmentAUsar);
    }

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
        VioAdvertencia=false;
        FragmentMenu fragmentMenu=new FragmentMenu();
        fragmentManager=getFragmentManager();
        preferencias=this.getBaseContext().getSharedPreferences("Uniscovery", Context.MODE_PRIVATE);
        ReemplazarFragment(fragmentMenu);
        ListaFragment=new ArrayList<>();
        numeroUltimaPregunta=1;
        isPlayingAudio=false;
        ResultadosUltimoTest=new RespuestaTest();
        seleccionado="";
    }
    public void ReemplazarInformacionUltimoTest(RespuestaTest respuestasAGuardar, int NumeroDePreguntas)
    {
        SharedPreferences.Editor editorPreferencias;
        editorPreferencias=preferencias.edit();
        editorPreferencias.putInt("NumeroDePregunta",NumeroDePreguntas);
        editorPreferencias.putFloat("InteresC",respuestasAGuardar.getInteresC());
        editorPreferencias.putFloat("InteresH",respuestasAGuardar.getInteresH());
        editorPreferencias.putFloat("InteresA",respuestasAGuardar.getInteresA());
        editorPreferencias.putFloat("InteresS",respuestasAGuardar.getInteresS());
        editorPreferencias.putFloat("InteresI",respuestasAGuardar.getInteresI());
        editorPreferencias.putFloat("InteresD",respuestasAGuardar.getInteresD());
        editorPreferencias.putFloat("InteresE",respuestasAGuardar.getInteresE());
        editorPreferencias.putFloat("ActiutdC",respuestasAGuardar.getAptitudC());
        editorPreferencias.putFloat("ActiutdH",respuestasAGuardar.getAptitudH());
        editorPreferencias.putFloat("ActiutdA",respuestasAGuardar.getAptitudA());
        editorPreferencias.putFloat("ActiutdS",respuestasAGuardar.getAptitudS());
        editorPreferencias.putFloat("ActiutdI",respuestasAGuardar.getAptitudI());
        editorPreferencias.putFloat("ActiutdD",respuestasAGuardar.getAptitudD());
        editorPreferencias.putFloat("ActitudE",respuestasAGuardar.getAptitudE());
        editorPreferencias.commit();

    }
    public void DevolverResultados()
    {
        numeroUltimaPregunta=preferencias.getInt("NumeroDePregunta",0);
        ResultadosUltimoTest.setInteresC(preferencias.getFloat("InteresC",0));
        ResultadosUltimoTest.setInteresH(preferencias.getFloat("InteresH",0));
        ResultadosUltimoTest.setInteresA(preferencias.getFloat("InteresA",0));
        ResultadosUltimoTest.setInteresS(preferencias.getFloat("InteresS",0));
        ResultadosUltimoTest.setInteresI(preferencias.getFloat("InteresI",0));
        ResultadosUltimoTest.setInteresD(preferencias.getFloat("InteresD",0));
        ResultadosUltimoTest.setInteresE(preferencias.getFloat("InteresE",0));
        ResultadosUltimoTest.setAptitudC(preferencias.getFloat("AptitudC",0));
        ResultadosUltimoTest.setAptitudH(preferencias.getFloat("AptitudH",0));
        ResultadosUltimoTest.setAptitudA(preferencias.getFloat("AptitudA",0));
        ResultadosUltimoTest.setAptitudS(preferencias.getFloat("AptitudS",0));
        ResultadosUltimoTest.setAptitudI(preferencias.getFloat("AptitudI",0));
        ResultadosUltimoTest.setAptitudD(preferencias.getFloat("AptitudD",0));
        ResultadosUltimoTest.setAptitudE(preferencias.getFloat("AptitudE",0));
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
        seleccionado="";
        FragmentMostrarListaCarreras mostrarListaCarreras=new FragmentMostrarListaCarreras();
        ReemplazarFragment(mostrarListaCarreras);

    }
    public void ReemplazarDeGraficoALista()
    {
        Fragment Grafico=new FragmentMostrarResultados();
        ListaFragment.add(Grafico);
        FragmentMostrarListaCarreras mostrarListaCarreras=new FragmentMostrarListaCarreras();
        ReemplazarFragment(mostrarListaCarreras);
    }
    public void RemplazarPorTest()
    {
        EstaEnElTest=true;
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
        if(isPlayingAudio)
        {
            Reproductor.stop();
            isPlayingAudio=false;
        }
        ListaFragment.remove(ListaFragment.size()-1);
        Log.d("LLego","al final");
    }
    @Override
    protected void onStart() {
        if(EstaEnElTest)
        {
            Reproductor.start();
            isPlayingAudio=true;
        }
        super.onStart();
    }

    @Override
    protected void onPause() {

        super.onPause();
        if(isPlayingAudio)
        {
            Reproductor.pause();
            isPlayingAudio=false;
        }
    }
}
