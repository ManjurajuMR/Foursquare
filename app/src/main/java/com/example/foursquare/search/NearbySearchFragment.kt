package com.example.foursquare.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.foursquare.R

class NearbySearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_nearby_search, container, false)

        val sug = arrayListOf("Top Pick", "Popular", "Lunch", "Coffee")

        val mlist = root.findViewById<ListView>(R.id.suggestions_list)

        val adapter = context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,sug) }
        mlist.adapter = adapter

        mlist.setOnItemClickListener { parent , view, position, id ->
            if (position == 0){
                Toast.makeText(context,"top pick",Toast.LENGTH_LONG).show()
            }
            if (position == 1){
                Toast.makeText(context,"popular",Toast.LENGTH_LONG).show()
            }
            if (position == 2){
                Toast.makeText(context,"lunch",Toast.LENGTH_LONG).show()
            }
            if (position == 3){
                Toast.makeText(context,"coffee",Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

}