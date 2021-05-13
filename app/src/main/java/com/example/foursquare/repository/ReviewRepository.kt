package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.UserReviews
import com.example.foursquare.services.RetrofitApiInstance
import com.example.foursquare.services.ReviewsApi
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ReviewRepository(private val application: Application) {
    private val reviewApi = RetrofitApiInstance.getApiInstance(ReviewsApi::class.java)
    val getresReviews: MutableLiveData<UserReviews> = MutableLiveData()

    fun getReviews(PlaceId : Int, pageNo :Int, pageSize :Int): LiveData<UserReviews> {
        val reviewDetails = reviewApi.getReviews(PlaceId,pageNo,pageSize)
        reviewDetails.enqueue(object : retrofit2.Callback<UserReviews> {
            override fun onResponse(call: Call<UserReviews>, response: Response<UserReviews>) {
                //TODO("Not yet implemented")
                if (response.isSuccessful) {
                    getresReviews.value = response.body()
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<UserReviews>, t: Throwable) {
                //TODO("Not yet implemented")
                getresReviews.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return getresReviews
    }
}