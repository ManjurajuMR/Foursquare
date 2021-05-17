package com.example.foursquare.services


import com.example.foursquare.model.Rating
import com.example.foursquare.model.Rplace
import com.example.foursquare.model.User
import retrofit2.Call
import retrofit2.http.*

interface HomescreenApi {

    @GET("PlaceApi/placeById?")
    fun getPlaceDetailsByPlaceId(
        @Query("placeId") placeId: Int?
    ) : Call<Rplace>

    @POST("addRating")
    fun addRating(@Header("Authorization") token : String, @Body rating : HashMap<String,String>) : Call<Rating>
}