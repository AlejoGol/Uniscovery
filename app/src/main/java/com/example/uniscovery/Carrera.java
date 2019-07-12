package com.example.uniscovery;

public class Carrera {
    public int IDImagen;
    public String NombreCarrera;
    public String NombreFacultad;
    public String Descipcion;


    public Carrera(int IDImagen, String nombreCarrera, String nombreFacultad,String descipcion) {
        this.IDImagen = IDImagen;
        NombreCarrera = nombreCarrera;
        NombreFacultad = nombreFacultad;
        Descipcion=descipcion;
    }
    public Carrera() {
        this.IDImagen = IDImagen;
        NombreCarrera = "";
        NombreFacultad = "";
        Descipcion="";
    }
    public int getIDImagen(){return IDImagen; }
    public String getNombreCarrera(){return NombreCarrera;}
    public String getNombreFacultad(){return NombreFacultad;}
    public String getDescipcion(){return Descipcion;}
}
