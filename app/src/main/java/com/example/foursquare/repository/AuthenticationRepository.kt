package com.example.foursquare.repository

import android.app.Application
//import android.telecom.Call
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foursquare.model.User
import com.example.foursquare.services.AuthenticationApi
import com.example.foursquare.services.RetrofitApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationRepository(private val application: Application) {
    private val authenticationApi =RetrofitApiInstance.getApiInstance(AuthenticationApi::class.java)


    fun registerUser(user: HashMap<String, String>): LiveData<User> {
        val registerUser: MutableLiveData<User> = MutableLiveData()
        val registerDetails = authenticationApi.registerUser(user)
        registerDetails.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d("uservalue", response.body().toString())
                if (response.isSuccessful) {
                    registerUser.value = response.body()
                } else {
                    Toast.makeText(application, response.raw().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                registerUser.value = (null)
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return registerUser
    }

    fun authenticateUser(user: HashMap<String, String>): LiveData<User> {
        val loginUser: MutableLiveData<User> = MutableLiveData()
        val authenticateCall = authenticationApi.authenticateUser(user)
        authenticateCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    loginUser.value = response.body()
                } else {
                    Log.d("resposne","${response.body()?.error}")
                    Toast.makeText(application,response.errorBody()?.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loginUser.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()

            }

        })
        return loginUser
    }

    fun generateOtp(email: HashMap<String, String>) : LiveData<User> {

        val userOtp : MutableLiveData<User> = MutableLiveData()
        val generateOtpCall = authenticationApi.generateOtp(email)
        generateOtpCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d("user1", "login1_repo")
                if(response.isSuccessful){
                    userOtp.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                userOtp.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return userOtp
    }

    fun verifyOtp(otp: HashMap<String, String>): LiveData<User> {
        val userOtp : MutableLiveData<User> = MutableLiveData()
        val validateOtpCall = authenticationApi.verifyOtp(otp)
        validateOtpCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userOtp.value = response.body()
                } else {
                    Toast.makeText(application, "Invalid OTP", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                userOtp.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return userOtp
    }

    fun updateNewPassword(password: HashMap<String, String>): LiveData<User> {
        Log.d("user1", "login_cre_repo")
        val userPassword : MutableLiveData<User> = MutableLiveData()
        val validateOtpCall = authenticationApi.updateNewPassword(password)
        validateOtpCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userPassword.value = response.body()
                } else {
                    Toast.makeText(application, "Invalid OTP", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                userPassword.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return userPassword
    }
}