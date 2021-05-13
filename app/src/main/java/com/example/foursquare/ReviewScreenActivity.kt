package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReviewScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_screen)

        supportActionBar?.hide()

        val rvscreen_tb=findViewById<androidx.appcompat.widget.Toolbar>(R.id.reviewscreen_toolbar)
        rvscreen_tb.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}