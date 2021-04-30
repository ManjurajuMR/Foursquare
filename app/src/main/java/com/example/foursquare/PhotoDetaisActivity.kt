package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PhotoDetaisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detais)
        supportActionBar?.hide()

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}