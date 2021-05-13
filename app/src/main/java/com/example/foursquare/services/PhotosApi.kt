package com.example.foursquare.services

import com.example.foursquare.model.PhotoDetails
import com.example.foursquare.model.Photos
import com.example.foursquare.model.PlaceData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("getPictures")
    fun getPhotos(@Query("placeId") placeId: Int,
                  @Query("pageNo") pageNo :Int,
                  @Query("pageSize") pageSize : Int) : Call<Photos>

    @GET("getPhoto")
    fun getPhotoDetail(@Query("photoId") photoId: Int,
                  @Query("pageNo") pageNo :Int,
                  @Query("pageSize") pageSize : Int) : Call<PhotoDetails>
}