package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.Addfav
import com.example.foursquare.model.Review
import com.example.foursquare.model.ReviewData
import com.example.foursquare.services.AddFavouriteApi
import com.example.foursquare.services.AddReviewApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFavouriteRepository(private val application: Application) {
    private val favApi = RetrofitApiInstance.getApiInstance(AddFavouriteApi::class.java)

//    fun addfav(token: String, userid: Int,placeid: Int): LiveData<FavData> {
//        val addfav: MutableLiveData<FavData> = MutableLiveData()
//        val addfavDetails = favApi.addfav(token,userid,placeid)
//        addfavDetails.enqueue(object : Callback<FavData> {
//            override fun onResponse(call: Call<FavData>, response: Response<FavData>) {
//                Log.d("addfav", response.body().toString())
//                if (response.isSuccessful) {
//                    addfav.value = response.body()
//                } else {
//                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//
//            override fun onFailure(call: Call<FavData>, t: Throwable) {
//                addfav.value = (null)
//                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//        return addfav
//    }

    fun addUserFav(token: String, user: HashMap<String, String>): LiveData<Addfav> {
        val addFavUser: MutableLiveData<Addfav> = MutableLiveData()
        val addFavUserCall = favApi.addfav(token, user)
        addFavUserCall.enqueue(object : Callback<Addfav> {
            override fun onResponse(call: Call<Addfav>, response: Response<Addfav>) {
                if (response.isSuccessful) {
                    addFavUser.value = response.body()
                } else {
                    Log.d("response", "${response.body()}")
                    Toast.makeText(application, response.errorBody()?.string(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Addfav>, t: Throwable) {
                addFavUser.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()

            }

        })
        return addFavUser
    }

}