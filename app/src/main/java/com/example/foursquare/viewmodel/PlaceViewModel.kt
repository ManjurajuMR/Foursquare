package com.example.foursquare.viewmodel

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.PlaceData
import com.example.foursquare.model.Popular_PlaceData
import com.example.foursquare.repository.PlaceRepository

class PlaceViewModel(application: Application): AndroidViewModel(application) {
    private val placeRepository = PlaceRepository(application)

    private var locationData : MutableLiveData<Location> = MutableLiveData()
    fun setLocation(location : Location) {
        locationData.postValue( location)
    }
    fun getLocation() : LiveData<Location> =  locationData

    fun getPlaceDetails(type:String,latitude:Double,longitude:Double): LiveData<PlaceData> {
        Log.d("vmod","vm")
        return placeRepository.getPlaceDetails(type,latitude,longitude)
    }

    fun getPopularPlaceDetails(type:String,latitude:Double,longitude:Double): LiveData<Popular_PlaceData> {
        Log.d("vmod","vm")
        return placeRepository.getPopularPlaceDetails(type,latitude,longitude)
    }

}