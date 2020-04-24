package com.example.projectefinalandroid.cursorAdapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.projectefinalandroid.R

class MaquinesAdapter(context: Context, c: Cursor?): CursorAdapter(context, c, 0) {

    private lateinit var numeroSerieTV: TextView
    private lateinit var nomTV: TextView
    private lateinit var dataTV: TextView
    private lateinit var tipusTV: TextView
    private lateinit var zonaTV: TextView

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        return LayoutInflater.from(p0).inflate(R.layout.maquines_list_cell, p2, false)
    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {

        numeroSerieTV = p0!!.findViewById(R.id.numeroSerie)
        nomTV = p0.findViewById(R.id.nomClient)
        dataTV = p0.findViewById(R.id.dataModificacio)
        tipusTV = p0.findViewById(R.id.tipusMaquina)
        zonaTV = p0.findViewById(R.id.zonaMaquina)

        val numeroSerie = cursor.getString(cursor.getColumnIndex("numeroSerie"))
        val nom = cursor.getString(cursor.getColumnIndex("nom"))
        val data = cursor.getString(cursor.getColumnIndex("ultimaRevisio"))
        val tipus = cursor.getString(cursor.getColumnIndex("tipus"))
        val zona = cursor.getString(cursor.getColumnIndex("zona"))

        numeroSerieTV.text = numeroSerie
        nomTV.text = nom
        dataTV.text = data
        tipusTV.text = tipus
        zonaTV.text = zona

    }

}