package com.example.projectefinalandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.projectefinalandroid.pagerFragments.TipusFrg
import com.example.projectefinalandroid.pagerFragments.ZonesFrg
import com.example.projectefinalandroid.repos.TipusRepository
import com.example.projectefinalandroid.repos.ZonesRepository

class FormAct : AppCompatActivity() {

    private lateinit var idTV: TextView
    private lateinit var descET: EditText
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        idTV = findViewById(R.id.idTV)
        descET = findViewById(R.id.descET)
        saveBtn = findViewById(R.id.saveBtn)

        val type: String = intent.extras!!.getString("type")!!
        val isEdit: Boolean = intent.extras!!.getBoolean("isEdit")
        val itemID: String? = intent.extras!!.getString("itemID")
        val description: String? = intent.extras!!.getString("description")

        if (isEdit) {

            if (itemID.isNullOrEmpty()) {
                idTV.visibility = View.GONE
            } else {
                idTV.text = when (type) {
                    "zone" -> "ID de la zona: $itemID"
                    "type" -> "ID del tipus: $itemID"
                    else -> ""
                }
            }

            if (!description.isNullOrEmpty()) {
                descET.setText(description)
            }

        }

        saveBtn.setOnClickListener {
            if (isEdit) {
                when (type) {
                    "zone" -> ZonesRepository(this).modify(itemID!!, descET.text.toString())
                    "type" -> TipusRepository(this).modify(itemID!!, descET.text.toString())
                }
            } else {
                when (type) {
                    "zone" -> ZonesRepository(this).add(descET.text.toString())
                    "type" -> TipusRepository(this).add(descET.text.toString())
                }
            }
            this.finish()
        }
    }

}
