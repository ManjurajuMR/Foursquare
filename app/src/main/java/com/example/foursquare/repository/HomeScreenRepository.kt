package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.Place
import com.example.foursquare.model.Rplace
import com.example.foursquare.model.User
import com.example.foursquare.services.HomescreenApi
import com.example.foursquare.services.RetrofitApiInstance
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient.Builder as Builder1

class HomeScreenRepository(val application: Application) {
    private val homeApi = RetrofitApiInstance.getApiInstance(HomescreenApi::class.java)
    val resPlace: MutableLiveData<Rplace> = MutableLiveData()
    //val BaseUrl="http://ec2-3-16-111-8.us-east-2.compute.amazonaws.com:8080/"
    //private lateinit var okHttpClient: OkHttpClient

    fun getPlaceDetailsByPlaceId(placeId: Int) {
        val placeDetails = homeApi.getPlaceDetailsByPlaceId(placeId)
        placeDetails.enqueue(object : Callback<Rplace> {
            override fun onResponse(call: Call<Rplace>, response: Response<Rplace>) {
                //TODO("Not yet implemented")
                if (!response.isSuccessful) {
                    val statuscode = response.code()
                    return
                }
                val Response = response.body()!!
                resPlace.value = Response
                Log.d("pss","pass")
            }
            override fun onFailure(call: Call<Rplace>, t: Throwable) {
                //TODO("Not yet implemented")
                Log.d("Failure","fail")
            }
        })

    }

}