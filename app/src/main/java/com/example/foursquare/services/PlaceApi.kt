package com.example.foursquare.services

import com.example.foursquare.model.PlaceData
import com.example.foursquare.model.Popular_PlaceData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApi {
    @GET("PlaceApi/nearBy?")
    fun getNearyouPlace(@Query("latitude") latitude: Double,
                        @Query("longitude") longitude :Double,
                        @Query("pageNo") pageNo :Int,
                        @Query("pageSize") pageSize : Int) : Call<PlaceData>

    @GET("PlaceApi/topPick?")
    fun getTopPicPlace(@Query("latitude") latitude: Double,
                        @Query("longitude") longitude :Double,
                        @Query("pageNo") pageNo :Int,
                        @Query("pageSize") pageSize : Int) : Call<PlaceData>

    @GET("PlaceApi/popular?")
    fun getPopularPlace(@Query("latitude") latitude: Double,
                       @Query("longitude") longitude :Double,
                       @Query("pageNo") pageNo :Int,
                       @Query("pageSize") pageSize : Int) : Call<Popular_PlaceData>
}