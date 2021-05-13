package com.example.foursquare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.Review
import com.example.foursquare.model.ReviewData
import com.example.foursquare.repository.AddReviewRepository



class AddReviewViewModel(application: Application): AndroidViewModel(application) {
    private val mainRepository = AddReviewRepository(application)

/*    fun addReviews(token: String,userid: Int,placeid: Int,review: String): LiveData<ReviewData> {

        return mainRepository.addreview(token,userid,placeid,review)
    }*/

    fun addReview(token:String,user : HashMap<String,String>): LiveData<Review> {

        return mainRepository.addUserReview(token,user)
    }
}