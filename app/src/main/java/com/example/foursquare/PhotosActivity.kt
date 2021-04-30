package com.example.foursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        supportActionBar?.hide()

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
          onBackPressed()
        }

    }
}