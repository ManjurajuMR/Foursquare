package com.example.foursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.foursquare.Home.HomeActivity
import com.example.foursquare.authentication.AuthenticationActivity
import com.example.foursquare.authentication.Constents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        val sharedPreferences = getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
        if(sharedPreferences.contains(Constents.USER_ID))
            launchHomeScreen()
        else
            launchLoginScreen()
    }

    private fun launchLoginScreen() {
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@SplashScreenActivity, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun launchHomeScreen() {
        lifecycleScope.launch {
            delay(1000)
            val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}