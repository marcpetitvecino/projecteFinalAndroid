package com.example.projectefinalandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "DB"
val DATABASE_VERSION = 1

// TAULA MÀQUINES

val TABLE_MAQUINES = "Maquines"
val MAQUINES_ID = "_id"
val MAQUINES_NOM = "nom"
val MAQUINES_ADDRESS = "adreça"
val MAQUINES_CP = "cp"
val MAQUINES_POBLACIO = "poblacio"
val MAQUINES_TELEFON = "telefon"
val MAQUINES_EMAIL = "email"
val MAQUINES_NUMEROSERIE = "numeroSerie"
val MAQUINES_REVISIO = "ultimaRevisio"
val MAQUINES_TIPUS = "tipus"
val MAQUINES_ZONA = "zona"

// TAULA MÀQUINES


// TAULA ZONES

val TABLE_ZONES = "Zones"
val ZONES_ID = "_id"
val ZONES_DESCRIPCIO = "descripcio"

// TAULA ZONES


// TAULA TIPUS MÀQUINES

val TABLE_TIPUS = "Tipus"
val TIPUS_ID = "_id"
val TIPUS_DESCRIPCIO = "descripcio"

// TAULA TIPUS MÀQUINES

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase) {

        p0.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_ZONES (" +
                " $ZONES_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ZONES_DESCRIPCIO VARCHAR(80))")

        p0.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_TIPUS (" +
                " $TIPUS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $TIPUS_DESCRIPCIO VARCHAR(80))")

        p0.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_MAQUINES (" +
                " $MAQUINES_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $MAQUINES_NOM VARCHAR(80) NOT NULL," +
                " $MAQUINES_ADDRESS VARCHAR(80) NOT NULL," +
                " $MAQUINES_CP VARCHAR(10) NOT NULL," +
                " $MAQUINES_POBLACIO VARCHAR(80) NOT NULL," +
                " $MAQUINES_TELEFON VARCHAR(80)," +
                " $MAQUINES_EMAIL VARCHAR(80)," +
                " $MAQUINES_NUMEROSERIE VARCHAR(80) NOT NULL," +
                " $MAQUINES_REVISIO VARCHAR(80)," +
                " $MAQUINES_TIPUS INTEGER NOT NULL," +
                " $MAQUINES_ZONA INTEGER NOT NULL," +
                " FOREIGN KEY ($MAQUINES_TIPUS) REFERENCES $TABLE_TIPUS($TIPUS_ID) ON DELETE RESTRICT," +
                " FOREIGN KEY ($MAQUINES_ZONA) REFERENCES $TABLE_ZONES($ZONES_ID) ON DELETE RESTRICT)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}