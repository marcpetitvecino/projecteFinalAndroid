package com.example.projectefinalandroid.cursorAdapters

import android.app.AlertDialog
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.projectefinalandroid.R
import com.example.projectefinalandroid.repos.MaquinesRepository
import com.example.projectefinalandroid.repos.ZonesRepository

class ZonesAdapter(val context: Context, c: Cursor?, val delegate: ZonesInterface?): CursorAdapter(context, c, 0) {

    private lateinit var idTV: TextView
    private lateinit var descTV: TextView
    private lateinit var editBtn: ImageView
    private lateinit var deleteBtn: ImageView

    interface ZonesInterface {
        fun didDeleteZone()
        fun editZone(id: String, description: String)
    }

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        return LayoutInflater.from(p0).inflate(R.layout.zones_list_cell, p2, false)
    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {

        idTV = p0!!.findViewById(R.id.zonaID)
        descTV = p0.findViewById(R.id.zonaDescripcion)
        editBtn = p0.findViewById(R.id.editBtn)
        deleteBtn = p0.findViewById(R.id.deleteBtn)

        val id = cursor.getString(cursor.getColumnIndex("_id"))
        val desc = cursor.getString(cursor.getColumnIndex("descripcio"))

        idTV.text = "ID de la zona: $id"
        descTV.text = desc

        editBtn.setOnClickListener {
            delegate?.editZone(id, desc)
        }

        deleteBtn.setOnClickListener {

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Segur que vols esborrar aquesta zona?")
            builder.setNeutralButton("No", null)
            builder.setNegativeButton("Si") { dialog, which ->

                if (MaquinesRepository(context).checkZones(id)) {
                    builder.setTitle("")
                    builder.setMessage("No pots esborrar una zona que s'estigui utilitzant")
                    builder.show()
                } else {
                    ZonesRepository(context).delete(id)
                    delegate?.didDeleteZone()
                }

            }

            builder.show()
        }


    }

}