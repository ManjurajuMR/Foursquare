package com.example.foursquare.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foursquare.NearbySearchFragment
import com.example.foursquare.R

class Search_homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_home)
        supportActionBar?.hide()

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction().add(R.id.search_framelayout,NearbySearchFragment()).commit()

    }
}