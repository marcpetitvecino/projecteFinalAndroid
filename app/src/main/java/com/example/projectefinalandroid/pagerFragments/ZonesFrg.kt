package com.example.projectefinalandroid.pagerFragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.content.ContextCompat.startActivity
import com.example.projectefinalandroid.FormAct

import com.example.projectefinalandroid.R
import com.example.projectefinalandroid.cursorAdapters.ZonesAdapter
import com.example.projectefinalandroid.repos.TipusRepository
import com.example.projectefinalandroid.repos.ZonesRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ZonesFrg : Fragment(), ZonesAdapter.ZonesInterface {

    private lateinit var zoneList: ListView
    private lateinit var zoneAdapter: ZonesAdapter
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
        return inflater.inflate(R.layout.zones_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zoneList = view.findViewById(R.id.zonesList)
        newBtn = view.findViewById(R.id.newZoneBtn)
        zoneAdapter = ZonesAdapter(context!!, ZonesRepository(context!!).find(), this)
        zoneList.adapter = zoneAdapter
        initListeners()
    }

    private fun initListeners() {
        newBtn.setOnClickListener {
            val intent = Intent(context, FormAct::class.java)
            intent.putExtra("type", "zone")
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }

    }

    override fun didDeleteZone() {
        loadData()
    }

    override fun editZone(id: String, description: String) {
        val intent = Intent(context, FormAct::class.java)
        intent.putExtra("type", "zone")
        intent.putExtra("isEdit", true)
        intent.putExtra("itemID", id)
        intent.putExtra("description", description)
        startActivity(intent)
    }

    private fun loadData() {
        zoneAdapter.changeCursor(ZonesRepository(context!!).find())
        zoneAdapter.notifyDataSetChanged()
    }

}
