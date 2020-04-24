package com.example.projectefinalandroid.repos

import android.content.Context
import android.database.Cursor
import com.example.projectefinalandroid.*


class ZonesRepository (val context: Context) {
    val dbHelper = DatabaseHandler(context)
    val db = dbHelper.writableDatabase

    fun find(): Cursor? {

        var cursor: Cursor? = null

        if (db.isOpen) {
            val query = "SELECT * FROM $TABLE_ZONES"
            val c = db.rawQuery(query, null)
            if (c.moveToFirst()) {
                cursor = c
            }
        }
        return cursor
    }

    fun add(desc: String) {
        if (db.isOpen) {
            val query = "INSERT INTO $TABLE_ZONES($ZONES_DESCRIPCIO) VALUES ('$desc')"
            db.execSQL(query)
        }
    }

    fun modify(id: String, newDesc: String) {

        if (db.isOpen) {
            val query = "UPDATE $TABLE_ZONES SET $ZONES_DESCRIPCIO = '$newDesc' WHERE $ZONES_ID = $id"
            db.execSQL(query)
        }

    }

    fun delete(id: String) {
        if (db.isOpen) {
            val query = "DELETE FROM $TABLE_ZONES WHERE $ZONES_ID = $id"
            db.execSQL(query)
        }
    }
}