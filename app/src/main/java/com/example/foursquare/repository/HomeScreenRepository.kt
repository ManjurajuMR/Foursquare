package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.Rating
import com.example.foursquare.model.Rplace
import com.example.foursquare.services.HomescreenApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun addRating(token: String, rating: HashMap<String, String>): LiveData<Rating> {
        var ratingData = MutableLiveData<Rating>()
        val addRatingCall = homeApi.addRating(token, rating)
        addRatingCall.enqueue(object : Callback<Rating> {
            override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
                if (response.isSuccessful) {
                    ratingData.value = response.body()
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Rating>, t: Throwable) {
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return ratingData
    }

}