package com.example.uniscovery;

public class Materia {

    private int IDCarrera;
    private int Año;
    private String NombreMateria;
<<<<<<< HEAD
    private String DescripcionMateria;

    public Materia(int IDCarrera, int año, String nombreMateria, String descripcionMateria) {
        this.IDCarrera = IDCarrera;
        Año = año;
        NombreMateria = nombreMateria;
        DescripcionMateria = descripcionMateria;
    }

    public String getDescripcionMateria() {
        return DescripcionMateria;
    }

    public void setDescripcionMateria(String descripcionMateria) {
        DescripcionMateria = descripcionMateria;
=======
    private String Descripcion;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
>>>>>>> 3f9b6ee9c53b91e89400d97fcc22f297e7fcb1ad
    }

    public Materia() {
        IDCarrera=0;
        Año=0;
        NombreMateria="";
<<<<<<< HEAD
        DescripcionMateria="";
=======
        Descripcion="";
>>>>>>> 3f9b6ee9c53b91e89400d97fcc22f297e7fcb1ad
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
