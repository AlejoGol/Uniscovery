package com.example.uniscovery;

public class Carrera {
    public int IDImagen;
    public String NombreCarrera;
    public String NombreFacultad;
    public String Descipcion;
    private int IDCarrera;

    public int getIDCarrera() {
        return IDCarrera;
    }

    public void setIDCarrera(int IDCarrera) {
        this.IDCarrera = IDCarrera;
    }

    public void setIDImagen(int IDImagen) {
        this.IDImagen = IDImagen;
    }

    public Carrera(int IDImagen, String nombreCarrera, String nombreFacultad, String descipcion) {
        this.IDImagen = IDImagen;
        NombreCarrera = nombreCarrera;
        NombreFacultad = nombreFacultad;
        Descipcion=descipcion;
    }
    public Carrera() {
        this.IDImagen = 0;
        NombreCarrera = "";
        NombreFacultad = "";
        Descipcion="";
    }
    public int getIDImagen(){return IDImagen; }
    public String getNombreCarrera(){return NombreCarrera;}
    public String getNombreFacultad(){return NombreFacultad;}
    public String getDescipcion(){return Descipcion;}
}
