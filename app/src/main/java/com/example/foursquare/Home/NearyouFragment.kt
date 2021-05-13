package com.example.foursquare.Home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.PhotosActivity
import com.example.foursquare.R
import com.example.foursquare.viewmodel.PlaceViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class NearyouFragment : Fragment(),RecyclerviewAdapter.OnItemClickListener {
    lateinit var locationManager: FusedLocationProviderClient
    private lateinit var placeViewModel : PlaceViewModel
    private  var googleMap : GoogleMap? = null
    private var mapReady : Boolean = false
    private var location : Location? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        placeViewModel= ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(PlaceViewModel::class.java)
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_nearyou, container, false)
//
        locationManager= context?.let { LocationServices.getFusedLocationProviderClient(it) }!!

        val fragment = childFragmentManager
        val supportMapFragment = SupportMapFragment.newInstance()
        fragment.beginTransaction().replace(R.id.mapLayout, supportMapFragment).commit()
        supportMapFragment.getMapAsync{
            googleMap = it
            mapReady = true
            Location()
        }

        loadPlaceData()
        Log.d("r11","r11")
        return root

    }

    //
    fun Location(){
        val Currentlocation=locationManager.lastLocation

        if (context?.let { ActivityCompat.checkSelfPermission(
                it,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) }
            !=PackageManager.PERMISSION_GRANTED && context?.let { ActivityCompat.checkSelfPermission(
                it,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) } !=PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101)
            return
        }
        Currentlocation.addOnSuccessListener {
            if (it != null) {
                Log.d("latlag1","${it.latitude}+${it.longitude}")
                if(mapReady){
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 15.0f))
                    googleMap?.addMarker(MarkerOptions().position(LatLng(it.latitude,it.longitude)).title("Me"))
                   // googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(it!!.latitude,it!!.longitude)))
                }

                //Toast.makeText(context, "${it.latitude},${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        } }


    private fun loadPlaceData() {

            placeViewModel.getPlaceDetails(type="nearBy", 13.343528531501212, 74.74668065517001)
                ?.observe(viewLifecycleOwner, {
                    Log.d("res","re")
                    if (it != null) {
                           // Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                        val adapter = RecyclerviewAdapter(it.data,requireContext(),this)
                        val rv : RecyclerView = view?.findViewById(R.id.nearyou_recyclerView)!!
                        rv.adapter = adapter
                        rv.layoutManager = LinearLayoutManager(requireContext())
                    }

                })
        }


    private fun enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                100
            )
        } else {
            if (googleMap != null) {

                googleMap?.isMyLocationEnabled = true

            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    enableMyLocationIfPermitted()
                }
                return
            }
        }
    }

    override fun onItemClick(position: Int) {
        Log.d("position", "${position} got")
        Toast.makeText(context, "Item is clicked ${position}", Toast.LENGTH_LONG).show()
    }
}
