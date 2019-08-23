package com.example.uniscovery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public  class ManejadorBaseDeDatos extends SQLiteOpenHelper {

    SQLiteDatabase DB;
    public  ManejadorBaseDeDatos(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("BD","creada");
    }

    private void DropTables(SQLiteDatabase db){
        Log.d("BD","DropTables");
        db.execSQL("DROP TABLE IF EXISTS Carerras");
        db.execSQL("DROP TABLE IF EXISTS Relacion_Carrera_Tag");
        db.execSQL("DROP TABLE IF EXISTS Tags");
        db.execSQL("DROP TABLE IF EXISTS Materias");
        db.execSQL("DROP TABLE IF EXISTS Preguntas");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("BD","onCreate");
        DropTables(db);
        String SQLliteTabla="create table Carerras (ID_carrera INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Carrera TEXT, Nombre_Facultad TEXT, Descripcion TEXT);";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Relacion_Carrera_Tag (ID_Carrera INTEGER, ID_Tag INTEGER)";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Tags (ID_Tag INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Tag TEXT)";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Materias (ID_Materia INTEGER PRIMARY KEY AUTOINCREMENT, ID_Carrera INTEGER, Nombre_Materia TEXT, Anio INTEGER,Opcionalidad INTEGER)";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Preguntas (ID_Pregunta INTEGER PRIMARY KEY AUTOINCREMENT, TextoPregunta TEXT, InteligenciaMultiple TEXT)";
        db.execSQL(SQLliteTabla);
        DB=db;
        InsertarValores(db);
        Tags(db);
        MateriasDeCarrera(db);
        Preguntas(db);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("BD","onUpgrade");
        onCreate( db);
    }
    public Cursor EjecutarConsulta(String Consulta)
    {
        SQLiteDatabase midb;
        midb = this.getReadableDatabase();

        Cursor RegistrosLeidos;
        Log.d("BD","CargarInformacion:" + Consulta);
        RegistrosLeidos=midb.rawQuery(Consulta,null);
        return RegistrosLeidos;
    }
    private void InsertarValores(SQLiteDatabase BaseDeDatos)
    {
        InsertarCarrera(BaseDeDatos,"CARRERA DE ARQUITECTURA","DI TELLA","hola");
        InsertarCarrera(BaseDeDatos,"CARRERA DE ABOGACÍA","DI TELLA","Desde su fundación en 1996 la Escuela de Derecho de la UTDT ofrece un espacio de formación profesional e investigación jurídica avanzada que cumple el doble objetivo de participar del diálogo académico nacional e internacional sobre el derecho y de contribuir a la generación de recursos humanos altamente capacitados para desempeñar los diversos roles que tiene la profesión en el país.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACION DE EMPRESAS","DI TELLA","La Licenciatura en Administración de Empresas de la Di Tella busca formar profesionales ágiles, creativos y eficientes, con una visión global y completa de la empresa, capacitados para gestionar recursos en un mundo de creciente complejidad a nivel organizacional (empresas más grandes, en más países, con culturas diferentes). Para ello, nuestra Licenciatura brinda conocimientos en áreas de la gestión tales como dirección estratégica, gestión de recursos humanos, organización de la producción, marketing y finanzas, entre otras.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA EMPRESARIAL","DI TELLA","Comparte con la Licenciatura en Administración de Empresas un eje troncal de formación en áreas de economía, cultura general, finanzas y contabilidad gerencial, pero se distingue de la misma por su perspectiva más cuantitativa, focalizada en el uso de herramientas para la toma de decisiones empresariales. A diferencia de la Licenciatura en Economía, el enfoque está puesto principalmente en los negocios");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA","DI TELLA","Los graduados de la Licenciatura en Economía de la Universidad Torcuato Di Tella poseen una sólida formación científica y una excelente calidad profesional. Están habilitados para insertarse laboralmente en los sectores público y privado y también para enfrentar exigentes estudios de posgrado");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO","DI TELLA","La Licenciatura en Diseño de la Universidad Torcuato Di Tella conecta con su propia historia en el campo de la industria, el diseño y las artes -la de la empresa SIAM y la del Instituto Torcuato Di Tella- y retoma la experiencia académica y profesional de las últimas décadas para encarar los desafíos contemporáneos. Se trata de una propuesta innovadora para un mundo interconectado, en el que los productos, los proyectos y las ideas circulan a gran velocidad y en muy distintos soportes.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ESTUDIOS INTERNACIONALES","DI TELLA","Durante la carrera se analizan cuestiones tales como: ¿cuáles son los principales dilemas que caracterizan a la política mundial?; ¿quiénes ganan qué y cómo en la economía política internacional?; ¿de qué manera se expresan y despliegan las políticas exteriores de los países?; ¿cómo inciden fenómenos religiosos, históricos y culturales en la identidad nacional?; ¿cuándo y cómo ascienden las naciones en términos de las relaciones internacionales?; ¿existe un nuevo modelo de cooperación Sur-Sur?");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS POLÍTICA Y GOBIERNO","DI TELLA","El licenciado en Ciencia Política y Gobierno recibe una formación sistemática y empírica que lo prepara para analizar y actuar profesionalmente frente a los fenómenos complejos y los dilemas que rodean la vida política y la gestión de instituciones de gobierno. Para lograr estos objetivos el plan de estudios ofrece una sólida formación multidisciplinaria a cargo de un cuerpo de profesores caracterizado por la excelencia de su investigación, una importante experiencia académica y una fuerte vocación por la innovación. Dada la complejidad de los fenómenos políticos, nuestro plan de estudios incluye materias de Teoría Política, Economía, Historia, Derecho y Estadística.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS SOCIALES","DI TELLA","La Licenciatura en Ciencias Sociales se distingue por su organización en orientaciones profesionales. Cada una de ellas está coordinada por un profesor de la Di Tella. Desde el tercer año, los estudiantes eligen una entre cuatro posibilidades: Comunicación y Periodismo; Economía y Política; Historia y Cultura; Sociedad y Mercados. Las cuatro orientaciones están pensadas para diversas alternativas profesionales con perfil siglo XXI." );
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","DI TELLA","La carrera de Historia ofrece al graduado una amplia gama de posibilidades laborales y de desarrollo profesional. Para aquellos interesados en una carrera académica, la Licenciatura les provee las herramientas necesarias para iniciarse en la investigación. Al mismo tiempo, la carrera suministra al graduado un conjunto de saberes y habilidades cuya utilidad no es exclusiva del trabajo del historiador profesional");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS AMBIENTALES","UBA","Las Ciencias Ambientales reúnen la evaluación científica de las potencialidades, limitaciones y susceptibilidades del ambiente, con el desarrollo de soluciones a los problemas de deterioro de la calidad ambiental. Constituyen una respuesta a los requerimientos de armonizar el desarrollo con la preservación de la calidad de los recursos naturales renovables y con la conservación de la calidad del ambiente.");
        InsertarCarrera(BaseDeDatos,"AGRONOMIA","UBA","La carrera de Agronomía se dicta en Buenos Aires y tiene una duración de 5 años y medio. Su carga horaria total es de 3752 horas, que corresponden a 234,5 créditos de los cuales 218 pertenecen a 52 asignaturas obligatorias y al trabajo final, y el resto a asignaturas electivas y optativas. Al haber aprobado 28 asignaturas obligatorias correspondientes a los tres primeros años, se otorga el título intermedio de Bachiller Universitario en Agronomía.");
        /*InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA Y ADMINISTRACIÓN AGRARIAS","UBA");
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
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN","UBA");
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
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA ATMÓSFERA","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMPUTACIÓN","UBA");
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
        InsertarCarrera(BaseDeDatos,"PROFESORADO PARA LA ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS JURÍDICAS","UBA");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO PÚBLICO","UBA");
        InsertarCarrera(BaseDeDatos,"BIOQUÍMICA","UBA");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOGÍA DE ALIMENTOS","UBA");
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
        InsertarCarrera(BaseDeDatos,"INGENIERÍA CIVIL","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA DE ALIMENTOS","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTRICISTA","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTRÓNICA","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN AGRIMENSURA","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN INFORMÁTICA","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN PETRÓLEO","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA INDUSTRIAL","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA MECÁNICA","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA NAVAL Y MECÁNICA","UBA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA QUÍMICA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ANÁLISIS DE SISTEMAS","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMERÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMERÍA (CICLO DE COMPLEMENTACIÓN CURRICULAR)","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FONOAUDIOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN KINESIOLOGÍA Y FISIATRÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN NUTRICIÓN","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN OBSTETRICIA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PODOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCIÓN DE BIOIMAGENES","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCIÓN DE BIOIMÁGENES (CICLO DE COMPLEMENTACIÓN CURRICULAR)","UBA");
        InsertarCarrera(BaseDeDatos,"MEDICINA","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN ANESTESIA","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN COSMETOLOGÍA FACIAL Y CORPORAL","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN HEMOTERAPIA E INMUNOHEMATOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN INSTRUMENTACIÓN QUIRÚRGICA","UBA");
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN PODOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"TÉCNICOS UNIVERSITARIOS EN PRÁCTICAS CARDIOLÓGICAS","UBA");
        InsertarCarrera(BaseDeDatos,"ODONTOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN MUSICOTERAPIA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TERAPIA OCUPACIONAL","UBA");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN PSICOLOGÍA","UBA");
        InsertarCarrera(BaseDeDatos,"CIENCIAS BÁSICAS","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERIA CIVIL","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELÉCTRICA","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTRÓNICA","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA INDUSTRIAL","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA NAVAL","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA QUÍMICA","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN SISTEMAS DE INFORMACIÓN","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA TEXTIL","UTN");
        InsertarCarrera(BaseDeDatos,"MEDICINA","UCES");
        InsertarCarrera(BaseDeDatos,"KINESIOLOGIA Y FISIATRIA","UCES");
        InsertarCarrera(BaseDeDatos,"NUTRICION","UCES");
        InsertarCarrera(BaseDeDatos,"ENFERMERÍA","UCES");
        InsertarCarrera(BaseDeDatos,"GESTION Y ASISTENCIA DE QUIROFANOS","UCES");
        InsertarCarrera(BaseDeDatos,"MUSICOTERAPIA","UCES");
        InsertarCarrera(BaseDeDatos,"ADMINISTRACION DE SERVICIOS DE SALUD","UCES");
        InsertarCarrera(BaseDeDatos,"VETERINARIA","UCES");
        InsertarCarrera(BaseDeDatos,"AGRONOMIA","UCES");
        InsertarCarrera(BaseDeDatos,"MARKETING","UCES");
        InsertarCarrera(BaseDeDatos,"DIRECCION DE NEGOCIOS","UCES");
        InsertarCarrera(BaseDeDatos,"COMERCIO EXTERIOR","UCES");
        InsertarCarrera(BaseDeDatos,"TURISMO","UCES");
        InsertarCarrera(BaseDeDatos,"PROGRAMA DE SISTEMAS","UCES");
        InsertarCarrera(BaseDeDatos,"GERENCIAMIENTO AMBIENTAL","UCES");
        InsertarCarrera(BaseDeDatos,"COMUNICACION SOCIAL","UCES");
        InsertarCarrera(BaseDeDatos,"PUBLICICDAD","UCES");
        InsertarCarrera(BaseDeDatos,"RELACIONES PUBLICAS E INSTITUCIONALES","UCES");
        InsertarCarrera(BaseDeDatos,"PERIODISMO","UCES");
        InsertarCarrera(BaseDeDatos,"DISEÑO Y COMUNICACION VISUAL","UCES");
        InsertarCarrera(BaseDeDatos,"CONTADOR PUBLICO","UCES");
        InsertarCarrera(BaseDeDatos,"ADMINISTRACION DE EMPRESAS","UCES");
        InsertarCarrera(BaseDeDatos,"ECONOMIA","UCES");
        InsertarCarrera(BaseDeDatos,"RECURSOS HUMANOS","UCES");
        InsertarCarrera(BaseDeDatos,"ABOGACIA","UCES");
        InsertarCarrera(BaseDeDatos,"CIENCIAS POLITICAS Y DE GOBIERNO","UCES");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE PSICOLOGIA","UCES");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE SOCIOLOGIA","UCES");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE FILOSOFIA","UCES");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE EDUCACION INICAL","UCES");
        InsertarCarrera(BaseDeDatos,"BIOLOGIA","UMAI");
        InsertarCarrera(BaseDeDatos,"CIENCIAS BIOLOGICAS","UMAI");
        InsertarCarrera(BaseDeDatos,"ENFERMERIA","UMAI");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UMAI");
        InsertarCarrera(BaseDeDatos,"INSTRUMENTACION QUIRURGICA","UMAI");
        InsertarCarrera(BaseDeDatos,"KINESIOLOGIA Y FISIATRIA","UMAI");
        InsertarCarrera(BaseDeDatos,"MEDICINA","UMAI");
        InsertarCarrera(BaseDeDatos,"MUSICOTERAPIA","UMAI");
        InsertarCarrera(BaseDeDatos,"NUTRICION","UMAI");
        InsertarCarrera(BaseDeDatos,"ODONTOLOGIA","UMAI");
        InsertarCarrera(BaseDeDatos,"ABOGACIA","UMAI");
        InsertarCarrera(BaseDeDatos,"ADMINISTRACION","UMAI");
        InsertarCarrera(BaseDeDatos,"CIENCIAS POLITICAS","UMAI");
        InsertarCarrera(BaseDeDatos,"CONSULTORIA PSICOLOGICA","UMAI");
        InsertarCarrera(BaseDeDatos,"CONTADOR PUBLICO","UMAI");
        InsertarCarrera(BaseDeDatos,"EDUCACION FISICA Y DEPORTE","UMAI");
        InsertarCarrera(BaseDeDatos,"ESTRATEGA CONTEMPORANEA","UMAI");
        InsertarCarrera(BaseDeDatos,"GERONTOLOGIA","UMAI");
        InsertarCarrera(BaseDeDatos,"GESTION ORGANIZACIONAL","UMAI");
        InsertarCarrera(BaseDeDatos,"HISTORIA","UMAI");
        InsertarCarrera(BaseDeDatos,"PERIODISMO","UMAI");
        InsertarCarrera(BaseDeDatos,"PROFESORA UNIVERSITARIO","UMAI");
        InsertarCarrera(BaseDeDatos,"PSICOLOGIA","UMAI");
        InsertarCarrera(BaseDeDatos,"RECURSOS HUMANOS","UMAI");
        InsertarCarrera(BaseDeDatos,"RELACIONES INTERNACIONALES","UMAI");
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO DE LA IMAGEN VISUAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO AUDIOVISUAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO DE INTERIOR","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO GRÁFICO","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO INDUSTRIAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO MULTIMEDIA Y DE INTERACCIÓN","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEO Y GESTION DE ESTÉTICA PARA LA MODA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTIÓN DE ARTE","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO DISEÑO GRÁFICO Y DISEÑO MULTIMEDIA Y DE INTERACCIÓN","UADE");
        InsertarCarrera(BaseDeDatos,"CONTADOR PUBLICO","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTION DE SERVICIOS DE SALUD","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACION DE EMPRESAS","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACION","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIO INTERNACIONAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DIRECCION DE NEGOCIOS GLOBALES","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMIA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FINANZAS","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RECURSOS HUMANOS","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO ECONOMIA Y FINANZAS","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO ADMINISTRACION DE EMPRESAS Y COMERCIALIZACION","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO ADMINISTRACION DE EMPRESAS Y COMERCIO INTERNACIONAL","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO ADMINISTRACION DE EMPRESAS Y CONTADOR PUBLICO","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO ADMINISTRACION DE EMPRESAS Y FINANZAS","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO ADMINISTRACION DE EMPRESAS Y RECURSOS HUMANOS","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO COMERCIALIZACION Y COMERCIO INTERNACIONAL","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO CONTADOR PUBLICO Y ABOGADO","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO CONTADOR PUBLICO Y FINANZAS","UADE");
        InsertarCarrera(BaseDeDatos,"ABOGACIA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GOBIERNO Y RELACIONES INTERNACIONALES","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOLOGIA","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO TRADUCTORADO PUBLICO EN IDIDOMA INGLES E INTERPRETARIADO SIMULTANEO DE IDIOMA INGLES","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO POLITICA Y ADMINISTRACION PUBLICA - GOBIERNO Y RELACIONES INTERNACIONALES","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO TRADUCTORADO PUBLICO EN IDIOMA INGLES Y TRADUCTORADO CIENTIFICO - TECNICO EN IDIOMA INGLES","UADE");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO PUBLICO EN IDIOMA INGLES","UADE");
        InsertarCarrera(BaseDeDatos,"CICLO EN LICENCIATURA EN CIENCIAS DE LA COMUNICACION","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ARTES ESCENICAS","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMUNICACION","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMUNICACION GLOBAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTION DE EMPRESAS GASTRONOMICAS Y DE ALOJAMIENTO","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTION DE MEDIOS Y ENTRETENIMIENTO","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTION DEPORTIVA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PUBLICIDAD","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RELACIONES PUBLICAS E INSTITUCIONALES","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TURISMO Y HOTELERIA","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO RELACIONES PUBLICAS E INSTITUCIONALES Y CIENCIAS DE LA COMUNICACION","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO RELACIONES PUBLICAS E INSTITUCIONALES Y GOBIERNO Y RELACIONES INTERNACIONALES","UADE");//LISTO UBA,UTN,UADE,MAIMONIDES,DI TELLA Y UCES
        InsertarCarrera(BaseDeDatos,"INGENIERIA  ELECTROMECANICA","UADE");
        InsertarCarrera(BaseDeDatos,"INGENIERIA ELECTRONICA","UADE");
        InsertarCarrera(BaseDeDatos,"INGENIERIA EN ALIMENTOS","UADE");
        InsertarCarrera(BaseDeDatos,"INGENIERIA EN TELECOMUNICACIONES","UADE");
        InsertarCarrera(BaseDeDatos,"INGENIERIA INDUSTRIAL","UADE");
        InsertarCarrera(BaseDeDatos,"INGENIERIA INFORMATICA ","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIOINFORMATICA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTION AMBIENTAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTION DE TECNOLOGIA DE LA INFORMACION","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN NUTRICION","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCION Y GESTION AGROPECUARIA","UADE");
        InsertarCarrera(BaseDeDatos,"KICENCIATURA EN TECNOLOGIA INDUUSTRIAL DE LOS ALIMENTOS","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TECNOLOGIA INDUSTRIAL DE LOS ALIMENTOS Y LICENCIATURA EN BIOTECNOLOGIA","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO LICENCIATURA EN TURISMO Y HOTELERIA","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO INGENIERIA INDUSTRIAL E INGENIERIA ELECTROMECANICA","UADE");
        InsertarCarrera(BaseDeDatos,"ABOGACIA","UDESA");
        InsertarCarrera(BaseDeDatos,"ECONOMIA","UDESA");
        InsertarCarrera(BaseDeDatos,"ADMINISTRACION DE EMPRESAS","UDESA");
        InsertarCarrera(BaseDeDatos,"CIENCIAS DE LA EDUCACION","UDESA");
        InsertarCarrera(BaseDeDatos,"CIENCIAS POLITICAS Y GOBIERNO","UDESA");
        InsertarCarrera(BaseDeDatos,"CONTADOR PUBLICO","UDESA");
        InsertarCarrera(BaseDeDatos,"DISEÑO","UDESA");
        InsertarCarrera(BaseDeDatos,"COMUNIDAD","UDESA");
        InsertarCarrera(BaseDeDatos,"FINANZAS","UDESA");
        InsertarCarrera(BaseDeDatos,"HUMANIDADES","UDESA");
        InsertarCarrera(BaseDeDatos,"RELACIONES INTERNACIONALES","UDESA");
        InsertarCarrera(BaseDeDatos,"NEGOCIOS DIGITALES","UDESA");//LISTA UDESA(SAN MARTIN)
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UP");
        InsertarCarrera(BaseDeDatos,"INGENIERIA ELECTRONICA","UP");
        InsertarCarrera(BaseDeDatos,"INGENIERIA EN INFORMATICA","UP");
        InsertarCarrera(BaseDeDatos,"INGENIERIA INDUSTRIAL","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN INFORMATICA","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACION DE SISTEMAS Y EMPRESAS","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN REDES Y COMUNICACION DE DATOS","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TECNOLOGIA DE LA INFORMACION","UP");
        InsertarCarrera(BaseDeDatos,"TELECOMUNICACIONES","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA DE ADMINISTRACION","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACION DE SISTEMAS Y EMPRESAS","UP");
        InsertarCarrera(BaseDeDatos,"CONTADOR PUBLICO","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RECURSOS HUMANOS","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN MARKETING","UP");
        InsertarCarrera(BaseDeDatos,"MARKERTING Y PUBLICIDAD","UP");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIO INTERNACIONAL","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Management: Economía y Finanzas","UP");
        InsertarCarrera(BaseDeDatos,"Programa Conjunto en Hotelería y Turismo","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gastronomía","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Arte","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Relaciones Internacionales y Ciencia Política","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Humanidades y Ciencias Sociales","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Periodismo","UP");
        InsertarCarrera(BaseDeDatos,"Periodismo Deportivo","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicología","UP");
        InsertarCarrera(BaseDeDatos,"Arte Multimedial","UP");
        InsertarCarrera(BaseDeDatos,"Actuación Profesional","UP");
        InsertarCarrera(BaseDeDatos,"Comunicación de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Comunicación Digital","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Calzado","UP");
        InsertarCarrera(BaseDeDatos,"Diseño Gráfico","UP");
        InsertarCarrera(BaseDeDatos,"Diseño Editorial","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Imagen Empresarial","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Joyas","UP");
        InsertarCarrera(BaseDeDatos,"Diseño Packaging","UP");
        InsertarCarrera(BaseDeDatos,"Dirección de Actores de Cine y TV","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Espacios Comerciales","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Imagen y Sonido","UP");
        InsertarCarrera(BaseDeDatos,"Diseño Industrial","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Interiores","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Mobiliarios","UP");
        InsertarCarrera(BaseDeDatos,"Diseño de Vidrieras","UP");
        InsertarCarrera(BaseDeDatos,"Escenografía","UP");
        InsertarCarrera(BaseDeDatos,"Fotografía de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Guión de Cine y TV","UP");
        InsertarCarrera(BaseDeDatos,"Historietas","UP");
        InsertarCarrera(BaseDeDatos,"Ilustración","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Actuación","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comunicación Audiovisual","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Creación Sonora","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Fotografía","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Dirección Cinematográfica","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Diseño de Espectáculos","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Dirección Teatral","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Publicidad","UP");
        InsertarCarrera(BaseDeDatos,"Marketing de la Moda","UP");
        InsertarCarrera(BaseDeDatos,"Moldería & Confección","UP");
        InsertarCarrera(BaseDeDatos,"Producción de Espectáculos","UP");
        InsertarCarrera(BaseDeDatos,"Producción de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Producción Musical","UP");
        InsertarCarrera(BaseDeDatos,"Producción de Televisión","UP");
        InsertarCarrera(BaseDeDatos,"Relaciones Públicas","UP");
        InsertarCarrera(BaseDeDatos,"Organización de Eventos","UP");
        InsertarCarrera(BaseDeDatos,"Vestuario","UP");
        InsertarCarrera(BaseDeDatos,"Abogacia","UP");//LISTO UP Y LA RE CONCHA DE TU HERMANA
        InsertarCarrera(BaseDeDatos,"Licenciatura en Marketing","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración de Negocios","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración de Recursos Humanos","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Comercio Internacional","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Higiene y Seguridad del Trabajo","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Turismo","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gestión Ambiental","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Biológicas","CAECE");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Gestión, Manejo y Conservación de Biodiversidad Educación","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Educación","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Gestión Educativa","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Matemática","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Matemática","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicología","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicomotricidad","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Psicomotricidad","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicopedagogía","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Psicopedagogía","CAECE");
        InsertarCarrera(BaseDeDatos,"Ingeniería en Sistemas","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Sistemas","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gestión de Sistemas y Negocios","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Nanotecnología","CAECE");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Programación","CAECE");//LISTO CAECE Y +1 HORA
        InsertarCarrera(BaseDeDatos,"Licenciatura en analitica empresarial y social","ITBA");
        InsertarCarrera(BaseDeDatos,"Licenciatura en administracion y sistemas","ITBA");
        InsertarCarrera(BaseDeDatos,"bioingenieria","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenieria electronica","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingeniería industrial","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingeniería informatica","ITBA");
        InsertarCarrera(BaseDeDatos,"ingenieria mecanica","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingeniería naval","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingeniería en petroleo","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingeniería quimica","ITBA");//LISTO ITBA +15 MIN
        InsertarCarrera(BaseDeDatos,"Arquitectura Naval","UNQ");
        InsertarCarrera(BaseDeDatos,"Ingeniería en Alimentos","UNQ");
        InsertarCarrera(BaseDeDatos,"Ingeniería en Automatización y Control Industrial","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Bioinformática","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Biotecnología","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Informática","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Biotecnología","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Programación Informática","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Química","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Tecnología Ambiental y Petroquímica","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Sociales","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comunicación Social","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Educación","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Educación ","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Enfermería","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Historia","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Terapia Ocupacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Ciencias Sociales","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Comunicación Social","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Educación","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Historia","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Sociales y Humanidades","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Educación","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Geografía","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Terapia Ocupacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura en Gestión de Medios Comunitarios","UNQ");
        InsertarCarrera(BaseDeDatos,"Economía y Administración","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración Hotelera","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comercio Internacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Economía del Desarrollo","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gestión de Recursos Humanos y Relaciones Laborales","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Economía Social y Solidaria","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Gestión de Pequeñas y Medianas Empresas","UNQ");
        InsertarCarrera(BaseDeDatos,"Contador Público","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comercio Internacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Turismo y Hotelería","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Ciencias Empresariales","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Artes Digitales","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Composición con Medios Electroacústico","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Música y Tecnología","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Creación Musical","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Producción Digital","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Producción Musical y Nuevas Tecnologías","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Artes y Tecnologías","UNQ");//LISTO UNQ FALTAN BELGRANO UNLAM UCEMA
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UB");
        InsertarCarrera(BaseDeDatos,"DISEÑO GRÁFICO","UB");
        InsertarCarrera(BaseDeDatos,"PUBLICIDAD","UB");
        InsertarCarrera(BaseDeDatos,"DISEÑO DE INTERIORES","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIAS QUÍMICAS","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIAS BIOLÓGICAS","UB");
        InsertarCarrera(BaseDeDatos,"TECNOLOGÍA DE ALIMENTOS","UB");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UB");
        InsertarCarrera(BaseDeDatos,"ABOGACÍA","UB");
        InsertarCarrera(BaseDeDatos,"RELACIONES INTERNACIONALES","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIA POLÍTICA, GOBIERNO Y ADMINISTRACIÓN","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIAS DE LA COMUNICACIÓN","UB");
        InsertarCarrera(BaseDeDatos,"ADMINISTRACIÓN Y GESTIÓN DE AGRONEGOCIOS","UB");
        InsertarCarrera(BaseDeDatos,"CONTADOR PÚBLICO","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIO EXTERIOR","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACIÓN","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN DE RECURSOS HUMANOS","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HOTELERÍA","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TURISMO","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GASTRONOMÍA","UB");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA CIVIL","UB");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA INDUSTRIAL","UB");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN INFORMÁTICA","UB");
        InsertarCarrera(BaseDeDatos,"SISTEMAS DE INFORMACIÓN","UB");
        InsertarCarrera(BaseDeDatos,"NUTRICIÓN","UB");
        InsertarCarrera(BaseDeDatos,"PSICOLOGÍA","UB");
        InsertarCarrera(BaseDeDatos,"RELACIONES PÚBLICAS E INSTITUCIONALES","UB");
        InsertarCarrera(BaseDeDatos,"PRODUCCIÓN Y DIRECCIÓN DE TV, CINE Y RADIO","UB");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO PÚBLICO, LITERARIO Y CIENTÍFICO-TÉCNICO DE INGLÉS","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LENGUA INGLESA","UB");
        InsertarCarrera(BaseDeDatos,"Contador Público","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Administración","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Economía","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Comercio Internacional","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. en Informática","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. en Electrónica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. Industrial","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. Civil","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. Mecánica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Arquitectura","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Trabajo Social","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Comunicación Social","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Relaciones Laborales","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Relaciones Públicas","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Educación Física","UNLAM");
        InsertarCarrera(BaseDeDatos,"Prof. en Educación Física","UNLAM");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Deportiva","UNLAM");
        InsertarCarrera(BaseDeDatos,"Abogacía ","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Ciencia Política","UNLAM");
        InsertarCarrera(BaseDeDatos,"Procurador","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Enfermería","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Nutrición","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Kinesiología y Fisiatría","UNLAM");
        InsertarCarrera(BaseDeDatos,"Medicina","UNLAM");
        InsertarCarrera(BaseDeDatos,"Abogacía","UCA");
        InsertarCarrera(BaseDeDatos,"Administración de Empresas","UCA");
        InsertarCarrera(BaseDeDatos,"Ciencias Políticas","UCA");
        InsertarCarrera(BaseDeDatos,"Composición","UCA");
        InsertarCarrera(BaseDeDatos,"Comunicación Digital e Interactiva","UCA");
        InsertarCarrera(BaseDeDatos,"Comunicación Periodística","UCA");
        InsertarCarrera(BaseDeDatos,"Comunicación Publicitaria e Institucional","UCA");
        InsertarCarrera(BaseDeDatos,"Contador Público","UCA");
        InsertarCarrera(BaseDeDatos,"Dirección y Gestión de Bienes","UCA");
        InsertarCarrera(BaseDeDatos,"Dirección Orquestal","UCA");
        InsertarCarrera(BaseDeDatos,"Economía","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.Educación Inicial","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.Educación Primaria","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.FILOSOFIA","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.FILOSOFIA","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.HISTORIA","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.HISTORIA","UCA");
        InsertarCarrera(BaseDeDatos,"Ingeniería Agronómica","UCA");
        InsertarCarrera(BaseDeDatos,"Ingeniería Ambiental","UCA");
        InsertarCarrera(BaseDeDatos,"ING en Alimentos","UCA");
        InsertarCarrera(BaseDeDatos,"ING Civil","UCA");
        InsertarCarrera(BaseDeDatos,"ING Electrónica","UCA");
        InsertarCarrera(BaseDeDatos,"ING Industrial","UCA");
        InsertarCarrera(BaseDeDatos,"ING en Informática","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Inglés","UCA");
        InsertarCarrera(BaseDeDatos,"PROF.Inglés","UCA");
        InsertarCarrera(BaseDeDatos,"TRADUC.Inglés","UCA");
        InsertarCarrera(BaseDeDatos,"Kinesiología y Fisiatría","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Letras","UCA");
        InsertarCarrera(BaseDeDatos,"PROF.Letras","UCA");
        InsertarCarrera(BaseDeDatos,"Marketing","UCA");
        InsertarCarrera(BaseDeDatos,"Medicina","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Música Cinematográfica","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Musicología, Crítica, Teoría y Cognición Musical","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Música Popular","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Nutrición","UCA");
        InsertarCarrera(BaseDeDatos,"Odontología","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Piano","UCA");
        InsertarCarrera(BaseDeDatos,"Psicopedagogía","UCA");
        InsertarCarrera(BaseDeDatos,"Psicología","UCA");
        InsertarCarrera(BaseDeDatos,"Recursos Humanos","UCA");
        InsertarCarrera(BaseDeDatos,"Relaciones Internacionales","UCA");
        InsertarCarrera(BaseDeDatos,"PROF.Teología","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Teología Sistemática","UCA");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Enfermería","UCA");
        InsertarCarrera(BaseDeDatos,"Abogacía","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Administración de Empresas","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Agronegocios","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ciencia Política","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Comunicación Social","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ingeniería Industrial","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ingeniería Biomédica","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Lic. en Enfermería","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"DISEÑO","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Contador Público","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ingeniería en Informática","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Medicina","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Nutrición","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Relaciones Internacionales","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Psicología","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Robótica Diseño","Image Campus");
        InsertarCarrera(BaseDeDatos,"Robótica Electrónica","Image Campus");
        InsertarCarrera(BaseDeDatos,"Robótica Programación","Image Campus");
        InsertarCarrera(BaseDeDatos,"Game Design","Image Campus");
        InsertarCarrera(BaseDeDatos,"Introduccion a la programacion de videojuegos","Image Campus");
        InsertarCarrera(BaseDeDatos,"Game Art","Image Campus");
        InsertarCarrera(BaseDeDatos,"Desarrollo de videojuegos con Unity","Image Campus");
        InsertarCarrera(BaseDeDatos,"Introduccion a Game Audio","Image Campus");
        InsertarCarrera(BaseDeDatos,"Game Audio","Image Campus");
        InsertarCarrera(BaseDeDatos,"Testing y Technical QA","Image Campus");
        InsertarCarrera(BaseDeDatos,"Programacion Avanzada de videojuegos","Image Campus");
        InsertarCarrera(BaseDeDatos,"Environment para videojuegos con unreal engine","Image Campus");
        InsertarCarrera(BaseDeDatos,"Concept art","Image Campus");
        InsertarCarrera(BaseDeDatos,"Carrera corta de produccion multimedia","Image Campus");
        InsertarCarrera(BaseDeDatos,"tecnicas de animacion tradicional","Image Campus");
        InsertarCarrera(BaseDeDatos,"animacion digital 2D CUT OUT","Image Campus");
        InsertarCarrera(BaseDeDatos,"Animacion profesional 3D","Image Campus");
        InsertarCarrera(BaseDeDatos,"motion graphics","Image Campus");
        InsertarCarrera(BaseDeDatos,"modelado profesional 3D","Image Campus");
        InsertarCarrera(BaseDeDatos,"animacion profesional 3D","Image Campus");
        InsertarCarrera(BaseDeDatos,"visualizacion arquitectonica","Image Campus");
        InsertarCarrera(BaseDeDatos,"Unreal engine 4 para arquitectura","Image Campus");
        InsertarCarrera(BaseDeDatos,"efectos visuales (VFX)","Image Campus");
        InsertarCarrera(BaseDeDatos,"Motion Graphics","Image Campus");
        InsertarCarrera(BaseDeDatos,"Produccion de historietas","Image Campus");
        InsertarCarrera(BaseDeDatos,"Manga","Image Campus");
        InsertarCarrera(BaseDeDatos,"Manga Avanzado","Image Campus");
        InsertarCarrera(BaseDeDatos,"Realidad virtual diseño de contenidos","Image Campus");
        InsertarCarrera(BaseDeDatos,"Realidad aumentada","Image Campus");//Listo Image Campus
        InsertarCarrera(BaseDeDatos,"Medicina","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Kinesiología y Fisiatría","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Nutrición","Favaloro");
        InsertarCarrera(BaseDeDatos,"Enfermería","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Producción de Bioimágenes","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Instrumentación Quirúrgica","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Instrumentación Quirúrgica – Ciclo de Complementación Curricular","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicologia","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicopedagogía","Favaloro");
        InsertarCarrera(BaseDeDatos,"Ingeniería Biomédica","Favaloro");
        InsertarCarrera(BaseDeDatos,"Ingeniería en Física Médica","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Biológicas","Favaloro");//Listo Favaloro
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Economía","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Negocios Digitales","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Administración de Empresas","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Marketing","UCEMA");
        InsertarCarrera(BaseDeDatos,"Contador Público","UCEMA");
        InsertarCarrera(BaseDeDatos,"Abogacía","UCEMA");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN Informática","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Ciencias Políticas","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Relaciones Internacionales","UCEMA");//Listo UCEMA
        InsertarCarrera(BaseDeDatos,"CONTADOR PÚBLICO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACIÓN","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN OBSTETRICIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TRABAJO SOCIAL","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN MECANIZACIÓN DE LA PRODUCCIÓN AGROPECUARIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACIÓN A DISTANCIA","UM");
        InsertarCarrera(BaseDeDatos,"ABOGACÍA","UM");
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UM");
        InsertarCarrera(BaseDeDatos,"BIOQUÍMICA","UM");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA AGRONÓMICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA CIVIL","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTRÓNICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTROMECÁNICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN ALIMENTOS","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN INFORMÁTICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERÍA INDUSTRIAL","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERO AGRIMENSOR","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ÓPTICA OFTÁLMICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIOLOGÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIOTECNOLOGÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACIÓN","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS QUÍMICAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACIÓN","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIO INTERNACIONAL","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CRIMINALÍSTICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO DE INDUMENTARIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO DE INTERIORES ","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO GRÁFICO MULTIMEDIAL","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO GRÁFICO PUBLICITARIO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEÑO Y PRODUCCIÓN EN COMUNICACIÓN MULTIMEDIAL","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMERÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FILOSOFÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GENÉTICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTIÓN HOTELERA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HIGIENE Y SEGURIDAD EN EL TRABAJO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN INSTRUMENTACIÓN QUIRÚRGICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN KINESIOLOGÍA Y FISIATRÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LETRAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN NUTRICIÓN","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PERIODISMO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PREVENCIÓN VIAL Y TRANSPORTE","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOLOGÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOPEDAGOGÍA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PUBLICIDAD","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RECURSOS HUMANOS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RELACIONES PÚBLICAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SEGURIDAD","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SISTEMAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TASACIÓN, CORREDURÍA Y GESTIÓN DE BIENES (MOD.PRES)","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TASACIÓN, CORREDURÍA Y GESTIÓN DE BIENES - MOD.A DIST.","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TURISMO","UM");
        InsertarCarrera(BaseDeDatos,"MEDICINA","UM");
        InsertarCarrera(BaseDeDatos,"MEDICINA - RM 1230/17 - Resolución CONEAU 1220/14","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE PSICOLOGÍA","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN CIENCIAS DE LA EDUCACIÓN","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN FILOSOFÍA","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN HISTORIA","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN LETRAS","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN PSICOPEDAGOGÍA","UM");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO PÚBLICO DE INGLÉS","UM");//Listo UM
        InsertarCarrera(BaseDeDatos,"Licenciatura en Nutrición","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Música Autóctona, Clásica y Popular de América","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Protección Civil y Emergencias","UNTREF");
        InsertarCarrera(BaseDeDatos,"Gestión del Arte y la Cultura","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Artes Electrónicas","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Higiene y Seguridad del Trabajo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Profesorado en Historia","UNTREF");
        InsertarCarrera(BaseDeDatos,"Artes del Circo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Sistemas de Información Geográfica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Relaciones Comerciales Internacionales","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicomotricidad","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Música","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Historia","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Enfermería","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración y Gestión de Políticas Sociales","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Geografía","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Estadística","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración Pública","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administración de Empresas","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Producción Audiovisual","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Logística","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ingeniería de Sonido","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ingeniería en Computación","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ingeniería Ambiental","UNTREF");
        InsertarCarrera(BaseDeDatos,"Instrumentación Quirúrgica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Enfermería","UNTREF");
        InsertarCarrera(BaseDeDatos,"Educación Secundaria","UNTREF");
        InsertarCarrera(BaseDeDatos,"Historia","UNTREF");
        InsertarCarrera(BaseDeDatos,"Gestión Educativa","UNTREF");
        InsertarCarrera(BaseDeDatos,"Gestión del Deporte","UNTREF");
        InsertarCarrera(BaseDeDatos,"Profesorado en Geografía","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ciencias de la Educación","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Resolución de Conflictos y Mediación","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Marketing Deportivo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Enseñanza de Baile de Tango","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Inclusión Escolar, con orientación en TES","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura Universitaria en Enseñanza del Ajedrez","UNTREF");
        InsertarCarrera(BaseDeDatos,"Educación Intercultural","UNTREF");
        InsertarCarrera(BaseDeDatos,"Dirección de Instituciones Educativas","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Psicomotricidad y Medio Acúatico","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Psicomotricidad en Educación","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Relaciones Sindicales Internacionales","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Psicomotricidad y Educación Especial","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Organización de Eventos","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura Universitaria en Gestión del Deporte y Entrenamiento Deportivo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Liderazgo Público","UNTREF");//Listo UNTREF
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Musicales con orientación en Composición","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Música con orientación en Instrumento","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Actuación","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Administración y Gestión Universitaria","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Audiovisuales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes de la Escritura","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Multimediales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Musicales con orientación en Dirección Coral","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Musicales con orientación en Dirección Orquestal","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Visuales ","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Visuales orientación artes del fuego","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Composición con Medios Electroacústicos","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Composición Coreográfica","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Conservación-Restauración de Bienes Culturales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Crítica de Artes","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Curaduría en Artes","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Dirección Escénica","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Diseño de Iluminación de Espectáculos","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Escenografía","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore mención culturas tradicionales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore mención danzas folklóricas y tango","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore mención instrumentos criollos","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore mención tango","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Música con orientación en Canto","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Artes Visuales con orientación en cerámica","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Artes Visuales","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Danza","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Danza con orientación en expresión corporal","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Danza con orientación en folklore","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Música","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Música con orientación en folklore","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Teatro","UNA");
        InsertarCarrera(BaseDeDatos,"Instrumentista Orquestal","UNA");
        InsertarCarrera(BaseDeDatos,"Interpretación de danzas folklóricas y tango","UNA");
        InsertarCarrera(BaseDeDatos,"Interpretación de instrumentos criollos","UNA");
        InsertarCarrera(BaseDeDatos,"Interpretación de tango","UNA");
        InsertarCarrera(BaseDeDatos,"Tecnicatura en Interpretación en Danza","UNA")*/;//FALTAN USAL UAI UMSA FUC KENNEDY UdeMM UFLO ISALUD Atlántida Argentina UNDEF ENZEÑANZAS LOLA MORA
    }
    private void Tags(SQLiteDatabase BaseDeDatos)
    {
        InsertarTags(BaseDeDatos,"Ingenieria");//0
        InsertarTags(BaseDeDatos,"Profesorado");//1
        InsertarTags(BaseDeDatos,"Matematica");//2
        InsertarTags(BaseDeDatos,"Nutricion");//3
        InsertarTags(BaseDeDatos,"Leyes");//4
        InsertarTags(BaseDeDatos,"Arquitectura");//5
        InsertarTags(BaseDeDatos,"Economicas");//6
        InsertarTags(BaseDeDatos,"Tecnicatura");//7
        InsertarTags(BaseDeDatos,"Sociales");//8
        InsertarTags(BaseDeDatos,"Administracion");//9
        InsertarTags(BaseDeDatos,"Diseño");//10
        InsertarTags(BaseDeDatos,"Diplomacia");//11
        InsertarTags(BaseDeDatos,"Ciencias naturales");//12
        InsertarTags(BaseDeDatos,"Agronomia");//13
        InsertarTags(BaseDeDatos,"Diseño");//14
        InsertarTags(BaseDeDatos,"Construccion");//15
        InsertarTags(BaseDeDatos,"Visual");//16
        InsertarTags(BaseDeDatos,"Espacial");//17
        InsertarTags(BaseDeDatos,"Humanidades");//18
        InsertarTags(BaseDeDatos,"Intrapersonal");//19
        InsertarTags(BaseDeDatos,"Sistemas");//20
        InsertarTags(BaseDeDatos,"Analisis");//21
        InsertarTags(BaseDeDatos,"Numeros");//22
        InsertarTags(BaseDeDatos,"Matematica");//23
        InsertarTags(BaseDeDatos,"Logico");//24
        InsertarTags(BaseDeDatos,"ritmo");//25
        InsertarTags(BaseDeDatos,"melodia");//26
        InsertarTags(BaseDeDatos,"emociones");//27
        InsertarTags(BaseDeDatos,"componer");//28
        InsertarTags(BaseDeDatos,"baile");//29
        InsertarTags(BaseDeDatos,"Musical");//30
        InsertarTags(BaseDeDatos,"negocios");//31
        InsertarTags(BaseDeDatos,"relaciones");//32
        InsertarTags(BaseDeDatos,"Interpersonal");//33
        InsertarTags(BaseDeDatos,"escribir");//34
        InsertarTags(BaseDeDatos,"redactar");//35
        InsertarTags(BaseDeDatos,"leer");//36
        InsertarTags(BaseDeDatos,"idiomas");//38
        InsertarTags(BaseDeDatos,"Linguisticas");//38
        /*InsertarTags(BaseDeDatos,"");//39
        InsertarTags(BaseDeDatos,"");//40
        InsertarTags(BaseDeDatos,"");//41
        InsertarTags(BaseDeDatos,"");//42
        InsertarTags(BaseDeDatos,"");//43
        InsertarTags(BaseDeDatos,"");//44
        InsertarTags(BaseDeDatos,"");//45*/
        InsertarRelacionTags(BaseDeDatos,1,6);
        InsertarRelacionTags(BaseDeDatos,2,5);
        InsertarRelacionTags(BaseDeDatos,3,10);
        InsertarRelacionTags(BaseDeDatos,4,7);
        InsertarRelacionTags(BaseDeDatos,5,7);
        InsertarRelacionTags(BaseDeDatos,6,11);
        InsertarRelacionTags(BaseDeDatos,7,12);
        InsertarRelacionTags(BaseDeDatos,8,12);
        InsertarRelacionTags(BaseDeDatos,9,9);
        InsertarRelacionTags(BaseDeDatos,10,9);
        InsertarRelacionTags(BaseDeDatos,11,13);
        InsertarRelacionTags(BaseDeDatos,12,14);

    }
    private void Preguntas(SQLiteDatabase BaseDeDatos)
    {
        InsertarPreguntas(BaseDeDatos,"","");
    }
    private void InsertarPreguntas(SQLiteDatabase BD,String TextoPreg,String Inteligencia)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("TextoPregunta",TextoPreg.toUpperCase());
        NuevoRegistro.put("InteligenciaMultiple",Inteligencia.toUpperCase());
        BD.insert("Preguntas",null,NuevoRegistro);
    }
    private void MateriasDeCarrera(SQLiteDatabase BD)
    {

        InsertarMaterias(BD,2,"Derecho Constitucional I",1,0);
        InsertarMaterias(BD,2,"Teoría General del Derecho",1,0);
        InsertarMaterias(BD,2,"Fundamentos del Derecho Privado",1,0);
        InsertarMaterias(BD,2,"Historia Contemporánea",1,0);
        InsertarMaterias(BD,2,"Derecho Penal I",1,0);
        InsertarMaterias(BD,2,"Derecho Constitucional II",1,0);
        InsertarMaterias(BD,2,"Obligaciones",1,0);
        InsertarMaterias(BD,2,"Filosofía Moral",1,0);
        InsertarMaterias(BD,2,"Derecho de Daños y Seguros",2,0);
        InsertarMaterias(BD,2,"Derecho Penal II",2,0);
        InsertarMaterias(BD,2,"Microeconomía",2,0);
        InsertarMaterias(BD,2,"Lógica y Redacción",2,0);
        InsertarMaterias(BD,2,"Filosofía Política",2,0);
        InsertarMaterias(BD,2,"Derechos Reales",2,0);
        InsertarMaterias(BD,2,"Derecho Procesal Penal",2,0);
        InsertarMaterias(BD,2,"Análisis Económico del Derecho",2,0);
        InsertarMaterias(BD,2,"Contratos I",3,0);
        InsertarMaterias(BD,2,"Familia y Sucesiones",3,0);
        InsertarMaterias(BD,2,"Derecho Laboral y de la Seguridad Social ",3,0);
        InsertarMaterias(BD,2,"Derecho y Sociedad",3,0);
        InsertarMaterias(BD,2,"Derecho Internacional Público",3,0);
        InsertarMaterias(BD,2,"Derecho Procesal Civil I",3,0);
        InsertarMaterias(BD,2,"Derecho Administrativo",3,0);
        InsertarMaterias(BD,2,"Sociedades",3,0);
        InsertarMaterias(BD,2,"Derecho Procesal Civil II",4,1);
        InsertarMaterias(BD,2,"Macroeconomía",4,1);
        InsertarMaterias(BD,2,"Derecho Tributario",4,1);
        InsertarMaterias(BD,2,"Concursos y Quiebras",4,1);
        InsertarMaterias(BD,2,"Contabilidad y Análisis Financiero",4,1);
        InsertarMaterias(BD,2,"Contratos II",4,1);
        InsertarMaterias(BD,2,"Derecho Internacional Privado",4,1);
        InsertarMaterias(BD,2,"Derecho Ambiental y de los Recursos Naturales",5,1);
        InsertarMaterias(BD,2,"Seminario Mediación y Arbitraje",5,1);
        InsertarMaterias(BD,2,"Ética Profesional",5,1);
        InsertarMaterias(BD,6,"Laboratorio de Diseño I",1,0);
        InsertarMaterias(BD,6,"Forma e Imagen",1,0);
        InsertarMaterias(BD,6,"Arte y Cultura de la Modernidad",1,0);
        InsertarMaterias(BD,6,"Matemática I",1,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño II",1,0);
        InsertarMaterias(BD,6,"Teorías de la Comunicación",1,0);
        InsertarMaterias(BD,6,"Introducción a la Administración Pública y a las Organizaciones",1,0);
        InsertarMaterias(BD,6,"Economía",1,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño III",2,0);
        InsertarMaterias(BD,6,"Objeto y Materialidad",2,0);
        InsertarMaterias(BD,6,"Introducción a los Estudios Visuales",2,0);
        InsertarMaterias(BD,6,"Historia del Diseño Moderno",2,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño IV",2,0);
        InsertarMaterias(BD,6,"Teoría y Metodología del Diseño",2,0);
        InsertarMaterias(BD,6,"Ingeniería de Materiales",2,0);
        InsertarMaterias(BD,6,"Sociología",2,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño V",3,0);
        InsertarMaterias(BD,6,"Visualización de la Información",3,0);
        InsertarMaterias(BD,6,"Programación Orientada al Diseño",3,0);
        InsertarMaterias(BD,6,"Historia del Diseño Latinoamericano",3,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño VI",3,0);
        InsertarMaterias(BD,6,"Diseño de Interactividad",3,0);
        InsertarMaterias(BD,6,"Marketing",3,0);
        InsertarMaterias(BD,6,"Curso de Campo Menor",3,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño VII",4,0);
        InsertarMaterias(BD,6,"Diseño y Gestión Cultural",4,0);
        InsertarMaterias(BD,6,"Gestión Estratégica de Diseño",4,0);
        InsertarMaterias(BD,6,"Laboratorio de Diseño VIII (Proyecto Final)",4,0);
        InsertarMaterias(BD,6,"Narrativas y Medios",4,0);
        InsertarMaterias(BD,6,"Diseño Sustentable",4,0);
        InsertarMaterias(BD,7,"Introducción a la Ciencia Política",1,0);
        InsertarMaterias(BD,7,"Matemática I",1,0);
        InsertarMaterias(BD,7,"Economía I",1,0);
        InsertarMaterias(BD,7,"Introducción a las Relaciones Internacionales",1,0);
        InsertarMaterias(BD,7,"Comprensión de Textos y Escritura",1,0);
        InsertarMaterias(BD,7,"Teoría Política I",1,0);
        InsertarMaterias(BD,7,"Matemática II",1,0);
        InsertarMaterias(BD,7,"Lógica y Técnicas de Investigación en Ciencias Sociales",1,0);
        InsertarMaterias(BD,7,"Historia de Occidente a partir de la Modernidad",1,0);
        InsertarMaterias(BD,7,"Economía II",2,0);
        InsertarMaterias(BD,7,"Introducción a las Políticas Públicas",2,0);
        InsertarMaterias(BD,7,"Política Comparada",2,0);
        InsertarMaterias(BD,7,"Política y Sociedad en la Argentina (Siglos XIX y XX)",2,0);
        InsertarMaterias(BD,7,"Política y Sociedad en América Latina",2,0);
        InsertarMaterias(BD,7,"Teoría Política II",2,0);
        InsertarMaterias(BD,7,"Teoría de las Relaciones Internacionales",2,0);
        InsertarMaterias(BD,7,"Historia del Mundo Contemporáneo (1914-2000)",2,0);
        InsertarMaterias(BD,7,"Política Exterior Argentina",3,0);
        InsertarMaterias(BD,7,"Política y Economía",3,0);
        InsertarMaterias(BD,7,"Diseño y Metodología de la Investigación Social",3,0);
        InsertarMaterias(BD,7,"Organizaciones y Teoría de la Decisión",3,0);
        InsertarMaterias(BD,7,"Expresión Oral y Escrita",3,0);
        InsertarMaterias(BD,7,"Relaciones Internacionales Contemporáneas",3,0);
        InsertarMaterias(BD,7,"Política Exterior de Estados Unidos",3,0);
        InsertarMaterias(BD,7,"Estadística para Ciencias Sociales",3,0);
        InsertarMaterias(BD,7,"Política y Derecho",3,0);
        InsertarMaterias(BD,7,"Organismos Internacionales",4,0);
        InsertarMaterias(BD,7,"Derecho Internacional",4,0);
        InsertarMaterias(BD,7,"Comercio Internacional",4,0);
        InsertarMaterias(BD,7,"Conflictos Internacionales y Seguridad",4,0);
        InsertarMaterias(BD,7,"Seminario de Graduación",4,0);
        InsertarMaterias(BD,8,"Introducción a la Ciencia Política",1,0);
        InsertarMaterias(BD,8,"Matemática I",1,0);
        InsertarMaterias(BD,8,"Economía I",1,0);
        InsertarMaterias(BD,8,"Introducción a las Relaciones Internacionales",1,0);
        InsertarMaterias(BD,8,"Comprensión de Textos y Escritura",1,0);
        InsertarMaterias(BD,8,"Teoría Política I",1,0);
        InsertarMaterias(BD,8,"Matemática II ",1,0);
        InsertarMaterias(BD,8,"Historia de Occidente a partir de la Modernidad",1,0);
        InsertarMaterias(BD,8,"Lógica y Técnicas de Investigación en Ciencias Sociales",1,0);
        InsertarMaterias(BD,8,"Economia II",2,0);
        InsertarMaterias(BD,8,"Política Comparada",2,0);
        InsertarMaterias(BD,8,"Introducción a las Políticas Públicas",2,0);
        InsertarMaterias(BD,8,"Política y Sociedad en la Argentina (Siglos XIX y XX)",2,0);
        InsertarMaterias(BD,8,"Política y Sociedad en América Latina",2,0);
        InsertarMaterias(BD,8,"Teoría Política II",2,0);
        InsertarMaterias(BD,8,"Teoría de las Relaciones Internacionales",2,0);
        InsertarMaterias(BD,8,"Historia del Mundo Contemporáneo (1914-2000)",2,0);
        InsertarMaterias(BD,8,"Estructura Social y Demografía",3,0);
        InsertarMaterias(BD,8,"Política y Economía",3,0);
        InsertarMaterias(BD,8,"Diseño y Metodología de la Investigación Social",3,0);
        InsertarMaterias(BD,8,"Organizaciones y Teoría de la Decisión",3,0);
        InsertarMaterias(BD,8,"Expresión Oral y Escrita",3,0);
        InsertarMaterias(BD,8,"Estado y Políticas Públicas en la Argentina",3,0);
        InsertarMaterias(BD,8,"Política y Derecho",3,0);
        InsertarMaterias(BD,8,"Política y Comunicación",3,0);
        InsertarMaterias(BD,8,"Estadística para Ciencias Sociales",3,0);
        InsertarMaterias(BD,8,"Finanzas Públicas",4,0);
        InsertarMaterias(BD,8,"Seminario de Temas de Política Económica Argentina",4,0);
        InsertarMaterias(BD,8,"Tópicos de Teoría Política Social",4,0);
        InsertarMaterias(BD,8,"Actores y Procesos Políticos",4,0);
        InsertarMaterias(BD,8,"Seminario de Graduación",4,0);
        InsertarMaterias(BD,11,"Matemática",1,0);
        InsertarMaterias(BD,11,"Química",1,0);
        InsertarMaterias(BD,11,"Introducción al Conocimiento de la Sociedad y el Estado ",1,0);
        InsertarMaterias(BD,11,"Física e Introducción a la Biofísica",1,0);
        InsertarMaterias(BD,11,"Biología",1,0);
        InsertarMaterias(BD,11,"Química Aplicada",2,0);
        InsertarMaterias(BD,11,"Física Aplicada",2,0);
        InsertarMaterias(BD,11,"Estadística General",2,0);
        InsertarMaterias(BD,11,"Biomoléculas",2,0);
        InsertarMaterias(BD,11,"Bioquímica Aplicada",2,0);
        InsertarMaterias(BD,11,"Edafología",2,0);
        InsertarMaterias(BD,11,"Climatología y Agrometeorología",2,0);
        InsertarMaterias(BD,11,"Botánica",2,0);
        InsertarMaterias(BD,11,"Evolución y Genética",3,0);
        InsertarMaterias(BD,11,"Fisiología de las Plantas Superiores",3,0);
        InsertarMaterias(BD,11,"Zoología General ",3,0);
        InsertarMaterias(BD,11,"Química de la Contaminación y Toxicología",3,0);
        InsertarMaterias(BD,11,"Sociología y Antropología General",3,0);
        InsertarMaterias(BD,11,"Ecología",3,0);
        InsertarMaterias(BD,11,"Economía Política",3,0);
        InsertarMaterias(BD,11,"Microbiología Ambiental",3,0);
        InsertarMaterias(BD,11,"Nociones de Geología y Geomorfología",3,0);
        InsertarMaterias(BD,11,"Derechos Humanos",3,0);
        InsertarMaterias(BD,11,"Ecología Acuática ",4,0);
        InsertarMaterias(BD,11,"Hidrología",4,0);
        InsertarMaterias(BD,11,"Geografía Ambiental",4,0);
        InsertarMaterias(BD,11,"Bioindicadores",4,0);
        InsertarMaterias(BD,11,"Sistemas de Información Geográfica, Cartografía y Teledetección",4,0);
        InsertarMaterias(BD,11,"Economía Aplicada al Agro y al Ambiente",4,0);
        InsertarMaterias(BD,11,"Agroecosistemas",4,0);
        InsertarMaterias(BD,11,"Ambiente y Sociedad",4,0);
        InsertarMaterias(BD,11,"Gestión de Proyectos",4,0);
        InsertarMaterias(BD,11,"Economía y Política del Ambiente",4,0);
        InsertarMaterias(BD,11,"Gestión y Conservación de los Recursos Naturales",5,0);
        InsertarMaterias(BD,11,"Ética y Legislación Ambiental",5,0);
        InsertarMaterias(BD,11,"Biodiversidad",5,0);
        InsertarMaterias(BD,11,"Modelos Estadísticos",5,0);
        InsertarMaterias(BD,11,"Conservación y Planificación del Uso de la Tierra",5,0);
        InsertarMaterias(BD,11,"Ordenamiento Territorial",5,0);
        InsertarMaterias(BD,11,"Modelos de Simulación",6,0);
        InsertarMaterias(BD,11,"Cambio Global",6,0);
        InsertarMaterias(BD,11,"Evaluación de Impacto Ambiental",6,0);
        InsertarMaterias(BD,11,"Análisis de Riesgo Ambiental",6,0);
        InsertarMaterias(BD,11,"Trabajo Final",6,0);
        InsertarMaterias(BD,12,"Matemática",1,0);
        InsertarMaterias(BD,12,"Quimica",1,0);
        InsertarMaterias(BD,12,"Introduccion al conocimiento de la sociedad y el estado",1,0);
        InsertarMaterias(BD,12,"Biologia",1,0);
        InsertarMaterias(BD,12,"Fisica e introduccion a la biologia",1,0);
        InsertarMaterias(BD,12,"Introduccion al pensamiento cientifico",1,0);
        InsertarMaterias(BD,12,"Quimica aplicada",2,0);
        InsertarMaterias(BD,12,"Biomelucas",2,0);
        InsertarMaterias(BD,12,"fisica aplicada",2,0);
        InsertarMaterias(BD,12,"Estadistica General",2,0);
        InsertarMaterias(BD,12,"Botanica Morfologica",2,0);
        InsertarMaterias(BD,12,"Bioquima Aplicada",2,0);
        InsertarMaterias(BD,12,"Edafologia",2,0);
        InsertarMaterias(BD,12,"Climatologia y agronomia",2,0);
        InsertarMaterias(BD,12,"Botanica Sistematica",2,0);
        InsertarMaterias(BD,12,"Ingles",2,0);
        InsertarMaterias(BD,12,"Informatica",2,0);
        InsertarMaterias(BD,12,"Genetica y mejoramiento Vegetal",3,0);
        InsertarMaterias(BD,12,"Fisiologia de las plantas superiores",3,0);
        InsertarMaterias(BD,12,"Microbiologa Agricola y Ambiental",3,0);
        InsertarMaterias(BD,12,"Bases Biologicas de la produccion animal",3,0);
        InsertarMaterias(BD,12,"Topografia Agricola",3,0);
        InsertarMaterias(BD,12,"TALLER DE PRACTICA I",3,0);
        InsertarMaterias(BD,12,"Produccion Vegetal",3,0);
        InsertarMaterias(BD,12,"Ecología",3,0);
        InsertarMaterias(BD,12,"Nutricion y Alimentacion Animal",3,0);
        InsertarMaterias(BD,12,"Economia Politica",3,0);
        InsertarMaterias(BD,12,"Mejoramiento Genetico Animal",3,0);
        InsertarMaterias(BD,12,"Produccion y Utilizacion de Forrajes",4,0);
        InsertarMaterias(BD,12,"Produccion de Carne Bovina",4,0);
        InsertarMaterias(BD,12,"Economia Agricola",4,0);
        InsertarMaterias(BD,12,"Malezas",4,0);
        InsertarMaterias(BD,12,"Teledeteccion y Sistemas de informacion Geograficas",4,0);
        InsertarMaterias(BD,12,"TALLER DE PRACTICA II",4,0);
        InsertarMaterias(BD,12,"FITOPATOLOGIA",4,0);
        InsertarMaterias(BD,12,"ZOOLOGIA AGRICOLA",4,0);
        InsertarMaterias(BD,12,"FERTILIDAD DE SUELOS Y FERTILIZACION",4,0);
        InsertarMaterias(BD,12,"MAQUINAS AGRICOLAS",4,0);
        InsertarMaterias(BD,12,"TALLER DE TRABAJO FINAL",4,0);
        InsertarMaterias(BD,12,"SOCIOLOGIA Y EXTENCSION AGRARIAS",5,0);
        InsertarMaterias(BD,12,"PRODUCCION LECHERA",5,0);
        InsertarMaterias(BD,12,"MODELOS ESTADISTICOS",5,0);
        InsertarMaterias(BD,12,"PROTECCION VEGETAL",5,0);
        InsertarMaterias(BD,12,"DERECHOS HUMANOS",5,0);
        InsertarMaterias(BD,12,"PRODUCCION DE GRANOS",5,0);
        InsertarMaterias(BD,12,"CONSERVACION Y PLANIFICACION DEL USO DE LA TIERRA",5,0);
        InsertarMaterias(BD,12,"HORTICULTURA",5,0);
        InsertarMaterias(BD,12,"SISTEMA DE RIEGO Y DRENAJE",5,0);
        InsertarMaterias(BD,12,"TALLER DE PRACTICA III",5,0);
        InsertarMaterias(BD,12,"ADMINISTRACION RURAL",6,0);
        InsertarMaterias(BD,12,"PRODUCCION FORESTAL",6,0);
        InsertarMaterias(BD,12,"MERCADOS AGROPECUARIOS",6,0);
        InsertarMaterias(BD,12,"FRUTICULTURA",6,0);
        InsertarMaterias(BD,12,"TRABAJO FINAL",6,0);
    }
    private void InsertarMaterias(SQLiteDatabase BD,int Id_carrera,String NombreMateria,int Anio,int opcionalidad)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("ID_Carrera",Id_carrera);
        NuevoRegistro.put("Nombre_Materia",NombreMateria.toUpperCase());
        NuevoRegistro.put("Anio",Anio);
        NuevoRegistro.put("Opcionalidad",opcionalidad);
        BD.insert("Materias",null,NuevoRegistro);
    }
    private void InsertarCarrera(SQLiteDatabase BD,String Carreras,String Facultad,String Descripcion)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("Nombre_Carrera",Carreras.toUpperCase());
        NuevoRegistro.put("Nombre_Facultad",Facultad.toUpperCase());
        NuevoRegistro.put("Descripcion",Descripcion.toUpperCase());
        BD.insert("Carerras",null,NuevoRegistro);
    }
    public SQLiteDatabase getDB()
    {
        return DB;
    }
    private void InsertarTags(SQLiteDatabase BD,String Tags)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("Nombre_Tag",Tags.toUpperCase());
        BD.insert("Tags",null,NuevoRegistro);
    }
    private void InsertarRelacionTags(SQLiteDatabase BD,int ID_Carrera,int Id_Tags)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("ID_Carrera",ID_Carrera);
        NuevoRegistro.put("ID_Tag",Id_Tags);
        BD.insert("Relacion_Carrera_Tag",null,NuevoRegistro);
    }

}
