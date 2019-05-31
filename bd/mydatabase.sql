CREATE TABLE "Carerras" ("ID_carrera" INTEGER PRIMARY KEY AUTOINCREMENT, "Nombre_Carrera" TEXT, "Nombre_Facultad" TEXT);
CREATE TABLE "Relacion_Carrera_Tag" ("ID_Carrera" INTEGER, "ID_Tag" INTEGER);
CREATE TABLE "Tags" ("ID_Tag" INTEGER PRIMARY KEY AUTOINCREMENT, "Nombre_Tag" TEXT);
