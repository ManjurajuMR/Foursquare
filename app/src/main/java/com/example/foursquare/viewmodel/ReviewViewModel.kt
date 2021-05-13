package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.ReviewData
import com.example.foursquare.repository.ReviewRepository



class ReviewViewModel(application: Application): AndroidViewModel(application) {
    private val mainRepository = ReviewRepository(application)

    fun addReviews(token: String,userid: Int,placeid: Int,review: String): LiveData<ReviewData> {

        return mainRepository.addreview(token,userid,placeid,review)
    }
}