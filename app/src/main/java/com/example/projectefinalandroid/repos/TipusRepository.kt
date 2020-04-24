package com.example.projectefinalandroid.repos

import android.content.Context
import android.database.Cursor
import com.example.projectefinalandroid.*

class TipusRepository(val context: Context) {

    val dbHelper = DatabaseHandler(context)
    val db = dbHelper.writableDatabase

    fun find(): Cursor? {

        var cursor: Cursor? = null

        if (db.isOpen) {
            val query = "SELECT * FROM $TABLE_TIPUS"
            val c = db.rawQuery(query, null)
            if (c.moveToFirst()) {
                cursor = c
            }
        }
        return cursor
    }

    fun add(desc: String) {
        if (db.isOpen) {
            val query = "INSERT INTO $TABLE_TIPUS($TIPUS_DESCRIPCIO) VALUES ('$desc')"
            db.execSQL(query)
        }
    }

    fun modify(id: String, newDesc: String) {

        if (db.isOpen) {
            val query = "UPDATE $TABLE_TIPUS SET $TIPUS_DESCRIPCIO = '$newDesc' WHERE $TIPUS_ID = $id"
            db.execSQL(query)
        }

    }

    fun delete(id: String) {
        if (db.isOpen) {
            val query = "DELETE FROM $TABLE_TIPUS WHERE $TIPUS_ID = $id"
            db.execSQL(query)
        }
    }

}