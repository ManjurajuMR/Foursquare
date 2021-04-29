package com.example.foursquare.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foursquare.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportActionBar?.hide()


        supportFragmentManager.beginTransaction().add(R.id.authenticaion_framelayout,SigninFragment()).commit()
    }
}