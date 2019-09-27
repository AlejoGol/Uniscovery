package com.example.uniscovery;

public class Pregunta {
    private int _IdPregunta;
    private String _TextoPregunta;
    private String _Objetivo;
    private String _Letra;

    public Pregunta(int IdPregunta, String TextoPregunta, String Objetivo,String Letra) {
        _IdPregunta = IdPregunta;
        _TextoPregunta = TextoPregunta;
        _Objetivo = Objetivo;
        _Letra=Letra;
    }

    public int get_IdPregunta() {
        return _IdPregunta;
    }

    public void set_IdPregunta(int _IdPregunta) {
        this._IdPregunta = _IdPregunta;
    }

    public String get_TextoPregunta() {
        return _TextoPregunta;
    }

    public void set_TextoPregunta(String _TextoPregunta) {
        this._TextoPregunta = _TextoPregunta;
    }

    public String get_Objetivo() {
        return _Objetivo;
    }

    public void set_Objetivo(String _Objetivo) {
        this._Objetivo = _Objetivo;
    }
}
