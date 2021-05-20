package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)

        supportActionBar?.hide()

        val aboutus_tb=findViewById<androidx.appcompat.widget.Toolbar>(R.id.aboutus_toolbar)
        aboutus_tb.setNavigationOnClickListener {
            onBackPressed()
        }
        aboutus_tb.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.home -> onBackPressed()
            }
            true
        }
    }
}