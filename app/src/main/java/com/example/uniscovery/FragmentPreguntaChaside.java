package com.example.uniscovery;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class FragmentPreguntaChaside extends Fragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    View VistaAUsar;
    Button botonSiguiente,BotonGuardarResultados;
    TextView numeroPregunta;
    TextView textoPregunta;
    RadioGroup radioOpcion;
    int NumeropreguntaActual;
    Pregunta PreguntaActual;
    RespuestaTest Respuestas;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos) {
        VistaAUsar=inflater.inflate(R.layout.preguntas_chaside,null,true);
        botonSiguiente=VistaAUsar.findViewById(R.id.boton_siguiente);
        numeroPregunta=VistaAUsar.findViewById(R.id.numero_pregunta);
        textoPregunta=VistaAUsar.findViewById(R.id.texto_pregunta);
        radioOpcion=VistaAUsar.findViewById(R.id.radioGroup);
        NumeropreguntaActual=1;
        BotonGuardarResultados=VistaAUsar.findViewById(R.id.SeguirDesPues);
        BotonGuardarResultados.setOnClickListener(this);
        botonSiguiente.setOnClickListener(this);
        botonSiguiente.setEnabled(false);
        radioOpcion.setOnCheckedChangeListener(this);
        Respuestas=new RespuestaTest();
        MainActivity main=(MainActivity) getActivity();
        main.DevolverResultados();
        MainActivity.PlayAudio(this.getContext(),R.raw.relax);
        if(main.numeroUltimaPregunta>1&&main.numeroUltimaPregunta!=99)
        {
            NumeropreguntaActual=main.numeroUltimaPregunta;
            Respuestas=main.getResultadosUltimoTest();
            CargarPreguntas();
            SetearValores();
        }
        else
        {
            CargarPreguntas();
            SetearValores();
        }
        return VistaAUsar;
    }
    public void onClick(View v) {

        if(v.getId()==botonSiguiente.getId())
        {
            int IDElegido=radioOpcion.getCheckedRadioButtonId();
            if(IDElegido==R.id.opcion_si)
            {
                Log.d("CHASIDE","entro al si ");
                Log.d("CHASIDE"," "+PreguntaActual.get_Objetivo());

                if(PreguntaActual.get_Objetivo().equals("INTERES"))
                {
                    Log.d("Chaside",""+PreguntaActual.get_Letra());
                    switch(PreguntaActual.get_Letra()) {
                        case "C":
                            float contador=Respuestas.getInteresC();
                            contador++;
                            Log.d("Switch ", ""+contador);
                            Respuestas.setInteresC(contador);
                            Log.d("CHASIDE", " valor respuesta " + Respuestas.getInteresC());
                            break;
                        case "H":
                            Respuestas.setInteresH((Respuestas.getInteresH() + 1f));
                            Log.d("CHASIDE", " valor respuesta " + Respuestas.getInteresH());
                            break;
                        case "A":
                            Respuestas.setInteresA((Respuestas.getInteresA() + 1f));
                            Log.d("CHASIDE"," valor respuesta " + Respuestas.getInteresA());
                            break;
                        case "S":
                            Respuestas.setInteresS((Respuestas.getInteresS())+1f);
                            Log.d("CHASIDE"," valor respuesta " + Respuestas.getInteresS());
                            break;
                        case "D":
                            Respuestas.setInteresD((Respuestas.getInteresD())+1f);
                            Log.d("CHASIDE"," valor respuesta " + Respuestas.getInteresD());
                            break;
                        case "E":
                            Respuestas.setInteresE((Respuestas.getInteresE())+1f);
                            Log.d("CHASIDE"," valor respuesta " + Respuestas.getInteresE());
                            break;
                        case "I":
                            Respuestas.setInteresI((Respuestas.getInteresI())+1f);
                            Log.d("CHASIDE"," valor respuesta " + Respuestas.getInteresI());
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    switch(PreguntaActual.get_Letra())
                    {
                        case "C":
                            Respuestas.setAptitudC((Respuestas.getAptitudC())+1f);
                            break;
                        case "H":
                            Respuestas.setAptitudH((Respuestas.getAptitudH())+1f);
                            break;
                        case "A":
                            Respuestas.setAptitudA((Respuestas.getAptitudA())+1f);
                            break;
                        case "S":
                            Respuestas.setAptitudS((Respuestas.getAptitudS())+1f);
                            break;
                        case "I":
                            Respuestas.setAptitudI((Respuestas.getAptitudI())+1f);
                            break;
                        case "D":
                            Respuestas.setAptitudD((Respuestas.getAptitudD())+1f);
                            break;
                        case "E":
                            Respuestas.setAptitudE((Respuestas.getAptitudE())+1f);
                            break;
                        default:
                            break;
                    }
                }
            }
            NumeropreguntaActual++;
            if(NumeropreguntaActual==99)
            {
                MainActivity main=(MainActivity)getActivity();
                main.setResultadosUltimoTest(Respuestas);
                main.RemplazarPorResultados();
            }
            else
            {
                radioOpcion.clearCheck();
                CargarPreguntas();
                SetearValores();
            }
        }
        else
        {
         MainActivity mainActivity=(MainActivity) getActivity();
         mainActivity.ReemplazarInformacionUltimoTest(Respuestas,NumeropreguntaActual);
        }

    }
    public void SetearValores()
    {
        textoPregunta.setText(PreguntaActual.get_TextoPregunta());
        numeroPregunta.setText("Pregunta Numero "+NumeropreguntaActual);
    }
    public void CargarPreguntas()
    {
        Log.d("BD","CargarPreguntas");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,16);
        Cursor RegistrosLeidos;
        String SqlConsulta="select * from Preguntas where ID_Pregunta="+NumeropreguntaActual;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        Log.d("BD","Ejecute mi consulta y me fijo que trae");

        Log.d("BD","Numero Registros: "+RegistrosLeidos.getColumnCount());
        Log.d("BD","Numero Registros: "+RegistrosLeidos.getColumnName(0));
        Log.d("BD","Numero Registros: "+RegistrosLeidos.getColumnName(3));
        Log.d("BD","Numero Registros: "+RegistrosLeidos.getCount());
        if (RegistrosLeidos.moveToFirst()) {
             do{
                Log.d("BD","entra");
                int id=RegistrosLeidos.getInt(0);
                String NombrePregunta=RegistrosLeidos.getString(1);
                Log.d("BD","Pregunta :"+NombrePregunta);
                String Opcion=RegistrosLeidos.getString(2);
                String Letra=RegistrosLeidos.getString(3);
                Log.d("BD","Opcion de la pregunta: "+Opcion);
                PreguntaActual=new Pregunta(id,NombrePregunta,Opcion,Letra);
             }while(RegistrosLeidos.moveToNext());
        }
        else
        {
            Log.d("BD","No entra al if");
        }
        RegistrosLeidos.close();
        //Log.d("BD","Pregunta: "+PreguntaActual.get_IdPregunta());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId)
            {
                case R.id.opcion_no:
                case R.id.opcion_si:
                    botonSiguiente.setEnabled(true);
                    break;
                    default:
                        botonSiguiente.setEnabled(false);
                        break;
            }
    }
}
