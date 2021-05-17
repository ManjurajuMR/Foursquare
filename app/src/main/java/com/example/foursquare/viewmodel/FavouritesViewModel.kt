package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.Favourites
import com.example.foursquare.repository.FavouritesRepository

class FavouritesViewModel(application: Application): AndroidViewModel(application) {
    var favouritesRepository = FavouritesRepository(application)

    fun getFavouritesByUserId(token:String, userId : Int, pageNo :Int, pageSize :Int): LiveData<Favourites> {
        return favouritesRepository.getFavourites(token,userId,pageNo,pageSize)
    }
}