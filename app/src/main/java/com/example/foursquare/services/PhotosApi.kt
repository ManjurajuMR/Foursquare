package com.example.foursquare.services

import com.example.foursquare.model.ApiResponse
import com.example.foursquare.model.PhotoDetails
import com.example.foursquare.model.Photos
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface PhotosApi {
    @GET("getPictures")
    fun getPhotos(@Query("placeId") placeId: Int,
                  @Query("pageNo") pageNo :Int,
                  @Query("pageSize") pageSize : Int) : Call<Photos>

    @GET("getPhoto")
    fun getPhotoDetail(@Query("photoId") photoId: Int,
                  @Query("pageNo") pageNo :Int,
                  @Query("pageSize") pageSize : Int) : Call<PhotoDetails>

    @Multipart
    @POST("uploadReviewImage")
    fun uploadReviewImage(
        @Query("placeId") placeId: Int?,
        @Query("userId") userId:Int,
        @Header("Authorization") token:String,
        @Part files: ArrayList<MultipartBody.Part>
    ):Call<ApiResponse>

}