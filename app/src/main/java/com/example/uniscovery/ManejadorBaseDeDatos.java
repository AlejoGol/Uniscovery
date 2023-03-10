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
        InsertarCarrera(BaseDeDatos, "CARRERA DE ABOGACÍA", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "En un mundo cambiante, nuestra educación jurídica interdisciplinaria e internacional te prepara para enfrentar y liderar las respuestas a los grandes desafíos profesionales que acarrean la globalización, la integración regional, las nuevas tecnologías y la necesidad de interactuar con profesionales de otras áreas, desde economistas y contadores hasta politólogos o diplomáticos.");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ADMINISTRACION DE EMPRESAS", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El LicRenciado en Administración de Empresas es un profesional con ánimo emprendedor, formado para la gestión y dirección de los recursos de la empresa desde una perspectiva integral.\n" + "\n" + "Su formación le permite interactuar con todos los estamentos de la organización, con vistas a facilitar la coordinación para lograr objetivos comunes.");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ECONOMÍA EMPRESARIAL", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El Licenciado en Economía Empresarial comparte con el Licenciado en Economía, una vocación por el rigor analítico y el detalle metodológico que soportan las herramientas de decisión más modernas utilizadas en las organizaciones.\n" + "\n" + "Comparte también con el Licenciado en Administración una naturaleza emprendedora y de gestión empresarial que le permite dirigir los recursos de la empresa desde una perspectiva integral.\n" + "\n");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ECONOMÍA", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "Formula diagnósticos de situaciones, escenarios micro y macroeconómicos e implementa políticas en el ámbito público o privado. Se inserta laboralmente en empresas, instituciones financieras, consultoras, ONG, organismos nacionales e internacionales, así como en instituciones de investigación y docencia de la Argentina y del exterior.");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN DISEÑO", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", " Su inserción profesional le permite proponer ideas, acciones y métodos del diseño para los ámbitos de la tecnología, los negocios y la cultura, en el marco de equipos de trabajo multiprofesionales, para detectar oportunidades de solución e innovación. La formación humanística y ética, proyectual y técnica, le permitirá al graduado actuar con idoneidad y eficiencia como profesional del Diseño en relación con imágenes, objetos, mensajes, información, medios tecnológicos y emprendimientos institucionales y empresariales.\n" + "\n");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN ESTUDIOS INTERNACIONALES", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El licenciado en Estudios Internacionales, desde su formación multidisciplinaria, aporta al análisis y comprensión de un mundo globalizado, interdependiente y pluricultural interpretando las dimensiones políticas, económicas, jurídicas, militares, ambientales y sociales de un entorno en proceso de permanente transformación. Maneja técnicas, metodologías y herramientas que le permiten una aproximación profunda y rigurosa a los complejos fenómenos internacionales");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN CIENCIAS POLÍTICA Y GOBIERNO", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "El licenciado en Ciencia Política y Gobierno cuenta con valiosas herramientas teóricas, empíricas y metodológicas que le permiten abordar y evaluar fenómenos complejos de la vida política y la gestión de instituciones de gobierno. Posee una mirada analítica y sistemática para comprender y accionar frente a esos fenómenos, tanto en el caso argentino como en términos comparados. Propone alternativas innovadoras al nutrirse de otras áreas de conocimiento como la Economía, la Historia, las Políticas Públicas y el Derecho");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN CIENCIAS SOCIALES", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "La formación del licenciado en Ciencias Sociales de la Universidad Torcuato Di Tella le permite comprender y analizar fenómenos sociales; generar contenidos, estrategias y acciones de impacto en la sociedad; formular, desarrollar e implementar estrategias empresariales y programas de políticas públicas y evaluar su influencia en los grupos destinatarios; y comunicar contenidos por diversas vías (informes, presentaciones, artículos periodísticos, material 2.0).");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN HISTORIA", "DI TELLA", "https://www.utdt.edu/Images/prensa/utdt-color-alta-cmyk.jpg", "La carrera de Historia ofrece al graduado una amplia gama de posibilidades laborales y de desarrollo profesional. Para aquellos interesados en una carrera académica, la Licenciatura les provee las herramientas necesarias para iniciarse en la investigación. Al mismo tiempo, la carrera suministra al graduado un conjunto de saberes y habilidades cuya utilidad no es exclusiva del trabajo del historiador profesional. ");
        InsertarCarrera(BaseDeDatos, "LICENCIATURA EN CIENCIAS AMBIENTALES", "UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg", "El ejercicio de identificar las actividades que puede llevar a cabo un profesional\n" +
                "en las ciencias ambientales nos fuerza a definir un conjunto cuyos límites son difusos,\n" +
                "que se superpone a las de actividades de profesionales de otras áreas y que, de hecho,\n" +
                "incluye tareas que ni siquiera imaginamos en el presente. ");
        InsertarCarrera(BaseDeDatos, "AGRONOMIA", "UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg", "El Ingeniero Agrónomo es aquel graduado universitario con una sólida formación científica y tecnológica que le permite intervenir en las cadenas productivas de base agropecuaria, en el ambiente y en la preservación de los recursos naturales desde una visión integral y sustentable, dentro de un contexto socioeconómico con diversos niveles de innovación e incertidumbre, con el fin de promover el desarrollo nacional y el del sector agropecuario.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA Y ADMINISTRACIÓN AGRARIAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","La Facultad se propone formar un Licenciado en Economía y Administración Agrarias con conocimientos, habilidades y actitudes para: \nComprender el contexto económico y social en el que se insertan la producción agropecuaria, asumiendo la sustentabilidad como eje de toda intervención y el enfoque sistémico como visión necesaria.\nManejar herramientas conceptuales y técnicas necesarias para intervenir en los distintos ámbitos en los que interaccionan los agentes económicos que intervienen en la producción agropecuaria. \nParticipar en el diseño e implementación de investigaciones científicas en el campo disciplinar de la economía y administración agrarias. ");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTIÓN DE AGROALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg"," Los principios rectores de calidad, ética social y cuidado del medio ambiente recorrerán la carrera como conceptos transversales. Por esta razón, serán los marcos para el desarrollo de los saberes específicos de las diversas disciplinas curriculares.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PLANIFICACIÓN Y DISEÑO DEL PAISAJE","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg"," Capacitar a los alumnos a nivel profesional para planificar y diseñar el paisaje natural, urbano y cultural, integrando el ambiente al entorno construido por el hombre. Formarlos para vincularse con otras disciplinas científicas y sociales y así llevar a cabo el planeamiento y diseño del paisaje. Promover, incentivar, desarrollar y divulgar la formación de una conciencia plena de los problemas del ambiente y el uso que el hombre hace de él");
        InsertarCarrera(BaseDeDatos,"ARQUITECTURA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","La Carrera de Arquitectura tiene como objetivo fundamental formar profesionales aptos para diseñar, programar, dirigir y construir los edificios y espacios necesarios para albergar las actividades del hombre en sociedad, satisfaciendo las necesidades y aspiraciones que ésta demanda.");
        InsertarCarrera(BaseDeDatos,"DISEÑO DE IMAGEN Y SONIDO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Instalar la actividad audiovisual como disciplina proyectual y contribuir a su actualización permanente por medio de la investigación y el conocimiento de todas las fases del proceso de Diseño.");
        InsertarCarrera(BaseDeDatos,"DISEÑO DE INDUMENTARIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Contribuir a través de la formación de profesionales idóneos en la materia a la consolidación de la identidad nacional promoviendo el desarrollo competitivo de la actividad tanto internamente como internacionalmente.");
        InsertarCarrera(BaseDeDatos,"DISEÑO GRÁFICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","La carrera de Diseño Gráfico tiene como objetivo la preparación de profesionales capaces de ejercer la actividad proyectual que posibilita comunicar visualmente información, hechos, ideas y valores útiles al hombre. Esta disciplina implica procesar y expresar en términos de forma, factores sociales, culturales, perceptivos, estéticos, tecnológicos y ambientales.");
        InsertarCarrera(BaseDeDatos,"DISEÑO INDUSTRIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","El objetivo principal es formar profesionales que se ocupen del proyecto de los objetos que rodean al hombre y que serán producidos industrialmente. Los Diseñadores Industriales se insertarán en el medio productivo con alta capacitación en la resolución de productos, sean estos estáticos o dinámicos, en sus aspectos formales, de uso y tecnología. Simultáneamente se loscapacitará para una actitud crítica y reflexiva de su actividad proyectual, a través del estudio del hombre y el contexto en el que actúan.");
        InsertarCarrera(BaseDeDatos,"DISEÑO TEXTIL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Contribuir a través de la formación de profesionales idóneos en la materia a la consolidación de la identidad nacional promoviendo el desarrollo competitivo de la actividad tanto internamente como internacionalmente.");
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PLANIFICACIÓN Y DISEÑO DEL PAISAJE","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","Contribuir a través de la formación de profesionales idóneos en la materia a la consolidación de la identidad nacional promoviendo el desarrollo competitivo de la actividadtanto internamente como internacionalmente.");
        InsertarCarrera(BaseDeDatos,"ACTUARIO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","El Actuario es un profesional de las Ciencias Económicas, especializado en la valuación de operaciones y entidades sujetas a riesgos relacionados con los seguros personales, seguros patrimoniales, la seguridad social, mercados de capitales y sistema financiero, sobre la base del establecimiento de condiciones de equilibrio actuarial integrado en procesos de planificación económico-financiera.");
        InsertarCarrera(BaseDeDatos,"CONTADOR PÚBLICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg","El Contador Público asesora personas y empresas en las áreas financiera, impositiva, contable, laboral, de costos y societaria. Diseña, interpreta e implementa sistemas de información contables, dentro de las organizaciones públicas y privadas, para la toma de decisiones, sobre políticas de inversión, organización de recursos y análisis de los sistemas económicos.");
        /*InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ADMINISTRACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ECONOMÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SISTEMAS DE INFORMACIÓN DE LAS ORGANIZACIONES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOGÍA DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS BIOLÓGICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA ATMÓSFERA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMPUTACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS FÍSICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS GEOLÓGICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS MATEMÁTICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS OCEANOGRÁFICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS QUÍMICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PALEONTOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA ATMÓSFERA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMPUTACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS GEOLÓGICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN FÍSICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN LA ESPECIALIDAD BIOLOGÍA\n","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN MATEMATICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN QUIMICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA POLÍTICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA COMUNICACIÓN SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN RELACIONES DEL TRABAJO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN SOCIOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TRABAJO SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIA POLÍTICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA COMUNICACIÓN SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN RELACIONES DEL TRABAJO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN TRABAJO SOCIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA SECUNDARIA NORMAL Y ESPECIAL EN SOCIOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"CIENCIAS VETERINARIAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GESTIÓN DE AGROALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TÉCNICO PARA BIOTERIOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"ABOGACÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"CALÍGRAFO PÚBLICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO PARA LA ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS JURÍDICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TRADUCTORADO PÚBLICO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"BIOQUÍMICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"FARMACIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIA Y TECNOLOGÍA DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN ÓPTICA Y CONTACTOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TÉCNICO PARA BIOTERIOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TÉCNICO UNIVERSITARIO EN MEDICINA NUCLEAR","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ARTES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN BIBLIOTECOLOGÍA Y CIENCIA DE LA INFORMACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS ANTROPOLÓGICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN CIENCIAS DE LA EDUCACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FILOSOFÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN GEOGRAFÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN HISTORIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN LETRAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN ARTES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN BIBLIOTECOLOGÍA Y CIENCIA DE LA INFORMACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS ANTROPOLÓGICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN CIENCIAS DE LA EDUCACIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN FILOSOFÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN GEOGRAFÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN HISTORIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN LETRAS\n","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"EDICION","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA CIVIL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA DE ALIMENTOS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTRICISTA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA ELECTRÓNICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN AGRIMENSURA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN INFORMÁTICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA EN PETRÓLEO","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA INDUSTRIAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA MECÁNICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA NAVAL Y MECÁNICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"INGENIERÍA QUÍMICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ANÁLISIS DE SISTEMAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMERÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN ENFERMERÍA (CICLO DE COMPLEMENTACIÓN CURRICULAR)","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN FONOAUDIOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN KINESIOLOGÍA Y FISIATRÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN NUTRICIÓN","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN OBSTETRICIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PODOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCIÓN DE BIOIMAGENES","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PRODUCCIÓN DE BIOIMÁGENES (CICLO DE COMPLEMENTACIÓN CURRICULAR)","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"MEDICINA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN ANESTESIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN COSMETOLOGÍA FACIAL Y CORPORAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN HEMOTERAPIA E INMUNOHEMATOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN INSTRUMENTACIÓN QUIRÚRGICA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TECNICATURA UNIVERSITARIA EN PODOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"TÉCNICOS UNIVERSITARIOS EN PRÁCTICAS CARDIOLÓGICAS","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"ODONTOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN MUSICOTERAPIA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN PSICOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"LICENCIATURA EN TERAPIA OCUPACIONAL","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
        InsertarCarrera(BaseDeDatos,"PROFESORADO DE ENSEÑANZA MEDIA Y SUPERIOR EN PSICOLOGÍA","UBA", "https://i0.wp.com/www.alevelplayingfield.group.shef.ac.uk/wp-content/uploads/2018/04/UBA-logo.jpg",);
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
        InsertarCarrera(BaseDeDatos,"Tecnicatura en Interpretación en Danza","UNA")*/
        ;//FALTAN USAL UAI UMSA FUC KENNEDY UdeMM UFLO ISALUD Atlántida Argentina UNDEF ENZEÑANZAS LOLA MORA

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
        InsertarTags(BaseDeDatos,"Diseño");//11
        InsertarTags(BaseDeDatos,"Diplomacia");//12
        InsertarTags(BaseDeDatos,"Ciencias naturales");//13
        InsertarTags(BaseDeDatos,"Agronomia");//14
        InsertarTags(BaseDeDatos,"Diseño");//15
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
        InsertarTags(BaseDeDatos,"Humanísticas y Sociales");//124
        InsertarTags(BaseDeDatos,"Artísticas");//125
        InsertarTags(BaseDeDatos,"Medicina y Cs. de la Salud");//126
        InsertarTags(BaseDeDatos,"Ingeniería y Computación");//127
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
        InsertarPreguntas(BaseDeDatos,1,"¿Aceptarías trabajar escribiendo artículos en la sección económica de un diario?","Interes","C");
        InsertarPreguntas(BaseDeDatos,2,"¿Te ofrecerías para organizar la despedida de soltero de uno de tus amigos?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,3,"¿Te gustaría dirigir un proyecto de urbanización en tu provincia?","Interes","A");
        InsertarPreguntas(BaseDeDatos,4,"A una frustración siempre opones un pensamiento positivo?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,5,"¿Te dedicarías a socorrer a personas accidentadas o atacadas por asaltantes?","Interes","D");
        InsertarPreguntas(BaseDeDatos,6,"¿Cuando eras chico, te interesaba saber cómo estaban construidos tus juguetes?","Interes","I");
        InsertarPreguntas(BaseDeDatos,7,"¿Te interesan más los misterios de la naturaleza que los secretos de la tecnología?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,8,"¿Escuchas atentamente los problemas que te plantean tus amigos?","Interes","S");
        InsertarPreguntas(BaseDeDatos,9,"¿Te ofrecerías para explicar a tus compañeros un determinado tema que ellos no entendieron?","Interes","H");
        InsertarPreguntas(BaseDeDatos,10,"¿Eres exigente y crítico con tu equipo de trabajo?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,11,"¿Te atrae armar rompecabezas o puzzles?","Interes","A");
        InsertarPreguntas(BaseDeDatos,12,"¿Puedes establecer la diferencia conceptual entre macroeconomía y microeconomía?","Interes","C");
        InsertarPreguntas(BaseDeDatos,13,"¿Usar uniforme te hace sentir distinto, importante?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,14,"¿Participarías como profesional en un espectáculo de acrobacia aérea?","Interes","D");
        InsertarPreguntas(BaseDeDatos,15,"¿Organizas tu dinero de manera que te alcance hasta el próximo cobro?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,16,"¿Convences fácilmente a otras personas sobre la validez de tus argumentos?","Interes","S");
        InsertarPreguntas(BaseDeDatos,17,"¿Estás informado sobre los nuevos descubrimientos que se están realizando sobre la Teoría del Big-Bang?","Interes","E");
        InsertarPreguntas(BaseDeDatos,18,"¿Ante una situación de emergencia actúas rápidamente?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,19,"¿Cuando tienes que resolver un problema matemático, perseveras hasta encontrar la solución?","Interes","I");
        InsertarPreguntas(BaseDeDatos,20,"¿Si te convocara tu club preferido para planificar, organizar y dirigir un campo de deportes, aceptarías?","Interes","C");
        InsertarPreguntas(BaseDeDatos,21,"¿Eres el que pone un toque de alegría en las fiestas?","Interes","A");
        InsertarPreguntas(BaseDeDatos,22,"¿Crees que los detalles son tan importantes como el todo?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,23,"¿Te sentirías a gusto trabajando en un ámbito hospitalario?","Interes","S");
        InsertarPreguntas(BaseDeDatos,24,"¿Te gustaría participar para mantener el orden ante grandes desórdenes y cataclismos?","Interes","D");
        InsertarPreguntas(BaseDeDatos,25,"¿Pasarías varias horas leyendo algún libro de tu interés?","Interes","H");
        InsertarPreguntas(BaseDeDatos,26,"¿Planificas detalladamente tus trabajos antes de empezar?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,27,"¿Entablas una relación casi personal con tu computadora?","Interes","I");
        InsertarPreguntas(BaseDeDatos,28,"¿Disfrutas modelando con arcilla?","Interes","A");
        InsertarPreguntas(BaseDeDatos,29,"¿Ayudas habitualmente a los no videntes a cruzar la calle?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,30,"¿Consideras importante que desde la escuela primaria se fomente la actitud crítica y la participación activa?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,31,"¿Aceptarías que las mujeres formaran parte de las fuerzas armadas bajo las mismas normas que los hombres?","Interes","D");
        InsertarPreguntas(BaseDeDatos,32,"¿Te gustaría crear nuevas técnicas para descubrir las patologías de algunas enfermedades a través del microscopio?","Interes","E");
        InsertarPreguntas(BaseDeDatos,33,"¿Participarías en una campaña de prevención contra la enfermedad de Chagas?","Interes","S");
        InsertarPreguntas(BaseDeDatos,34,"¿Te interesan los temas relacionados al pasado y a la evolución del hombre?","Interes","H");
        InsertarPreguntas(BaseDeDatos,35,"¿Te incluirías en un proyecto de investigación de los movimientos sísmicos y sus consecuencias?","Interes","E");
        InsertarPreguntas(BaseDeDatos,36,"¿Fuera de los horarios escolares, dedicas algún día de la semana a la realización de actividades corporales?","Interes","A");
        InsertarPreguntas(BaseDeDatos,37,"¿Te interesan las actividades de mucha acción y de reacción rápida en situaciones imprevistas y de peligro?","Interes","D");
        InsertarPreguntas(BaseDeDatos,38,"¿Te ofrecerías para colaborar como voluntario en los gabinetes espaciales de la NASA?","Interes","I");
        InsertarPreguntas(BaseDeDatos,39,"¿Te gusta más el trabajo manual que el trabajo intelectual?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,40,"¿Estarías dispuesto a renunciar a un momento placentero para ofrecer tu servicio como profesional?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,41,"¿Participarías de una investigación sobre la violencia en el fútbol?","Interes","H");
        InsertarPreguntas(BaseDeDatos,42,"¿Te gustaría trabajar en un laboratorio mientras estudias?","Interes","E");
        InsertarPreguntas(BaseDeDatos,43,"¿Arriesgarías tu vida para salvar la vida de otro que no conoces?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,44,"¿Te agradaría hacer un curso de primeros auxilios?","Interes","S");
        InsertarPreguntas(BaseDeDatos,45,"¿Tolerarías empezar tantas veces como fuere necesario hasta obtener el logro deseado?","Interes","A");
        InsertarPreguntas(BaseDeDatos,46,"¿Distribuyes tus horarios del día adecuadamente para poder hacer todo lo planeado?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,47,"¿Harías un curso para aprender a fabricar los instrumentos y/o piezas de las máquinas o aparatos con que trabajas?","Interes","I");
        InsertarPreguntas(BaseDeDatos,48,"¿Elegirías una profesión en la tuvieras que estar algunos meses alejado de tu familia, por ejemplo el marino?","Interes","D");
        InsertarPreguntas(BaseDeDatos,49,"¿Te radicarías en una zona agrícola-ganadera para desarrollar tus actividades como profesional?","Interes","E");
        InsertarPreguntas(BaseDeDatos,50,"¿Cuando estás en un grupo trabajando, te entusiasma producir ideas originales y que sean tenidas en cuenta?","Interes","A");
        InsertarPreguntas(BaseDeDatos,51,"¿Te resulta fácil coordinar un grupo de trabajo?","Aptitud","C");
        InsertarPreguntas(BaseDeDatos,52,"¿Te resultó interesante el estudio de las ciencias biológicas?","Interes","S");
        InsertarPreguntas(BaseDeDatos,53,"¿Si una gran empresa solicita un profesional como gerente de comercialización, te sentirías a gusto desempeñando ese rol?","Interes","C");
        InsertarPreguntas(BaseDeDatos,54,"¿Te incluirías en un proyecto nacional de desarrollo de la principal fuente de recursos de tu provincia?","Interes","I");
        InsertarPreguntas(BaseDeDatos,55,"¿Tenés interés por saber cuales son las causas que determinan ciertos fenómenos, aunque saberlo no altere tu vida?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,56,"¿Descubriste algún filósofo o escritor que haya expresado tus mismas ideas con antelación?","Interes","H");
        InsertarPreguntas(BaseDeDatos,57,"¿Desearías que te regalen algún instrumento musical para tu cumpleaños?","Interes","A");
        InsertarPreguntas(BaseDeDatos,58,"¿Aceptarías colaborar con el cumplimiento de las normas en lugares públicos?","Interes","D");
        InsertarPreguntas(BaseDeDatos,59,"¿Crees que tus ideas son importantes,y haces todo lo posible para ponerlas en práctica?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,60,"¿Cuando se descompone un artefacto en tu casa, te disponés prontamente a repararlo?","Interes","I");
        InsertarPreguntas(BaseDeDatos,61,"¿Formarías parte de un equipo de trabajo orientado a la preservación de la flora y la fauna en extinción?","Interes","E");
        InsertarPreguntas(BaseDeDatos,62,"¿Acostumbrás a leer revistas relacionadas con los últimos avances científicos y tecnológicos en el área de la salud?","Interes","S");
        InsertarPreguntas(BaseDeDatos,63,"¿Preservar las raíces culturales de nuestro país, te parece importante y necesario?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,64,"¿Te gustaría realizar una investigación que contribuyera a hacer más justa la distribución de la riqueza?","Interes","C");
        InsertarPreguntas(BaseDeDatos,65,"¿Te gustaría realizar tareas auxiliares en una nave, como por ejemplo izado y arriado de velas, pintura y conservación del casco, arreglo de averías, conservación de motores, etc?","Interes","D");
        InsertarPreguntas(BaseDeDatos,66,"¿Crees que un país debe poseer la más alta tecnología armamentista, a cualquier precio?","Aptitud","D");
        InsertarPreguntas(BaseDeDatos,67,"¿La libertad y la justicia son valores fundamentales en tu vida?","Interes","H");
        InsertarPreguntas(BaseDeDatos,68,"¿Aceptarías hacer una práctica rentada en una industria de productos alimenticios en el sector de control de calidad?","Interes","E");
        InsertarPreguntas(BaseDeDatos,69,"¿Consideras que la salud pública debe ser prioritaria, gratuita y eficiente para todos?","Aptitud","S");
        InsertarPreguntas(BaseDeDatos,70,"¿Te interesaría investigar sobre alguna nueva vacuna?","Interes","S");
        InsertarPreguntas(BaseDeDatos,71,"¿En un equipo de trabajo, preferís el rol de coordinador?","Interes","C");
        InsertarPreguntas(BaseDeDatos,72,"¿En una discusión entre amigos, te ofrecés como mediador?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,73,"¿Estás de acuerdo con la formación de un cuerpo de soldados profesionales?","Interes","D");
        InsertarPreguntas(BaseDeDatos,74,"¿Lucharías por una causa justa hasta las últimas consecuencias?","Interes","H");
        InsertarPreguntas(BaseDeDatos,75,"¿Te gustaría investigar científicamente sobre cultivos agrícolas?","Interes","I");
        InsertarPreguntas(BaseDeDatos,76,"¿Harías un nuevo diseño de una prenda pasada de moda, ante una reunión imprevista?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,77,"¿Visitarías un observatorio astronómico para conocer en acción el funcionamiento de los aparatos?","Interes","E");
        InsertarPreguntas(BaseDeDatos,78,"¿Dirigirías el área de importación y exportación de una empresa?","Interes","C");
        InsertarPreguntas(BaseDeDatos,79,"¿Te inhibís al entrar a un lugar nuevo con gente desconocida?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,80,"¿Te gratificaría el trabajar con niños?","Interes","H");
        InsertarPreguntas(BaseDeDatos,81,"¿Harías el diseño de un afiche para una campaña contra el sida?","Interes","A");
        InsertarPreguntas(BaseDeDatos,82,"¿Dirigirías un grupo de teatro independiente?","Aptitud","A");
        InsertarPreguntas(BaseDeDatos,83,"¿Enviarías tu curriculum a una empresa automotriz que solicita gerente para su área de producción?","Interes","I");
        InsertarPreguntas(BaseDeDatos,84,"¿Participarías en un grupo de defensa internacional dentro de alguna fuerza armada?","Interes","D");
        InsertarPreguntas(BaseDeDatos,85,"¿Te costearías tus estudios trabajando en una auditoría?","Interes","C");
        InsertarPreguntas(BaseDeDatos,86,"¿Sos de los que defendés causas perdidas?","Aptitud","H");
        InsertarPreguntas(BaseDeDatos,87,"¿Ante una emergencia epidémica participarías en una campaña brindando tu ayuda?","Interes","S");
        InsertarPreguntas(BaseDeDatos,88,"¿Sabrías responder que significa ADN y ARN?","Interes","E");
        InsertarPreguntas(BaseDeDatos,89,"¿Elegirías una carrera cuyo instrumento de trabajo fuere la utilización de un idioma extranjero?","Interes","H");
        InsertarPreguntas(BaseDeDatos,90,"¿Trabajar con objetos te resulta más gratificante que trabajar con personas?","Aptitud","I");
        InsertarPreguntas(BaseDeDatos,91,"¿Te resultaría gratificante ser asesor contable en una empresa reconocida?","Interes","C");
        InsertarPreguntas(BaseDeDatos,92,"¿Ante un llamado solidario, te ofrecerías para cuidar a un enfermo?","Interes","S");
        InsertarPreguntas(BaseDeDatos,93,"¿Te atrae investigar sobre los misterios del universo, por ejemplo los agujeros negros?","Interes","E");
        InsertarPreguntas(BaseDeDatos,94,"¿El trabajo individual te resulta más rápido y efectivo que el trabajo grupal?","Aptitud","E");
        InsertarPreguntas(BaseDeDatos,95,"¿Dedicarías parte de tu tiempo a ayudar a personas de zonas carenciadas?","Interes","H");
        InsertarPreguntas(BaseDeDatos,96,"¿Cuando elegís tu ropa o decorás un ambiente, tenés en cuenta la combinación de los colores, las telas o el estilo de los muebles?","Interes","A");
        InsertarPreguntas(BaseDeDatos,97,"¿Te gustaría trabajar como profesional dirigiendo la construcción de una empresa hidroeléctrica?","Interes","I");
        InsertarPreguntas(BaseDeDatos,98,"¿Sabés qué es el PBI?","Interes","C");
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

        InsertarMaterias(BD,2,"Derecho Constitucional I",1,0,"El objetivo del curso es examinar una diversidad de problemas teóricos vinculados con el hecho de vivir en una democracia constitucional a la luz de la teoría constitucional contemporánea.Algunas de las preguntas que se intentan responder son: ¿Por qué tener una Constitución? ¿Cómo justificar su dictado? ¿Cómo defender la restricción de la democracia que, teóricamente, aparece cuando adoptamos un documento constitucional? ¿Pueden las generaciones pasadas limitar a las generaciones subsiguientes?");
        InsertarMaterias(BD,2,"Teoría General del Derecho",1,0,"El curso de Teoría General del Derecho se divide en tres partes. En primer lugar, los alumnos se familiarizan con diferentes teorías del derecho En segundo lugar, los alumnos estudian los fundamentos filosóficos de las principales ramas del derecho, como derecho constitucional, derecho civil, derecho penal y derecho internacional. Finalmente, una vez aprendidos estos fundamentos, los alumnos analizan desde una perspectiva filosófica los fallos más importantes de la Corte Suprema argentina y norteamericana. El objetivo del curso es que los alumnos aprendan a encarar el estudio del derecho de forma tal que intenten entenderlo y criticarlo, no simplemente memorizarlo.");
        InsertarMaterias(BD,2,"Fundamentos del Derecho Privado",1,0,"Este curso analiza los conceptos fundamentales del derecho civil y comercial . El curso discute las instituciones del derecho privado desde diferentes enfoques – utilitarista, deontológico, económico, aristotélico, histórico, entre otros -. El debate entre Vélez Sársfield y Juan Bautista Alberdi respecto del Código Civil, la legalidad del matrimonio entre personas del mismo sexo, la concepción tomista de la propiedad y el fundamento kantiano de la responsabilidad civil son ejemplos de los temas discutidos en el curso.");
        InsertarMaterias(BD,2,"Historia Contemporánea",1,0,"Este curso se concentra en la historia de Europa entre el siglo XVII y los años 20 del siglo XX, con énfasis en la historia de las ideas y los aspectos institucionales y económicos. Así, para el análisis de la Revolución Francesa y sus repercusiones (1789-99), el curso se concentra en el Iluminismo, Locke, Hume, Rousseau, Montesquieu, el debate sobre la Revolución Francesa (Sieyès, Burke y Paine) y las transformaciones políticas y económicas de la Revolución Industrial que marcaron el fin del Antiguo Régimen. El mismo criterio se utiliza para los demás períodos: la era de Napoleón Bonaparte (1800-1814); la reorganización de Europa (1818-48); las revoluciones del 48 y los primeros movimientos nacionales (1848-60); la era de Bismarck y la nueva expansión colonialista europea (1860-75) y los acontecimientos que llevaron a la Primera Guerra Mundial. Finalmente, se anali{za el surgimiento de nuevos movimientos políticos europeos (comunismo, fascismo y nacionalsocialismo).");
        InsertarMaterias(BD,2,"Derecho Penal I",1,0,"El objetivo principal de este curso reside en la comprensión de los principios básicos de la responsabilidad penal desarrollados por la, así llamada, teoría de la imputación jurídico-penal (o también: teoría del delito o teoría de la responsabilidad penal). El curso combina el tratamiento de problemas y conceptos teóricos tales como los fines del derecho penal y sus límites, la acción, la causalidad, la fundamentación del ilícito, la justificación y la excusa, etc., y su aplicación sobre casos hipotéticos.");
        InsertarMaterias(BD,2,"Derecho Constitucional II",1,0,"El objetivo del curso es explorar aspectos centrales de la Constitución de Argentina a la luz de la jurisprudencia de la Corte Suprema de Justicia de la Nación. Entre las preguntas que se tratan de responder en el curso figuran las siguientes: ¿Cuál es la estructura de incentivos implícita en la Constitución en materia de derechos individuales y de organización del poder? ¿Cómo puede ser evaluada dicha estructura de incentivos en términos de eficiencia y equidad? ¿En qué medida, si es que alguna, tal estructura de incentivos facilitó la inédita crisis que Argentina está experimentando? ¿Cuáles son las fallas centrales en el derecho constitucional de Argentina? ¿Es suficiente una reforma constitucional para enmendar tales fallas?");
        InsertarMaterias(BD,2,"Obligaciones",1,0,"Este curso se concentra en los fundamentos filosóficos de las obligaciones civiles y comerciales. Entre las preguntas que el curso comprende se encuentran las siguientes: ¿qué es “daño” en el derecho civil? ¿Cuál es el fundamento de la responsabilidad subjetiva y de la objetiva? ¿Cuál es la mejor explicación de la causalidad en el derecho privado? ¿Cuál es el fundamento de los daños punitivos? ¿Cómo responden los sistemas jurídicos en tiempos de crisis? ¿Qué son las “deudas odiosas”? ");
        InsertarMaterias(BD,2,"Filosofía Moral",1,0,"En este curso exploramos las nociones de moralmente correcto y moralmente incorrecto, deberes y valores morales, y derechos morales, tal como ellas son discutidas en la filosofía moral contemporánea. Intentamos responder algunas preguntas conceptuales, como ¿Qué significa 'correcto'?, y algunas preguntas normativas, como ¿Tenemos un deber moral de promover la felicidad general? y ¿Hay alguna excusa válida para incumplir una promesa?");
        InsertarMaterias(BD,2,"Derecho de Daños y Seguros",2,0,"Este curso tiene como objetivo principal proveer una visión integrada sobre los principios y fundamentos que rigen el Derecho de Daños. El objetivo específico consiste en analizar los elementos de la responsabilidad civil, así como las funciones que cumple el Derecho de Daños. El curso combina el estudio pormenorizado de la normativa y jurisprudencia nacional y extranjera con una metodología interdisciplinaria que integra al derecho con herramientas provistas por la Economía.");
        InsertarMaterias(BD,2,"Derecho Penal II",2,0,"En gran medida este es un curso de ejercitación intensiva de lo aprendido en Responsabilidad Penal. A través de la solución de casos hipotéticos y jurisprudenciales se confronta la aplicación de los principios básicos de la responsabilidad penal con las figuras legales principales de la parte especial del Código Penal argentino y de su legislación accesoria. Así, se examinan delitos tales como el homicidio, el aborto, la asistencia al suicidio, el abandono de personas, los delitos sexuales, la calumnia y la injuria, el hurto, el robo, estafas y defraudaciones, delitos de tenencia de materiales prohibidos, enriquecimiento de funcionario público, etc.");
        InsertarMaterias(BD,2,"Microeconomía",2,0,"Este curso es una introducción a la microeconomía. El contenido del curso incluye la discusión de los siguientes conceptos: teoría de los precios, elección y curvas de indiferencia, competencia perfecta, competencia monopólica, oligopolio y teoría de los juegos, equilibrio de mercado, equilibrio general, nociones de eficiencia, teoremas del bienestar, fallas del mercado, teoría del comportamiento de la empresa y organización industrial.");
        InsertarMaterias(BD,2,"Lógica y Redacción",2,0,"El objetivo del curso de Lógica y Redacción es que los alumnos aprendan a argumentar de forma clara, rigurosa y original. Para adquirir esta habilidad, los alumnos leen textos de gramática, lógica formal, lógica informal y filosofía de la lógica, entre otros. Asimismo, los alumnos realizan un trabajo científico cada dos semanas y, de esta forma, aplican las herramientas teóricas que adquirieron leyendo los textos. Estos trabajos, que deben tener la misma estructura que aquellos publicados en los mejores journals científicos, son minuciosamente corregidos por el profesor.");
        InsertarMaterias(BD,2,"Filosofía Política",2,0,"Este curso versa sobre los modernos desarrollos en la filosofía política relacionados con la teoría normativa de la justicia social.  El curso se divide en tres partes.  En la primera, se utiliza como bibliografía básica la edición revisada de A Theory of Justice de John Rawls (Harvard University Press, 1999), y se la estudia en su integridad por su papel central en la configuración de los estudios normativos contemporáneos. La segunda parte del curso analiza cuatro ideas relacionadas con la justicia muy corrientes en los argumentos jurídicos y de políticas públicas: las ideas de merecimiento, igualdad, reciprocidad y necesidad.  En lugar del enfoque sistemático de Rawls, se sigue en esta parte el enfoque pluralista y contextualista de David Schmidtz en Elements of Justice (Cambridge University Press, 2006).  El uso combinado de dos obras basadas en  intuiciones normativas divergentes sobre la justicia social (intuiciones “socialistas” en el caso de  Rawls y proclives a la “economía de mercado” en el de Schmidtz) fomenta el pluralismo ideológico en un curso que inevitablemente está cargado de presupuestos ideológicos.  Finalmente, la tercera parte del curso se dedica a estudiar las ideas de justicia y suerte en la literatura y filosofía de Grecia antigua.  El objetivo de esta parte es invitar al estudiante a reflexionar sobre la relación entre las ideas normativas y la cultura pública.  Para ello se utiliza la obra de Martha C. Nussbaum: The Fragility of goodness, Luck and ethics in Greek tragedy and philosophy (Cambridge University Press, 1986).  Esta parte del curso, dictada por el ayudante, se desarrolla a través de la confección de trabajos escritos. ");
        InsertarMaterias(BD,2,"Derechos Reales",2,0,"Este curso tiene como objetivo el estudio de las normas que rigen los distintos derechos que pueden tener las personas sobre las cosas muebles e inmuebles. El curso se propone familiarizar a los estudiantes con la normativa de los derechos reales, su origen y su evolución.  Se busca también el examen crítico, de modo de aprovechar el curso para advertir las ventajas de algunas soluciones, así como lo inconveniente que pueden resultar otras, y las necesidades de cambio.");
        InsertarMaterias(BD,2,"Derecho Procesal Penal",2,0,"Este curso tiene como objetivo proveer herramientas para enfrentar todos los problemas jurídicos de un proceso penal. Para ello se combinan, por un lado, contenidos teóricos - especialmente los referidos a la interpretación de las garantías constitucionales de aplicación en el proceso penal, a la derivación a partir de ellas de reglas procesales concretas y al conocimiento de los distintos modelos procesales penales históricamente dados -; y, por otro, ejercitación práctica sobre la base de casos hipotéticos y de la simulación de diversas instancias procesales que sitúan a los alumnos en distintos roles procesales (juez de instrucción, tribunal de juicio, acusador público, acusador particular, defensor).");
        InsertarMaterias(BD,2,"Análisis Económico del Derecho",2,0,"El objetivo del curso es que los alumnos aprendan a analizar el derecho desde la perspectiva de la microeconomía. A partir de la escasez de los recursos y de la racionalidad de los individuos (dos supuestos que se mantienen durante el curso), se analizan las consecuencias que trae aparejado el diseño de las leyes, tanto desde el punto de vista normativo como el positivo. Durante el curso, se utilizan diferentes áreas del derecho - derecho de la propiedad, el derecho penal, el derecho de daños, el derecho contractual y el derecho procesal - como medios para internalizar el uso de las herramientas de la microeconomía");
        InsertarMaterias(BD,2,"Contratos I",3,0,"El objetivo de este curso reside en la comprensión de las preguntas fundamentales del derecho de los contratos a la luz de la legislación y jurisprudencia tanto argentina como de otras jurisdicciones. Entre las preguntas que el curso comprende se encuentran las siguientes: ¿Qué acuerdos tienen valor legal y por qué? ¿Qué efectos tienen los contratos entre las partes y con respecto a los terceros? ¿Cuáles son las acciones en caso de incumplimiento contractual? ¿Qué criterios deben utilizar los tribunales para interpretar contratos? ¿Qué bienes y servicios pueden ser objeto de un contrato y por qué?");
        InsertarMaterias(BD,2,"Familia y Sucesiones",3,0,"El objetivo de este curso es analizar las instituciones fundamentales del derecho de familia y del derecho sucesorio, a través del análisis del derecho positivo, la doctrina y las decisiones judiciales. Entre los temas discutidos en el curso se encuentran los siguientes: efectos personales y patrimoniales del matrimonio, uniones de hecho, filiación, adopción, patria potestad, herencia, legados, entre otros.");
        InsertarMaterias(BD,2,"Derecho Laboral y de la Seguridad Social ",3,0,"");
        InsertarMaterias(BD,2,"Derecho y Sociedad",3,0,"El curso ofrece una introducción a los estudios de Derecho y Sociedad para familiarizar a los estudiantes con los usos de la investigación empírica desde los puntos de vista interno y externo del derecho. Con ese objetivo, se organiza en tres secciones.  En la primera etapa se revisarán diversas tradiciones de estudios de Derecho y Sociedad en América Latina y otros continentes. En este marco se estudiarán cuestiones de eficacia, pluralismo, desigualdad, y derecho ydesarrollo. Se revisarán también las discusiones realistas, neorrealistas y pragmáticas sobre las relaciones entre el derecho “en los libros,” el derecho “en acción” y el derecho “en las conciencias” de las personas.");
        InsertarMaterias(BD,2,"Derecho Internacional Público",3,0,"Este curso tiene como objetivo el impartir conocimientos básicos sobre la estructura y efectos de las relaciones jurídicas que vinculan a los Estados. En la primera parte se analizan los mecanismos de creación de las normas del sistema conocido como derecho internacional público; las relaciones de ese derecho con los derechos internos de los Estados; la responsabilidad internacional y sus consecuencias. En la segunda parte, se estudian los temas fundamentales que relacionan al Estado en el tiempo (reconocimiento y sucesión de Estados); la jurisdicción del Estado frente a la de otros Estados y el ámbito territorial de sus competencias (terrestre, marítimo y fluvial); las obligaciones de los Estados frente a la promoción y protección de los derechos humanos; y los principios fundamentales que rigen las relaciones amistosas entre Estados");
        InsertarMaterias(BD,2,"Derecho Procesal Civil I",3,0,"El curso tiene como objetivo brindar al alumno los conocimientos necesarios para obtener una formación sólida con relación a distintos aspectos del Derecho Procesal Civil. El punto de partida es entender al Proceso como un método de resolución de conflictos. Debido a la división temática entre las asignaturas Derecho Procesal Civil I y II, aquí se discutirán los problemas que presenta la asignatura en los siguientes ejes temáticos: (i) nociones básicas del proceso; (i) el abogado y el juez; y (iii) el debate procesal en su etapa postulatoria y probatoria.  De tal modo, quedarán reservados para el curso de Derecho Procesal Civil II los temas referidos a la etapa decisoria del proceso, medios de impugnación, procesos de ejecución y procesos especiales. ");
        InsertarMaterias(BD,2,"Derecho Administrativo",3,0,"Este curso tiene como objetivo el estudio de los principios y reglas que rigen el ejercicio de la función administrativa. Para ello, pone especial énfasis en la interacción permanente entre dicha función y el Poder Judicial en virtud de las limitaciones que la Administración impone al ejercicio de los derechos de los particulares, así como en la relación existente entre los tópicos que suelen ser discutidos bajo el rótulo y las normas constitucionales y los parámetros que pueden deducirse de ellas con relación al ejercicio de la función administrativa y la organización estatal en generalho Administrativo");
        InsertarMaterias(BD,2,"Sociedades",3,0,"Los objetivos principales de este curso son la comprensión de los problemas básicos objeto del derecho societario, el estudio de sus normas y principios y la reflexión acerca de las diversas finalidades que éstas persiguen.  Para ello, se utiliza el análisis económico del derecho como marco teórico y el método de casos, y se presta especial atención a la forma en que el derecho comparado enfoca y soluciona dichos problemas.");
        InsertarMaterias(BD,2,"Derecho Procesal Civil II",4,1,"Los objetivos de esta etapa de la materia son similares a los de Derecho Procesal Civil I, sólo cambia un tramo de la partitura. Esta parte del Derecho Procesal Civil es más técnica, lo que no impide que se apliquen todas las nociones teóricas adquiridas en la parte inicial; más bien, son esas ideas basales las que facilitan el abordaje de los nuevos tópicos. El desafío de este curso reside en lograr explicar los problemas que debe afrontar el juez para resolver controversias, la dinámica de las diferentes clases de impugnación y otras formas alternativas de disputabilidad tales como los procesos especiales y el juicio ejecutivo. El curso se concentra, primordialmente, en los problemas de la decisión (y del decisor) y en la teoría de la impugnación.");
        InsertarMaterias(BD,2,"Macroeconomía",4,1,"Este curso es una introducción a problemas de macroeconomía. El contenido del curso incluye la discusión de los siguientes tópicos, entre otros de importancia: dinero, inflación y tipo de Cambio; gobierno y política Fiscal; modelos de economía abierta; ahorro e inversión; efectos de las corridas bancarias.");
        InsertarMaterias(BD,2,"Derecho Tributario",4,1,"Esta materia tiene por objetivo enseñar al alumno cómo funcionan los impuestos en Argentina y cuáles son las reglas básicas sobre tributación internacional para lograr que: a) los alumnos que se especialicen en el área de impuestos egresen con fuertes herramientas teóricas y prácticas para poder comprenderlos y trabajar con ellos; y b) los que trabajen en cualquier otro área sepan lo que tienen que saber (como mínimo) para poder convivir con ellos sin sobresaltos.");
        InsertarMaterias(BD,2,"Concursos y Quiebras",4,1,"Este curso tiene como objeto introducir a los alumnos en el estudio del derecho concursal. Para ello se utilizan contenidos teóricos con especial énfasis en el análisis económico y financiero de los temas a desarrollar. El curso buscará responder preguntas tales como: ¿Por qué un sistema concursal? ¿A quién protege un sistema concursal? ¿Cómo conviene iniciar un proceso concursal? ¿Cómo debe constituirse el pasivo concursal? ¿Cómo se debe distribuir el activo concursal? De esta manera nutrirá a los alumnos de conocimientos y herramientas analíticas que les permitirán interpretar y actuar sobre la legislación existente, así como anticiparse a los futuros cambios posibles");
        InsertarMaterias(BD,2,"Contabilidad y Análisis Financiero",4,1,"Este curso tiene 2 partes. La primera parte del curso tiene como objetivo introducir a los alumnos en los procesos de registro y medición de la información económica y financiera vinculada con el desempeño de las empresas, prestando particular atención a la comprensión de los principales criterios utilizados en el campo contable. Esta parte tiene un énfasis especial en analizar las técnicas contables en los contextos legales en los que es muy probable que el abogado deba enfrentarse con cuestiones económicas y financieras.  La segunda parte se concentra en  los procesos de decisión económica y financiera. Fundamentalmente, se tratan temas vinculados con proyecciones financieras, análisis de inversión y valor tiempo del dinero, determinación del costo de capital, valuación de empresas y decisiones sobre utilización de capital propio o ajeno.");
        InsertarMaterias(BD,2,"Contratos II",4,1,"Este curso analiza los contratos utilizados con mayor frecuencia en la actualidad. Su objetivo es brindar a los participantes: (i) el marco teórico necesario para ese análisis, integrado por la normativa, jurisprudencia, doctrina y demás fuentes del derecho relevantes en cada caso y (ii) las herramientas necesarias para la aplicación de ese marco conceptual, incluyendo técnicas de redacción, interpretación y negociación de cláusulas y contratos.");
        InsertarMaterias(BD,2,"Derecho Internacional Privado",4,1,"Este curso tiene como objetivo principal proveer una visión integrada sobre los principios y fundamentos que rigen el Derecho Internacional Privado. El objetivo específico consiste en analizar la determinación del ordenamiento jurídico competente para regir las relaciones jurídicas internacionales. El curso combina el estudio pormenorizado de la normativa argentina, tanto de fuente interna como convencional, con el estudio de jurisprudencia nacional y  extranjera");
        InsertarMaterias(BD,2,"Derecho Ambiental y de los Recursos Naturales",5,1,"El curso consiste en una introducción al derecho ambiental en general, y el derecho ambiental argentino en particular. Se examinan, en consecuencia, los fundamentos, evolución, contenidos e incentivos de las principales reglas de contenido ambiental del sistema legal. Adicionalmente, se analizan elementos de política, economía y ética ambiental que permiten una mirada crítica de los problemas y regulaciones ambientales.");
        InsertarMaterias(BD,2,"Seminario Mediación y Arbitraje",5,1,"Este curso es una introducción a la mediación y el arbitraje, medios de resolución de conflictos basados en la autonomía de las partes. La mediación se estudia como una negociación asistida, fundada en principios orientados a procurar acuerdos que satisfagan los interes es de las partes. Se exponen los temas centrales del arbitraje doméstico e internacional: el acuerdo arbitral, la instancia arbitral y el laudo o sentencia arbitral. Se examinan las relaciones entre el arbitraje y el proceso judicial estatal, en particular en materia de impugnación de laudos, y reconocimiento y ejecución de laudos extranjeros.");
        InsertarMaterias(BD,2,"Ética Profesional",5,1,"El curso tiene tres objetivos principales. En primer lugar, se busca introducir a los alumnos en la normativa vigente en materia de conducta profesional, tal como es regulada en la Capital Federal por el Colegio Público de Abogados, comparándola con los códigos utilizados en EUA y Europa. En segundo lugar, se discuten una serie de casos reales e hipotéticos que plantean problemas éticos en el ejercicio de la profesión, con el objetivo de obtener una clasificación de los distintos tipos de problemas involucrados (confidencialidad, conflicto de intereses, aceptación y rechazo de clientes, acceso y distribución de recursos legales, límites del sistema adversarial, etc.). Por último, se busca profundizar algunos de estos problemas, a través de la discusión de bibliografía más teórica, aunque sin descuidar la discusión de casos concretos.");
        InsertarMaterias(BD,6,"Laboratorio de Diseño I",1,0,"");
        InsertarMaterias(BD,6,"Forma e Imagen",1,0,"");
        InsertarMaterias(BD,6,"Arte y Cultura de la Modernidad",1,0,"");
        InsertarMaterias(BD,6,"Matemática I",1,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño II",1,0,"");
        InsertarMaterias(BD,6,"Teorías de la Comunicación",1,0,"");
        InsertarMaterias(BD,6,"Introducción a la Administración Pública y a las Organizaciones",1,0,"");
        InsertarMaterias(BD,6,"Economía",1,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño III",2,0,"");
        InsertarMaterias(BD,6,"Objeto y Materialidad",2,0,"");
        InsertarMaterias(BD,6,"Introducción a los Estudios Visuales",2,0,"");
        InsertarMaterias(BD,6,"Historia del Diseño Moderno",2,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño IV",2,0,"");
        InsertarMaterias(BD,6,"Teoría y Metodología del Diseño",2,0,"");
        InsertarMaterias(BD,6,"Ingeniería de Materiales",2,0,"");
        InsertarMaterias(BD,6,"Sociología",2,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño V",3,0,"");
        InsertarMaterias(BD,6,"Visualización de la Información",3,0,"");
        InsertarMaterias(BD,6,"Programación Orientada al Diseño",3,0,"");
        InsertarMaterias(BD,6,"Historia del Diseño Latinoamericano",3,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño VI",3,0,"");
        InsertarMaterias(BD,6,"Diseño de Interactividad",3,0,"");
        InsertarMaterias(BD,6,"Marketing",3,0,"");
        InsertarMaterias(BD,6,"Curso de Campo Menor",3,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño VII",4,0,"");
        InsertarMaterias(BD,6,"Diseño y Gestión Cultural",4,0,"");
        InsertarMaterias(BD,6,"Gestión Estratégica de Diseño",4,0,"");
        InsertarMaterias(BD,6,"Laboratorio de Diseño VIII (Proyecto Final)",4,0,"");
        InsertarMaterias(BD,6,"Narrativas y Medios",4,0,"");
        InsertarMaterias(BD,6,"Diseño Sustentable",4,0,"");
        InsertarMaterias(BD,7,"Introducción a la Ciencia Política",1,0,"");
        InsertarMaterias(BD,7,"Matemática I",1,0,"");
        InsertarMaterias(BD,7,"Economía I",1,0,"");
        InsertarMaterias(BD,7,"Introducción a las Relaciones Internacionales",1,0,"");
        InsertarMaterias(BD,7,"Comprensión de Textos y Escritura",1,0,"");
        InsertarMaterias(BD,7,"Teoría Política I",1,0,"");
        InsertarMaterias(BD,7,"Matemática II",1,0,"");
        InsertarMaterias(BD,7,"Lógica y Técnicas de Investigación en Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,7,"Historia de Occidente a partir de la Modernidad",1,0,"");
        InsertarMaterias(BD,7,"Economía II",2,0,"");
        InsertarMaterias(BD,7,"Introducción a las Políticas Públicas",2,0,"");
        InsertarMaterias(BD,7,"Política Comparada",2,0,"");
        InsertarMaterias(BD,7,"Política y Sociedad en la Argentina (Siglos XIX y XX)",2,0,"");
        InsertarMaterias(BD,7,"Política y Sociedad en América Latina",2,0,"");
        InsertarMaterias(BD,7,"Teoría Política II",2,0,"");
        InsertarMaterias(BD,7,"Teoría de las Relaciones Internacionales",2,0,"");
        InsertarMaterias(BD,7,"Historia del Mundo Contemporáneo (1914-2000)",2,0,"");
        InsertarMaterias(BD,7,"Política Exterior Argentina",3,0,"");
        InsertarMaterias(BD,7,"Política y Economía",3,0,"");
        InsertarMaterias(BD,7,"Diseño y Metodología de la Investigación Social",3,0,"");
        InsertarMaterias(BD,7,"Organizaciones y Teoría de la Decisión",3,0,"");
        InsertarMaterias(BD,7,"Expresión Oral y Escrita",3,0,"");
        InsertarMaterias(BD,7,"Relaciones Internacionales Contemporáneas",3,0,"");
        InsertarMaterias(BD,7,"Política Exterior de Estados Unidos",3,0,"");
        InsertarMaterias(BD,7,"Estadística para Ciencias Sociales",3,0,"");
        InsertarMaterias(BD,7,"Política y Derecho",3,0,"");
        InsertarMaterias(BD,7,"Organismos Internacionales",4,0,"");
        InsertarMaterias(BD,7,"Derecho Internacional",4,0,"");
        InsertarMaterias(BD,7,"Comercio Internacional",4,0,"");
        InsertarMaterias(BD,7,"Conflictos Internacionales y Seguridad",4,0,"");
        InsertarMaterias(BD,7,"Seminario de Graduación",4,0,"");
        InsertarMaterias(BD,8,"Introducción a la Ciencia Política",1,0,"");
        InsertarMaterias(BD,8,"Matemática I",1,0,"");
        InsertarMaterias(BD,8,"Economía I",1,0,"");
        InsertarMaterias(BD,8,"Introducción a las Relaciones Internacionales",1,0,"");
        InsertarMaterias(BD,8,"Comprensión de Textos y Escritura",1,0,"");
        InsertarMaterias(BD,8,"Teoría Política I",1,0,"");
        InsertarMaterias(BD,8,"Matemática II ",1,0,"");
        InsertarMaterias(BD,8,"Historia de Occidente a partir de la Modernidad",1,0,"");
        InsertarMaterias(BD,8,"Lógica y Técnicas de Investigación en Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,8,"Economia II",2,0,"");
        InsertarMaterias(BD,8,"Política Comparada",2,0,"");
        InsertarMaterias(BD,8,"Introducción a las Políticas Públicas",2,0,"");
        InsertarMaterias(BD,8,"Política y Sociedad en la Argentina (Siglos XIX y XX)",2,0,"");
        InsertarMaterias(BD,8,"Política y Sociedad en América Latina",2,0,"");
        InsertarMaterias(BD,8,"Teoría Política II",2,0,"");
        InsertarMaterias(BD,8,"Teoría de las Relaciones Internacionales",2,0,"");
        InsertarMaterias(BD,8,"Historia del Mundo Contemporáneo (1914-2000)",2,0,"");
        InsertarMaterias(BD,8,"Estructura Social y Demografía",3,0,"");
        InsertarMaterias(BD,8,"Política y Economía",3,0,"");
        InsertarMaterias(BD,8,"Diseño y Metodología de la Investigación Social",3,0,"");
        InsertarMaterias(BD,8,"Organizaciones y Teoría de la Decisión",3,0,"");
        InsertarMaterias(BD,8,"Expresión Oral y Escrita",3,0,"");
        InsertarMaterias(BD,8,"Estado y Políticas Públicas en la Argentina",3,0,"");
        InsertarMaterias(BD,8,"Política y Derecho",3,0,"");
        InsertarMaterias(BD,8,"Política y Comunicación",3,0,"");
        InsertarMaterias(BD,8,"Estadística para Ciencias Sociales",3,0,"");
        InsertarMaterias(BD,8,"Finanzas Públicas",4,0,"");
        InsertarMaterias(BD,8,"Seminario de Temas de Política Económica Argentina",4,0,"");
        InsertarMaterias(BD,8,"Tópicos de Teoría Política Social",4,0,"");
        InsertarMaterias(BD,8,"Actores y Procesos Políticos",4,0,"");
        InsertarMaterias(BD,8,"Seminario de Graduación",4,0,"");
        InsertarMaterias(BD,11,"Matemática",1,0,"");
        InsertarMaterias(BD,11,"Química",1,0,"");
        InsertarMaterias(BD,11,"Introducción al Conocimiento de la Sociedad y el Estado ",1,0,"");
        InsertarMaterias(BD,11,"Física e Introducción a la Biofísica",1,0,"");
        InsertarMaterias(BD,11,"Biología",1,0,"");
        InsertarMaterias(BD,11,"Química Aplicada",2,0,"");
        InsertarMaterias(BD,11,"Física Aplicada",2,0,"");
        InsertarMaterias(BD,11,"Estadística General",2,0,"");
        InsertarMaterias(BD,11,"Biomoléculas",2,0,"");
        InsertarMaterias(BD,11,"Bioquímica Aplicada",2,0,"");
        InsertarMaterias(BD,11,"Edafología",2,0,"");
        InsertarMaterias(BD,11,"Climatología y Agrometeorología",2,0,"");
        InsertarMaterias(BD,11,"Botánica",2,0,"");
        InsertarMaterias(BD,11,"Evolución y Genética",3,0,"");
        InsertarMaterias(BD,11,"Fisiología de las Plantas Superiores",3,0,"");
        InsertarMaterias(BD,11,"Zoología General ",3,0,"");
        InsertarMaterias(BD,11,"Química de la Contaminación y Toxicología",3,0,"");
        InsertarMaterias(BD,11,"Sociología y Antropología General",3,0,"");
        InsertarMaterias(BD,11,"Ecología",3,0,"");
        InsertarMaterias(BD,11,"Economía Política",3,0,"");
        InsertarMaterias(BD,11,"Microbiología Ambiental",3,0,"");
        InsertarMaterias(BD,11,"Nociones de Geología y Geomorfología",3,0,"");
        InsertarMaterias(BD,11,"Derechos Humanos",3,0,"");
        InsertarMaterias(BD,11,"Ecología Acuática ",4,0,"");
        InsertarMaterias(BD,11,"Hidrología",4,0,"");
        InsertarMaterias(BD,11,"Geografía Ambiental",4,0,"");
        InsertarMaterias(BD,11,"Bioindicadores",4,0,"");
        InsertarMaterias(BD,11,"Sistemas de Información Geográfica, Cartografía y Teledetección",4,0,"");
        InsertarMaterias(BD,11,"Economía Aplicada al Agro y al Ambiente",4,0,"");
        InsertarMaterias(BD,11,"Agroecosistemas",4,0,"");
        InsertarMaterias(BD,11,"Ambiente y Sociedad",4,0,"");
        InsertarMaterias(BD,11,"Gestión de Proyectos",4,0,"");
        InsertarMaterias(BD,11,"Economía y Política del Ambiente",4,0,"");
        InsertarMaterias(BD,11,"Gestión y Conservación de los Recursos Naturales",5,0,"");
        InsertarMaterias(BD,11,"Ética y Legislación Ambiental",5,0,"");
        InsertarMaterias(BD,11,"Biodiversidad",5,0,"");
        InsertarMaterias(BD,11,"Modelos Estadísticos",5,0,"");
        InsertarMaterias(BD,11,"Conservación y Planificación del Uso de la Tierra",5,0,"");
        InsertarMaterias(BD,11,"Ordenamiento Territorial",5,0,"");
        InsertarMaterias(BD,11,"Modelos de Simulación",6,0,"");
        InsertarMaterias(BD,11,"Cambio Global",6,0,"");
        InsertarMaterias(BD,11,"Evaluación de Impacto Ambiental",6,0,"");
        InsertarMaterias(BD,11,"Análisis de Riesgo Ambiental",6,0,"");
        InsertarMaterias(BD,11,"Trabajo Final",6,0,"");
        InsertarMaterias(BD,12,"Matemática",1,0,"");
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
        InsertarMaterias(BD,12,"Ecología",3,0,"");
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

        InsertarMaterias(BD,13,"Análisis Matemático I",1,0,"");
        InsertarMaterias(BD,13,"Economía",1,0,"");
        InsertarMaterias(BD,13,"Sociología",1,0,"");
        InsertarMaterias(BD,13,"Metodología de las Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,13,"Álgebra",1,0,"");
        InsertarMaterias(BD,13,"Historia Económica Social General",1,0,"");
        InsertarMaterias(BD,13,"Estadística",2,0,"");
        InsertarMaterias(BD,13,"Introducción a los Sistemas Productivos",2,0,"");
        InsertarMaterias(BD,13,"Microeconomía I",2,0,"");
        InsertarMaterias(BD,13,"Administración General",2,0,"");
        InsertarMaterias(BD,13,"Macroeconomía I",2,0,"");
        InsertarMaterias(BD,13,"Bases Biológicas de los Sistemas Agropecuarios",2,0,"");
        InsertarMaterias(BD,13,"Interpretación Contable y Diagnóstico Financiero",2,0,"");
        InsertarMaterias(BD,13,"Aplicaciones de la Genética a la Bioeconomía",2,0,"");
        InsertarMaterias(BD,13,"Producción Vegetal",3,0,"");
        InsertarMaterias(BD,13,"Geografía Económica",3,0,"");
        InsertarMaterias(BD,13,"Macroeconomía Aplicada",3,0,"");
        InsertarMaterias(BD,13,"Econometría",3,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producción Animal I",3,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producción de Cultivos Extensivos",3,0,"");
        InsertarMaterias(BD,13,"Sociología Agraria",3,0,"");
        InsertarMaterias(BD,13,"Costos Agrarios y Análisis de Proyectos",3,0,"");
        InsertarMaterias(BD,13,"Ecología de los Ecosistemas",3,0,"");
        InsertarMaterias(BD,13,"Electivas",3,0,"");
        InsertarMaterias(BD,13,"Política Agraria",4,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producción Animal II",4,0,"");
        InsertarMaterias(BD,13,"Economía de la Producción y Complejos Agroindustriales",4,0,"");
        InsertarMaterias(BD,13,"Economía y Política de los Recursos Naturales y Sustentabilidad",4,0,"");
        InsertarMaterias(BD,13,"Sistemas de Producción de Cultivos Intensivos",4,0,"");
        InsertarMaterias(BD,13,"Administración de la Empresa Agropecuaria",4,0,"");
        InsertarMaterias(BD,13,"Comercio Interno y Externo de Productos Agropecuarios",4,0,"");
        InsertarMaterias(BD,13,"Legislación Agraria",4,0,"");
        InsertarMaterias(BD,13,"Introducción a los Impuestos Agropecuarios",4,0,"");
        InsertarMaterias(BD,13,"Derechos Humanos",4,0,"");
        InsertarMaterias(BD,13,"Taller de Trabajo Final",4,0,"");
        InsertarMaterias(BD,13,"Trabajo Final",5,0,"");
        InsertarMaterias(BD,13,"Asignaturas Electivas (1)",5,0,"");
        InsertarMaterias(BD,13,"Asignaturas Optativas (1)",5,0,"");

        InsertarMaterias(BD,14,"Matemática",1,0,"");
        InsertarMaterias(BD,14,"Química",1,0,"");
        InsertarMaterias(BD,14,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,14,"Biología",1,0,"");
        InsertarMaterias(BD,14,"Física e Introducción a la Biofísica",1,0,"");
        InsertarMaterias(BD,14,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,14,"Biomoléculas",2,0,"");
        InsertarMaterias(BD,14,"Física Aplicada",2,0,"");
        InsertarMaterias(BD,14,"Elementos de Estadística",2,0,"");
        InsertarMaterias(BD,14,"Economía Política",2,0,"");
        InsertarMaterias(BD,14,"Microbiología General",2,0,"");
        InsertarMaterias(BD,14,"Estadística Analítica",2,0,"");
        InsertarMaterias(BD,14,"Inglés Técnico",2,0,"");
        InsertarMaterias(BD,14,"Nutrición",2,0,"");
        InsertarMaterias(BD,14,"Introducción a los Sistemas Productivos",2,0,"");
        InsertarMaterias(BD,14,"Química de Agroalimentos",3,0,"");
        InsertarMaterias(BD,14,"Microbiología de los Agroalimentos ",3,0,"");
        InsertarMaterias(BD,14,"Sistemas de Producción de Granos",3,0,"");
        InsertarMaterias(BD,14,"Sistemas de Producción y Postcosecha de Frutos y Hortalizas",3,0,"");
        InsertarMaterias(BD,14,"Biotecnología de los Agroalimentos ",3,0,"");
        InsertarMaterias(BD,14,"Teoría de las Organizaciones",3,0,"");
        InsertarMaterias(BD,14,"Sistemas de Producción Animal",3,0,"");
        InsertarMaterias(BD,14,"Calidad Agroalimentaria",3,0,"");
        InsertarMaterias(BD,14,"Industrialización de los Alimentos de Origen Animal",4,0,"");
        InsertarMaterias(BD,14,"Ética, Legislación y Seguridad Agroalimentaria",4,0,"");
        InsertarMaterias(BD,14,"Comercialización y Mercados de Agroalimentos",4,0,"");
        InsertarMaterias(BD,14,"Gestión y Planificación de la Empresa Agroalimentaria",4,0,"");
        InsertarMaterias(BD,14,"Comercialización y Mercados de Agroalimentos",4,0,"");
        InsertarMaterias(BD,14,"Gestión y Planificación de la Empresa Agroalimentaria",4,0,"");
        InsertarMaterias(BD,14,"Industrialización de los Alimentos de Origen Vegetal",4,0,"");
        InsertarMaterias(BD,14,"Formulación y Evaluación de Proyectos Agroalimentarios",4,0,"");
        InsertarMaterias(BD,14,"Sociología de las Organizaciones Agroalimentarias",4,0,"");
        InsertarMaterias(BD,14,"Gestión de las Cadenas Agroalimentarias",4,0,"");
        InsertarMaterias(BD,14,"Taller de Integración de la Práctica Profesional",5,0,"");
        InsertarMaterias(BD,14,"Práctica Profesional",5,0,"");
        InsertarMaterias(BD,14,"Asignaturas Optativas",5,0,"");

        InsertarMaterias(BD,15,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,15,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,15,"Introducción al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,15,"Introducción al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,15,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,15,"Matemática",1,0,"");
        InsertarMaterias(BD,15,"Biología",1,0,"");
        InsertarMaterias(BD,15,"Planificación y Diseño del Paisaje I",2,0,"");
        InsertarMaterias(BD,15,"Vegetación I",2,0,"");
        InsertarMaterias(BD,15,"Química General",2,0,"");
        InsertarMaterias(BD,15,"Química Biológica",2,0,"");
        InsertarMaterias(BD,15,"Botánica General",2,0,"");
        InsertarMaterias(BD,15,"Botánica Sistemática",2,0,"");
        InsertarMaterias(BD,15,"Física Aplicada",2,0,"");
        InsertarMaterias(BD,15,"Materiales, Elementos y Procedimientos de Construcción",2,0,"");
        InsertarMaterias(BD,15,"Sistemas de Representación Geométrica",2,0,"");
        InsertarMaterias(BD,15,"Planificación y Diseño del Paisaje II",3,0,"");
        InsertarMaterias(BD,15,"Vegetación II",3,0,"");
        InsertarMaterias(BD,15,"Fisiología Vegetal",3,0,"");
        InsertarMaterias(BD,15,"Edafología",3,0,"");
        InsertarMaterias(BD,15,"Climatología y Fenología",3,0,"");
        InsertarMaterias(BD,15,"Morfología y Comunicación I",3,0,"");
        InsertarMaterias(BD,15,"Instalaciones y Equipamientos",3,0,"");
        InsertarMaterias(BD,15,"Topografía",3,0,"");
        InsertarMaterias(BD,15,"Planificación y Diseño del Paisaje III",4,0,"");
        InsertarMaterias(BD,15,"Morfología y Comunicación II",4,0,"");
        InsertarMaterias(BD,15,"Historia de la Arquitectura Paisajística I",4,0,"");
        InsertarMaterias(BD,15,"Geografía",4,0,"");
        InsertarMaterias(BD,15,"Ecología",4,0,"");
        InsertarMaterias(BD,15,"Fitogeografía",4,0,"");
        InsertarMaterias(BD,15,"Manejo del Suelo y la Vegetación",4,0,"");
        InsertarMaterias(BD,15,"Electiva",4,0,"");
        InsertarMaterias(BD,15,"Planificación y Diseño del Paisaje IV",5,0,"");
        InsertarMaterias(BD,15,"Trabajo Final",5,0,"");
        InsertarMaterias(BD,15,"Historia de la Arquitectura Paisajística II",5,0,"");
        InsertarMaterias(BD,15,"Planeamiento Urbano y Regional",5,0,"");
        InsertarMaterias(BD,15,"Ecología del Paisaje",5,0,"");
        InsertarMaterias(BD,15,"Electivas",5,0,"");
        InsertarMaterias(BD,15,"Práctica Profesional",5,0,"");

        InsertarMaterias(BD,16,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,16,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,16,"Introducción al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,16,"Introducción al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,16,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,16,"Matemática",1,0,"");
        InsertarMaterias(BD,16,"Filosofía",1,0,"");
        InsertarMaterias(BD,16,"Arquitectura I",2,0,"");
        InsertarMaterias(BD,16,"Introducción a la Arquitectura Contemporánea",2,0,"");
        InsertarMaterias(BD,16,"Sistemas de Representación Geométrica",2,0,"");
        InsertarMaterias(BD,16,"Introducción a los Tipos Constructivos",2,0,"");
        InsertarMaterias(BD,16,"Introducción a los Tipos Estructurales",2,0,"");
        InsertarMaterias(BD,16,"Física Aplicada a la Arquitectura",2,0,"");
        InsertarMaterias(BD,16,"Matemática II",2,0,"");
        InsertarMaterias(BD,16,"Arquitectura II",3,0,"");
        InsertarMaterias(BD,16,"Representación Arquitectónica",3,0,"");
        InsertarMaterias(BD,16,"Historia I",3,0,"");
        InsertarMaterias(BD,16,"Morfología I",3,0,"");
        InsertarMaterias(BD,16,"Construcciones I",3,0,"");
        InsertarMaterias(BD,16,"Estructuras I",3,0,"");
        InsertarMaterias(BD,16,"Instalaciones I",3,0,"");
        InsertarMaterias(BD,16,"Arquitectura III",4,0,"");
        InsertarMaterias(BD,16,"Materialización de Proyectos",4,0,"");
        InsertarMaterias(BD,16,"Historia II",4,0,"");
        InsertarMaterias(BD,16,"Morfología II",4,0,"");
        InsertarMaterias(BD,16,"Construcciones II",4,0,"");
        InsertarMaterias(BD,16,"Estructuras II",4,0,"");
        InsertarMaterias(BD,16,"Instalaciones II",4,0,"");
        InsertarMaterias(BD,16,"Arquitectura IV",5,0,"");
        InsertarMaterias(BD,16,"Teoría de la Arquitectura",5,0,"");
        InsertarMaterias(BD,16,"Historia III",5,0,"");
        InsertarMaterias(BD,16,"Construcciones III",5,0,"");
        InsertarMaterias(BD,16,"Estructuras III",5,0,"");
        InsertarMaterias(BD,16,"Instalaciones III",5,0,"");
        InsertarMaterias(BD,16,"Planificación Urbana",5,0,"");
        InsertarMaterias(BD,16,"Proyecto Urbano",6,0,"");
        InsertarMaterias(BD,16,"Proyecto Arquitectónico",6,0,"");
        InsertarMaterias(BD,16,"Dirección y Legislación de Obra",6,0,"");
        InsertarMaterias(BD,16,"Materias Optativas",6,0,"");

        InsertarMaterias(BD,17,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,17,"Introducción al conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,17,"Introducción al Conocimiento Proyectual 1",1,0,"");
        InsertarMaterias(BD,17,"Introducción al Conocimiento Proyectual 2",1,0,"");
        InsertarMaterias(BD,17,"Matemática",1,0,"");
        InsertarMaterias(BD,17,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,17,"Semiología",1,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 1",2,0,"");
        InsertarMaterias(BD,17,"Narrativas Audiovisuales",2,0,"");
        InsertarMaterias(BD,17,"Escrituras Audiovisuales 1",2,0,"");
        InsertarMaterias(BD,17,"Dibujo y Representación Audiovisual",2,0,"");
        InsertarMaterias(BD,17,"Montaje 1",2,0,"");
        InsertarMaterias(BD,17,"Iluminación y Cámara 1",2,0,"");
        InsertarMaterias(BD,17,"Sonido 1",2,0,"");
        InsertarMaterias(BD,17,"Estética",2,0,"");
        InsertarMaterias(BD,17,"Sociología",2,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 2",3,0,"");
        InsertarMaterias(BD,17,"Producción y Planificación",3,0,"");
        InsertarMaterias(BD,17,"Escrituras Audiovisuales 2",3,0,"");
        InsertarMaterias(BD,17,"Técnicas Audiovisuales",3,0,"");
        InsertarMaterias(BD,17,"Iluminación y Cámara 2",3,0,"");
        InsertarMaterias(BD,17,"Sonido 2",3,0,"");
        InsertarMaterias(BD,17,"Historia Analítica de los Medios Audiovisuales",3,0,"");
        InsertarMaterias(BD,17,"Teorías Audiovisuales",3,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 3",4,0,"");
        InsertarMaterias(BD,17,"Difusión y Comercialización de los Medios",4,0,"");
        InsertarMaterias(BD,17,"Montaje 2",4,0,"");
        InsertarMaterias(BD,17,"Historia Analítica de los Medios Audiovisuales 2",4,0,"");
        InsertarMaterias(BD,17,"Teoría y Estética de los Medios",4,0,"");
        InsertarMaterias(BD,17,"Proyecto Audiovisual 4",5,0,"");

        InsertarMaterias(BD,18,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,18,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,18,"Introducción al Conocimiento Proyectual 1",1,0,"");
        InsertarMaterias(BD,18,"Introducción al Conocimiento Proyectual 2",1,0,"");
        InsertarMaterias(BD,18,"Matemática",1,0,"");
        InsertarMaterias(BD,18,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,18,"Semiología",1,0,"");
        InsertarMaterias(BD,18,"Fundamentos Geométricos del Modelaje",2,0,"");
        InsertarMaterias(BD,18,"Introducción al Proyecto",2,0,"");
        InsertarMaterias(BD,18,"Medios Expresivos 1",2,0,"");
        InsertarMaterias(BD,18,"Técnicas de Producción e Industria Nacional 1",2,0,"");
        InsertarMaterias(BD,18,"Historia del Diseño de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,18,"Análisis del Diseño de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,18,"Técnicas de Producción e Industria Nacional 2",2,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 1",3,0,"");
        InsertarMaterias(BD,18,"Materialización de Proyecto",3,0,"");
        InsertarMaterias(BD,18,"Medios Expresivos 2",3,0,"");
        InsertarMaterias(BD,18,"Técnicas de Producción Indumentaria 1",3,0,"");
        InsertarMaterias(BD,18,"Historia del Diseño de Indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,18,"Análisis del Diseño de indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,18,"Técnicas de Producción Indumentaria 2",3,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 2",4,0,"");
        InsertarMaterias(BD,18,"Sociología",4,0,"");
        InsertarMaterias(BD,18,"Comercialización y Mercado 1",4,0,"");
        InsertarMaterias(BD,18,"Técnicas de Producción Indumentaria 3",4,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 3",4,0,"");
        InsertarMaterias(BD,18,"Proyecto de Accesorios 1",4,0,"");
        InsertarMaterias(BD,18,"Técnicas de Producción Indumentaria 4",4,0,"");
        InsertarMaterias(BD,18,"Comunicación y Crítica",4,0,"");
        InsertarMaterias(BD,18,"Comercialización y Mercado 2",4,0,"");
        InsertarMaterias(BD,18,"Proyecto de Indumentaria 4",5,0,"");
        InsertarMaterias(BD,18,"Ética Profesional",5,0,"");
        InsertarMaterias(BD,18,"Proyecto de Accesorios 2",5,0,"");
        InsertarMaterias(BD,18,"Optativa 1",5,0,"");
        InsertarMaterias(BD,18,"Optativa 2",5,0,"");
        InsertarMaterias(BD,18,"Trabajo Final de Carrera",5,0,"");
        InsertarMaterias(BD,18,"Práctica Profesional Asistida",5,0,"");


        InsertarMaterias(BD,19,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,19,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,19,"Introducción al Conocimiento Proyectual 1",1,0,"");
        InsertarMaterias(BD,19,"Introducción al Conocimiento Proyectual 2",1,0,"");
        InsertarMaterias(BD,19,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,19,"Matemática",1,0,"");
        InsertarMaterias(BD,19,"Semiología",1,0,"");
        InsertarMaterias(BD,19,"Diseño Gráfico 1",2,0,"");
        InsertarMaterias(BD,19,"Morfología 1",2,0,"");
        InsertarMaterias(BD,19,"Tipografía 1",2,0,"");
        InsertarMaterias(BD,19,"Historia 1",2,0,"");
        InsertarMaterias(BD,19,"Comunicación 1",2,0,"");
        InsertarMaterias(BD,19,"Tecnología 1",2,0,"");
        InsertarMaterias(BD,19,"Electiva: Fotografía o Ilustración",2,0,"");
        InsertarMaterias(BD,19,"Diseño Gráfico 2",3,0,"");
        InsertarMaterias(BD,19,"Morfología 2",3,0,"");
        InsertarMaterias(BD,19,"Historia 2",3,0,"");
        InsertarMaterias(BD,19,"Comunicación 2",3,0,"");
        InsertarMaterias(BD,19,"Medios Expresivos 1",3,0,"");
        InsertarMaterias(BD,19,"Tecnología 2",3,0,"");
        InsertarMaterias(BD,19,"Diseño Gráfico 3",4,0,"");
        InsertarMaterias(BD,19,"Tipografía 2 *",4,0,"");
        InsertarMaterias(BD,19,"Legislación y Práctica Profesional",4,0,"");
        InsertarMaterias(BD,19,"Electiva Socio Humanística",4,0,"");
        InsertarMaterias(BD,19,"Medios Expresivos 2",4,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",4,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",4,0,"");
        InsertarMaterias(BD,19,"Diseño Gráfico 4",5,0,"");
        InsertarMaterias(BD,19,"Electiva Orientada 1",5,0,"");
        InsertarMaterias(BD,19,"Electiva Orientada 2",5,0,"");
        InsertarMaterias(BD,19,"Materia Optativa 1",5,0,"");
        InsertarMaterias(BD,19,"Materia Optativa 2",5,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",5,0,"");
        InsertarMaterias(BD,19,"Seminario optativo",5,0,"");

        InsertarMaterias(BD,20,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,20,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,20,"Introducción al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,20,"Introducción al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,20,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,20,"Matemática",1,0,"");
        InsertarMaterias(BD,20,"Semiología",1,0,"");
        InsertarMaterias(BD,20,"Taller de Diseño Industrial 1",2,0,"");
        InsertarMaterias(BD,20,"Tecnología 1",2,0,"");
        InsertarMaterias(BD,20,"Morfología 1",2,0,"");
        InsertarMaterias(BD,20,"Matemática",2,0,"");
        InsertarMaterias(BD,20,"Física 1",2,0,"");
        InsertarMaterias(BD,20,"Análisis de Productos",2,0,"");
        InsertarMaterias(BD,20,"Taller de Diseño Industrial 2",3,0,"");
        InsertarMaterias(BD,20,"Tecnología 2",3,0,"");
        InsertarMaterias(BD,20,"Morfología 2",3,0,"");
        InsertarMaterias(BD,20,"Ergonomía y factores humanos",3,0,"");
        InsertarMaterias(BD,20,"Historia del Diseño Industrial 1",3,0,"");
        InsertarMaterias(BD,20,"Taller de Diseño Industrial 3",4,0,"");
        InsertarMaterias(BD,20,"Tecnología 3",4,0,"");
        InsertarMaterias(BD,20,"Morfología 3",4,0,"");
        InsertarMaterias(BD,20,"Historia del Diseño Industrial 2",4,0,"");
        InsertarMaterias(BD,20,"Física 2",4,0,"");
        InsertarMaterias(BD,20,"Taller de Diseño Industrial 4",5,0,"");
        InsertarMaterias(BD,20,"Tecnología 4",5,0,"");
        InsertarMaterias(BD,20,"Sociología aplicada al Diseño",5,0,"");
        InsertarMaterias(BD,20,"Gestión industrial",5,0,"");
        InsertarMaterias(BD,20,"Proyecto Final",6,0,"");
        InsertarMaterias(BD,20,"Gestión de proyectos",6,0,"");
        InsertarMaterias(BD,20,"Metodología del pensamiento proyectual",6,0,"");
        InsertarMaterias(BD,20,"Legislación y Práctica Profesional",6,0,"");
        InsertarMaterias(BD,20,"Materia/s optativas y electivas",6,0,"");

        InsertarMaterias(BD,21,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,21,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,21,"Introducción al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,21,"Introducción al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,21,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,21,"Matemática",1,0,"");
        InsertarMaterias(BD,21,"Semiología",1,0,"");
        InsertarMaterias(BD,21,"Fundamentos Geométricos del Textil",2,0,"");
        InsertarMaterias(BD,21,"Introducción al Proyecto",2,0,"");
        InsertarMaterias(BD,21,"Medios Expresivos 1",2,0,"");
        InsertarMaterias(BD,21,"Técnicas de Producción e Industria Nacional 1",2,0,"");
        InsertarMaterias(BD,21,"Historia del Diseño de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,21,"Análisis del Diseño de Indumentaria y Textil 1",2,0,"");
        InsertarMaterias(BD,21,"Técnicas de Producción e Industria Nacional 2",2,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 1",3,0,"");
        InsertarMaterias(BD,21,"Proceso de Teñido, Estampado y Terminación",3,0,"");
        InsertarMaterias(BD,21,"Medios Expresivos 2",3,0,"");
        InsertarMaterias(BD,21,"Técnicas de Producción Textil 1",3,0,"");
        InsertarMaterias(BD,21,"Historia del Diseño de Indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,21,"Análisis del Diseño de Indumentaria y Textil 2",3,0,"");
        InsertarMaterias(BD,21,"Técnicas de Producción Textil 2",3,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 2",4,0,"");
        InsertarMaterias(BD,21,"Sociología",4,0,"");
        InsertarMaterias(BD,21,"Historia del Textil",4,0,"");
        InsertarMaterias(BD,21,"Comercialización y Mercado 1",4,0,"");
        InsertarMaterias(BD,21,"Técnicas de Producción Textil 3",4,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 3",4,0,"");
        InsertarMaterias(BD,21,"Técnicas de Producción Textil 4",4,0,"");
        InsertarMaterias(BD,21,"Comunicación y Crítica",4,0,"");
        InsertarMaterias(BD,21,"Comercialización y Mercado 2",4,0,"");
        InsertarMaterias(BD,21,"Proyecto Textil 4",5,0,"");
        InsertarMaterias(BD,21,"Ética Profesional",5,0,"");
        InsertarMaterias(BD,21,"Proyecto de Accesorios 1",5,0,"");
        InsertarMaterias(BD,21,"Optativa 1",5,0,"");
        InsertarMaterias(BD,21,"Optativa 2",5,0,"");
        InsertarMaterias(BD,21,"Trabajo Final de la Carrera",5,0,"");
        InsertarMaterias(BD,21,"Práctica Profesional Asistida",5,0,"");

        InsertarMaterias(BD,22,"Introducción al Conocimiento de la Sociedad y el Estado",1,0,"");
        InsertarMaterias(BD,22,"Introducción al Pensamiento Científico",1,0,"");
        InsertarMaterias(BD,22,"Introducción al Conocimiento Proyectual I",1,0,"");
        InsertarMaterias(BD,22,"Introducción al Conocimiento Proyectual II",1,0,"");
        InsertarMaterias(BD,22,"Taller de Dibujo",1,0,"");
        InsertarMaterias(BD,22,"Matemática",1,0,"");
        InsertarMaterias(BD,22,"Biología",1,0,"");
        InsertarMaterias(BD,22,"Planificación y Diseño del Paisaje I",2,0,"");
        InsertarMaterias(BD,22,"Vegetación I",2,0,"");
        InsertarMaterias(BD,22,"Química General",2,0,"");
        InsertarMaterias(BD,22,"Química Biológica",2,0,"");
        InsertarMaterias(BD,22,"Botánica General",2,0,"");
        InsertarMaterias(BD,22,"Botánica Sistemática",2,0,"");
        InsertarMaterias(BD,22,"Física Aplicada",2,0,"");
        InsertarMaterias(BD,22,"Materiales, Elementos y Procedimientos de Construcción",2,0,"");
        InsertarMaterias(BD,22,"Sistemas de Representación Geométrica",2,0,"");
        InsertarMaterias(BD,22,"Planificación y Diseño del Paisaje II",3,0,"");
        InsertarMaterias(BD,22,"Vegetación II",3,0,"");
        InsertarMaterias(BD,22,"Fisiología Vegetal",3,0,"");
        InsertarMaterias(BD,22,"Edafología",3,0,"");
        InsertarMaterias(BD,22,"Climatología y Fenología",3,0,"");
        InsertarMaterias(BD,22,"Morfología y Comunicación I",3,0,"");
        InsertarMaterias(BD,22,"Instalaciones y Equipamientos",3,0,"");
        InsertarMaterias(BD,22,"Topografía",3,0,"");
        InsertarMaterias(BD,22,"Planificación y Diseño del Paisaje III",4,0,"");
        InsertarMaterias(BD,22,"Morfología y Comunicación II",4,0,"");
        InsertarMaterias(BD,22,"Historia de la Arquitectura Paisajística I",4,0,"");
        InsertarMaterias(BD,22,"Geografía",4,0,"");
        InsertarMaterias(BD,22,"Ecología",4,0,"");
        InsertarMaterias(BD,22,"Fitogeografía",4,0,"");
        InsertarMaterias(BD,22,"Manejo del Suelo y la Vegetación",4,0,"");
        InsertarMaterias(BD,22,"Electiva",4,0,"");
        InsertarMaterias(BD,22,"Planificación y Diseño del Paisaje IV",5,0,"");
        InsertarMaterias(BD,22,"Trabajo Final",5,0,"");
        InsertarMaterias(BD,22,"Historia de la Arquitectura Paisajística II",5,0,"");
        InsertarMaterias(BD,22,"Planeamiento Urbano y Regional",5,0,"");
        InsertarMaterias(BD,22,"Ecología del Paisaje",5,0,"");
        InsertarMaterias(BD,22,"Electivas",5,0,"");
        InsertarMaterias(BD,22,"Práctica Profesional",5,0,"");

        InsertarMaterias(BD,23,"Análisis Matemático I",1,0,"");
        InsertarMaterias(BD,23,"Economía",1,0,"");
        InsertarMaterias(BD,23,"Sociología",1,0,"");
        InsertarMaterias(BD,23,"Metodología de las Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,23,"Álgebra",1,0,"");
        InsertarMaterias(BD,23,"Historia Económica y Social General",1,0,"");
        InsertarMaterias(BD,23,"Teoría Contable",2,0,"");
        InsertarMaterias(BD,23,"Estadística I",2,0,"");
        InsertarMaterias(BD,23,"Historia Económica y Social Argentina",2,0,"");
        InsertarMaterias(BD,23,"Microeconomía",2,0,"");
        InsertarMaterias(BD,23,"Instituciones de Derecho Público",2,0,"");
        InsertarMaterias(BD,23,"Administración General",2,0,"");
        InsertarMaterias(BD,23,"Instituciones de Derecho Privado",3,0,"");
        InsertarMaterias(BD,23,"Sistemas Administrativos",3,0,"");
        InsertarMaterias(BD,23,"Tecnología de la Información",3,0,"");
        InsertarMaterias(BD,23,"Cálculo Financiero",3,0,"");
        InsertarMaterias(BD,23,"Gestión y Costos",3,0,"");
        InsertarMaterias(BD,23,"Macroeconomía y Política Económica",3,0,"");
        InsertarMaterias(BD,23,"Administración Financiera",3,0,"");
        InsertarMaterias(BD,23,"Análisis Matemático II",3,0,"");
        InsertarMaterias(BD,23,"Estadística II",3,0,"");
        InsertarMaterias(BD,23,"Matemática para Economistas",3,0,"");
        InsertarMaterias(BD,23,"Dinero, Crédito y Bancos",3,0,"");
        InsertarMaterias(BD,23,"Estadística Actuarial",3,0,"");
        InsertarMaterias(BD,23,"Análisis Numérico",3,0,"");
        InsertarMaterias(BD,23,"Biometría Actuarial",3,0,"");
        InsertarMaterias(BD,23,"Teoría Actuarial de los Seguros Personales",3,0,"");
        InsertarMaterias(BD,23,"Teoría Actuarial de los Seguros Patrimoniales",3,0,"");
        InsertarMaterias(BD,23,"Teoría de los Fondos y Planes de Jubilaciones, Pensiones y Salud",3,0,"");
        InsertarMaterias(BD,23,"Teoría del Equilibrio Actuarial",3,0,"");
        InsertarMaterias(BD,23,"Bases Actuariales de las Inversiones y Financiaciones",3,0,"");
        InsertarMaterias(BD,23,"Electivas u Optativas",3,0,"");
        InsertarMaterias(BD,23,"Seminario de Integración y Aplicación (Trabajo Final)",3,0,"");

        InsertarMaterias(BD,24,"Análisis Matemático I",1,0,"");
        InsertarMaterias(BD,24,"Economía",1,0,"");
        InsertarMaterias(BD,24,"Sociología",1,0,"");
        InsertarMaterias(BD,24,"Metodología de las Ciencias Sociales",1,0,"");
        InsertarMaterias(BD,24,"Álgebra",1,0,"");
        InsertarMaterias(BD,24,"Historia Económica y Social General",1,0,"");
        InsertarMaterias(BD,24,"Teoría Contable",2,0,"");
        InsertarMaterias(BD,24,"Estadística I",2,0,"");
        InsertarMaterias(BD,24,"Historia Económica y Social Argentina",2,0,"");
        InsertarMaterias(BD,24,"Microeconomía I",2,0,"");
        InsertarMaterias(BD,24,"Instituciones de Derecho Público",2,0,"");
        InsertarMaterias(BD,24,"Administración General",2,0,"");
        InsertarMaterias(BD,24,"Instituciones de Derecho Privado",3,0,"");
        InsertarMaterias(BD,24,"Sistemas Administrativos",3,0,"");
        InsertarMaterias(BD,24,"Tecnología de la Información",3,0,"");
        InsertarMaterias(BD,24,"Cálculo Financiero",3,0,"");
        InsertarMaterias(BD,24,"Macroeconomía y Política Económica",3,0,"");
        InsertarMaterias(BD,24,"Administración Financiera",3,0,"");
        InsertarMaterias(BD,24,"Gestión y Costos para Contadores",3,0,"");
        InsertarMaterias(BD,24,"Sistemas Contables",3,0,"");
        InsertarMaterias(BD,24,"Contabilidad Patrimonial",3,0,"");
        InsertarMaterias(BD,24,"Sistemas de Costos",3,0,"");
        InsertarMaterias(BD,24,"Derecho del Trabajo y de la Seguridad Social",3,0,"");
        InsertarMaterias(BD,24,"Auditoría",3,0,"");
        InsertarMaterias(BD,24,"Teoría y Técnica Impositiva I",3,0,"");
        InsertarMaterias(BD,24,"Teoría y Técnica Impositiva II",3,0,"");
        InsertarMaterias(BD,24,"Derecho Económico I",3,0,"");
        InsertarMaterias(BD,24,"Derecho Económico II",3,0,"");
        InsertarMaterias(BD,24,"Actuación Profesional Judicial",3,0,"");
        InsertarMaterias(BD,24,"Electivas u Optativas",3,0,"");
        InsertarMaterias(BD,24,"Seminario de Integración y Aplicación (Trabajo Final)",3,0,"");

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
