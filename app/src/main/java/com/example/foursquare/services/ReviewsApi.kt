package com.example.foursquare.services

import com.example.foursquare.model.UserReviews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewsApi {
    @GET("reviews?")
    fun getReviews(@Query("PlaceId") PlaceId: Int?,
                   @Query("pageNo") pageNo :Int,
                   @Query("pageSize") pageSize :Int) : Call<UserReviews>
}