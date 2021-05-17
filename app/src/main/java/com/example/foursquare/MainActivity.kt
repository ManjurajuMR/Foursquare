package com.example.foursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import com.example.foursquare.Home.HomeActivity
import com.example.foursquare.authentication.AuthenticationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val check_btn=findViewById<TextView>(R.id.hellowold)
        check_btn.setOnClickListener {

            val intent=Intent(this,AuthenticationActivity::class.java)


            startActivity(intent)
        }

    }
}