package com.example.foursquare

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeAdapter(private val context: Context, fm: FragmentManager, var totalTabs:Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        when(position){
            0 -> return NearyouFragment()
            1 -> return ToppickFragment()
            2 -> return PopularFragment()
            3 -> return LunchFragment()
            else -> return CoffeeFragment()
        }

    }

    override fun getCount(): Int {
        return totalTabs
    }
}