package com.example.foursquare.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.PhotoDetails
import com.example.foursquare.model.Photos
import com.example.foursquare.model.Popular_PlaceData
import com.example.foursquare.services.PhotosApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRepository(private val application: Application) {
    private val photosApi = RetrofitApiInstance.getApiInstance(PhotosApi::class.java)

    fun getPhotos(placeId:Int): LiveData<Photos> {
        Log.d("rep", "rp")
        val getphotosData: MutableLiveData<Photos> = MutableLiveData()
        val photos = photosApi.getPhotos(placeId, 0, 5)
        photos.enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                Log.d("uservalue", response.body().toString())
                if (response.isSuccessful) {
                    getphotosData.value = response.body()
                    Log.d("ressss", "${response.body()}")
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                getphotosData.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }


        })
        return getphotosData
    }

    fun getPhotoDetails(photoId:Int): LiveData<PhotoDetails> {
        Log.d("rep", "rp")
        val getphotoDetailsData: MutableLiveData<PhotoDetails> = MutableLiveData()
        val photoDetails = photosApi.getPhotoDetail(photoId, 0, 5)
        photoDetails.enqueue(object : Callback<PhotoDetails> {
            override fun onResponse(call: Call<PhotoDetails>, response: Response<PhotoDetails>) {
                Log.d("uservalue", response.body().toString())
                if (response.isSuccessful) {
                    getphotoDetailsData.value = response.body()
                    Log.d("ressss", "${response.body()}")
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<PhotoDetails>, t: Throwable) {
                getphotoDetailsData.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }


        })
        return getphotoDetailsData
    }
}