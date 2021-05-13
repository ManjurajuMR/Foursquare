package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.UserReviews
import com.example.foursquare.repository.ReviewRepository

class ReviewsViewModel(application: Application): AndroidViewModel(application) {
    var reviewsRepository = ReviewRepository(application)

    fun getPlaceDetailsByPlaceId(PlaceId : Int, pageNo :Int, pageSize :Int): LiveData<UserReviews> {
        return reviewsRepository.getReviews(PlaceId,pageNo,pageSize)
    }

}