package com.example.projectefinalandroid.repos

import android.content.Context
import android.database.Cursor
import com.example.projectefinalandroid.*

class MaquinesRepository (val context: Context) {
    val dbHelper = DatabaseHandler(context)
    val db = dbHelper.writableDatabase

    fun find(): Cursor? {

        var cursor: Cursor? = null

        if (db.isOpen) {
            val query = "SELECT * FROM $TABLE_MAQUINES"
            val c = db.rawQuery(query, null)
            if (c.moveToFirst()) {
                cursor = c
            }
        }
        return cursor
    }

    fun checkZones(idZona: String): Boolean {

        if (db.isOpen) {
            val query = "SELECT * FROM $TABLE_MAQUINES WHERE $MAQUINES_ZONA = $idZona"
            val c = db.rawQuery(query, null)
            if (c.moveToFirst()) {
                return true
            }
            c.close()
        }
        return false
    }

    fun checkTipus(idTipus: String): Boolean {

        if (db.isOpen) {
            val query = "SELECT * FROM $TABLE_MAQUINES WHERE $MAQUINES_TIPUS = $idTipus"
            val c = db.rawQuery(query, null)
            if (c.moveToFirst()) {
                return true
            }
            c.close()
        }
        return false
    }
}