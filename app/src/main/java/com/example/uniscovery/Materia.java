package com.example.uniscovery;

public class Materia {

    private int IDCarrera;
    private int Año;
    private String NombreMateria;
    private String Descripcion;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Materia() {
        IDCarrera=0;
        Año=0;
        NombreMateria="";
        Descripcion="";
    }

    public int getIDCarrera() {
        return IDCarrera;
    }

    public void setIDCarrera(int IDCarrera) {
        this.IDCarrera = IDCarrera;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int año) {
        Año = año;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }
    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }


}
