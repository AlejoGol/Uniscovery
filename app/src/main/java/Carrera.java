import java.util.List;

public class Carrera {
    public int IDImagen;
    public String NombreCarrera;
    public String NombreFacultad;
    public List<String> Tags;

    public Carrera(int IDImagen, String nombreCarrera, String nombreFacultad, List<String> tags) {
        this.IDImagen = IDImagen;
        NombreCarrera = nombreCarrera;
        NombreFacultad = nombreFacultad;
        Tags = tags;
    }
    public Carrera() {
        this.IDImagen = IDImagen;
        NombreCarrera = "";
        NombreFacultad = "";
        Tags = null;
    }
}
