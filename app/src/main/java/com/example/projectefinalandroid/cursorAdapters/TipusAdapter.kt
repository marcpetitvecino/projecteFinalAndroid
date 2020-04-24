package com.example.projectefinalandroid.cursorAdapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.projectefinalandroid.R
import com.example.projectefinalandroid.repos.MaquinesRepository
import com.example.projectefinalandroid.repos.TipusRepository
import com.example.projectefinalandroid.repos.ZonesRepository

class TipusAdapter (val context: Context, c: Cursor?, val delegate: TipusInterface?): CursorAdapter(context, c, 0) {

    private lateinit var idTV: TextView
    private lateinit var descTV: TextView
    private lateinit var editBtn: ImageView
    private lateinit var deleteBtn: ImageView

    interface TipusInterface {
        fun didDeleteType()
        fun editType(id: String, description: String)
    }

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        return LayoutInflater.from(p0).inflate(R.layout.tipus_list_cell, p2, false)
    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {

        idTV = p0!!.findViewById(R.id.tipusID)
        descTV = p0.findViewById(R.id.tipusDescripcion)
        editBtn = p0.findViewById(R.id.editBtn)
        deleteBtn = p0.findViewById(R.id.deleteBtn)

        val id = cursor.getString(cursor.getColumnIndex("_id"))
        val desc = cursor.getString(cursor.getColumnIndex("descripcio"))

        idTV.text = "ID del tipus: $id"
        descTV.text = desc

        editBtn.setOnClickListener {
            delegate?.editType(id, desc)
        }

        deleteBtn.setOnClickListener {

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Segur que vols esborrar aquest tipus?")
            builder.setNeutralButton("No", null)
            builder.setNegativeButton("Si") { dialog, which ->

                if (MaquinesRepository(context).checkTipus(id)) {
                    builder.setTitle("")
                    builder.setMessage("No pots esborrar un tipus que s'estigui utilitzant")
                    builder.show()
                } else {
                    TipusRepository(context).delete(id)
                    delegate?.didDeleteType()
                }

            }

            builder.show()
        }


    }

}