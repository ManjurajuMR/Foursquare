package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.Rating
import com.example.foursquare.repository.HomeScreenRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {
     var homeRepository = HomeScreenRepository(application)

    fun getPlaceDetailsByPlaceId(placeId: Int) {
         return homeRepository.getPlaceDetailsByPlaceId(placeId)
    }

    fun addRating(token : String,rating : HashMap<String,String>) : LiveData<Rating> {
        return homeRepository.addRating(token,rating)
    }
}