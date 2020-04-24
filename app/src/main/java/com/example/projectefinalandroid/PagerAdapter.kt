package com.example.projectefinalandroid

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.projectefinalandroid.pagerFragments.MapsFrg
import com.example.projectefinalandroid.pagerFragments.MaquinesFrg
import com.example.projectefinalandroid.pagerFragments.TipusFrg
import com.example.projectefinalandroid.pagerFragments.ZonesFrg

class PagerAdapter(context: Context, fm: FragmentManager, internal var totalTabs: Int): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                MaquinesFrg()
            }
            1 -> {
                TipusFrg()
            }
            2 -> {
                ZonesFrg()
            }

            3 -> {
                MapsFrg()
            }
            else -> null
        }

    }

    override fun getCount(): Int {
        return totalTabs
    }

}