package com.example.foursquare.services

import com.example.foursquare.model.Place
import com.example.foursquare.model.Rplace
import com.example.foursquare.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomescreenApi {

    @GET("PlaceApi/placeById?")
    fun getPlaceDetailsByPlaceId(
        @Query("placeId") placeId: Int?
    ) : Call<Rplace>

}