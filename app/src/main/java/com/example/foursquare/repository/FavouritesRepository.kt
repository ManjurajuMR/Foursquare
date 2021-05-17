package com.example.foursquare.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.Favourites
import com.example.foursquare.services.FavouritesApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Response

class FavouritesRepository(private val application: Application) {
    private val favouritesApi = RetrofitApiInstance.getApiInstance(FavouritesApi::class.java)
    val getFavourites: MutableLiveData<Favourites> = MutableLiveData()

    fun getFavourites(token: String, userId : Int, pageNo :Int, pageSize :Int): LiveData<Favourites> {
        val favDetails = favouritesApi.getFavourites(token, userId, pageNo, pageSize)
        favDetails.enqueue(object  : retrofit2.Callback<Favourites> {
            override fun onResponse(call: Call<Favourites>, response: Response<Favourites>) {
                //TODO("Not yet implemented")
                if (response.isSuccessful) {
                    getFavourites.value = response.body()
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Favourites>, t: Throwable) {
                //TODO("Not yet implemented")
                getFavourites.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return getFavourites
    }

}