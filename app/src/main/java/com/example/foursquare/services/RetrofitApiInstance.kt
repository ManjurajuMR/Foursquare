package com.example.foursquare.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiInstance {
    val BASE_URL: String = "http://ec2-3-16-111-8.us-east-2.compute.amazonaws.com:8080/"
    private var RetrifitInstance : Retrofit? = null

    fun <T> getApiInstance(apiClass: Class<T>): T {

        return buildInstance().create(apiClass)
    }

    private fun buildInstance(): Retrofit {
        if (RetrifitInstance == null) {
            RetrifitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return RetrifitInstance!!
    }
}