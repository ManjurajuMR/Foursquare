package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.Addfav
import com.example.foursquare.model.Review
import com.example.foursquare.model.ReviewData
import com.example.foursquare.repository.AddFavouriteRepository
import com.example.foursquare.repository.AddReviewRepository

class AddFavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val mainRepository = AddFavouriteRepository(application)

//    fun addfav(token: String,userid: Int,placeid: Int): LiveData<FavData> {
//
//        return mainRepository.addfav(token,userid,placeid)
//    }

    fun addfav(token:String,user : HashMap<String,String>): LiveData<Addfav> {

        return mainRepository.addUserFav(token,user)
    }
}