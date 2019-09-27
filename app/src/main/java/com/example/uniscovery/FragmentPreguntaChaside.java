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
    Button botonSiguiente;
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
        CargarPreguntas();
        SetearValores();
        botonSiguiente.setEnabled(false);
        radioOpcion.setOnCheckedChangeListener(this);
        Respuestas=new RespuestaTest();
        return VistaAUsar;
    }
    public void onClick(View v) {

        int IDElegido=radioOpcion.getCheckedRadioButtonId();
        if(IDElegido==R.id.opcion_si)
        {
            if(PreguntaActual.get_Objetivo()=="Interes")
            {
                switch(PreguntaActual.get_IdPregunta())
                {
                    case 'C':
                        Respuestas.setInteresC((Respuestas.getInteresC())+1);
                        break;
                    case 'H':

                        Respuestas.setInteresH((Respuestas.getInteresH())+1);
                        break;
                    case 'A':

                        Respuestas.setInteresA((Respuestas.getInteresA())+1);
                        break;
                    case 'S':
                        Respuestas.setInteresS((Respuestas.getInteresS())+1);
                        break;
                    case 'D':
                        Respuestas.setInteresD((Respuestas.getInteresD())+1);
                        break;
                    case 'E':

                        Respuestas.setInteresE((Respuestas.getInteresE())+1);
                        break;
                    case 'I':
                        Respuestas.setInteresI((Respuestas.getInteresI())+1);
                        break;
                    default:
                        break;
                }
            }
            else
            {
                switch(PreguntaActual.get_IdPregunta())
                {
                    case 'C':
                        Respuestas.setAptitudC((Respuestas.getAptitudC())+1);
                        break;
                    case 'H':
                        Respuestas.setAptitudH((Respuestas.getAptitudH())+1);
                        break;
                    case 'A':
                        Respuestas.setAptitudA((Respuestas.getAptitudA())+1);
                        break;
                    case 'S':
                        Respuestas.setAptitudS((Respuestas.getAptitudS())+1);
                        break;
                    case 'I':
                        Respuestas.setAptitudI((Respuestas.getAptitudI())+1);
                        break;
                    case 'D':
                        Respuestas.setAptitudD((Respuestas.getAptitudD())+1);
                        break;
                    case 'E':
                        Respuestas.setAptitudE((Respuestas.getAptitudE())+1);
                        break;
                    default:
                        break;
                }
            }
        }
        NumeropreguntaActual++;
        CargarPreguntas();
        SetearValores();
    }
    public void SetearValores()
    {
        textoPregunta.setText(PreguntaActual.get_TextoPregunta());
        numeroPregunta.setText("Pregunta Numero"+NumeropreguntaActual);
    }
    public void CargarPreguntas()
    {
        Log.d("BD","CargarPreguntas");
        ManejadorBaseDeDatos DB = new ManejadorBaseDeDatos(this.getActivity().getApplicationContext(), "Universidades.db", null,MainActivity.VersionBD);
        Cursor RegistrosLeidos;
        String SqlConsulta="select * from Preguntas where ID_Pregunta="+NumeropreguntaActual;
        RegistrosLeidos=DB.EjecutarConsulta(SqlConsulta);
        Log.d("BD","Ejecute mi consulta y me fijo que trae");

        Log.d("BD","Numero Registros: "+RegistrosLeidos.getColumnCount());
        Log.d("BD","Numero Registros: "+RegistrosLeidos.getColumnName(0));
        Log.d("BD","Numero Registros: "+RegistrosLeidos.getCount());
        if (RegistrosLeidos.moveToFirst()) {
             do{
                Log.d("BD","entra");
                int id=RegistrosLeidos.getInt(0);
                String NombrePregunta=RegistrosLeidos.getString(1);
                Log.d("BD","Pregunta :"+NombrePregunta);
                String Opcion=RegistrosLeidos.getString(2);
                String Letra=RegistrosLeidos.getString(4);
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
