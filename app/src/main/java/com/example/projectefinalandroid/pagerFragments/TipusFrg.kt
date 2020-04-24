package com.example.projectefinalandroid.pagerFragments


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.projectefinalandroid.FormAct

import com.example.projectefinalandroid.R
import com.example.projectefinalandroid.cursorAdapters.TipusAdapter
import com.example.projectefinalandroid.repos.TipusRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TipusFrg : Fragment(), TipusAdapter.TipusInterface {


    private lateinit var tipusList: ListView
    private lateinit var tipusAdapter: TipusAdapter
    private lateinit var newBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tipus_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tipusList = view.findViewById(R.id.tipusList)
        newBtn = view.findViewById(R.id.newTipusBtn)
        tipusAdapter = TipusAdapter(context!!, TipusRepository(context!!).find(), this)
        tipusList.adapter = tipusAdapter
        initListeners()
    }

    private fun initListeners() {
        newBtn.setOnClickListener {

            val intent = Intent(context, FormAct::class.java)
            intent.putExtra("type", "type")
            intent.putExtra("isEdit", false)
            startActivity(intent)

        }
    }

    override fun didDeleteType() {
        loadData()
    }

    override fun editType(id: String, description: String) {

        val intent = Intent(context, FormAct::class.java)
        intent.putExtra("type", "type")
        intent.putExtra("isEdit", true)
        intent.putExtra("itemID", id)
        intent.putExtra("description", description)
        startActivity(intent)
    }

    private fun loadData() {
        tipusAdapter.changeCursor(TipusRepository(context!!).find())
        tipusAdapter.notifyDataSetChanged()
    }

}
