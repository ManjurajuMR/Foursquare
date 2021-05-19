package com.example.foursquare.services

import com.example.foursquare.model.DelFavourite
import com.example.foursquare.model.Favourites
import retrofit2.Call
import retrofit2.http.*

interface FavouritesApi {

    @GET("getFavourite?")
    fun getFavourites(@Header("Authorization") token: String,

                      @Query("userId") userId: String?,
                      @Query("pageNo") pageNo:Int,
                      @Query("pageSize") pageSize:Int) : Call<Favourites>

    @DELETE("deleteFavourite")
    fun delFavourite(
        @Header("Authorization") token:String,
        @Query("userId") userId: Int?,
        @Query("placeId") placeId: Int
    ) : Call<DelFavourite>

}