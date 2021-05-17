package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.FavData
import com.example.foursquare.model.ReviewData
import com.example.foursquare.repository.AddFavouriteRepository
import com.example.foursquare.repository.AddReviewRepository

class AddFavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val mainRepository = AddFavouriteRepository(application)

    fun addfav(token: String,userid: Int,placeid: Int): LiveData<FavData> {

        return mainRepository.addfav(token,userid,placeid)
    }
}