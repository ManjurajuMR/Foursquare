package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.Place
import com.example.foursquare.repository.AuthenticationRepository
import com.example.foursquare.repository.HomeScreenRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {
     var homeRepository = HomeScreenRepository(application)

    fun getPlaceDetailsByPlaceId(placeId: Int) {
         return homeRepository.getPlaceDetailsByPlaceId(placeId)
    }
}