package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.PlaceData
import com.example.foursquare.model.Popular_PlaceData
import com.example.foursquare.services.PlaceApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceRepository (private val application: Application){
    private val placeApi = RetrofitApiInstance.getApiInstance(PlaceApi::class.java)

    fun getPlaceDetails(type:String,latitude:Double,longitude:Double): LiveData<PlaceData> {
        Log.d("rep","rp")
        val getplaceData: MutableLiveData<PlaceData> = MutableLiveData()

        if (type=="nearBy") {
            val registerDetails = placeApi.getNearyouPlace(latitude, longitude, 0, 4)
            registerDetails.enqueue(object : Callback<PlaceData> {
                override fun onResponse(call: Call<PlaceData>, response: Response<PlaceData>) {
                    Log.d("uservalue", response.body().toString())
                    if (response.isSuccessful) {
                        getplaceData.value = response.body()
                        Log.d("ressss", "${response.body()}")
                    } else {
                        Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<PlaceData>, t: Throwable) {
                    getplaceData.value = (null)
                    Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                }


            })
        }else if (type=="topPic") {
            val registerDetails = placeApi.getTopPicPlace(latitude, longitude, 0, 4)
            registerDetails.enqueue(object : Callback<PlaceData> {
                override fun onResponse(call: Call<PlaceData>, response: Response<PlaceData>) {
                    Log.d("uservalue", response.body().toString())
                    if (response.isSuccessful) {
                        getplaceData.value = response.body()
                        Log.d("ressss", "${response.body()}")
                    } else {
                        Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<PlaceData>, t: Throwable) {
                    getplaceData.value = (null)
                    Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                }


            })
        }/*else if (type=="popular") {
            val registerDetails = placeApi.getPopularPlace(latitude, longitude, 0, 4)
            registerDetails.enqueue(object : Callback<PlaceData> {
                override fun onResponse(call: Call<PlaceData>, response: Response<PlaceData>) {
                    Log.d("uservalue", response.body().toString())
                    if (response.isSuccessful) {
                        getplaceData.value = response.body()
                        Log.d("ressss", "${response.body()}")
                    } else {
                        Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<PlaceData>, t: Throwable) {
                    getplaceData.value = (null)
                    Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
                }


            })
        }*/
        return getplaceData
    }

    fun getPopularPlaceDetails(type:String,latitude:Double,longitude:Double): LiveData<Popular_PlaceData> {
        Log.d("rep", "rp")
        val getplaceData: MutableLiveData<Popular_PlaceData> = MutableLiveData()
        val registerDetails = placeApi.getPopularPlace(latitude, longitude, 0, 4)
        registerDetails.enqueue(object : Callback<Popular_PlaceData> {
            override fun onResponse(call: Call<Popular_PlaceData>, response: Response<Popular_PlaceData>) {
                Log.d("uservalue", response.body().toString())
                if (response.isSuccessful) {
                    getplaceData.value = response.body()
                    Log.d("ressss", "${response.body()}")
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Popular_PlaceData>, t: Throwable) {
                getplaceData.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }


        })
        return getplaceData
    }
    }

//
