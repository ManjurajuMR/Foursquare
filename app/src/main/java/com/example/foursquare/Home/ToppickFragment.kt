package com.example.foursquare.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.R
import com.example.foursquare.viewmodel.PlaceViewModel

class ToppickFragment : Fragment() {
    private lateinit var placeViewModel : PlaceViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        placeViewModel= ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(PlaceViewModel::class.java)

        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_toppick, container, false)

        loadPlaceData()

        return root
    }

    private fun loadPlaceData() {

        placeViewModel.getPlaceDetails(type="topPic", 13.343528531501212, 74.74668065517001)
            ?.observe(viewLifecycleOwner, {
                Log.d("res","re")
                if (it != null) {
                    // Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    val adapter = RecyclerviewAdapter(it.data,requireContext())
                    val rv : RecyclerView = view?.findViewById(R.id.toppick_recyclerView)!!
                    rv.adapter = adapter
                    rv.layoutManager = LinearLayoutManager(requireContext())
                }

            })
    }

}