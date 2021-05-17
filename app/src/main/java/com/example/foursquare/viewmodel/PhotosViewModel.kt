package com.example.foursquare.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.PhotoDetails
import com.example.foursquare.model.Photos
import com.example.foursquare.repository.PhotosRepository

class PhotosViewModel(application: Application): AndroidViewModel(application) {
    private val photosRepository = PhotosRepository(application)

    fun getPhotos(placeId: Int): LiveData<Photos> {
        Log.d("vmod","vm")
        return photosRepository.getPhotos(placeId)
    }

    fun getPhotoDetails(photoId:Int): LiveData<PhotoDetails> {
        Log.d("vmod","vm")
        return photosRepository.getPhotoDetails(photoId)
    }
}