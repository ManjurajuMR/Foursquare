package com.example.foursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val check_btn=findViewById<TextView>(R.id.hellowold)
        check_btn.setOnClickListener {
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}