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
        String SQLliteTabla="create table Carerras (ID_carrera INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Carrera TEXT, Nombre_Facultad TEXT,LinkImagen TEXT,Descripcion TEXT);";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Relacion_Carrera_Tag (ID_Carrera INTEGER, ID_Tag INTEGER ,FOREIGN KEY(ID_Tag) REFERENCES Tags(ID_Tags),FOREIGN KEY(ID_Carrera) REFERENCES Carerras(ID_Carrera))";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Tags (ID_Tag INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Tag TEXT)";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Materias (ID_Materia INTEGER PRIMARY KEY AUTOINCREMENT, ID_Carrera INTEGER, Nombre_Materia TEXT, Anio INTEGER,Descripcion_Materia TEXT,Opcionalidad INTEGER)";
        db.execSQL(SQLliteTabla);
        SQLliteTabla="create table Preguntas (ID_Pregunta INTEGER PRIMARY KEY, TextoPregunta TEXT, Orientacion TEXT,Letra Text)";
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
    private void InsertarValores(SQLiteDatabase BaseDeDatos) {
        InsertarCarrera(BaseDeDatos, "CARRERA DE ARQUITECTURA", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "hola");
        InsertarCarrera(BaseDeDatos, "CARRERA DE ABOGAC??A", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "En un mundo cambiante, nuestra educaci??n jur??dica interdisciplinaria e internacional te prepara para enfrentar y liderar las respuestas a los grandes desaf??os profesionales que acarrean la globalizaci??n, la integraci??n regional, las nuevas tecnolog??as y la necesidad de interactuar con profesionales de otras ??reas, desde economistas y contadores hasta polit??logos o diplom??ticos.");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ADMINISTRACION DE EMPRESAS", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El LicRenciado en Administraci??n de Empresas es un profesional con ??nimo emprendedor, formado para la gesti??n y direcci??n de los recursos de la empresa desde una perspectiva integral.\n" + "\n" + "Su formaci??n le permite interactuar con todos los estamentos de la organizaci??n, con vistas a facilitar la coordinaci??n para lograr objetivos comunes.");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ECONOM??A EMPRESARIAL", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El Licenciado en Econom??a Empresarial comparte con el Licenciado en Econom??a, una vocaci??n por el rigor anal??tico y el detalle metodol??gico que soportan las herramientas de decisi??n m??s modernas utilizadas en las organizaciones.\n" + "\n" + "Comparte tambi??n con el Licenciado en Administraci??n una naturaleza emprendedora y de gesti??n empresarial que le permite dirigir los recursos de la empresa desde una perspectiva integral.\n" + "\n");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ECONOM??A", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "Formula diagn??sticos de situaciones, escenarios micro y macroecon??micos e implementa pol??ticas en el ??mbito p??blico o privado. Se inserta laboralmente en empresas, instituciones financieras, consultoras, ONG, organismos nacionales e internacionales, as?? como en instituciones de investigaci??n y docencia de la Argentina y del exterior.");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN DISE??O", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", " Su inserci??n profesional le permite proponer ideas, acciones y m??todos del dise??o para los ??mbitos de la tecnolog??a, los negocios y la cultura, en el marco de equipos de trabajo multiprofesionales, para detectar oportunidades de soluci??n e innovaci??n. La formaci??n human??stica y ??tica, proyectual y t??cnica, le permitir?? al graduado actuar con idoneidad y eficiencia como profesional del Dise??o en relaci??n con im??genes, objetos, mensajes, informaci??n, medios tecnol??gicos y emprendimientos institucionales y empresariales.\n" + "\n");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ESTUDIOS INTERNACIONALES", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El licenciado en Estudios Internacionales, desde su formaci??n multidisciplinaria, aporta al an??lisis y comprensi??n de un mundo globalizado, interdependiente y pluricultural interpretando las dimensiones pol??ticas, econ??micas, jur??dicas, militares, ambientales y sociales de un entorno en proceso de permanente transformaci??n. Maneja t??cnicas, metodolog??as y herramientas que le permiten una aproximaci??n profunda y rigurosa a los complejos fen??menos internacionales");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN CIENCIAS POL??TICA Y GOBIERNO", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El licenciado en Ciencia Pol??tica y Gobierno cuenta con valiosas herramientas te??ricas, emp??ricas y metodol??gicas que le permiten abordar y evaluar fen??menos complejos de la vida pol??tica y la gesti??n de instituciones de gobierno. Posee una mirada anal??tica y sistem??tica para comprender y accionar frente a esos fen??menos, tanto en el caso argentino como en t??rminos comparados. Propone alternativas innovadoras al nutrirse de otras ??reas de conocimiento como la Econom??a, la Historia, las Pol??ticas P??blicas y el Derecho");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN CIENCIAS SOCIALES", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "La formaci??n del licenciado en Ciencias Sociales de la Universidad Torcuato Di Tella le permite comprender y analizar fen??menos sociales; generar contenidos, estrategias y acciones de impacto en la sociedad; formular, desarrollar e implementar estrategias empresariales y programas de pol??ticas p??blicas y evaluar su influencia en los grupos destinatarios; y comunicar contenidos por diversas v??as (informes, presentaciones, art??culos period??sticos, material 2.0).");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN HISTORIA", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "La carrera de Historia ofrece al graduado una amplia gama de posibilidades laborales y de desarrollo profesional. Para aquellos interesados en una carrera acad??mica, la Licenciatura les provee las herramientas necesarias para iniciarse en la investigaci??n. Al mismo tiempo, la carrera suministra al graduado un conjunto de saberes y habilidades cuya utilidad no es exclusiva del trabajo del historiador profesional. ");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN CIENCIAS AMBIENTALES", "UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg", "El ejercicio de identificar las actividades que puede llevar a cabo un profesional\n" +
                "en las ciencias ambientales nos fuerza a definir un conjunto cuyos l??mites son difusos,\n" +
                "que se superpone a las de actividades de profesionales de otras ??reas y que, de hecho,\n" +
                "incluye tareas que ni siquiera imaginamos en el presente. ");
        InsertarCarrera(BaseDeDatos, "AGRONOMIA", "UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg", "El Ingeniero Agr??nomo es aquel graduado universitario con una s??lida formaci??n cient??fica y tecnol??gica que le permite intervenir en las cadenas productivas de base agropecuaria, en el ambiente y en la preservaci??n de los recursos naturales desde una visi??n integral y sustentable, dentro de un contexto socioecon??mico con diversos niveles de innovaci??n e incertidumbre, con el fin de promover el desarrollo nacional y el del sector agropecuario.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOM??A Y ADMINISTRACI??N AGRARIAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","La Facultad se propone formar un Licenciado en Econom??a y Administraci??n Agrarias con conocimientos, habilidades y actitudes para: \nComprender el contexto econ??mico y social en el que se insertan la producci??n agropecuaria, asumiendo la sustentabilidad como eje de toda intervenci??n y el enfoque sist??mico como visi??n necesaria.\nManejar herramientas conceptuales y t??cnicas necesarias para intervenir en los distintos ??mbitos en los que interaccionan los agentes econ??micos que intervienen en la producci??n agropecuaria. \nParticipar en el dise??o e implementaci??n de investigaciones cient??ficas en el campo disciplinar de la econom??a y administraci??n agrarias. ");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTI??N DE AGROALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg"," Los principios rectores de calidad, ??tica social y cuidado del medio ambiente recorrer??n la carrera como conceptos transversales. Por esta raz??n, ser??n los marcos para el desarrollo de los saberes espec??ficos de las diversas disciplinas curriculares.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PLANIFICACI??N Y DISE??O DEL PAISAJE","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg"," Capacitar a los alumnos a nivel profesional para planificar y dise??ar el paisaje natural, urbano y cultural, integrando el ambiente al entorno construido por el hombre. Formarlos para vincularse con otras disciplinas cient??ficas y sociales y as?? llevar a cabo el planeamiento y dise??o del paisaje. Promover, incentivar, desarrollar y divulgar la formaci??n de una conciencia plena de los problemas del ambiente y el uso que el hombre hace de ??l");
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","La Carrera de Arquitectura tiene como objetivo fundamental formar profesionales aptos para dise??ar, programar, dirigir y construir los edificios y espacios necesarios para albergar las actividades del hombre en sociedad, satisfaciendo las necesidades y aspiraciones que ??sta demanda.");
        InsertarCarrera(BaseDeDatos,"DISE??O DE IMAGEN Y SONIDO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Instalar la actividad audiovisual como disciplina proyectual y contribuir a su actualizaci??n permanente por medio de la investigaci??n y el conocimiento de todas las fases del proceso de Dise??o.");
        InsertarCarrera(BaseDeDatos,"DISE??O DE INDUMENTARIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Contribuir a trav??s de la formaci??n de profesionales id??neos en la materia a la consolidaci??n de la identidad nacional promoviendo el desarrollo competitivo de la actividad tanto internamente como internacionalmente.");
        InsertarCarrera(BaseDeDatos,"DISE??O GR??FICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","La carrera de Dise??o Gr??fico tiene como objetivo la preparaci??n de profesionales capaces de ejercer la actividad proyectual que posibilita comunicar visualmente informaci??n, hechos, ideas y valores ??tiles al hombre. Esta disciplina implica procesar y expresar en t??rminos de forma, factores sociales, culturales, perceptivos, est??ticos, tecnol??gicos y ambientales.");
        InsertarCarrera(BaseDeDatos,"DISE??O INDUSTRIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","El objetivo principal es formar profesionales que se ocupen del proyecto de los objetos que rodean al hombre y que ser??n producidos industrialmente. Los Dise??adores Industriales se insertar??n en el medio productivo con alta capacitaci??n en la resoluci??n de productos, sean estos est??ticos o din??micos, en sus aspectos formales, de uso y tecnolog??a. Simult??neamente se loscapacitar?? para una actitud cr??tica y reflexiva de su actividad proyectual, a trav??s del estudio del hombre y el contexto en el que act??an.");
        InsertarCarrera(BaseDeDatos,"DISE??O TEXTIL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Contribuir a trav??s de la formaci??n de profesionales id??neos en la materia a la consolidaci??n de la identidad nacional promoviendo el desarrollo competitivo de la actividad tanto internamente como internacionalmente.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PLANIFICACI??N Y DISE??O DEL PAISAJE","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Contribuir a trav??s de la formaci??n de profesionales id??neos en la materia a la consolidaci??n de la identidad nacional promoviendo el desarrollo competitivo de la actividadtanto internamente como internacionalmente.");
        InsertarCarrera(BaseDeDatos,"ACTUARIO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","El Actuario es un profesional de las Ciencias Econ??micas, especializado en la valuaci??n de operaciones y entidades sujetas a riesgos relacionados con los seguros personales, seguros patrimoniales, la seguridad social, mercados de capitales y sistema financiero, sobre la base del establecimiento de condiciones de equilibrio actuarial integrado en procesos de planificaci??n econ??mico-financiera.");
        InsertarCarrera(BaseDeDatos,"CONTADOR P??BLICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","El Contador P??blico asesora personas y empresas en las ??reas financiera, impositiva, contable, laboral, de costos y societaria. Dise??a, interpreta e implementa sistemas de informaci??n contables, dentro de las organizaciones p??blicas y privadas, para la toma de decisiones, sobre pol??ticas de inversi??n, organizaci??n de recursos y an??lisis de los sistemas econ??micos.");
        /*InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOM??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SISTEMAS DE INFORMACI??N DE LAS ORGANIZACIONES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOG??A DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS BIOL??GICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA ATM??SFERA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMPUTACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS F??SICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS GEOL??GICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS MATEM??TICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS OCEANOGR??FICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS QU??MICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PALEONTOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA ATM??SFERA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMPUTACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS GEOL??GICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN F??SICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN LA ESPECIALIDAD BIOLOG??A\n","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN MATEMATICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN QUIMICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA POL??TICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMUNICACI??N SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RELACIONES DEL TRABAJO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SOCIOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TRABAJO SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIA POL??TICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMUNICACI??N SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN RELACIONES DEL TRABAJO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN TRABAJO SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA SECUNDARIA NORMAL Y ESPECIAL EN SOCIOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"CIENCIAS VETERINARIAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTI??N DE AGROALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"T??CNICO PARA BIOTERIOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"ABOGAC??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"CAL??GRAFO P??BLICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO PARA LA ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS JUR??DICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO P??BLICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"BIOQU??MICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"FARMACIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOG??A DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN ??PTICA Y CONTACTOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"T??CNICO PARA BIOTERIOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"T??CNICO UNIVERSITARIO EN MEDICINA NUCLEAR","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ARTES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIBLIOTECOLOG??A Y CIENCIA DE LA INFORMACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS ANTROPOL??GICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FILOSOF??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GEOGRAF??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LETRAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN ARTES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN BIBLIOTECOLOG??A Y CIENCIA DE LA INFORMACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS ANTROPOL??GICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA EDUCACI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN FILOSOF??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN GEOGRAF??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN HISTORIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN LETRAS\n","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"EDICION","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A CIVIL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A ELECTRICISTA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A ELECTR??NICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN AGRIMENSURA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN INFORM??TICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN PETR??LEO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A INDUSTRIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A MEC??NICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A NAVAL Y MEC??NICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIER??A QU??MICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN AN??LISIS DE SISTEMAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMER??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMER??A (CICLO DE COMPLEMENTACI??N CURRICULAR)","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FONOAUDIOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN KINESIOLOG??A Y FISIATR??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN NUTRICI??N","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN OBSTETRICIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PODOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCI??N DE BIOIMAGENES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCI??N DE BIOIM??GENES (CICLO DE COMPLEMENTACI??N CURRICULAR)","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"MEDICINA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN ANESTESIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN COSMETOLOG??A FACIAL Y CORPORAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN HEMOTERAPIA E INMUNOHEMATOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN INSTRUMENTACI??N QUIR??RGICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN PODOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"T??CNICOS UNIVERSITARIOS EN PR??CTICAS CARDIOL??GICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"ODONTOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN MUSICOTERAPIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TERAPIA OCUPACIONAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSE??ANZA MEDIA Y SUPERIOR EN PSICOLOG??A","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"CIENCIAS B??SICAS","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIERIA CIVIL","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EL??CTRICA","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A ELECTR??NICA","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A INDUSTRIAL","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A NAVAL","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A QU??MICA","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN SISTEMAS DE INFORMACI??N","UTN");
        InsertarCarrera(BaseDeDatos,"INGENIER??A TEXTIL","UTN");
        InsertarCarrera(BaseDeDatos,"MEDICINA","UCES");
        InsertarCarrera(BaseDeDatos,"KINESIOLOGIA Y FISIATRIA","UCES");
        InsertarCarrera(BaseDeDatos,"NUTRICION","UCES");
        InsertarCarrera(BaseDeDatos,"ENFERMER??A","UCES");
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
        InsertarCarrera(BaseDeDatos,"DISE??O Y COMUNICACION VISUAL","UCES");
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
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O DE LA IMAGEN VISUAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O AUDIOVISUAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O DE INTERIOR","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O GR??FICO","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O INDUSTRIAL","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O MULTIMEDIA Y DE INTERACCI??N","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISEO Y GESTION DE EST??TICA PARA LA MODA","UADE");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTI??N DE ARTE","UADE");
        InsertarCarrera(BaseDeDatos,"PROGRAMA CONJUNTO DISE??O GR??FICO Y DISE??O MULTIMEDIA Y DE INTERACCI??N","UADE");
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
        InsertarCarrera(BaseDeDatos,"DISE??O","UDESA");
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
        InsertarCarrera(BaseDeDatos,"Licenciatura en Management: Econom??a y Finanzas","UP");
        InsertarCarrera(BaseDeDatos,"Programa Conjunto en Hoteler??a y Turismo","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gastronom??a","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Arte","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Relaciones Internacionales y Ciencia Pol??tica","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Humanidades y Ciencias Sociales","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Periodismo","UP");
        InsertarCarrera(BaseDeDatos,"Periodismo Deportivo","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicolog??a","UP");
        InsertarCarrera(BaseDeDatos,"Arte Multimedial","UP");
        InsertarCarrera(BaseDeDatos,"Actuaci??n Profesional","UP");
        InsertarCarrera(BaseDeDatos,"Comunicaci??n de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Comunicaci??n Digital","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Calzado","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o Gr??fico","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o Editorial","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Imagen Empresarial","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Joyas","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o Packaging","UP");
        InsertarCarrera(BaseDeDatos,"Direcci??n de Actores de Cine y TV","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Espacios Comerciales","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Imagen y Sonido","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o Industrial","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Interiores","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Mobiliarios","UP");
        InsertarCarrera(BaseDeDatos,"Dise??o de Vidrieras","UP");
        InsertarCarrera(BaseDeDatos,"Escenograf??a","UP");
        InsertarCarrera(BaseDeDatos,"Fotograf??a de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Gui??n de Cine y TV","UP");
        InsertarCarrera(BaseDeDatos,"Historietas","UP");
        InsertarCarrera(BaseDeDatos,"Ilustraci??n","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Actuaci??n","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comunicaci??n Audiovisual","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Creaci??n Sonora","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Fotograf??a","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Direcci??n Cinematogr??fica","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Dise??o de Espect??culos","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Direcci??n Teatral","UP");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Publicidad","UP");
        InsertarCarrera(BaseDeDatos,"Marketing de la Moda","UP");
        InsertarCarrera(BaseDeDatos,"Molder??a & Confecci??n","UP");
        InsertarCarrera(BaseDeDatos,"Producci??n de Espect??culos","UP");
        InsertarCarrera(BaseDeDatos,"Producci??n de Moda","UP");
        InsertarCarrera(BaseDeDatos,"Producci??n Musical","UP");
        InsertarCarrera(BaseDeDatos,"Producci??n de Televisi??n","UP");
        InsertarCarrera(BaseDeDatos,"Relaciones P??blicas","UP");
        InsertarCarrera(BaseDeDatos,"Organizaci??n de Eventos","UP");
        InsertarCarrera(BaseDeDatos,"Vestuario","UP");
        InsertarCarrera(BaseDeDatos,"Abogacia","UP");//LISTO UP Y LA RE CONCHA DE TU HERMANA
        InsertarCarrera(BaseDeDatos,"Licenciatura en Marketing","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n de Negocios","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n de Recursos Humanos","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Comercio Internacional","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Higiene y Seguridad del Trabajo","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Turismo","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gesti??n Ambiental","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Biol??gicas","CAECE");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Gesti??n, Manejo y Conservaci??n de Biodiversidad Educaci??n","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Educaci??n","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Gesti??n Educativa","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Matem??tica","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Matem??tica","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicolog??a","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicomotricidad","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Psicomotricidad","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicopedagog??a","CAECE");
        InsertarCarrera(BaseDeDatos,"Ciclo de Licenciatura en Psicopedagog??a","CAECE");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en Sistemas","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Sistemas","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gesti??n de Sistemas y Negocios","CAECE");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Nanotecnolog??a","CAECE");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Programaci??n","CAECE");//LISTO CAECE Y +1 HORA
        InsertarCarrera(BaseDeDatos,"Licenciatura en analitica empresarial y social","ITBA");
        InsertarCarrera(BaseDeDatos,"Licenciatura en administracion y sistemas","ITBA");
        InsertarCarrera(BaseDeDatos,"bioingenieria","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenieria electronica","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a industrial","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a informatica","ITBA");
        InsertarCarrera(BaseDeDatos,"ingenieria mecanica","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a naval","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en petroleo","ITBA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a quimica","ITBA");//LISTO ITBA +15 MIN
        InsertarCarrera(BaseDeDatos,"Arquitectura Naval","UNQ");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en Alimentos","UNQ");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en Automatizaci??n y Control Industrial","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Bioinform??tica","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Biotecnolog??a","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Inform??tica","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Biotecnolog??a","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Programaci??n Inform??tica","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Qu??mica","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Tecnolog??a Ambiental y Petroqu??mica","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Sociales","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comunicaci??n Social","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Educaci??n","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Educaci??n ","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Enfermer??a","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Historia","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Terapia Ocupacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Ciencias Sociales","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Comunicaci??n Social","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Educaci??n","UNQ");
        InsertarCarrera(BaseDeDatos,"Profesorado de Historia","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Sociales y Humanidades","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Educaci??n","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Geograf??a","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Terapia Ocupacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura en Gesti??n de Medios Comunitarios","UNQ");
        InsertarCarrera(BaseDeDatos,"Econom??a y Administraci??n","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n Hotelera","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comercio Internacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Econom??a del Desarrollo","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Gesti??n de Recursos Humanos y Relaciones Laborales","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Econom??a Social y Solidaria","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Gesti??n de Peque??as y Medianas Empresas","UNQ");
        InsertarCarrera(BaseDeDatos,"Contador P??blico","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Comercio Internacional","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Turismo y Hoteler??a","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Ciencias Empresariales","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Artes Digitales","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Composici??n con Medios Electroac??stico","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en M??sica y Tecnolog??a","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Creaci??n Musical","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Producci??n Digital","UNQ");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Universitaria en Producci??n Musical y Nuevas Tecnolog??as","UNQ");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Artes y Tecnolog??as","UNQ");//LISTO UNQ FALTAN BELGRANO UNLAM UCEMA
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UB");
        InsertarCarrera(BaseDeDatos,"DISE??O GR??FICO","UB");
        InsertarCarrera(BaseDeDatos,"PUBLICIDAD","UB");
        InsertarCarrera(BaseDeDatos,"DISE??O DE INTERIORES","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIAS QU??MICAS","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIAS BIOL??GICAS","UB");
        InsertarCarrera(BaseDeDatos,"TECNOLOG??A DE ALIMENTOS","UB");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UB");
        InsertarCarrera(BaseDeDatos,"ABOGAC??A","UB");
        InsertarCarrera(BaseDeDatos,"RELACIONES INTERNACIONALES","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIA POL??TICA, GOBIERNO Y ADMINISTRACI??N","UB");
        InsertarCarrera(BaseDeDatos,"CIENCIAS DE LA COMUNICACI??N","UB");
        InsertarCarrera(BaseDeDatos,"ADMINISTRACI??N Y GESTI??N DE AGRONEGOCIOS","UB");
        InsertarCarrera(BaseDeDatos,"CONTADOR P??BLICO","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOM??A","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACI??N","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIO EXTERIOR","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACI??N","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACI??N DE RECURSOS HUMANOS","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HOTELER??A","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TURISMO","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GASTRONOM??A","UB");
        InsertarCarrera(BaseDeDatos,"INGENIER??A CIVIL","UB");
        InsertarCarrera(BaseDeDatos,"INGENIER??A INDUSTRIAL","UB");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN INFORM??TICA","UB");
        InsertarCarrera(BaseDeDatos,"SISTEMAS DE INFORMACI??N","UB");
        InsertarCarrera(BaseDeDatos,"NUTRICI??N","UB");
        InsertarCarrera(BaseDeDatos,"PSICOLOG??A","UB");
        InsertarCarrera(BaseDeDatos,"RELACIONES P??BLICAS E INSTITUCIONALES","UB");
        InsertarCarrera(BaseDeDatos,"PRODUCCI??N Y DIRECCI??N DE TV, CINE Y RADIO","UB");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO P??BLICO, LITERARIO Y CIENT??FICO-T??CNICO DE INGL??S","UB");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LENGUA INGLESA","UB");
        InsertarCarrera(BaseDeDatos,"Contador P??blico","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Administraci??n","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Econom??a","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Comercio Internacional","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. en Inform??tica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. en Electr??nica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. Industrial","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. Civil","UNLAM");
        InsertarCarrera(BaseDeDatos,"Ing. Mec??nica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Arquitectura","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Trabajo Social","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Comunicaci??n Social","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Relaciones Laborales","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Relaciones P??blicas","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Educaci??n F??sica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Prof. en Educaci??n F??sica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Tecnicatura Deportiva","UNLAM");
        InsertarCarrera(BaseDeDatos,"Abogac??a ","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. Ciencia Pol??tica","UNLAM");
        InsertarCarrera(BaseDeDatos,"Procurador","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Enfermer??a","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Nutrici??n","UNLAM");
        InsertarCarrera(BaseDeDatos,"Lic. en Kinesiolog??a y Fisiatr??a","UNLAM");
        InsertarCarrera(BaseDeDatos,"Medicina","UNLAM");
        InsertarCarrera(BaseDeDatos,"Abogac??a","UCA");
        InsertarCarrera(BaseDeDatos,"Administraci??n de Empresas","UCA");
        InsertarCarrera(BaseDeDatos,"Ciencias Pol??ticas","UCA");
        InsertarCarrera(BaseDeDatos,"Composici??n","UCA");
        InsertarCarrera(BaseDeDatos,"Comunicaci??n Digital e Interactiva","UCA");
        InsertarCarrera(BaseDeDatos,"Comunicaci??n Period??stica","UCA");
        InsertarCarrera(BaseDeDatos,"Comunicaci??n Publicitaria e Institucional","UCA");
        InsertarCarrera(BaseDeDatos,"Contador P??blico","UCA");
        InsertarCarrera(BaseDeDatos,"Direcci??n y Gesti??n de Bienes","UCA");
        InsertarCarrera(BaseDeDatos,"Direcci??n Orquestal","UCA");
        InsertarCarrera(BaseDeDatos,"Econom??a","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.Educaci??n Inicial","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.Educaci??n Primaria","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.FILOSOFIA","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.FILOSOFIA","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.HISTORIA","UCA");
        InsertarCarrera(BaseDeDatos,"Prof.HISTORIA","UCA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a Agron??mica","UCA");
        InsertarCarrera(BaseDeDatos,"Ingenier??a Ambiental","UCA");
        InsertarCarrera(BaseDeDatos,"ING en Alimentos","UCA");
        InsertarCarrera(BaseDeDatos,"ING Civil","UCA");
        InsertarCarrera(BaseDeDatos,"ING Electr??nica","UCA");
        InsertarCarrera(BaseDeDatos,"ING Industrial","UCA");
        InsertarCarrera(BaseDeDatos,"ING en Inform??tica","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Ingl??s","UCA");
        InsertarCarrera(BaseDeDatos,"PROF.Ingl??s","UCA");
        InsertarCarrera(BaseDeDatos,"TRADUC.Ingl??s","UCA");
        InsertarCarrera(BaseDeDatos,"Kinesiolog??a y Fisiatr??a","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Letras","UCA");
        InsertarCarrera(BaseDeDatos,"PROF.Letras","UCA");
        InsertarCarrera(BaseDeDatos,"Marketing","UCA");
        InsertarCarrera(BaseDeDatos,"Medicina","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.M??sica Cinematogr??fica","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Musicolog??a, Cr??tica, Teor??a y Cognici??n Musical","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.M??sica Popular","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Nutrici??n","UCA");
        InsertarCarrera(BaseDeDatos,"Odontolog??a","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Piano","UCA");
        InsertarCarrera(BaseDeDatos,"Psicopedagog??a","UCA");
        InsertarCarrera(BaseDeDatos,"Psicolog??a","UCA");
        InsertarCarrera(BaseDeDatos,"Recursos Humanos","UCA");
        InsertarCarrera(BaseDeDatos,"Relaciones Internacionales","UCA");
        InsertarCarrera(BaseDeDatos,"PROF.Teolog??a","UCA");
        InsertarCarrera(BaseDeDatos,"LIC.Teolog??a Sistem??tica","UCA");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Enfermer??a","UCA");
        InsertarCarrera(BaseDeDatos,"Abogac??a","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Administraci??n de Empresas","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Agronegocios","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ciencia Pol??tica","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Comunicaci??n Social","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ingenier??a Industrial","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ingenier??a Biom??dica","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Lic. en Enfermer??a","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"DISE??O","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Contador P??blico","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en Inform??tica","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Medicina","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Nutrici??n","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Relaciones Internacionales","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Psicolog??a","AUSTRAL");
        InsertarCarrera(BaseDeDatos,"Rob??tica Dise??o","Image Campus");
        InsertarCarrera(BaseDeDatos,"Rob??tica Electr??nica","Image Campus");
        InsertarCarrera(BaseDeDatos,"Rob??tica Programaci??n","Image Campus");
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
        InsertarCarrera(BaseDeDatos,"Realidad virtual dise??o de contenidos","Image Campus");
        InsertarCarrera(BaseDeDatos,"Realidad aumentada","Image Campus");//Listo Image Campus
        InsertarCarrera(BaseDeDatos,"Medicina","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Kinesiolog??a y Fisiatr??a","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Nutrici??n","Favaloro");
        InsertarCarrera(BaseDeDatos,"Enfermer??a","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Producci??n de Bioim??genes","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Instrumentaci??n Quir??rgica","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Instrumentaci??n Quir??rgica ??? Ciclo de Complementaci??n Curricular","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicologia","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicopedagog??a","Favaloro");
        InsertarCarrera(BaseDeDatos,"Ingenier??a Biom??dica","Favaloro");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en F??sica M??dica","Favaloro");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Ciencias Biol??gicas","Favaloro");//Listo Favaloro
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Econom??a","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Negocios Digitales","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Administraci??n de Empresas","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Marketing","UCEMA");
        InsertarCarrera(BaseDeDatos,"Contador P??blico","UCEMA");
        InsertarCarrera(BaseDeDatos,"Abogac??a","UCEMA");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN Inform??tica","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Ciencias Pol??ticas","UCEMA");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN Relaciones Internacionales","UCEMA");//Listo UCEMA
        InsertarCarrera(BaseDeDatos,"CONTADOR P??BLICO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACI??N","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN OBSTETRICIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TRABAJO SOCIAL","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN MECANIZACI??N DE LA PRODUCCI??N AGROPECUARIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACI??N","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACI??N A DISTANCIA","UM");
        InsertarCarrera(BaseDeDatos,"ABOGAC??A","UM");
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UM");
        InsertarCarrera(BaseDeDatos,"BIOQU??MICA","UM");
        InsertarCarrera(BaseDeDatos,"FARMACIA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A AGRON??MICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A CIVIL","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A ELECTR??NICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A ELECTROMEC??NICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN ALIMENTOS","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A EN INFORM??TICA","UM");
        InsertarCarrera(BaseDeDatos,"INGENIER??A INDUSTRIAL","UM");
        InsertarCarrera(BaseDeDatos,"INGENIERO AGRIMENSOR","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACI??N","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ??PTICA OFT??LMICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIOLOG??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIOTECNOLOG??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACI??N","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS QU??MICAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIALIZACI??N","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN COMERCIO INTERNACIONAL","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CRIMINAL??STICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O DE INDUMENTARIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O DE INTERIORES ","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O GR??FICO MULTIMEDIAL","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O GR??FICO PUBLICITARIO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN DISE??O Y PRODUCCI??N EN COMUNICACI??N MULTIMEDIAL","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOM??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMER??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FILOSOF??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GEN??TICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTI??N HOTELERA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HIGIENE Y SEGURIDAD EN EL TRABAJO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN INSTRUMENTACI??N QUIR??RGICA","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN KINESIOLOG??A Y FISIATR??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LETRAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN NUTRICI??N","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PERIODISMO","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PREVENCI??N VIAL Y TRANSPORTE","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOLOG??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOPEDAGOG??A","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PUBLICIDAD","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RECURSOS HUMANOS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RELACIONES P??BLICAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SEGURIDAD","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SISTEMAS","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TASACI??N, CORREDUR??A Y GESTI??N DE BIENES (MOD.PRES)","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TASACI??N, CORREDUR??A Y GESTI??N DE BIENES - MOD.A DIST.","UM");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TURISMO","UM");
        InsertarCarrera(BaseDeDatos,"MEDICINA","UM");
        InsertarCarrera(BaseDeDatos,"MEDICINA - RM 1230/17 - Resoluci??n CONEAU 1220/14","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE PSICOLOG??A","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN CIENCIAS DE LA EDUCACI??N","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN FILOSOF??A","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN HISTORIA","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN LETRAS","UM");
        InsertarCarrera(BaseDeDatos,"PROFESORADO EN PSICOPEDAGOG??A","UM");
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO P??BLICO DE INGL??S","UM");//Listo UM
        InsertarCarrera(BaseDeDatos,"Licenciatura en Nutrici??n","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en M??sica Aut??ctona, Cl??sica y Popular de Am??rica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Protecci??n Civil y Emergencias","UNTREF");
        InsertarCarrera(BaseDeDatos,"Gesti??n del Arte y la Cultura","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Artes Electr??nicas","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Higiene y Seguridad del Trabajo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Profesorado en Historia","UNTREF");
        InsertarCarrera(BaseDeDatos,"Artes del Circo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Sistemas de Informaci??n Geogr??fica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Relaciones Comerciales Internacionales","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Psicomotricidad","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en M??sica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Historia","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Enfermer??a","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n y Gesti??n de Pol??ticas Sociales","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Geograf??a","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Estad??stica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n P??blica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Administraci??n de Empresas","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Producci??n Audiovisual","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Log??stica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ingenier??a de Sonido","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ingenier??a en Computaci??n","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ingenier??a Ambiental","UNTREF");
        InsertarCarrera(BaseDeDatos,"Instrumentaci??n Quir??rgica","UNTREF");
        InsertarCarrera(BaseDeDatos,"Enfermer??a","UNTREF");
        InsertarCarrera(BaseDeDatos,"Educaci??n Secundaria","UNTREF");
        InsertarCarrera(BaseDeDatos,"Historia","UNTREF");
        InsertarCarrera(BaseDeDatos,"Gesti??n Educativa","UNTREF");
        InsertarCarrera(BaseDeDatos,"Gesti??n del Deporte","UNTREF");
        InsertarCarrera(BaseDeDatos,"Profesorado en Geograf??a","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ciencias de la Educaci??n","UNTREF");
        InsertarCarrera(BaseDeDatos,"Licenciatura en Resoluci??n de Conflictos y Mediaci??n","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Marketing Deportivo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Ense??anza de Baile de Tango","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Inclusi??n Escolar, con orientaci??n en TES","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura Universitaria en Ense??anza del Ajedrez","UNTREF");
        InsertarCarrera(BaseDeDatos,"Educaci??n Intercultural","UNTREF");
        InsertarCarrera(BaseDeDatos,"Direcci??n de Instituciones Educativas","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Psicomotricidad y Medio Ac??atico","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Psicomotricidad en Educaci??n","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Relaciones Sindicales Internacionales","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Psicomotricidad y Educaci??n Especial","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Organizaci??n de Eventos","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura Universitaria en Gesti??n del Deporte y Entrenamiento Deportivo","UNTREF");
        InsertarCarrera(BaseDeDatos,"Diplomatura en Liderazgo P??blico","UNTREF");//Listo UNTREF
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Musicales con orientaci??n en Composici??n","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en M??sica con orientaci??n en Instrumento","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Actuaci??n","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Administraci??n y Gesti??n Universitaria","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Audiovisuales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes de la Escritura","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Multimediales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Musicales con orientaci??n en Direcci??n Coral","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Musicales con orientaci??n en Direcci??n Orquestal","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Visuales ","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Artes Visuales orientaci??n artes del fuego","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Composici??n con Medios Electroac??sticos","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Composici??n Coreogr??fica","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Conservaci??n-Restauraci??n de Bienes Culturales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Cr??tica de Artes","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Curadur??a en Artes","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Direcci??n Esc??nica","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Dise??o de Iluminaci??n de Espect??culos","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Escenograf??a","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore menci??n culturas tradicionales","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore menci??n danzas folkl??ricas y tango","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore menci??n instrumentos criollos","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en Folklore menci??n tango","UNA");
        InsertarCarrera(BaseDeDatos,"LIC. en M??sica con orientaci??n en Canto","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Artes Visuales con orientaci??n en cer??mica","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Artes Visuales","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Danza","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Danza con orientaci??n en expresi??n corporal","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Danza con orientaci??n en folklore","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en M??sica","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en M??sica con orientaci??n en folklore","UNA");
        InsertarCarrera(BaseDeDatos,"Prof. de Artes en Teatro","UNA");
        InsertarCarrera(BaseDeDatos,"Instrumentista Orquestal","UNA");
        InsertarCarrera(BaseDeDatos,"Interpretaci??n de danzas folkl??ricas y tango","UNA");
        InsertarCarrera(BaseDeDatos,"Interpretaci??n de instrumentos criollos","UNA");
        InsertarCarrera(BaseDeDatos,"Interpretaci??n de tango","UNA");
        InsertarCarrera(BaseDeDatos,"Tecnicatura en Interpretaci??n en Danza","UNA")*/
        ;//FALTAN USAL UAI UMSA FUC KENNEDY UdeMM UFLO ISALUD Atl??ntida Argentina UNDEF ENZE??ANZAS LOLA MORA

    }
    private void Tags(SQLiteDatabase BaseDeDatos)
    {
        InsertarTags(BaseDeDatos,"Ingenieria");//1
        InsertarTags(BaseDeDatos,"Profesorado");//2
        InsertarTags(BaseDeDatos,"Matematica");//3
        InsertarTags(BaseDeDatos,"Nutricion");//4
        InsertarTags(BaseDeDatos,"Leyes");//5
        InsertarTags(BaseDeDatos,"Arquitectura");//6
        InsertarTags(BaseDeDatos,"Economicas");//7
        InsertarTags(BaseDeDatos,"Tecnicatura");//8
        InsertarTags(BaseDeDatos,"Sociales");//9
        InsertarTags(BaseDeDatos,"Administracion");//10
        InsertarTags(BaseDeDatos,"Dise??o");//11
        InsertarTags(BaseDeDatos,"Diplomacia");//12
        InsertarTags(BaseDeDatos,"Ciencias naturales");//13
        InsertarTags(BaseDeDatos,"Agronomia");//14
        InsertarTags(BaseDeDatos,"Dise??o");//15
        InsertarTags(BaseDeDatos,"Construccion");//16
        InsertarTags(BaseDeDatos,"Visual");//17
        InsertarTags(BaseDeDatos,"Espacial");//18
        InsertarTags(BaseDeDatos,"Humanidades");//19
        InsertarTags(BaseDeDatos,"Intrapersonal");//20
        InsertarTags(BaseDeDatos,"Sistemas");//21
        InsertarTags(BaseDeDatos,"Analisis");//22
        InsertarTags(BaseDeDatos,"Numeros");//23
        InsertarTags(BaseDeDatos,"Matematica");//24
        InsertarTags(BaseDeDatos,"Logico");//25
        InsertarTags(BaseDeDatos,"ritmo");//26
        InsertarTags(BaseDeDatos,"melodia");//27
        InsertarTags(BaseDeDatos,"emociones");//28
        InsertarTags(BaseDeDatos,"componer");//29
        InsertarTags(BaseDeDatos,"baile");//30
        InsertarTags(BaseDeDatos,"Musical");//31
        InsertarTags(BaseDeDatos,"negocios");//32
        InsertarTags(BaseDeDatos,"relaciones");//33
        InsertarTags(BaseDeDatos,"Interpersonal");//34
        InsertarTags(BaseDeDatos,"escribir");//35
        InsertarTags(BaseDeDatos,"redactar");//36
        InsertarTags(BaseDeDatos,"leer");//37
        InsertarTags(BaseDeDatos,"idiomas");//38
        InsertarTags(BaseDeDatos,"Linguisticas");//39
        InsertarTags(BaseDeDatos,"UBA");//40
        InsertarTags(BaseDeDatos,"universidad de buenos aires");//41
        InsertarTags(BaseDeDatos,"ditella");//42
        InsertarTags(BaseDeDatos,"di tella");//43
        //TODO ahora son los tags del chaside
        //C
        InsertarTags(BaseDeDatos,"organizativo");//44
        InsertarTags(BaseDeDatos,"supervision");//45
        InsertarTags(BaseDeDatos,"orden");//46
        InsertarTags(BaseDeDatos,"analisis y sintesis");//47
        InsertarTags(BaseDeDatos,"colaboracion");//48
        InsertarTags(BaseDeDatos,"calculo");//49
        InsertarTags(BaseDeDatos,"persuacion");//50
        InsertarTags(BaseDeDatos,"objetivo");//51
        InsertarTags(BaseDeDatos,"practico");//52
        InsertarTags(BaseDeDatos,"tolerante");//53
        InsertarTags(BaseDeDatos,"responsable");//54
        InsertarTags(BaseDeDatos,"ambisioso");//55
        //H
        InsertarTags(BaseDeDatos,"precision verval");//56
        InsertarTags(BaseDeDatos,"organizacion");//57
        InsertarTags(BaseDeDatos,"relacion de hechos");//58
        InsertarTags(BaseDeDatos,"linguistica");//59
        InsertarTags(BaseDeDatos,"orden");//60
        InsertarTags(BaseDeDatos,"justicia");//61
        InsertarTags(BaseDeDatos,"responsable");//62
        InsertarTags(BaseDeDatos,"justo");//63
        InsertarTags(BaseDeDatos,"Conciliador");//64
        InsertarTags(BaseDeDatos,"persuasivo");//65
        InsertarTags(BaseDeDatos,"sagaz");//66
        InsertarTags(BaseDeDatos,"imaginativo");//67
        //A
        InsertarTags(BaseDeDatos,"esterico");//68
        InsertarTags(BaseDeDatos,"armonico");//69
        InsertarTags(BaseDeDatos,"manual");//70
        InsertarTags(BaseDeDatos,"visual");//71
        InsertarTags(BaseDeDatos,"auditivo");//72
        InsertarTags(BaseDeDatos,"sensible");//73
        InsertarTags(BaseDeDatos,"imaginativo");//74
        InsertarTags(BaseDeDatos,"creativo");//75
        InsertarTags(BaseDeDatos,"detallista");//76
        InsertarTags(BaseDeDatos,"inovador");//77
        InsertarTags(BaseDeDatos,"intuitivo");//78
        //S
        InsertarTags(BaseDeDatos,"asistir");//79
        InsertarTags(BaseDeDatos,"investigativo");//80
        InsertarTags(BaseDeDatos,"precision");//81
        InsertarTags(BaseDeDatos,"senso-perceptivo");//82
        InsertarTags(BaseDeDatos,"analitico");//83
        InsertarTags(BaseDeDatos,"ayudar");//84
        InsertarTags(BaseDeDatos,"altruista");//85
        InsertarTags(BaseDeDatos,"solidario");//86
        InsertarTags(BaseDeDatos,"paciente");//87
        InsertarTags(BaseDeDatos,"compresivo");//88
        InsertarTags(BaseDeDatos,"respetuoso");//89
        InsertarTags(BaseDeDatos,"persuasivo");//90
        //I
        InsertarTags(BaseDeDatos,"calculo");//91
        InsertarTags(BaseDeDatos,"cientifico");//92
        InsertarTags(BaseDeDatos,"manual");//93
        InsertarTags(BaseDeDatos,"exacto");//94
        InsertarTags(BaseDeDatos,"planificar");//95
        InsertarTags(BaseDeDatos,"preciso");//96
        InsertarTags(BaseDeDatos,"practico");//97
        InsertarTags(BaseDeDatos,"critico");//98
        InsertarTags(BaseDeDatos,"analitico");//99
        InsertarTags(BaseDeDatos,"rigido");//100
        //D
        InsertarTags(BaseDeDatos,"justicia");//101
        InsertarTags(BaseDeDatos,"equidad");//102
        InsertarTags(BaseDeDatos,"colaboracion");//103
        InsertarTags(BaseDeDatos,"espiritu de equipo");//104
        InsertarTags(BaseDeDatos,"liderazgo");//105
        InsertarTags(BaseDeDatos,"arriesgado");//106
        InsertarTags(BaseDeDatos,"solidario");//107
        InsertarTags(BaseDeDatos,"valiente");//108
        InsertarTags(BaseDeDatos,"agresivo");//109
        InsertarTags(BaseDeDatos,"persuasivo");//110
        //E
        InsertarTags(BaseDeDatos,"investigacion");//111
        InsertarTags(BaseDeDatos,"orden");//112
        InsertarTags(BaseDeDatos,"organizacion");//113
        InsertarTags(BaseDeDatos,"analisis y sintesis");//114
        InsertarTags(BaseDeDatos,"numerico");//115
        InsertarTags(BaseDeDatos,"clasificar");//116
        InsertarTags(BaseDeDatos,"metodico");//117
        InsertarTags(BaseDeDatos,"analitico");//118
        InsertarTags(BaseDeDatos,"observador");//119
        InsertarTags(BaseDeDatos,"introvertido");//120
        InsertarTags(BaseDeDatos,"paciente");//121
        InsertarTags(BaseDeDatos,"seguro");//122
        //Chaside
        InsertarTags(BaseDeDatos,"Administrativas y Contables");//123
        InsertarTags(BaseDeDatos,"Human??sticas y Sociales");//124
        InsertarTags(BaseDeDatos,"Art??sticas");//125
        InsertarTags(BaseDeDatos,"Medicina y Cs. de la Salud");//126
        InsertarTags(BaseDeDatos,"Ingenier??a y Computaci??n");//127
        InsertarTags(BaseDeDatos,"Defensa y Seguridad");//128
        InsertarTags(BaseDeDatos,"Ciencias Exactas y Agrarias");//129
        /*InsertarTags(BaseDeDatos,"");//44
        InsertarTags(BaseDeDatos,"");//45
        InsertarTags(BaseDeDatos,"");//46*/
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
        InsertarRelacionTags(BaseDeDatos,1,43);
        InsertarRelacionTags(BaseDeDatos,2,43);
        InsertarRelacionTags(BaseDeDatos,3,43);
        InsertarRelacionTags(BaseDeDatos,4,43);
        InsertarRelacionTags(BaseDeDatos,5,43);
        InsertarRelacionTags(BaseDeDatos,6,43);
        InsertarRelacionTags(BaseDeDatos,7,43);
        InsertarRelacionTags(BaseDeDatos,8,43);
        InsertarRelacionTags(BaseDeDatos,9,43);
        InsertarRelacionTags(BaseDeDatos,10,43);
        InsertarRelacionTags(BaseDeDatos,1,42);
        InsertarRelacionTags(BaseDeDatos,2,42);
        InsertarRelacionTags(BaseDeDatos,3,42);
        InsertarRelacionTags(BaseDeDatos,4,42);
        InsertarRelacionTags(BaseDeDatos,5,42);
        InsertarRelacionTags(BaseDeDatos,6,42);
        InsertarRelacionTags(BaseDeDatos,7,42);
        InsertarRelacionTags(BaseDeDatos,8,42);
        InsertarRelacionTags(BaseDeDatos,9,42);
        InsertarRelacionTags(BaseDeDatos,10,42);
        InsertarRelacionTags(BaseDeDatos,12,41);
        InsertarRelacionTags(BaseDeDatos,11,41);
        InsertarRelacionTags(BaseDeDatos,11,40);
        InsertarRelacionTags(BaseDeDatos,12,40);
        for(int i=44;i<=55;i++)
        {
            //insercion chaside c
            InsertarRelacionTags(BaseDeDatos,2,i);
            InsertarRelacionTags(BaseDeDatos,3,i);
            InsertarRelacionTags(BaseDeDatos,4,i);
            InsertarRelacionTags(BaseDeDatos,5,i);
            InsertarRelacionTags(BaseDeDatos,8,i);
            InsertarRelacionTags(BaseDeDatos,23,i);
            InsertarRelacionTags(BaseDeDatos,24,i);

        }
        InsertarRelacionTags(BaseDeDatos,2,123);
        InsertarRelacionTags(BaseDeDatos,3,123);
        InsertarRelacionTags(BaseDeDatos,4,123);
        InsertarRelacionTags(BaseDeDatos,5,123);
        InsertarRelacionTags(BaseDeDatos,8,123);
        InsertarRelacionTags(BaseDeDatos,23,123);
        InsertarRelacionTags(BaseDeDatos,24,123);
        for(int i=56;i<=67;i++)
        {
            InsertarRelacionTags(BaseDeDatos,7,i);
            InsertarRelacionTags(BaseDeDatos,9,i);
            InsertarRelacionTags(BaseDeDatos,10,i);
            //insercion chaside h
        }
        InsertarRelacionTags(BaseDeDatos,7,124);
        InsertarRelacionTags(BaseDeDatos,9,124);
        InsertarRelacionTags(BaseDeDatos,10,124);
        for(int i=68;i<=78;i++)
        {
            //insercion chaside a
            InsertarRelacionTags(BaseDeDatos,1,i);
            InsertarRelacionTags(BaseDeDatos,6,i);
            InsertarRelacionTags(BaseDeDatos,15,i);
            InsertarRelacionTags(BaseDeDatos,16,i);
            InsertarRelacionTags(BaseDeDatos,17,i);
            InsertarRelacionTags(BaseDeDatos,18,i);
            InsertarRelacionTags(BaseDeDatos,19,i);
            InsertarRelacionTags(BaseDeDatos,20,i);
            InsertarRelacionTags(BaseDeDatos,21,i);
            InsertarRelacionTags(BaseDeDatos,22,i);

        }
        InsertarRelacionTags(BaseDeDatos,6,125);
        InsertarRelacionTags(BaseDeDatos,1,125);
        InsertarRelacionTags(BaseDeDatos,15,125);
        InsertarRelacionTags(BaseDeDatos,16,125);
        InsertarRelacionTags(BaseDeDatos,17,125);
        InsertarRelacionTags(BaseDeDatos,18,125);
        InsertarRelacionTags(BaseDeDatos,19,125);
        InsertarRelacionTags(BaseDeDatos,20,125);
        InsertarRelacionTags(BaseDeDatos,21,125);
        InsertarRelacionTags(BaseDeDatos,22,125);
        for(int i=79;i<=90;i++)
        {
            //insercion chaside s
        }
        for(int i=91;i<=100;i++)
        {
            //insercion chaside i
        }
        for(int i=101;i<=110;i++)
        {
            //insercion chaside d
        }
        for(int i=111;i<=122;i++)
        {
            //insercion chaside e
            InsertarRelacionTags(BaseDeDatos,11,i);
            InsertarRelacionTags(BaseDeDatos,12,i);
            InsertarRelacionTags(BaseDeDatos,13,i);
            InsertarRelacionTags(BaseDeDatos,14,i);

        }
        InsertarRelacionTags(BaseDeDatos,11,129);
        InsertarRelacionTags(BaseDeDatos,12,129);
        InsertarRelacionTags(BaseDeDatos,13,129);
        InsertarRelacionTags(BaseDeDatos,14,129);
    }
    private void Preguntas(SQLiteDatabase BaseDeDatos)
    {
        InsertarPreguntas(BaseDeDatos,1,"??Aceptar??as trabajar escribiendo art??culos en la secci??n econ??mica de un diario?","Interes","C");
        InsertarPreguntas(BaseDeDatos,2,"??Te ofrecer??as para organizar la despedida de soltero de uno de tus amigos?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,3,"??Te gustar??a dirigir un proyecto de urbanizaci??n en tu provincia?","Interes","A");
        InsertarPreguntas(BaseDeDatos,4,"A una frustraci??n siempre opones un pensamiento positivo?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,5,"??Te dedicar??as a socorrer a personas accidentadas o atacadas por asaltantes?","Interes","D");
        InsertarPreguntas(BaseDeDatos,6,"??Cuando eras chico, te interesaba saber c??mo estaban construidos tus juguetes?","Interes","I");
        InsertarPreguntas(BaseDeDatos,7,"??Te interesan m??s los misterios de la naturaleza que los secretos de la tecnolog??a?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,8,"??Escuchas atentamente los problemas que te plantean tus amigos?","Interes","S");
        InsertarPreguntas(BaseDeDatos,9,"??Te ofrecer??as para explicar a tus compa??eros un determinado tema que ellos no entendieron?","Interes","H");
        InsertarPreguntas(BaseDeDatos,10,"??Eres exigente y cr??tico con tu equipo de trabajo?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,11,"??Te atrae armar rompecabezas o puzzles?","Interes","A");
        InsertarPreguntas(BaseDeDatos,12,"??Puedes establecer la diferencia conceptual entre macroeconom??a y microeconom??a?","Interes","C");
        InsertarPreguntas(BaseDeDatos,13,"??Usar uniforme te hace sentir distinto, importante?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,14,"??Participar??as como profesional en un espect??culo de acrobacia a??rea?","Interes","D");
        InsertarPreguntas(BaseDeDatos,15,"??Organizas tu dinero de manera que te alcance hasta el pr??ximo cobro?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,16,"??Convences f??cilmente a otras personas sobre la validez de tus argumentos?","Interes","S");
        InsertarPreguntas(BaseDeDatos,17,"??Est??s informado sobre los nuevos descubrimientos que se est??n realizando sobre la Teor??a del Big-Bang?","Interes","E");
        InsertarPreguntas(BaseDeDatos,18,"??Ante una situaci??n de emergencia act??as r??pidamente?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,19,"??Cuando tienes que resolver un problema matem??tico, perseveras hasta encontrar la soluci??n?","Interes","I");
        InsertarPreguntas(BaseDeDatos,20,"??Si te convocara tu club preferido para planificar, organizar y dirigir un campo de deportes, aceptar??as?","Interes","C");
        InsertarPreguntas(BaseDeDatos,21,"??Eres el que pone un toque de alegr??a en las fiestas?","Interes","A");
        InsertarPreguntas(BaseDeDatos,22,"??Crees que los detalles son tan importantes como el todo?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,23,"??Te sentir??as a gusto trabajando en un ??mbito hospitalario?","Interes","S");
        InsertarPreguntas(BaseDeDatos,24,"??Te gustar??a participar para mantener el orden ante grandes des??rdenes y cataclismos?","Interes","D");
        InsertarPreguntas(BaseDeDatos,25,"??Pasar??as varias horas leyendo alg??n libro de tu inter??s?","Interes","H");
        InsertarPreguntas(BaseDeDatos,26,"??Planificas detalladamente tus trabajos antes de empezar?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,27,"??Entablas una relaci??n casi personal con tu computadora?","Interes","I");
        InsertarPreguntas(BaseDeDatos,28,"??Disfrutas modelando con arcilla?","Interes","A");
        InsertarPreguntas(BaseDeDatos,29,"??Ayudas habitualmente a los no videntes a cruzar la calle?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,30,"??Consideras importante que desde la escuela primaria se fomente la actitud cr??tica y la participaci??n activa?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,31,"??Aceptar??as que las mujeres formaran parte de las fuerzas armadas bajo las mismas normas que los hombres?","Interes","D");
        InsertarPreguntas(BaseDeDatos,32,"??Te gustar??a crear nuevas t??cnicas para descubrir las patolog??as de algunas enfermedades a trav??s del microscopio?","Interes","E");
        InsertarPreguntas(BaseDeDatos,33,"??Participar??as en una campa??a de prevenci??n contra la enfermedad de Chagas?","Interes","S");
        InsertarPreguntas(BaseDeDatos,34,"??Te interesan los temas relacionados al pasado y a la evoluci??n del hombre?","Interes","H");
        InsertarPreguntas(BaseDeDatos,35,"??Te incluir??as en un proyecto de investigaci??n de los movimientos s??smicos y sus consecuencias?","Interes","E");
        InsertarPreguntas(BaseDeDatos,36,"??Fuera de los horarios escolares, dedicas alg??n d??a de la semana a la realizaci??n de actividades corporales?","Interes","A");
        InsertarPreguntas(BaseDeDatos,37,"??Te interesan las actividades de mucha acci??n y de reacci??n r??pida en situaciones imprevistas y de peligro?","Interes","D");
        InsertarPreguntas(BaseDeDatos,38,"??Te ofrecer??as para colaborar como voluntario en los gabinetes espaciales de la NASA?","Interes","I");
        InsertarPreguntas(BaseDeDatos,39,"??Te gusta m??s el trabajo manual que el trabajo intelectual?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,40,"??Estar??as dispuesto a renunciar a un momento placentero para ofrecer tu servicio como profesional?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,41,"??Participar??as de una investigaci??n sobre la violencia en el f??tbol?","Interes","H");
        InsertarPreguntas(BaseDeDatos,42,"??Te gustar??a trabajar en un laboratorio mientras estudias?","Interes","E");
        InsertarPreguntas(BaseDeDatos,43,"??Arriesgar??as tu vida para salvar la vida de otro que no conoces?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,44,"??Te agradar??a hacer un curso de primeros auxilios?","Interes","S");
        InsertarPreguntas(BaseDeDatos,45,"??Tolerar??as empezar tantas veces como fuere necesario hasta obtener el logro deseado?","Interes","A");
        InsertarPreguntas(BaseDeDatos,46,"??Distribuyes tus horarios del d??a adecuadamente para poder hacer todo lo planeado?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,47,"??Har??as un curso para aprender a fabricar los instrumentos y/o piezas de las m??quinas o aparatos con que trabajas?","Interes","I");
        InsertarPreguntas(BaseDeDatos,48,"??Elegir??as una profesi??n en la tuvieras que estar algunos meses alejado de tu familia, por ejemplo el marino?","Interes","D");
        InsertarPreguntas(BaseDeDatos,49,"??Te radicar??as en una zona agr??cola-ganadera para desarrollar tus actividades como profesional?","Interes","E");
        InsertarPreguntas(BaseDeDatos,50,"??Cuando est??s en un grupo trabajando, te entusiasma producir ideas originales y que sean tenidas en cuenta?","Interes","A");
        InsertarPreguntas(BaseDeDatos,51,"??Te resulta f??cil coordinar un grupo de trabajo?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,52,"??Te result?? interesante el estudio de las ciencias biol??gicas?","Interes","S");
        InsertarPreguntas(BaseDeDatos,53,"??Si una gran empresa solicita un profesional como gerente de comercializaci??n, te sentir??as a gusto desempe??ando ese rol?","Interes","C");
        InsertarPreguntas(BaseDeDatos,54,"??Te incluir??as en un proyecto nacional de desarrollo de la principal fuente de recursos de tu provincia?","Interes","I");
        InsertarPreguntas(BaseDeDatos,55,"??Ten??s inter??s por saber cuales son las causas que determinan ciertos fen??menos, aunque saberlo no altere tu vida?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,56,"??Descubriste alg??n fil??sofo o escritor que haya expresado tus mismas ideas con antelaci??n?","Interes","H");
        InsertarPreguntas(BaseDeDatos,57,"??Desear??as que te regalen alg??n instrumento musical para tu cumplea??os?","Interes","A");
        InsertarPreguntas(BaseDeDatos,58,"??Aceptar??as colaborar con el cumplimiento de las normas en lugares p??blicos?","Interes","D");
        InsertarPreguntas(BaseDeDatos,59,"??Crees que tus ideas son importantes,y haces todo lo posible para ponerlas en pr??ctica?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,60,"??Cuando se descompone un artefacto en tu casa, te dispon??s prontamente a repararlo?","Interes","I");
        InsertarPreguntas(BaseDeDatos,61,"??Formar??as parte de un equipo de trabajo orientado a la preservaci??n de la flora y la fauna en extinci??n?","Interes","E");
        InsertarPreguntas(BaseDeDatos,62,"??Acostumbr??s a leer revistas relacionadas con los ??ltimos avances cient??ficos y tecnol??gicos en el ??rea de la salud?","Interes","S");
        InsertarPreguntas(BaseDeDatos,63,"??Preservar las ra??ces culturales de nuestro pa??s, te parece importante y necesario?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,64,"??Te gustar??a realizar una investigaci??n que contribuyera a hacer m??s justa la distribuci??n de la riqueza?","Interes","C");
        InsertarPreguntas(BaseDeDatos,65,"??Te gustar??a realizar tareas auxiliares en una nave, como por ejemplo izado y arriado de velas, pintura y conservaci??n del casco, arreglo de aver??as, conservaci??n de motores, etc?","Interes","D");
        InsertarPreguntas(BaseDeDatos,66,"??Crees que un pa??s debe poseer la m??s alta tecnolog??a armamentista, a cualquier precio?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,67,"??La libertad y la justicia son valores fundamentales en tu vida?","Interes","H");
        InsertarPreguntas(BaseDeDatos,68,"??Aceptar??as hacer una pr??ctica rentada en una industria de productos alimenticios en el sector de control de calidad?","Interes","E");
        InsertarPreguntas(BaseDeDatos,69,"??Consideras que la salud p??blica debe ser prioritaria, gratuita y eficiente para todos?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,70,"??Te interesar??a investigar sobre alguna nueva vacuna?","Interes","S");
        InsertarPreguntas(BaseDeDatos,71,"??En un equipo de trabajo, prefer??s el rol de coordinador?","Interes","C");
        InsertarPreguntas(BaseDeDatos,72,"??En una discusi??n entre amigos, te ofrec??s como mediador?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,73,"??Est??s de acuerdo con la formaci??n de un cuerpo de soldados profesionales?","Interes","D");
        InsertarPreguntas(BaseDeDatos,74,"??Luchar??as por una causa justa hasta las ??ltimas consecuencias?","Interes","H");
        InsertarPreguntas(BaseDeDatos,75,"??Te gustar??a investigar cient??ficamente sobre cultivos agr??colas?","Interes","I");
        InsertarPreguntas(BaseDeDatos,76,"??Har??as un nuevo dise??o de una prenda pasada de moda, ante una reuni??n imprevista?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,77,"??Visitar??as un observatorio astron??mico para conocer en acci??n el funcionamiento de los aparatos?","Interes","E");
        InsertarPreguntas(BaseDeDatos,78,"??Dirigir??as el ??rea de importaci??n y exportaci??n de una empresa?","Interes","C");
        InsertarPreguntas(BaseDeDatos,79,"??Te inhib??s al entrar a un lugar nuevo con gente desconocida?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,80,"??Te gratificar??a el trabajar con ni??os?","Interes","H");
        InsertarPreguntas(BaseDeDatos,81,"??Har??as el dise??o de un afiche para una campa??a contra el sida?","Interes","A");
        InsertarPreguntas(BaseDeDatos,82,"??Dirigir??as un grupo de teatro independiente?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,83,"??Enviar??as tu curriculum a una empresa automotriz que solicita gerente para su ??rea de producci??n?","Interes","I");
        InsertarPreguntas(BaseDeDatos,84,"??Participar??as en un grupo de defensa internacional dentro de alguna fuerza armada?","Interes","D");
        InsertarPreguntas(BaseDeDatos,85,"??Te costear??as tus estudios trabajando en una auditor??a?","Interes","C");
        InsertarPreguntas(BaseDeDatos,86,"??Sos de los que defend??s causas perdidas?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,87,"??Ante una emergencia epid??mica participar??as en una campa??a brindando tu ayuda?","Interes","S");
        InsertarPreguntas(BaseDeDatos,88,"??Sabr??as responder que significa ADN y ARN?","Interes","E");
        InsertarPreguntas(BaseDeDatos,89,"??Elegir??as una carrera cuyo instrumento de trabajo fuere la utilizaci??n de un idioma extranjero?","Interes","H");
        InsertarPreguntas(BaseDeDatos,90,"??Trabajar con objetos te resulta m??s gratificante que trabajar con personas?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,91,"??Te resultar??a gratificante ser asesor contable en una empresa reconocida?","Interes","C");
        InsertarPreguntas(BaseDeDatos,92,"??Ante un llamado solidario, te ofrecer??as para cuidar a un enfermo?","Interes","S");
        InsertarPreguntas(BaseDeDatos,93,"??Te atrae investigar sobre los misterios del universo, por ejemplo los agujeros negros?","Interes","E");
        InsertarPreguntas(BaseDeDatos,94,"??El trabajo individual te resulta m??s r??pido y efectivo que el trabajo grupal?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,95,"??Dedicar??as parte de tu tiempo a ayudar a personas de zonas carenciadas?","Interes","H");
        InsertarPreguntas(BaseDeDatos,96,"??Cuando eleg??s tu ropa o decor??s un ambiente, ten??s en cuenta la combinaci??n de los colores, las telas o el estilo de los muebles?","Interes","A");
        InsertarPreguntas(BaseDeDatos,97,"??Te gustar??a trabajar como profesional dirigiendo la construcci??n de una empresa hidroel??ctrica?","Interes","I");
        InsertarPreguntas(BaseDeDatos,98,"??Sab??s qu?? es el PBI?","Interes","C");
    }
    private void InsertarPreguntas(SQLiteDatabase BD,int idPregunta,String TextoPreg,String Opcionalidad,String Letra)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("TextoPregunta",TextoPreg);
        NuevoRegistro.put("ID_Pregunta",idPregunta);
        NuevoRegistro.put("Orientacion",Opcionalidad.toUpperCase());
        NuevoRegistro.put("Letra",Letra.toUpperCase());
        BD.insert("Preguntas",null,NuevoRegistro);
    }
    private void MateriasDeCarrera(SQLiteDatabase BD)
    {

        InsertarMaterias(BD,2,"Derecho Constitucional I",1,0,"El objetivo del curso es examinar una diversidad de problemas te??ricos vinculados con el hecho de vivir en una democracia constitucional a la luz de la teor??a constitucional contempor??nea.Algunas de las preguntas que se intentan responder son: ??Por qu?? tener una Constituci??n? ??C??mo justificar su dictado? ??C??mo defender la restricci??n de la democracia que, te??ricamente, aparece cuando adoptamos un documento constitucional? ??Pueden las generaciones pasadas limitar a las generaciones subsiguientes?");
        InsertarMaterias(BD,2,"Teor??a General del Derecho",1,0,"El curso de Teor??a General del Derecho se divide en tres partes. En primer lugar, los alumnos se familiarizan con diferentes teor??as del derecho En segundo lugar, los alumnos estudian los fundamentos filos??ficos de las principales ramas del derecho, como derecho constitucional, derecho civil, derecho penal y derecho internacional. Finalmente, una vez aprendidos estos fundamentos, los alumnos analizan desde una perspectiva filos??fica los fallos m??s importantes de la Corte Suprema argentina y norteamericana. El objetivo del curso es que los alumnos aprendan a encarar el estudio del derecho de forma tal que intenten entenderlo y criticarlo, no simplemente memorizarlo.");
        InsertarMaterias(BD,2,"Fundamentos del Derecho Privado",1,0,"Este curso analiza los conceptos fundamentales del derecho civil y comercial . El curso discute las instituciones del derecho privado desde diferentes enfoques ??? utilitarista, deontol??gico, econ??mico, aristot??lico, hist??rico, entre otros -. El debate entre V??lez S??rsfield y Juan Bautista Alberdi respecto del C??digo Civil, la legalidad del matrimonio entre personas del mismo sexo, la concepci??n tomista de la propiedad y el fundamento kantiano de la responsabilidad civil son ejemplos de los temas discutidos en el curso.");
        InsertarMaterias(BD,2,"Historia Contempor??nea",1,0,"Este curso se concentra en la historia de Europa entre el siglo XVII y los a??os 20 del siglo XX, con ??nfasis en la historia de las ideas y los aspectos institucionales y econ??micos. As??, para el an??lisis de la Revoluci??n Francesa y sus repercusiones (1789-99), el curso se concentra en el Iluminismo, Locke, Hume, Rousseau, Montesquieu, el debate sobre la Revoluci??n Francesa (Siey??s, Burke y Paine) y las transformaciones pol??ticas y econ??micas de la Revoluci??n Industrial que marcaron el fin del Antiguo R??gimen. El mismo criterio se utiliza para los dem??s per??odos: la era de Napole??n Bonaparte (1800-1814); la reorganizaci??n de Europa (1818-48); las revoluciones del 48 y los primeros movimientos nacionales (1848-60); la era de Bismarck y la nueva expansi??n colonialista europea (1860-75) y los acontecimientos que llevaron a la Primera Guerra Mundial. Finalmente, se anali{za el surgimiento de nuevos movimientos pol??ticos europeos (comunismo, fascismo y nacionalsocialismo).");
        InsertarMaterias(BD,2,"Derecho Penal I",1,0,"El objetivo principal de este curso reside en la comprensi??n de los principios b??sicos de la responsabilidad penal desarrollados por la, as?? llamada, teor??a de la imputaci??n jur??dico-penal (o tambi??n: teor??a del delito o teor??a de la responsabilidad penal). El curso combina el tratamiento de problemas y conceptos te??ricos tales como los fines del derecho penal y sus l??mites, la acci??n, la causalidad, la fundamentaci??n del il??cito, la justificaci??n y la excusa, etc., y su aplicaci??n sobre casos hipot??ticos.");
        InsertarMaterias(BD,2,"Derecho Constitucional II",1,0,"El objetivo del curso es explorar aspectos centrales de la Constituci??n de Argentina a la luz de la jurisprudencia de la Corte Suprema de Justicia de la Naci??n. Entre las preguntas que se tratan de responder en el curso figuran las siguientes: ??Cu??l es la estructura de incentivos impl??cita en la Constituci??n en materia de derechos individuales y de organizaci??n del poder? ??C??mo puede ser evaluada dicha estructura de incentivos en t??rminos de eficiencia y equidad? ??En qu?? medida, si es que alguna, tal estructura de incentivos facilit?? la in??dita crisis que Argentina est?? experimentando? ??Cu??les son las fallas centrales en el derecho constitucional de Argentina? ??Es suficiente una reforma constitucional para enmendar tales fallas?");
        InsertarMaterias(BD,2,"Obligaciones",1,0,"Este curso se concentra en los fundamentos filos??ficos de las obligaciones civiles y comerciales. Entre las preguntas que el curso comprende se encuentran las siguientes: ??qu?? es ???da??o??? en el derecho civil? ??Cu??l es el fundamento de la responsabilidad subjetiva y de la objetiva? ??Cu??l es la mejor explicaci??n de la causalidad en el derecho privado? ??Cu??l es el fundamento de los da??os punitivos? ??C??mo responden los sistemas jur??dicos en tiempos de crisis? ??Qu?? son las ???deudas odiosas???? ");
        InsertarMaterias(BD,2,"Filosof??a Moral",1,0,"En este curso exploramos las nociones de moralmente correcto y moralmente incorrecto, deberes y valores morales, y derechos morales, tal como ellas son discutidas en la filosof??a moral contempor??nea. Intentamos responder algunas preguntas conceptuales, como ??Qu?? significa 'correcto'?, y algunas preguntas normativas, como ??Tenemos un deber moral de promover la felicidad general? y ??Hay alguna excusa v??lida para incumplir una promesa?");
        InsertarMaterias(BD,2,"Derecho de Da??os y Seguros",2,0,"Este curso tiene como objetivo principal proveer una visi??n integrada sobre los principios y fundamentos que rigen el Derecho de Da??os. El objetivo espec??fico consiste en analizar los elementos de la responsabilidad civil, as?? como las funciones que cumple el Derecho de Da??os. El curso combina el estudio pormenorizado de la normativa y jurisprudencia nacional y extranjera con una metodolog??a interdisciplinaria que integra al derecho con herramientas provistas por la Econom??a.");
        InsertarMaterias(BD,2,"Derecho Penal II",2,0,"En gran medida este es un curso de ejercitaci??n intensiva de lo aprendido en Responsabilidad Penal. A trav??s de la soluci??n de casos hipot??ticos y jurisprudenciales se confronta la aplicaci??n de los principios b??sicos de la responsabilidad penal con las figuras legales principales de la parte especial del C??digo Penal argentino y de su legislaci??n accesoria. As??, se examinan delitos tales como el homicidio, el aborto, la asistencia al suicidio, el abandono de personas, los delitos sexuales, la calumnia y la injuria, el hurto, el robo, estafas y defraudaciones, delitos de tenencia de materiales prohibidos, enriquecimiento de funcionario p??blico, etc.");
        InsertarMaterias(BD,2,"Microeconom??a",2,0,"Este curso es una introducci??n a la microeconom??a. El contenido del curso incluye la discusi??n de los siguientes conceptos: teor??a de los precios, elecci??n y curvas de indiferencia, competencia perfecta, competencia monop??lica, oligopolio y teor??a de los juegos, equilibrio de mercado, equilibrio general, nociones de eficiencia, teoremas del bienestar, fallas del mercado, teor??a del comportamiento de la empresa y organizaci??n industrial.");
        InsertarMaterias(BD,2,"L??gica y Redacci??n",2,0,"El objetivo del curso de L??gica y Redacci??n es que los alumnos aprendan a argumentar de forma clara, rigurosa y original. Para adquirir esta habilidad, los alumnos leen textos de gram??tica, l??gica formal, l??gica informal y filosof??a de la l??gica, entre otros. Asimismo, los alumnos realizan un trabajo cient??fico cada dos semanas y, de esta forma, aplican las herramientas te??ricas que adquirieron leyendo los textos. Estos trabajos, que deben tener la misma estructura que aquellos publicados en los mejores journals cient??ficos, son minuciosamente corregidos por el profesor.");
        InsertarMaterias(BD,2,"Filosof??a Pol??tica",2,0,"Este curso versa sobre los modernos desarrollos en la filosof??a pol??tica relacionados con la teor??a normativa de la justicia social.  El curso se divide en tres partes.  En la primera, se utiliza como bibliograf??a b??sica la edici??n revisada de A Theory of Justice de John Rawls (Harvard University Press, 1999), y se la estudia en su integridad por su papel central en la configuraci??n de los estudios normativos contempor??neos. La segunda parte del curso analiza cuatro ideas relacionadas con la justicia muy corrientes en los argumentos jur??dicos y de pol??ticas p??blicas: las ideas de merecimiento, igualdad, reciprocidad y necesidad.  En lugar del enfoque sistem??tico de Rawls, se sigue en esta parte el enfoque pluralista y contextualista de David Schmidtz en Elements of Justice (Cambridge University Press, 2006).  El uso combinado de dos obras basadas en  intuiciones normativas divergentes sobre la justicia social (intuiciones ???socialistas??? en el caso de  Rawls y proclives a la ???econom??a de mercado??? en el de Schmidtz) fomenta el pluralismo ideol??gico en un curso que inevitablemente est?? cargado de presupuestos ideol??gicos.  Finalmente, la tercera parte del curso se dedica a estudiar las ideas de justicia y suerte en la literatura y filosof??a de Grecia antigua.  El objetivo de esta parte es invitar al estudiante a reflexionar sobre la relaci??n entre las ideas normativas y la cultura p??blica.  Para ello se utiliza la obra de Martha C. Nussbaum: The Fragility of goodness, Luck and ethics in Greek tragedy and philosophy (Cambridge University Press, 1986).  Esta parte del curso, dictada por el ayudante, se desarrolla a trav??s de la confecci??n de trabajos escritos. ");
        InsertarMaterias(BD,2,"Derechos Reales",2,0,"Este curso tiene como objetivo el estudio de las normas que rigen los distintos derechos que pueden tener las personas sobre las cosas muebles e inmuebles. El curso se propone familiarizar a los estudiantes con la normativa de los derechos reales, su origen y su evoluci??n.  Se busca tambi??n el examen cr??tico, de modo de aprovechar el curso para advertir las ventajas de algunas soluciones, as?? como lo inconveniente que pueden resultar otras, y las necesidades de cambio.");
        InsertarMaterias(BD,2,"Derecho Procesal Penal",2,0,"Este curso tiene como objetivo proveer herramientas para enfrentar todos los problemas jur??dicos de un proceso penal. Para ello se combinan, por un lado, contenidos te??ricos - especialmente los referidos a la interpretaci??n de las garant??as constitucionales de aplicaci??n en el proceso penal, a la derivaci??n a partir de ellas de reglas procesales concretas y al conocimiento de los distintos modelos procesales penales hist??ricamente dados -; y, por otro, ejercitaci??n pr??ctica sobre la base de casos hipot??ticos y de la simulaci??n de diversas instancias procesales que sit??an a los alumnos en distintos roles procesales (juez de instrucci??n, tribunal de juicio, acusador p??blico, acusador particular, defensor).");
        InsertarMaterias(BD,2,"An??lisis Econ??mico del Derecho",2,0,"El objetivo del curso es que los alumnos aprendan a analizar el derecho desde la perspectiva de la microeconom??a. A partir de la escasez de los recursos y de la racionalidad de los individuos (dos supuestos que se mantienen durante el curso), se analizan las consecuencias que trae aparejado el dise??o de las leyes, tanto desde el punto de vista normativo como el positivo. Durante el curso, se utilizan diferentes ??reas del derecho - derecho de la propiedad, el derecho penal, el derecho de da??os, el derecho contractual y el derecho procesal - como medios para internalizar el uso de las herramientas de la microeconom??a");
        InsertarMaterias(BD,2,"Contratos I",3,0,"El objetivo de este curso reside en la comprensi??n de las preguntas fundamentales del derecho de los contratos a la luz de la legislaci??n y jurisprudencia tanto argentina como de otras jurisdicciones. Entre las preguntas que el curso comprende se encuentran las siguientes: ??Qu?? acuerdos tienen valor legal y por qu??? ??Qu?? efectos tienen los contratos entre las partes y con respecto a los terceros? ??Cu??les son las acciones en caso de incumplimiento contractual? ??Qu?? criterios deben utilizar los tribunales para interpretar contratos? ??Qu?? bienes y servicios pueden ser objeto de un contrato y por qu???");
        InsertarMaterias(BD,2,"Familia y Sucesiones",3,0,"El objetivo de este curso es analizar las instituciones fundamentales del derecho de familia y del derecho sucesorio, a trav??s del an??lisis del derecho positivo, la doctrina y las decisiones judiciales. Entre los temas discutidos en el curso se encuentran los siguientes: efectos personales y patrimoniales del matrimonio, uniones de hecho, filiaci??n, adopci??n, patria potestad, herencia, legados, entre otros.");
        InsertarMaterias(BD,2,"Derecho Laboral y de la Seguridad Social ",3,0,"");
        InsertarMaterias(BD,2,"Derecho y Sociedad",3,0,"El curso ofrece una introducci??n a los estudios de Derecho y Sociedad para familiarizar a los estudiantes con los usos de la investigaci??n emp??rica desde los puntos de vista interno y externo del derecho. Con ese objetivo, se organiza en tres secciones.  En la primera etapa se revisar??n diversas tradiciones de estudios de Derecho y Sociedad en Am??rica Latina y otros continentes. En este marco se estudiar??n cuestiones de eficacia, pluralismo, desigualdad, y derecho ydesarrollo. Se revisar??n tambi??n las discusiones realistas, neorrealistas y pragm??ticas sobre las relaciones entre el derecho ???en los libros,??? el derecho ???en acci??n??? y el derecho ???en las conciencias??? de las personas.");
        InsertarMaterias(BD,2,"Derecho Internacional P??blico",3,0,"Este curso tiene como objetivo el impartir conocimientos b??sicos sobre la estructura y efectos de las relaciones jur??dicas que vinculan a los Estados. En la primera parte se analizan los mecanismos de creaci??n de las normas del sistema conocido como derecho internacional p??blico; las relaciones de ese derecho con los derechos internos de los Estados; la responsabilidad internacional y sus consecuencias. En la segunda parte, se estudian los temas fundamentales que relacionan al Estado en el tiempo (reconocimiento y sucesi??n de Estados); la jurisdicci??n del Estado frente a la de otros Estados y el ??mbito territorial de sus competencias (terrestre, mar??timo y fluvial); las obligaciones de los Estados frente a la promoci??n y protecci??n de los derechos humanos; y los principios fundamentales que rigen las relaciones amistosas entre Estados");
        InsertarMaterias(BD,2,"Derecho Procesal Civil I",3,0,"El curso tiene como objetivo brindar al alumno los conocimientos necesarios para obtener una formaci??n s??lida con relaci??n a distintos aspectos del Derecho Procesal Civil. El punto de partida es entender al Proceso como un m??todo de resoluci??n de conflictos. Debido a la divisi??n tem??tica entre las asignaturas Derecho Procesal Civil I y II, aqu?? se discutir??n los problemas que presenta la asignatura en los siguientes ejes tem??ticos: (i) nociones b??sicas del proceso; (i) el abogado y el juez; y (iii) el debate procesal en su etapa postulatoria y probatoria.  De tal modo, quedar??n reservados para el curso de Derecho Procesal Civil II los temas referidos a la etapa decisoria del proceso, medios de impugnaci??n, procesos de ejecuci??n y procesos especiales. ");
        InsertarMaterias(BD,2,"Derecho Administrativo",3,0,"Este curso tiene como objetivo el estudio de los principios y reglas que rigen el ejercicio de la funci??n administrativa. Para ello, pone especial ??nfasis en la interacci??n permanente entre dicha funci??n y el Poder Judicial en virtud de las limitaciones que la Administraci??n impone al ejercicio de los derechos de los particulares, as?? como en la relaci??n existente entre los t??picos que suelen ser discutidos bajo el r??tulo y las normas constitucionales y los par??metros que pueden deducirse de ellas con relaci??n al ejercicio de la funci??n administrativa y la organizaci??n estatal en generalho Administrativo");
        InsertarMaterias(BD,2,"Sociedades",3,0,"Los objetivos principales de este curso son la comprensi??n de los problemas b??sicos objeto del derecho societario, el estudio de sus normas y principios y la reflexi??n acerca de las diversas finalidades que ??stas persiguen.  Para ello, se utiliza el an??lisis econ??mico del derecho como marco te??rico y el m??todo de casos, y se presta especial atenci??n a la forma en que el derecho comparado enfoca y soluciona dichos problemas.");
        InsertarMaterias(BD,2,"Derecho Procesal Civil II",4,1,"Los objetivos de esta etapa de la materia son similares a los de Derecho Procesal Civil I, s??lo cambia un tramo de la partitura. Esta parte del Derecho Procesal Civil es m??s t??cnica, lo que no impide que se apliquen todas las nociones te??ricas adquiridas en la parte inicial; m??s bien, son esas ideas basales las que facilitan el abordaje de los nuevos t??picos. El desaf??o de este curso reside en lograr explicar los problemas que debe afrontar el juez para resolver controversias, la din??mica de las diferentes clases de impugnaci??n y otras formas alternativas de disputabilidad tales como los procesos especiales y el juicio ejecutivo. El curso se concentra, primordialmente, en los problemas de la decisi??n (y del decisor) y en la teor??a de la impugnaci??n.");
        InsertarMaterias(BD,2,"Macroeconom??a",4,1,"Este curso es una introducci??n a problemas de macroeconom??a. El contenido del curso incluye la discusi??n de los siguientes t??picos, entre otros de importancia: dinero, inflaci??n y tipo de Cambio; gobierno y pol??tica Fiscal; modelos de econom??a abierta; ahorro e inversi??n; efectos de las corridas bancarias.");
        InsertarMaterias(BD,2,"Derecho Tributario",4,1,"Esta materia tiene por objetivo ense??ar al alumno c??mo funcionan los impuestos en Argentina y cu??les son las reglas b??sicas sobre tributaci??n internacional para lograr que: a) los alumnos que se especialicen en el ??rea de impuestos egresen con fuertes herramientas te??ricas y pr??cticas para poder comprenderlos y trabajar con ellos; y b) los que trabajen en cualquier otro ??rea sepan lo que tienen que saber (como m??nimo) para poder convivir con ellos sin sobresaltos.");
        InsertarMaterias(BD,2,"Concursos y Quiebras",4,1,"Este curso tiene como objeto introducir a los alumnos en el estudio del derecho concursal. Para ello se utilizan contenidos te??ricos con especial ??nfasis en el an??lisis econ??mico y financiero de los temas a desarrollar. El curso buscar?? responder preguntas tales como: ??Por qu?? un sistema concursal? ??A qui??n protege un sistema concursal? ??C??mo conviene iniciar un proceso concursal? ??C??mo debe constituirse el pasivo concursal? ??C??mo se debe distribuir el activo concursal? De esta manera nutrir?? a los alumnos de conocimientos y herramientas anal??ticas que les permitir??n interpretar y actuar sobre la legislaci??n existente, as?? como anticiparse a los futuros cambios posibles");
        InsertarMaterias(BD,2,"Contabilidad y An??lisis Financiero",4,1,"Este curso tiene 2 partes. La primera parte del curso tiene como objetivo introducir a los alumnos en los procesos de registro y medici??n de la informaci??n econ??mica y financiera vinculada con el desempe??o de las empresas, prestando particular atenci??n a la comprensi??n de los principales criterios utilizados en el campo contable. Esta parte tiene un ??nfasis especial en analizar las t??cnicas contables en los contextos legales en los que es muy probable que el abogado deba enfrentarse con cuestiones econ??micas y financieras.  La segunda parte se concentra en  los procesos de decisi??n econ??mica y financiera. Fundamentalmente, se tratan temas vinculados con proyecciones financieras, an??lisis de inversi??n y valor tiempo del dinero, determinaci??n del costo de capital, valuaci??n de empresas y decisiones sobre utilizaci??n de capital propio o ajeno.");
        InsertarMaterias(BD,2,"Contratos II",4,1,"Este curso analiza los contratos utilizados con mayor frecuencia en la actualidad. Su objetivo es brindar a los participantes: (i) el marco te??rico necesario para ese an??lisis, integrado por la normativa, jurisprudencia, doctrina y dem??s fuentes del derecho relevantes en cada caso y (ii) las herramientas necesarias para la aplicaci??n de ese marco conceptual, incluyendo t??cnicas de redacci??n, interpretaci??n y negociaci??n de cl??usulas y contratos.");
        InsertarMaterias(BD,2,"Derecho Internacional Privado",4,1,"Este curso tiene como objetivo principal proveer una visi??n integrada sobre los principios y fundamentos que rigen el Derecho Internacional Privado. El objetivo espec??fico consiste en analizar la determinaci??n del ordenamiento jur??dico competente para regir las relaciones jur??dicas internacionales. El curso combina el estudio pormenorizado de la normativa argentina, tanto de fuente interna como convencional, con el estudio de jurisprudencia nacional y  extranjera");
        InsertarMaterias(BD,2,"Derecho Ambiental y de los Recursos Naturales",5,1,"El curso consiste en una introducci??n al derecho ambiental en general, y el derecho ambiental argentino en particular. Se examinan, en consecuencia, los fundamentos, evoluci??n, contenidos e incentivos de las principales reglas de contenido ambiental del sistema legal. Adicionalmente, se analizan elementos de pol??tica, econom??a y ??tica ambiental que permiten una mirada cr??tica de los problemas y regulaciones ambientales.");
        InsertarMaterias(BD,2,"Seminario Mediaci??n y Arbitraje",5,1,"Este curso es una introducci??n a la mediaci??n y el arbitraje, medios de resoluci??n de conflictos basados en la autonom??a de las partes. La mediaci??n se estudia como una negociaci??n asistida, fundada en principios orientados a procurar acuerdos que satisfagan los interes es de las partes. Se exponen los temas centrales del arbitraje dom??stico e internacional: el acuerdo arbitral, la instancia arbitral y el laudo o sentencia arbitral. Se examinan las relaciones entre el arbitraje y el proceso judicial estatal, en particular en materia de impugnaci??n de laudos, y reconocimiento y ejecuci??n de laudos extranjeros.");
        InsertarMaterias(BD,2,"??tica Profesional",5,1,"El curso tiene tres objetivos principales. En primer lugar, se busca introducir a los alumnos en la normativa vigente en materia de conducta profesional, tal como es regulada en la Capital Federal por el Colegio P??blico de Abogados, compar??ndola con los c??digos utilizados en EUA y Europa. En segundo lugar, se discuten una serie de casos reales e hipot??ticos que plantean problemas ??ticos en el ejercicio de la profesi??n, con el objetivo de obtener una clasificaci??n de los distintos tipos de problemas involucrados (confidencialidad, conflicto de intereses, aceptaci??n y rechazo de clientes, acceso y distribuci??n de recursos legales, l??mites del sistema adversarial, etc.). Por ??ltimo, se busca profundizar algunos de estos problemas, a trav??s de la discusi??n de bibliograf??a m??s te??rica, aunque sin descuidar la discusi??n de casos concretos.");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o I",1,0,"");
        InsertarMaterias(BD,6,"Forma e Imagen",1,0,"");
        InsertarMaterias(BD,6,"Arte y Cultura de la Modernidad",1,0,"");
        InsertarMaterias(BD,6,"Matem??tica I",1,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o II",1,0,"");
        InsertarMaterias(BD,6,"Teor??as de la Comunicaci??n",1,0,"");
        InsertarMaterias(BD,6,"Introducci??n a la Administraci??n P??blica y a las Organizaciones",1,0,"");
        InsertarMaterias(BD,6,"Econom??a",1,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o III",2,0,"");
        InsertarMaterias(BD,6,"Objeto y Materialidad",2,0,"");
        InsertarMaterias(BD,6,"Introducci??n a los Estudios Visuales",2,0,"");
        InsertarMaterias(BD,6,"Historia del Dise??o Moderno",2,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o IV",2,0,"");
        InsertarMaterias(BD,6,"Teor??a y Metodolog??a del Dise??o",2,0,"");
        InsertarMaterias(BD,6,"Ingenier??a de Materiales",2,0,"");
        InsertarMaterias(BD,6,"Sociolog??a",2,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o V",3,0,"");
        InsertarMaterias(BD,6,"Visualizaci??n de la Informaci??n",3,0,"");
        InsertarMaterias(BD,6,"Programaci??n Orientada al Dise??o",3,0,"");
        InsertarMaterias(BD,6,"Historia del Dise??o Latinoamericano",3,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o VI",3,0,"");
        InsertarMaterias(BD,6,"Dise??o de Interactividad",3,0,"");
        InsertarMaterias(BD,6,"Marketing",3,0,"");
        InsertarMaterias(BD,6,"Curso de Campo Menor",3,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o VII",4,0,"");
        InsertarMaterias(BD,6,"Dise??o y Gesti??n Cultural",4,0,"");
        InsertarMaterias(BD,6,"Gesti??n Estrat??gica de Dise??o",4,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Dise??o VIII (Proyecto Final)",4,0,"");
        InsertarMaterias(BD,6,"Narrativas y Medios",4,0,"");
        InsertarMaterias(BD,6,"Dise??o Sustentable",4,0,"");
        InsertarMaterias(BD,7,"Introducci??n a la Ciencia Pol??tica",1,0,"");
        InsertarMaterias(BD,7,"Matem??tica I",1,0,"");
        InsertarMaterias(BD,7,"Econom??a I",1,0,"");
        InsertarMaterias(BD,7,"Introducci??n a las Relaciones Internacionales",1,0,"");
        InsertarMaterias(BD,7,"Comprensi??n de Textos y Escritura",1,0,"");
        InsertarMaterias(BD,7,"Teor??a Pol??tica I",1,0,"");
        InsertarMaterias(BD,7,"Matem??tica II",1,0,"");
        InsertarMaterias(BD,7,"L??gica y T??cnicas de Investigaci??n en Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,7,"Historia de Occidente a partir de la Modernidad",1,0,"");
        InsertarMaterias(BD,7,"Econom??a II",2,0,"");
        InsertarMaterias(BD,7,"Introducci??n a las Pol??ticas P??blicas",2,0,"");
        InsertarMaterias(BD,7,"Pol??tica Comparada",2,0,"");
        InsertarMaterias(BD,7,"Pol??tica y Sociedad en la Argentina (Siglos XIX y XX)",2,0,"");
        InsertarMaterias(BD,7,"Pol??tica y Sociedad en Am??rica Latina",2,0,"");
        InsertarMaterias(BD,7,"Teor??a Pol??tica II",2,0,"");
        InsertarMaterias(BD,7,"Teor??a de las Relaciones Internacionales",2,0,"");
        InsertarMaterias(BD,7,"Historia del Mundo Contempor??neo (1914-2000)",2,0,"");
        InsertarMaterias(BD,7,"Pol??tica Exterior Argentina",3,0,"");
        InsertarMaterias(BD,7,"Pol??tica y Econom??a",3,0,"");
        InsertarMaterias(BD,7,"Dise??o y Metodolog??a de la Investigaci??n Social",3,0,"");
        InsertarMaterias(BD,7,"Organizaciones y Teor??a de la Decisi??n",3,0,"");
        InsertarMaterias(BD,7,"Expresi??n Oral y Escrita",3,0,"");
        InsertarMaterias(BD,7,"Relaciones Internacionales Contempor??neas",3,0,"");
        InsertarMaterias(BD,7,"Pol??tica Exterior de Estados Unidos",3,0,"");
        InsertarMaterias(BD,7,"Estad??stica para Ciencias Sociales",3,0,"");
        InsertarMaterias(BD,7,"Pol??tica y Derecho",3,0,"");
        InsertarMaterias(BD,7,"Organismos Internacionales",4,0,"");
        InsertarMaterias(BD,7,"Derecho Internacional",4,0,"");
        InsertarMaterias(BD,7,"Comercio Internacional",4,0,"");
        InsertarMaterias(BD,7,"Conflictos Internacionales y Seguridad",4,0,"");
        InsertarMaterias(BD,7,"Seminario de Graduaci??n",4,0,"");
        InsertarMaterias(BD,8,"Introducci??n a la Ciencia Pol??tica",1,0,"");
        InsertarMaterias(BD,8,"Matem??tica I",1,0,"");
        InsertarMaterias(BD,8,"Econom??a I",1,0,"");
        InsertarMaterias(BD,8,"Introducci??n a las Relaciones Internacionales",1,0,"");
        InsertarMaterias(BD,8,"Comprensi??n de Textos y Escritura",1,0,"");
        InsertarMaterias(BD,8,"Teor??a Pol??tica I",1,0,"");
        InsertarMaterias(BD,8,"Matem??tica II ",1,0,"");
        InsertarMaterias(BD,8,"Historia de Occidente a partir de la Modernidad",1,0,"");
        InsertarMaterias(BD,8,"L??gica y T??cnicas de Investigaci??n en Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,8,"Economia II",2,0,"");
        InsertarMaterias(BD,8,"Pol??tica Comparada",2,0,"");
        InsertarMaterias(BD,8,"Introducci??n a las Pol??ticas P??blicas",2,0,"");
        InsertarMaterias(BD,8,"Pol??tica y Sociedad en la Argentina (Siglos XIX y XX)",2,0,"");
        InsertarMaterias(BD,8,"Pol??tica y Sociedad en Am??rica Latina",2,0,"");
        InsertarMaterias(BD,8,"Teor??a Pol??tica II",2,0,"");
        InsertarMaterias(BD,8,"Teor??a de las Relaciones Internacionales",2,0,"");
        InsertarMaterias(BD,8,"Historia del Mundo Contempor??neo (1914-2000)",2,0,"");
        InsertarMaterias(BD,8,"Estructura Social y Demograf??a",3,0,"");
        InsertarMaterias(BD,8,"Pol??tica y Econom??a",3,0,"");
        InsertarMaterias(BD,8,"Dise??o y Metodolog??a de la Investigaci??n Social",3,0,"");
        InsertarMaterias(BD,8,"Organizaciones y Teor??a de la Decisi??n",3,0,"");
        InsertarMaterias(BD,8,"Expresi??n Oral y Escrita",3,0,"");
        InsertarMaterias(BD,8,"Estado y Pol??ticas P??blicas en la Argentina",3,0,"");
        InsertarMaterias(BD,8,"Pol??tica y Derecho",3,0,"");
        InsertarMaterias(BD,8,"Pol??tica y Comunicaci??n",3,0,"");
        InsertarMaterias(BD,8,"Estad??stica para Ciencias Sociales",3,0,"");
        InsertarMaterias(BD,8,"Finanzas P??blicas",4,0,"");
        InsertarMaterias(BD,8,"Seminario de Temas de Pol??tica Econ??mica Argentina",4,0,"");
        InsertarMaterias(BD,8,"T??picos de Teor??a Pol??tica Social",4,0,"");
        InsertarMaterias(BD,8,"Actores y Procesos Pol??ticos",4,0,"");
        InsertarMaterias(BD,8,"Seminario de Graduaci??n",4,0,"");
        InsertarMaterias(BD,11,"Matem??tica",1,0,"");
        InsertarMaterias(BD,11,"Qu??mica",1,0,"");
        InsertarMaterias(BD,11,"Introducci??n al Conocimiento de la Sociedad y el Estado ",1,0,"");
        InsertarMaterias(BD,11,"F??sica e Introducci??n a la Biof??sica",1,0,"");
        InsertarMaterias(BD,11,"Biolog??a",1,0,"");
        InsertarMaterias(BD,11,"Qu??mica Aplicada",2,0,"");
        InsertarMaterias(BD,11,"F??sica Aplicada",2,0,"");
        InsertarMaterias(BD,11,"Estad??stica General",2,0,"");
        InsertarMaterias(BD,11,"Biomol??culas",2,0,"");
        InsertarMaterias(BD,11,"Bioqu??mica Aplicada",2,0,"");
        InsertarMaterias(BD,11,"Edafolog??a",2,0,"");
        InsertarMaterias(BD,11,"Climatolog??a y Agrometeorolog??a",2,0,"");
        InsertarMaterias(BD,11,"Bot??nica",2,0,"");
        InsertarMaterias(BD,11,"Evoluci??n y Gen??tica",3,0,"");
        InsertarMaterias(BD,11,"Fisiolog??a de las Plantas Superiores",3,0,"");
        InsertarMaterias(BD,11,"Zoolog??a General ",3,0,"");
        InsertarMaterias(BD,11,"Qu??mica de la Contaminaci??n y Toxicolog??a",3,0,"");
        InsertarMaterias(BD,11,"Sociolog??a y Antropolog??a General",3,0,"");
        InsertarMaterias(BD,11,"Ecolog??a",3,0,"");
        InsertarMaterias(BD,11,"Econom??a Pol??tica",3,0,"");
        InsertarMaterias(BD,11,"Microbiolog??a Ambiental",3,0,"");
        InsertarMaterias(BD,11,"Nociones de Geolog??a y Geomorfolog??a",3,0,"");
        InsertarMaterias(BD,11,"Derechos Humanos",3,0,"");
        InsertarMaterias(BD,11,"Ecolog??a Acu??tica ",4,0,"");
        InsertarMaterias(BD,11,"Hidrolog??a",4,0,"");
        InsertarMaterias(BD,11,"Geograf??a Ambiental",4,0,"");
        InsertarMaterias(BD,11,"Bioindicadores",4,0,"");
        InsertarMaterias(BD,11,"Sistemas de Informaci??n Geogr??fica, Cartograf??a y Teledetecci??n",4,0,"");
        InsertarMaterias(BD,11,"Econom??a Aplicada al Agro y al Ambiente",4,0,"");
        InsertarMaterias(BD,11,"Agroecosistemas",4,0,"");
        InsertarMaterias(BD,11,"Ambiente y Sociedad",4,0,"");
        InsertarMaterias(BD,11,"Gesti??n de Proyectos",4,0,"");
        InsertarMaterias(BD,11,"Econom??a y Pol??tica del Ambiente",4,0,"");
        InsertarMaterias(BD,11,"Gesti??n y Conservaci??n de los Recursos Naturales",5,0,"");
        InsertarMaterias(BD,11,"??tica y Legislaci??n Ambiental",5,0,"");
        InsertarMaterias(BD,11,"Biodiversidad",5,0,"");
        InsertarMaterias(BD,11,"Modelos Estad??sticos",5,0,"");
        InsertarMaterias(BD,11,"Conservaci??n y Planificaci??n del Uso de la Tierra",5,0,"");
        InsertarMaterias(BD,11,"Ordenamiento Territorial",5,0,"");
        InsertarMaterias(BD,11,"Modelos de Simulaci??n",6,0,"");
        InsertarMaterias(BD,11,"Cambio Global",6,0,"");
        InsertarMaterias(BD,11,"Evaluaci??n de Impacto Ambiental",6,0,"");
        InsertarMaterias(BD,11,"An??lisis de Riesgo Ambiental",6,0,"");
        InsertarMaterias(BD,11,"Trabajo Final",6,0,"");
        InsertarMaterias(BD,12,"Matem??tica",1,0,"");
        InsertarMaterias(BD,12,"Quimica",1,0,"");
        InsertarMaterias(BD,12,"Introduccion al conocimiento de la sociedad y el estado",1,0,"");
        InsertarMaterias(BD,12,"Biologia",1,0,"");
        InsertarMaterias(BD,12,"Fisica e introduccion a la biologia",1,0,"");
        InsertarMaterias(BD,12,"Introduccion al pensamiento cientifico",1,0,"");
        InsertarMaterias(BD,12,"Quimica aplicada",2,0,"");
        InsertarMaterias(BD,12,"Biomelucas",2,0,"");
        InsertarMaterias(BD,12,"fisica aplicada",2,0,"");
        InsertarMaterias(BD,12,"Estadistica General",2,0,"");
        InsertarMaterias(BD,12,"Botanica Morfologica",2,0,"");
        InsertarMaterias(BD,12,"Bioquima Aplicada",2,0,"");
        InsertarMaterias(BD,12,"Edafologia",2,0,"");
        InsertarMaterias(BD,12,"Climatologia y agronomia",2,0,"");
        InsertarMaterias(BD,12,"Botanica Sistematica",2,0,"");
        InsertarMaterias(BD,12,"Botanica Aplicada",2,0,"");
        InsertarMaterias(BD,12,"Ingles",2,0,"");
        InsertarMaterias(BD,12,"Informatica",2,0,"");
        InsertarMaterias(BD,12,"Genetica y mejoramiento Vegetal",3,0,"");
        InsertarMaterias(BD,12,"Fisiologia de las plantas superiores",3,0,"");
        InsertarMaterias(BD,12,"Microbiologa Agricola y Ambiental",3,0,"");
        InsertarMaterias(BD,12,"Bases Biologicas de la produccion animal",3,0,"");
        InsertarMaterias(BD,12,"Topografia Agricola",3,0,"");
        InsertarMaterias(BD,12,"TALLER DE PRACTICA I",3,0,"");
        InsertarMaterias(BD,12,"Produccion Vegetal",3,0,"");
        InsertarMaterias(BD,12,"Ecolog??a",3,0,"");
        InsertarMaterias(BD,12,"Nutricion y Alimentacion Animal",3,0,"");
        InsertarMaterias(BD,12,"Economia Politica",3,0,"");
        InsertarMaterias(BD,12,"Mejoramiento Genetico Animal",3,0,"");
        InsertarMaterias(BD,12,"Produccion y Utilizacion de Forrajes",4,0,"");
        InsertarMaterias(BD,12,"Produccion de Carne Bovina",4,0,"");
        InsertarMaterias(BD,12,"Economia Agricola",4,0,"");
        InsertarMaterias(BD,12,"Malezas",4,0,"");
        InsertarMaterias(BD,12,"Teledeteccion y Sistemas de informacion Geograficas",4,0,"");
        InsertarMaterias(BD,12,"TALLER DE PRACTICA II",4,0,"");
        InsertarMaterias(BD,12,"FITOPATOLOGIA",4,0,"");
        InsertarMaterias(BD,12,"ZOOLOGIA AGRICOLA",4,0,"");
        InsertarMaterias(BD,12,"FERTILIDAD DE SUELOS Y FERTILIZACION",4,0,"");
        InsertarMaterias(BD,12,"MAQUINAS AGRICOLAS",4,0,"");
        InsertarMaterias(BD,12,"TALLER DE TRABAJO FINAL",4,0,"");
        InsertarMaterias(BD,12,"SOCIOLOGIA Y EXTENCSION AGRARIAS",5,0,"");
        InsertarMaterias(BD,12,"PRODUCCION LECHERA",5,0,"");
        InsertarMaterias(BD,12,"MODELOS ESTADISTICOS",5,0,"");
        InsertarMaterias(BD,12,"PROTECCION VEGETAL",5,0,"");
        InsertarMaterias(BD,12,"DERECHOS HUMANOS",5,0,"");
        InsertarMaterias(BD,12,"PRODUCCION DE GRANOS",5,0,"");
        InsertarMaterias(BD,12,"CONSERVACION Y PLANIFICACION DEL USO DE LA TIERRA",5,0,"");
        InsertarMaterias(BD,12,"HORTICULTURA",5,0,"");
        InsertarMaterias(BD,12,"SISTEMA DE RIEGO Y DRENAJE",5,0,"");
        InsertarMaterias(BD,12,"TALLER DE PRACTICA III",5,0,"");
        InsertarMaterias(BD,12,"ADMINISTRACION RURAL",6,0,"");
        InsertarMaterias(BD,12,"PRODUCCION FORESTAL",6,0,"");
        InsertarMaterias(BD,12,"MERCADOS AGROPECUARIOS",6,0,"");
        InsertarMaterias(BD,12,"FRUTICULTURA",6,0,"");
        InsertarMaterias(BD,12,"TRABAJO FINAL",6,0,"");

        InsertarMaterias(BD,13,"An??lisis Matem??tico I",1,0,"");
        InsertarMaterias(BD,13,"Econom??a",1,0,"");
        InsertarMaterias(BD,13,"Sociolog??a",1,0,"");
        InsertarMaterias(BD,13,"Metodolog??a de las Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,13,"??lgebra",1,0,"");
        InsertarMaterias(BD,13,"Historia Econ??mica Social General",1,0,"");
        InsertarMaterias(BD,13,"Estad??stica",2,0,"");
        InsertarMaterias(BD,13,"Introducci??n a los Sistemas Productivos",2,0,"");
        InsertarMaterias(BD,13,"Microeconom??a I",2,0,"");
        InsertarMaterias(BD,13,"Administraci??n General",2,0,"");
        InsertarMaterias(BD,13,"Macroeconom??a I",2,0,"");
        InsertarMaterias(BD,13,"Bases Biol??gicas de los Sistemas Agropecuarios",2,0,"");
        InsertarMaterias(BD,13,"Interpretaci??n Contable y Diagn??stico Financiero",2,0,"");
        InsertarMaterias(BD,13,"Aplicaciones de la Gen??tica a la Bioeconom??a",2,0,"");
        InsertarMaterias(BD,13,"Producci??n Vegetal",3,0,"");
        InsertarMaterias(BD,13,"Geograf??a Econ??mica",3,0,"");
        InsertarMaterias(BD,13,"Macroeconom??a Aplicada",3,0,"");
        InsertarMaterias(BD,13,"Econometr??a",3,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producci??n Animal I",3,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producci??n de Cultivos Extensivos",3,0,"");
        InsertarMaterias(BD,13,"Sociolog??a Agraria",3,0,"");
        InsertarMaterias(BD,13,"Costos Agrarios y An??lisis de Proyectos",3,0,"");
        InsertarMaterias(BD,13,"Ecolog??a de los Ecosistemas",3,0,"");
        InsertarMaterias(BD,13,"Electivas",3,0,"");
        InsertarMaterias(BD,13,"Pol??tica Agraria",4,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producci??n Animal II",4,0,"");
        InsertarMaterias(BD,13,"Econom??a de la Producci??n y Complejos Agroindustriales",4,0,"");
        InsertarMaterias(BD,13,"Econom??a y Pol??tica de los Recursos Naturales y Sustentabilidad",4,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producci??n de Cultivos Intensivos",4,0,"");
        InsertarMaterias(BD,13,"Administraci??n de la Empresa Agropecuaria",4,0,"");
        InsertarMaterias(BD,13,"Comercio Interno y Externo de Productos Agropecuarios",4,0,"");
        InsertarMaterias(BD,13,"Legislaci??n Agraria",4,0,"");
        InsertarMaterias(BD,13,"Introducci??n a los Impuestos Agropecuarios",4,0,"");
        InsertarMaterias(BD,13,"Derechos Humanos",4,0,"");
        InsertarMaterias(BD,13,"Taller de Trabajo Final",4,0,"");
        InsertarMaterias(BD,13,"Trabajo Final",5,0,"");
        InsertarMaterias(BD,13,"Asignaturas Electivas (1)",5,0,"");
        InsertarMaterias(BD,13,"Asignaturas Optativas (1)",5,0,"");

        InsertarMaterias(BD,14,"Matem??tica",1,0,"");
        InsertarMaterias(BD,14,"Qu??mica",1,0,"");
        InsertarMaterias(BD,14,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,14,"Biolog??a",1,0,"");
        InsertarMaterias(BD,14,"F??sica e Introducci??n a la Biof??sica",1,0,"");
        InsertarMaterias(BD,14,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,14,"Biomol??culas",2,0,"");
        InsertarMaterias(BD,14,"F??sica Aplicada",2,0,"");
        InsertarMaterias(BD,14,"Elementos de Estad??stica",2,0,"");
        InsertarMaterias(BD,14,"Econom??a Pol??tica",2,0,"");
        InsertarMaterias(BD,14,"Microbiolog??a General",2,0,"");
        InsertarMaterias(BD,14,"Estad??stica Anal??tica",2,0,"");
        InsertarMaterias(BD,14,"Ingl??s T??cnico",2,0,"");
        InsertarMaterias(BD,14,"Nutrici??n",2,0,"");
        InsertarMaterias(BD,14,"Introducci??n a los Sistemas Productivos",2,0,"");
        InsertarMaterias(BD,14,"Qu??mica de Agroalimentos",3,0,"");
        InsertarMaterias(BD,14,"Microbiolog??a de los Agroalimentos ",3,0,"");
        InsertarMaterias(BD,14,"Sistemas de Producci??n de Granos",3,0,"");
        InsertarMaterias(BD,14,"Sistemas de Producci??n y Postcosecha de Frutos y Hortalizas",3,0,"");
        InsertarMaterias(BD,14,"Biotecnolog??a de los Agroalimentos ",3,0,"");
        InsertarMaterias(BD,14,"Teor??a de las Organizaciones",3,0,"");
        InsertarMaterias(BD,14,"Sistemas de Producci??n Animal",3,0,"");
        InsertarMaterias(BD,14,"Calidad Agroalimentaria",3,0,"");
        InsertarMaterias(BD,14,"Industrializaci??n de los Alimentos de Origen Animal",4,0,"");
        InsertarMaterias(BD,14,"??tica, Legislaci??n y Seguridad Agroalimentaria",4,0,"");
        InsertarMaterias(BD,14,"Comercializaci??n y Mercados de Agroalimentos",4,0,"");
        InsertarMaterias(BD,14,"Gesti??n y Planificaci??n de la Empresa Agroalimentaria",4,0,"");
        InsertarMaterias(BD,14,"Comercializaci??n y Mercados de Agroalimentos",4,0,"");
        InsertarMaterias(BD,14,"Gesti??n y Planificaci??n de la Empresa Agroalimentaria",4,0,"");
        InsertarMaterias(BD,14,"Industrializaci??n de los Alimentos de Origen Vegetal",4,0,"");
        InsertarMaterias(BD,14,"Formulaci??n y Evaluaci??n de Proyectos Agroalimentarios",4,0,"");
        InsertarMaterias(BD,14,"Sociolog??a de las Organizaciones Agroalimentarias",4,0,"");
        InsertarMaterias(BD,14,"Gesti??n de las Cadenas Agroalimentarias",4,0,"");
        InsertarMaterias(BD,14,"Taller de Integraci??n de la Pr??ctica Profesional",5,0,"");
        InsertarMaterias(BD,14,"Pr??ctica Profesional",5,0,"");
        InsertarMaterias(BD,14,"Asignaturas Optativas",5,0,"");

        InsertarMaterias(BD,15,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,15,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,15,"Introducci??n al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,15,"Introducci??n al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,15,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,15,"Matem??tica",1,0,"");
        InsertarMaterias(BD,15,"Biolog??a",1,0,"");
        InsertarMaterias(BD,15,"Planificaci??n y Dise??o del Paisaje I",2,0,"");
        InsertarMaterias(BD,15,"Vegetaci??n I",2,0,"");
        InsertarMaterias(BD,15,"Qu??mica General",2,0,"");
        InsertarMaterias(BD,15,"Qu??mica Biol??gica",2,0,"");
        InsertarMaterias(BD,15,"Bot??nica General",2,0,"");
        InsertarMaterias(BD,15,"Bot??nica Sistem??tica",2,0,"");
        InsertarMaterias(BD,15,"F??sica Aplicada",2,0,"");
        InsertarMaterias(BD,15,"Materiales, Elementos y Procedimientos de Construcci??n",2,0,"");
        InsertarMaterias(BD,15,"Sistemas de Representaci??n Geom??trica",2,0,"");
        InsertarMaterias(BD,15,"Planificaci??n y Dise??o del Paisaje II",3,0,"");
        InsertarMaterias(BD,15,"Vegetaci??n II",3,0,"");
        InsertarMaterias(BD,15,"Fisiolog??a Vegetal",3,0,"");
        InsertarMaterias(BD,15,"Edafolog??a",3,0,"");
        InsertarMaterias(BD,15,"Climatolog??a y Fenolog??a",3,0,"");
        InsertarMaterias(BD,15,"Morfolog??a y Comunicaci??n I",3,0,"");
        InsertarMaterias(BD,15,"Instalaciones y Equipamientos",3,0,"");
        InsertarMaterias(BD,15,"Topograf??a",3,0,"");
        InsertarMaterias(BD,15,"Planificaci??n y Dise??o del Paisaje III",4,0,"");
        InsertarMaterias(BD,15,"Morfolog??a y Comunicaci??n II",4,0,"");
        InsertarMaterias(BD,15,"Historia de la Arquitectura Paisaj??stica I",4,0,"");
        InsertarMaterias(BD,15,"Geograf??a",4,0,"");
        InsertarMaterias(BD,15,"Ecolog??a",4,0,"");
        InsertarMaterias(BD,15,"Fitogeograf??a",4,0,"");
        InsertarMaterias(BD,15,"Manejo del Suelo y la Vegetaci??n",4,0,"");
        InsertarMaterias(BD,15,"Electiva",4,0,"");
        InsertarMaterias(BD,15,"Planificaci??n y Dise??o del Paisaje IV",5,0,"");
        InsertarMaterias(BD,15,"Trabajo Final",5,0,"");
        InsertarMaterias(BD,15,"Historia de la Arquitectura Paisaj??stica II",5,0,"");
        InsertarMaterias(BD,15,"Planeamiento Urbano y Regional",5,0,"");
        InsertarMaterias(BD,15,"Ecolog??a del Paisaje",5,0,"");
        InsertarMaterias(BD,15,"Electivas",5,0,"");
        InsertarMaterias(BD,15,"Pr??ctica Profesional",5,0,"");

        InsertarMaterias(BD,16,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,16,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,16,"Introducci??n al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,16,"Introducci??n al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,16,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,16,"Matem??tica",1,0,"");
        InsertarMaterias(BD,16,"Filosof??a",1,0,"");
        InsertarMaterias(BD,16,"Arquitectura I",2,0,"");
        InsertarMaterias(BD,16,"Introducci??n a la Arquitectura Contempor??nea",2,0,"");
        InsertarMaterias(BD,16,"Sistemas de Representaci??n Geom??trica",2,0,"");
        InsertarMaterias(BD,16,"Introducci??n a los Tipos Constructivos",2,0,"");
        InsertarMaterias(BD,16,"Introducci??n a los Tipos Estructurales",2,0,"");
        InsertarMaterias(BD,16,"F??sica Aplicada a la Arquitectura",2,0,"");
        InsertarMaterias(BD,16,"Matem??tica II",2,0,"");
        InsertarMaterias(BD,16,"Arquitectura II",3,0,"");
        InsertarMaterias(BD,16,"Representaci??n Arquitect??nica",3,0,"");
        InsertarMaterias(BD,16,"Historia I",3,0,"");
        InsertarMaterias(BD,16,"Morfolog??a I",3,0,"");
        InsertarMaterias(BD,16,"Construcciones I",3,0,"");
        InsertarMaterias(BD,16,"Estructuras I",3,0,"");
        InsertarMaterias(BD,16,"Instalaciones I",3,0,"");
        InsertarMaterias(BD,16,"Arquitectura III",4,0,"");
        InsertarMaterias(BD,16,"Materializaci??n de Proyectos",4,0,"");
        InsertarMaterias(BD,16,"Historia II",4,0,"");
        InsertarMaterias(BD,16,"Morfolog??a II",4,0,"");
        InsertarMaterias(BD,16,"Construcciones II",4,0,"");
        InsertarMaterias(BD,16,"Estructuras II",4,0,"");
        InsertarMaterias(BD,16,"Instalaciones II",4,0,"");
        InsertarMaterias(BD,16,"Arquitectura IV",5,0,"");
        InsertarMaterias(BD,16,"Teor??a de la Arquitectura",5,0,"");
        InsertarMaterias(BD,16,"Historia III",5,0,"");
        InsertarMaterias(BD,16,"Construcciones III",5,0,"");
        InsertarMaterias(BD,16,"Estructuras III",5,0,"");
        InsertarMaterias(BD,16,"Instalaciones III",5,0,"");
        InsertarMaterias(BD,16,"Planificaci??n Urbana",5,0,"");
        InsertarMaterias(BD,16,"Proyecto Urbano",6,0,"");
        InsertarMaterias(BD,16,"Proyecto Arquitect??nico",6,0,"");
        InsertarMaterias(BD,16,"Direcci??n y Legislaci??n de Obra",6,0,"");
        InsertarMaterias(BD,16,"Materias Optativas",6,0,"");

        InsertarMaterias(BD,17,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,17,"Introducci??n al conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,17,"Introducci??n al Conocimiento Proyectual 1",1,0,"");
        InsertarMaterias(BD,17,"Introducci??n al Conocimiento Proyectual 2",1,0,"");
        InsertarMaterias(BD,17,"Matem??tica",1,0,"");
        InsertarMaterias(BD,17,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,17,"Semiolog??a",1,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 1",2,0,"");
        InsertarMaterias(BD,17,"Narrativas Audiovisuales",2,0,"");
        InsertarMaterias(BD,17,"Escrituras Audiovisuales 1",2,0,"");
        InsertarMaterias(BD,17,"Dibujo y Representaci??n Audiovisual",2,0,"");
        InsertarMaterias(BD,17,"Montaje 1",2,0,"");
        InsertarMaterias(BD,17,"Iluminaci??n y C??mara 1",2,0,"");
        InsertarMaterias(BD,17,"Sonido 1",2,0,"");
        InsertarMaterias(BD,17,"Est??tica",2,0,"");
        InsertarMaterias(BD,17,"Sociolog??a",2,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 2",3,0,"");
        InsertarMaterias(BD,17,"Producci??n y Planificaci??n",3,0,"");
        InsertarMaterias(BD,17,"Escrituras Audiovisuales 2",3,0,"");
        InsertarMaterias(BD,17,"T??cnicas Audiovisuales",3,0,"");
        InsertarMaterias(BD,17,"Iluminaci??n y C??mara 2",3,0,"");
        InsertarMaterias(BD,17,"Sonido 2",3,0,"");
        InsertarMaterias(BD,17,"Historia Anal??tica de los Medios Audiovisuales",3,0,"");
        InsertarMaterias(BD,17,"Teor??as Audiovisuales",3,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 3",4,0,"");
        InsertarMaterias(BD,17,"Difusi??n y Comercializaci??n de los Medios",4,0,"");
        InsertarMaterias(BD,17,"Montaje 2",4,0,"");
        InsertarMaterias(BD,17,"Historia Anal??tica de los Medios Audiovisuales 2",4,0,"");
        InsertarMaterias(BD,17,"Teor??a y Est??tica de los Medios",4,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 4",5,0,"");

        InsertarMaterias(BD,18,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,18,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,18,"Introducci??n al Conocimiento Proyectual 1",1,0,"");
        InsertarMaterias(BD,18,"Introducci??n al Conocimiento Proyectual 2",1,0,"");
        InsertarMaterias(BD,18,"Matem??tica",1,0,"");
        InsertarMaterias(BD,18,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,18,"Semiolog??a",1,0,"");
        InsertarMaterias(BD,18,"Fundamentos Geom??tricos del Modelaje",2,0,"");
        InsertarMaterias(BD,18,"Introducci??n al Proyecto",2,0,"");
        InsertarMaterias(BD,18,"Medios Expresivos 1",2,0,"");
        InsertarMaterias(BD,18,"T??cnicas de Producci??n e Industria Nacional 1",2,0,"");
        InsertarMaterias(BD,18,"Historia del Dise??o de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,18,"An??lisis del Dise??o de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,18,"T??cnicas de Producci??n e Industria Nacional 2",2,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 1",3,0,"");
        InsertarMaterias(BD,18,"Materializaci??n de Proyecto",3,0,"");
        InsertarMaterias(BD,18,"Medios Expresivos 2",3,0,"");
        InsertarMaterias(BD,18,"T??cnicas de Producci??n Indumentaria 1",3,0,"");
        InsertarMaterias(BD,18,"Historia del Dise??o de Indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,18,"An??lisis del Dise??o de indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,18,"T??cnicas de Producci??n Indumentaria 2",3,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 2",4,0,"");
        InsertarMaterias(BD,18,"Sociolog??a",4,0,"");
        InsertarMaterias(BD,18,"Comercializaci??n y Mercado 1",4,0,"");
        InsertarMaterias(BD,18,"T??cnicas de Producci??n Indumentaria 3",4,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 3",4,0,"");
        InsertarMaterias(BD,18,"Proyecto de Accesorios 1",4,0,"");
        InsertarMaterias(BD,18,"T??cnicas de Producci??n Indumentaria 4",4,0,"");
        InsertarMaterias(BD,18,"Comunicaci??n y Cr??tica",4,0,"");
        InsertarMaterias(BD,18,"Comercializaci??n y Mercado 2",4,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 4",5,0,"");
        InsertarMaterias(BD,18,"??tica Profesional",5,0,"");
        InsertarMaterias(BD,18,"Proyecto de Accesorios 2",5,0,"");
        InsertarMaterias(BD,18,"Optativa 1",5,0,"");
        InsertarMaterias(BD,18,"Optativa 2",5,0,"");
        InsertarMaterias(BD,18,"Trabajo Final de Carrera",5,0,"");
        InsertarMaterias(BD,18,"Pr??ctica Profesional Asistida",5,0,"");


        InsertarMaterias(BD,19,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,19,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,19,"Introducci??n al Conocimiento Proyectual 1",1,0,"");
        InsertarMaterias(BD,19,"Introducci??n al Conocimiento Proyectual 2",1,0,"");
        InsertarMaterias(BD,19,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,19,"Matem??tica",1,0,"");
        InsertarMaterias(BD,19,"Semiolog??a",1,0,"");
        InsertarMaterias(BD,19,"Dise??o Gr??fico 1",2,0,"");
        InsertarMaterias(BD,19,"Morfolog??a 1",2,0,"");
        InsertarMaterias(BD,19,"Tipograf??a 1",2,0,"");
        InsertarMaterias(BD,19,"Historia 1",2,0,"");
        InsertarMaterias(BD,19,"Comunicaci??n 1",2,0,"");
        InsertarMaterias(BD,19,"Tecnolog??a 1",2,0,"");
        InsertarMaterias(BD,19,"Electiva: Fotograf??a o Ilustraci??n",2,0,"");
        InsertarMaterias(BD,19,"Dise??o Gr??fico 2",3,0,"");
        InsertarMaterias(BD,19,"Morfolog??a 2",3,0,"");
        InsertarMaterias(BD,19,"Historia 2",3,0,"");
        InsertarMaterias(BD,19,"Comunicaci??n 2",3,0,"");
        InsertarMaterias(BD,19,"Medios Expresivos 1",3,0,"");
        InsertarMaterias(BD,19,"Tecnolog??a 2",3,0,"");
        InsertarMaterias(BD,19,"Dise??o Gr??fico 3",4,0,"");
        InsertarMaterias(BD,19,"Tipograf??a 2 *",4,0,"");
        InsertarMaterias(BD,19,"Legislaci??n y Pr??ctica Profesional",4,0,"");
        InsertarMaterias(BD,19,"Electiva Socio Human??stica",4,0,"");
        InsertarMaterias(BD,19,"Medios Expresivos 2",4,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",4,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",4,0,"");
        InsertarMaterias(BD,19,"Dise??o Gr??fico 4",5,0,"");
        InsertarMaterias(BD,19,"Electiva Orientada 1",5,0,"");
        InsertarMaterias(BD,19,"Electiva Orientada 2",5,0,"");
        InsertarMaterias(BD,19,"Materia Optativa 1",5,0,"");
        InsertarMaterias(BD,19,"Materia Optativa 2",5,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",5,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",5,0,"");

        InsertarMaterias(BD,20,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,20,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,20,"Introducci??n al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,20,"Introducci??n al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,20,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,20,"Matem??tica",1,0,"");
        InsertarMaterias(BD,20,"Semiolog??a",1,0,"");
        InsertarMaterias(BD,20,"Taller de Dise??o Industrial 1",2,0,"");
        InsertarMaterias(BD,20,"Tecnolog??a 1",2,0,"");
        InsertarMaterias(BD,20,"Morfolog??a 1",2,0,"");
        InsertarMaterias(BD,20,"Matem??tica",2,0,"");
        InsertarMaterias(BD,20,"F??sica 1",2,0,"");
        InsertarMaterias(BD,20,"An??lisis de Productos",2,0,"");
        InsertarMaterias(BD,20,"Taller de Dise??o Industrial 2",3,0,"");
        InsertarMaterias(BD,20,"Tecnolog??a 2",3,0,"");
        InsertarMaterias(BD,20,"Morfolog??a 2",3,0,"");
        InsertarMaterias(BD,20,"Ergonom??a y factores humanos",3,0,"");
        InsertarMaterias(BD,20,"Historia del Dise??o Industrial 1",3,0,"");
        InsertarMaterias(BD,20,"Taller de Dise??o Industrial 3",4,0,"");
        InsertarMaterias(BD,20,"Tecnolog??a 3",4,0,"");
        InsertarMaterias(BD,20,"Morfolog??a 3",4,0,"");
        InsertarMaterias(BD,20,"Historia del Dise??o Industrial 2",4,0,"");
        InsertarMaterias(BD,20,"F??sica 2",4,0,"");
        InsertarMaterias(BD,20,"Taller de Dise??o Industrial 4",5,0,"");
        InsertarMaterias(BD,20,"Tecnolog??a 4",5,0,"");
        InsertarMaterias(BD,20,"Sociolog??a aplicada al Dise??o",5,0,"");
        InsertarMaterias(BD,20,"Gesti??n industrial",5,0,"");
        InsertarMaterias(BD,20,"Proyecto Final",6,0,"");
        InsertarMaterias(BD,20,"Gesti??n de proyectos",6,0,"");
        InsertarMaterias(BD,20,"Metodolog??a del pensamiento proyectual",6,0,"");
        InsertarMaterias(BD,20,"Legislaci??n y Pr??ctica Profesional",6,0,"");
        InsertarMaterias(BD,20,"Materia/s optativas y electivas",6,0,"");

        InsertarMaterias(BD,21,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,21,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,21,"Introducci??n al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,21,"Introducci??n al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,21,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,21,"Matem??tica",1,0,"");
        InsertarMaterias(BD,21,"Semiolog??a",1,0,"");
        InsertarMaterias(BD,21,"Fundamentos Geom??tricos del Textil",2,0,"");
        InsertarMaterias(BD,21,"Introducci??n al Proyecto",2,0,"");
        InsertarMaterias(BD,21,"Medios Expresivos 1",2,0,"");
        InsertarMaterias(BD,21,"T??cnicas de Producci??n e Industria Nacional 1",2,0,"");
        InsertarMaterias(BD,21,"Historia del Dise??o de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,21,"An??lisis del Dise??o de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,21,"T??cnicas de Producci??n e Industria Nacional 2",2,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 1",3,0,"");
        InsertarMaterias(BD,21,"Proceso de Te??ido, Estampado y Terminaci??n",3,0,"");
        InsertarMaterias(BD,21,"Medios Expresivos 2",3,0,"");
        InsertarMaterias(BD,21,"T??cnicas de Producci??n Textil 1",3,0,"");
        InsertarMaterias(BD,21,"Historia del Dise??o de Indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,21,"An??lisis del Dise??o de Indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,21,"T??cnicas de Producci??n Textil 2",3,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 2",4,0,"");
        InsertarMaterias(BD,21,"Sociolog??a",4,0,"");
        InsertarMaterias(BD,21,"Historia del Textil",4,0,"");
        InsertarMaterias(BD,21,"Comercializaci??n y Mercado 1",4,0,"");
        InsertarMaterias(BD,21,"T??cnicas de Producci??n Textil 3",4,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 3",4,0,"");
        InsertarMaterias(BD,21,"T??cnicas de Producci??n Textil 4",4,0,"");
        InsertarMaterias(BD,21,"Comunicaci??n y Cr??tica",4,0,"");
        InsertarMaterias(BD,21,"Comercializaci??n y Mercado 2",4,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 4",5,0,"");
        InsertarMaterias(BD,21,"??tica Profesional",5,0,"");
        InsertarMaterias(BD,21,"Proyecto de Accesorios 1",5,0,"");
        InsertarMaterias(BD,21,"Optativa 1",5,0,"");
        InsertarMaterias(BD,21,"Optativa 2",5,0,"");
        InsertarMaterias(BD,21,"Trabajo Final de la Carrera",5,0,"");
        InsertarMaterias(BD,21,"Pr??ctica Profesional Asistida",5,0,"");

        InsertarMaterias(BD,22,"Introducci??n al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,22,"Introducci??n al Pensamiento Cient??fico",1,0,"");
        InsertarMaterias(BD,22,"Introducci??n al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,22,"Introducci??n al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,22,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,22,"Matem??tica",1,0,"");
        InsertarMaterias(BD,22,"Biolog??a",1,0,"");
        InsertarMaterias(BD,22,"Planificaci??n y Dise??o del Paisaje I",2,0,"");
        InsertarMaterias(BD,22,"Vegetaci??n I",2,0,"");
        InsertarMaterias(BD,22,"Qu??mica General",2,0,"");
        InsertarMaterias(BD,22,"Qu??mica Biol??gica",2,0,"");
        InsertarMaterias(BD,22,"Bot??nica General",2,0,"");
        InsertarMaterias(BD,22,"Bot??nica Sistem??tica",2,0,"");
        InsertarMaterias(BD,22,"F??sica Aplicada",2,0,"");
        InsertarMaterias(BD,22,"Materiales, Elementos y Procedimientos de Construcci??n",2,0,"");
        InsertarMaterias(BD,22,"Sistemas de Representaci??n Geom??trica",2,0,"");
        InsertarMaterias(BD,22,"Planificaci??n y Dise??o del Paisaje II",3,0,"");
        InsertarMaterias(BD,22,"Vegetaci??n II",3,0,"");
        InsertarMaterias(BD,22,"Fisiolog??a Vegetal",3,0,"");
        InsertarMaterias(BD,22,"Edafolog??a",3,0,"");
        InsertarMaterias(BD,22,"Climatolog??a y Fenolog??a",3,0,"");
        InsertarMaterias(BD,22,"Morfolog??a y Comunicaci??n I",3,0,"");
        InsertarMaterias(BD,22,"Instalaciones y Equipamientos",3,0,"");
        InsertarMaterias(BD,22,"Topograf??a",3,0,"");
        InsertarMaterias(BD,22,"Planificaci??n y Dise??o del Paisaje III",4,0,"");
        InsertarMaterias(BD,22,"Morfolog??a y Comunicaci??n II",4,0,"");
        InsertarMaterias(BD,22,"Historia de la Arquitectura Paisaj??stica I",4,0,"");
        InsertarMaterias(BD,22,"Geograf??a",4,0,"");
        InsertarMaterias(BD,22,"Ecolog??a",4,0,"");
        InsertarMaterias(BD,22,"Fitogeograf??a",4,0,"");
        InsertarMaterias(BD,22,"Manejo del Suelo y la Vegetaci??n",4,0,"");
        InsertarMaterias(BD,22,"Electiva",4,0,"");
        InsertarMaterias(BD,22,"Planificaci??n y Dise??o del Paisaje IV",5,0,"");
        InsertarMaterias(BD,22,"Trabajo Final",5,0,"");
        InsertarMaterias(BD,22,"Historia de la Arquitectura Paisaj??stica II",5,0,"");
        InsertarMaterias(BD,22,"Planeamiento Urbano y Regional",5,0,"");
        InsertarMaterias(BD,22,"Ecolog??a del Paisaje",5,0,"");
        InsertarMaterias(BD,22,"Electivas",5,0,"");
        InsertarMaterias(BD,22,"Pr??ctica Profesional",5,0,"");

        InsertarMaterias(BD,23,"An??lisis Matem??tico I",1,0,"");
        InsertarMaterias(BD,23,"Econom??a",1,0,"");
        InsertarMaterias(BD,23,"Sociolog??a",1,0,"");
        InsertarMaterias(BD,23,"Metodolog??a de las Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,23,"??lgebra",1,0,"");
        InsertarMaterias(BD,23,"Historia Econ??mica y Social General",1,0,"");
        InsertarMaterias(BD,23,"Teor??a Contable",2,0,"");
        InsertarMaterias(BD,23,"Estad??stica I",2,0,"");
        InsertarMaterias(BD,23,"Historia Econ??mica y Social Argentina",2,0,"");
        InsertarMaterias(BD,23,"Microeconom??a",2,0,"");
        InsertarMaterias(BD,23,"Instituciones de Derecho P??blico",2,0,"");
        InsertarMaterias(BD,23,"Administraci??n General",2,0,"");
        InsertarMaterias(BD,23,"Instituciones de Derecho Privado",3,0,"");
        InsertarMaterias(BD,23,"Sistemas Administrativos",3,0,"");
        InsertarMaterias(BD,23,"Tecnolog??a de la Informaci??n",3,0,"");
        InsertarMaterias(BD,23,"C??lculo Financiero",3,0,"");
        InsertarMaterias(BD,23,"Gesti??n y Costos",3,0,"");
        InsertarMaterias(BD,23,"Macroeconom??a y Pol??tica Econ??mica",3,0,"");
        InsertarMaterias(BD,23,"Administraci??n Financiera",3,0,"");
        InsertarMaterias(BD,23,"An??lisis Matem??tico II",3,0,"");
        InsertarMaterias(BD,23,"Estad??stica II",3,0,"");
        InsertarMaterias(BD,23,"Matem??tica para Economistas",3,0,"");
        InsertarMaterias(BD,23,"Dinero, Cr??dito y Bancos",3,0,"");
        InsertarMaterias(BD,23,"Estad??stica Actuarial",3,0,"");
        InsertarMaterias(BD,23,"An??lisis Num??rico",3,0,"");
        InsertarMaterias(BD,23,"Biometr??a Actuarial",3,0,"");
        InsertarMaterias(BD,23,"Teor??a Actuarial de los Seguros Personales",3,0,"");
        InsertarMaterias(BD,23,"Teor??a Actuarial de los Seguros Patrimoniales",3,0,"");
        InsertarMaterias(BD,23,"Teor??a de los Fondos y Planes de Jubilaciones, Pensiones y Salud",3,0,"");
        InsertarMaterias(BD,23,"Teor??a del Equilibrio Actuarial",3,0,"");
        InsertarMaterias(BD,23,"Bases Actuariales de las Inversiones y Financiaciones",3,0,"");
        InsertarMaterias(BD,23,"Electivas u Optativas",3,0,"");
        InsertarMaterias(BD,23,"Seminario de Integraci??n y Aplicaci??n (Trabajo Final)",3,0,"");

        InsertarMaterias(BD,24,"An??lisis Matem??tico I",1,0,"");
        InsertarMaterias(BD,24,"Econom??a",1,0,"");
        InsertarMaterias(BD,24,"Sociolog??a",1,0,"");
        InsertarMaterias(BD,24,"Metodolog??a de las Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,24,"??lgebra",1,0,"");
        InsertarMaterias(BD,24,"Historia Econ??mica y Social General",1,0,"");
        InsertarMaterias(BD,24,"Teor??a Contable",2,0,"");
        InsertarMaterias(BD,24,"Estad??stica I",2,0,"");
        InsertarMaterias(BD,24,"Historia Econ??mica y Social Argentina",2,0,"");
        InsertarMaterias(BD,24,"Microeconom??a I",2,0,"");
        InsertarMaterias(BD,24,"Instituciones de Derecho P??blico",2,0,"");
        InsertarMaterias(BD,24,"Administraci??n General",2,0,"");
        InsertarMaterias(BD,24,"Instituciones de Derecho Privado",3,0,"");
        InsertarMaterias(BD,24,"Sistemas Administrativos",3,0,"");
        InsertarMaterias(BD,24,"Tecnolog??a de la Informaci??n",3,0,"");
        InsertarMaterias(BD,24,"C??lculo Financiero",3,0,"");
        InsertarMaterias(BD,24,"Macroeconom??a y Pol??tica Econ??mica",3,0,"");
        InsertarMaterias(BD,24,"Administraci??n Financiera",3,0,"");
        InsertarMaterias(BD,24,"Gesti??n y Costos para Contadores",3,0,"");
        InsertarMaterias(BD,24,"Sistemas Contables",3,0,"");
        InsertarMaterias(BD,24,"Contabilidad Patrimonial",3,0,"");
        InsertarMaterias(BD,24,"Sistemas de Costos",3,0,"");
        InsertarMaterias(BD,24,"Derecho del Trabajo y de la Seguridad Social",3,0,"");
        InsertarMaterias(BD,24,"Auditor??a",3,0,"");
        InsertarMaterias(BD,24,"Teor??a y T??cnica Impositiva I",3,0,"");
        InsertarMaterias(BD,24,"Teor??a y T??cnica Impositiva II",3,0,"");
        InsertarMaterias(BD,24,"Derecho Econ??mico I",3,0,"");
        InsertarMaterias(BD,24,"Derecho Econ??mico II",3,0,"");
        InsertarMaterias(BD,24,"Actuaci??n Profesional Judicial",3,0,"");
        InsertarMaterias(BD,24,"Electivas u Optativas",3,0,"");
        InsertarMaterias(BD,24,"Seminario de Integraci??n y Aplicaci??n (Trabajo Final)",3,0,"");

   }
    private void InsertarMaterias(SQLiteDatabase BD,int Id_carrera,String NombreMateria,int Anio,int opcionalidad,String Descripcion_materis)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("ID_Carrera",Id_carrera);
        NuevoRegistro.put("Nombre_Materia",NombreMateria.toUpperCase());
        NuevoRegistro.put("Anio",Anio);
        NuevoRegistro.put("Opcionalidad",opcionalidad);
        NuevoRegistro.put("Descripcion_Materia",Descripcion_materis);
        BD.insert("Materias",null,NuevoRegistro);
    }
    private void InsertarCarrera(SQLiteDatabase BD,String Carreras,String Facultad,String LinkLogo,String Descripcion)
    {
        ContentValues NuevoRegistro=new ContentValues();
        NuevoRegistro.put("Nombre_Carrera",Carreras.toUpperCase());
        NuevoRegistro.put("Nombre_Facultad",Facultad.toUpperCase());
        NuevoRegistro.put("LinkImagen",LinkLogo);
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
