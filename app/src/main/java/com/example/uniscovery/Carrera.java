package com.example.uniscovery;

import java.util.List;

public class Carrera {
    public int IDImagen;
    public String NombreCarrera;
    public String NombreFacultad;

    public Carrera(int IDImagen, String nombreCarrera, String nombreFacultad) {
        this.IDImagen = IDImagen;
        NombreCarrera = nombreCarrera;
        NombreFacultad = nombreFacultad;

    }
    public Carrera() {
        this.IDImagen = IDImagen;
        NombreCarrera = "";
        NombreFacultad = "";

    }
    public int getIDImagen(){return IDImagen; }
    public String getNombreCarrera(){return NombreCarrera;}
    public String getNombreFacultad(){return NombreFacultad;}
}
