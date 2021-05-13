package com.example.foursquare.services

import com.example.foursquare.model.ReviewData
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AddReviewApi {
    @POST("addreview")
    fun addreview(@Header("authorization") token: String,
                  @Query("userId") userId:Int,
                  @Query("placeId") placeId:Int,
                  @Query("review") review: String) : Call<ReviewData>

}