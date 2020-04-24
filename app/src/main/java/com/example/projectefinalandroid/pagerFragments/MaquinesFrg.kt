package com.example.projectefinalandroid.pagerFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.example.projectefinalandroid.R
import com.example.projectefinalandroid.cursorAdapters.MaquinesAdapter
import com.example.projectefinalandroid.repos.MaquinesRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MaquinesFrg : Fragment() {

    private lateinit var maquinesList: ListView
    private lateinit var maquinesAdapter: MaquinesAdapter
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
        return inflater.inflate(R.layout.maquines_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        maquinesList = view.findViewById(R.id.maquinesList)
        newBtn = view.findViewById(R.id.newMaquinaBtn)
        maquinesAdapter = MaquinesAdapter(context!!, MaquinesRepository(context!!).find())
        maquinesList.adapter = maquinesAdapter
    }

    private fun initListeners() {
        newBtn.setOnClickListener {
            val intent
        }
    }

    private fun loadData() {
        maquinesAdapter.changeCursor(MaquinesRepository(context!!).find())
        maquinesAdapter.notifyDataSetChanged()
    }

}
