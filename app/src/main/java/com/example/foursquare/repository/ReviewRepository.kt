package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.Place
import com.example.foursquare.model.ReviewData
import com.example.foursquare.services.RetrofitApiInstance
import com.example.foursquare.services.ReviewApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewRepository(private val application: Application) {
    private val reviewApi = RetrofitApiInstance.getApiInstance(ReviewApi::class.java)

    fun addreview(token: String, userid: Int,placeid: Int,review: String): LiveData<ReviewData> {
        val addreview: MutableLiveData<ReviewData> = MutableLiveData()
        val addreviewDetails = reviewApi.addreview(token,userid,placeid,review)
        addreviewDetails.enqueue(object : Callback<ReviewData> {
            override fun onResponse(call: Call<ReviewData>, response: Response<ReviewData>) {
                Log.d("review", response.body().toString())
                if (response.isSuccessful) {
                    addreview.value = response.body()
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ReviewData>, t: Throwable) {
                addreview.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return addreview
    }
}