package com.example.foursquare.services

import com.example.foursquare.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthenticationApi {

    @POST("register")
    fun registerUser(@Body user : HashMap<String,String>) : Call<User>

    @POST("authenticate")
    fun authenticateUser(@Body user : HashMap<String,String>) : Call<User>

    @POST("generateOtp")
    fun generateOtp(@Body email: HashMap<String, String>) : Call<User>

    @PUT("validateOtp")
    fun verifyOtp(@Body otp: HashMap<String, String>) : Call<User>

    @PUT("changePassword")
    fun updateNewPassword(@Body password: HashMap<String, String>) : Call<User>

}