package com.example.foursquare.services

import com.example.foursquare.model.Favourites
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FavouritesApi {
    @GET("getFavourite?")
    fun getFavourites(@Header("Authorization") token: String,
                      @Query("userId") userId: Int?,
                      @Query("pageNo") pageNo :Int,
                      @Query("pageSize") pageSize :Int) : Call<Favourites>
}