package com.example.uniscovery;

public class Carrera {
    public int IDImagen;
    public String NombreCarrera;
    public String NombreFacultad;
    public String Descipcion;
    public String LinkImagen;

    public String getLinkImagen() {
        return LinkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        LinkImagen = linkImagen;
    }

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

    public Carrera(int IDImagen, String nombreCarrera, String nombreFacultad, String descipcion,String Link) {
        this.IDImagen = IDImagen;
        NombreCarrera = nombreCarrera;
        NombreFacultad = nombreFacultad;
        Descipcion=descipcion;
        LinkImagen=Link;
    }
    public Carrera() {
        this.IDImagen = 0;
        NombreCarrera = "";
        NombreFacultad = "";
        Descipcion="";
        LinkImagen="";
    }
    public int getIDImagen(){return IDImagen; }
    public String getNombreCarrera(){return NombreCarrera;}
    public String getNombreFacultad(){return NombreFacultad;}
    public String getDescipcion(){return Descipcion;}
}
