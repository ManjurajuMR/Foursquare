package com.example.foursquare.services

import com.example.foursquare.model.Addfav
import com.example.foursquare.model.Review
import com.example.foursquare.model.ReviewData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AddFavouriteApi {
//    @POST("addfav")
//    fun addfav(@Header("authorization") token: String,
//                  @Query("userId") userId:Int,
//                  @Query("placeId") placeId:Int) : Call<FavData>

    @POST("addfavourite")
    fun addfav(
        @Header("Authorization") token:String,
        @Body fav : HashMap<String,String>
    ) : Call<Addfav>
}