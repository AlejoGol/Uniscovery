package com.example.uniscovery;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ManejadorBaseDeDatos extends SQLiteOpenHelper {

    public ManejadorBaseDeDatos(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQLliteTabla="create table Carerras (ID_carrera INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Carrera TEXT, Nombre_Facultad TEXT);";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Relacion_Carrera_Tag (ID_Carrera INTEGER, ID_Tag INTEGER)";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Tags (ID_Tag INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Tag TEXT)";
        db.execSQL(SQLliteTabla);
        InsertarValores(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void InsertarValores(SQLiteDatabase BaseDeDatos)
    {
        InsertarCarrera(BaseDeDatos,"CARRERA DE ARQUITECTURA","DI TELLA");
        /*InsertarCarrera(BaseDeDatos,"MAESTRIA EN HISTORIA Y CULTURA DE LA ARQUITECTURA Y DE LA CIUDAD","DI TELLA");
        InsertarCarrera(BaseDeDatos,"PROGRAMA EN ARQUITECTURA Y TECNOLOGIA","DI TELLA");
        InsertarCarrera(BaseDeDatos,"PROGRAMA EN ARQUITECTURA DEL PAISAJE","DI TELLA");
        InsertarCarrera(BaseDeDatos,"PROGRAMA EN PRESERVACION Y CONSERVACION DEL PATRIMONIO","DI TELLA");
        InsertarCarrera(BaseDeDatos,"MAESTRIA EN ECONOMIA URBANA","DI TELLA");
        InsertarCarrera(BaseDeDatos,"CENTRO DE ESTUDIOS EN ARQUITECTURA CONTEMPORANEA","DI TELLA");
        InsertarCarrera(BaseDeDatos,"ARCHIVO DE ARQUITECTURA","DI TELLA");*/
        InsertarCarrera(BaseDeDatos,"CARRERA DE ABOGACÍA","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACION DE EMPRESAS","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA EMPRESARIAL","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ESTUDIOS INTERNACIONALES","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS POLÍTICA Y GOBIERNO","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS SOCIALES","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","DI TELLA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS AMBIENTALES","UBA");
        InsertarCarrera(BaseDeDatos,"AGRONOMIA","UBA");
        InsertarCarrera(BaseDeDatos,"BACHILLERATO UNIVERSITARIO EN AGRONOMIA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA Y ADMINISTRACIÓN AGRARIAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTIÓN DE AGROALIMENTOS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PLANIFICACIÓN Y DISEÑO DEL PAISAJE","UBA");
        InsertarCarrera(BaseDeDatos,"FLORICULTURA","UBA");
        InsertarCarrera(BaseDeDatos,"JARDINERÍA","UBA");
        InsertarCarrera(BaseDeDatos,"MARTILLERO Y CORREDOR PÚBLICO RURAL","UBA");
        InsertarCarrera(BaseDeDatos,"TÉCNICO EN PRODUCCIÓN VEGETAL ORGÁNICA","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICO EN TURISMO RURAL","UBA");
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UBA");
        InsertarCarrera(BaseDeDatos,"DISEÑO DE IMAGEN Y SONIDO","UBA");
        InsertarCarrera(BaseDeDatos,"DISEÑO DE INDUMENTARIA","UBA");
        InsertarCarrera(BaseDeDatos,"DISEÑO GRÁFICO","UBA");
        InsertarCarrera(BaseDeDatos,"DISEÑO INDUSTRIAL","UBA");
        InsertarCarrera(BaseDeDatos,"DISEÑO TEXTIL","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PLANIFICACIÓN Y DISEÑO DEL PAISAJE","UBA");
        InsertarCarrera(BaseDeDatos,"ACTUARIO","UBA");
        InsertarCarrera(BaseDeDatos,"CONTADOR PÚBLICO","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN\n","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SISTEMAS DE INFORMACIÓN DE LAS ORGANIZACIONES","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA DE ALIMENTOS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOGÍA DE ALIMENTOS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS BIOLÓGICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA ATMÓSFERA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMPUTACIÓN","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS FÍSICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS GEOLÓGICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS MATEMÁTICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS OCEANOGRÁFICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS QUÍMICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PALEONTOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA ATMÓSFERA\n","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMPUTACIÓN\n","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS GEOLÓGICAS","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN FÍSICA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN LA ESPECIALIDAD BIOLOGÍA\n","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN MATEMATICA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN QUIMICA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA POLÍTICA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMUNICACIÓN SOCIAL","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RELACIONES DEL TRABAJO","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SOCIOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TRABAJO SOCIAL","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIA POLÍTICA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMUNICACIÓN SOCIAL","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN RELACIONES DEL TRABAJO","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN TRABAJO SOCIAL","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA SECUNDARIA NORMAL Y ESPECIAL EN SOCIOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"CIENCIAS VETERINARIAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTIÓN DE AGROALIMENTOS","UBA");
        InsertarCarrera(BaseDeDatos,"TÉCNICO PARA BIOTERIOS","UBA");
        InsertarCarrera(BaseDeDatos,"ABOGACÍA","UBA");
        InsertarCarrera(BaseDeDatos,"CALÍGRAFO PÚBLICO","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO PARA LA ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS JURÍDICAS\n","UBA");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO PÚBLICO","UBA");
        InsertarCarrera(BaseDeDatos,"BIOQUÍMICA","UBA");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOGÍA DE ALIMENTOS\n","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN ÓPTICA Y CONTACTOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"TÉCNICO PARA BIOTERIOS","UBA");
        InsertarCarrera(BaseDeDatos,"TÉCNICO UNIVERSITARIO EN MEDICINA NUCLEAR","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ARTES","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIBLIOTECOLOGÍA Y CIENCIA DE LA INFORMACIÓN","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS ANTROPOLÓGICAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACIÓN","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FILOSOFÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GEOGRAFÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LETRAS","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN ARTES","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN BIBLIOTECOLOGÍA Y CIENCIA DE LA INFORMACIÓN","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS ANTROPOLÓGICAS","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA EDUCACIÓN","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN FILOSOFÍA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN GEOGRAFÍA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN HISTORIA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN LETRAS\n","UBA");
        InsertarCarrera(BaseDeDatos,"EDICION","UBA");
    }
    private void InsertarCarrera(SQLiteDatabase BD,String Carreras,String Facultad)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("Nombre_Carrera",Carreras);
        NuevoRegistro.put("Nombre_Facultad",Facultad);
        BD.insert("Carerras",null,NuevoRegistro);
    }
}
