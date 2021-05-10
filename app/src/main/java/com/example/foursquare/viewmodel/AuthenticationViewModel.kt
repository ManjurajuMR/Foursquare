package com.example.foursquare.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.foursquare.model.User
import com.example.foursquare.repository.AuthenticationRepository

class AuthenticationViewModel(application: Application):AndroidViewModel(application) {
    private val mainRepository = AuthenticationRepository(application)

    fun registerUser(user : HashMap<String,String>): LiveData<User> {

        return mainRepository.registerUser(user)
    }

    fun authenticateUser(user : HashMap<String,String>): LiveData<User> {
        return mainRepository.authenticateUser(user)
    }

    fun generateOtp(email: HashMap<String, String>): LiveData<User> {
        return mainRepository.generateOtp(email)
    }

    fun verifyOtp(otp: HashMap<String, String>) : LiveData<User> {
        return mainRepository.verifyOtp(otp)
    }

    fun updateNewPassword(password: HashMap<String, String>) : LiveData<User> {
        return  mainRepository.updateNewPassword(password)
    }
}