package com.example.uniscovery;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentPreguntaChaside extends Fragment implements View.OnClickListener {
    View VistaAUsar;
    Button botonSiguiente;
    TextView numeroPregunta;
    TextView textoPregunta;
    RadioGroup radioOpcion;
    public View onCreateView(LayoutInflater inflater, ViewGroup grupoView, Bundle datosRecibidos) {
        VistaAUsar=inflater.inflate(R.layout.preguntas_chaside,null,true);
        botonSiguiente=VistaAUsar.findViewById(R.id.boton_siguiente);
        numeroPregunta=VistaAUsar.findViewById(R.id.numero_pregunta);
        textoPregunta=VistaAUsar.findViewById(R.id.texto_pregunta);
        radioOpcion=VistaAUsar.findViewById(R.id.radioGroup);
        return VistaAUsar;
    }
    public void onClick(View v) {
        
    }
}
