package com.example.foursquare.Home.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foursquare.Home.*
import com.google.android.material.appbar.AppBarLayout

class HomeAdapter(private val context: Context, fm: FragmentManager,behavior: Int, var totalTabs:Int): FragmentPagerAdapter(fm) {

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