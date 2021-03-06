package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.DelFavourite
import com.example.foursquare.model.FavouriteResponse
import com.example.foursquare.model.Favourites
import com.example.foursquare.services.FavouritesApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavouritesRepository(private val application: Application) {
    private val favouritesApi = RetrofitApiInstance.getApiInstance(FavouritesApi::class.java)
    val getFavourites: MutableLiveData<Favourites> = MutableLiveData()
    val delFavPlace: MutableLiveData<DelFavourite> = MutableLiveData()

    fun getFavourites(token: String, userId: String?, pageNo:Int, pageSize:Int): LiveData<Favourites> {
        val favDetails = favouritesApi.getFavourites(token, userId, pageNo, pageSize)
        favDetails.enqueue(object  : retrofit2.Callback<Favourites> {
            override fun onResponse(call: Call<Favourites>, response: Response<Favourites>) {
                //TODO("Not yet implemented")
                if (response.isSuccessful) {
                    Log.d("favourites","${response.body()}")
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

    fun delFavourite(token: String, userId: Int, placeId: Int): LiveData<DelFavourite> {

        val addReviewUserCall = favouritesApi.delFavourite(token,userId,placeId)
        addReviewUserCall.enqueue(object : retrofit2.Callback<DelFavourite> {
            override fun onResponse(call: Call<DelFavourite>, response: Response<DelFavourite>) {

                if (response.isSuccessful) {
                    delFavPlace.value = response.body()
                } else {
                    Log.d("dres", "${response.body()}")
                }
            }
            override fun onFailure(call: Call<DelFavourite>, t: Throwable) {

                delFavPlace.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return delFavPlace
    }

    //
    fun deleteFavourite(token : String, favourite : HashMap<String,String>): LiveData<FavouriteResponse> {
        var deleteFavouriteResponse : MutableLiveData<FavouriteResponse> = MutableLiveData()
        val getFavouriteCall = favouritesApi.deleteFavourite(token,favourite)
        getFavouriteCall.enqueue(object : Callback<FavouriteResponse> {
            override fun onResponse(call: Call<FavouriteResponse>, response: Response<FavouriteResponse>) {
                if (response.isSuccessful) {
                    deleteFavouriteResponse.value = response.body()
                } else {
                    Log.d("response","${response.body()}")
                    Toast.makeText(application,response.errorBody()?.string(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FavouriteResponse>, t: Throwable) {

                deleteFavouriteResponse.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()

            }

        })
        return deleteFavouriteResponse
    }

}