package com.example.foursquare.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foursquare.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        findNavController(R.id.authenticaion_framelayout)
        //supportFragmentManager.beginTransaction().add(R.id.authenticaion_framelayout,SigninFragment()).commit()
    }
}