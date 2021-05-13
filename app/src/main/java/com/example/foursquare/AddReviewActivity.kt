package com.example.foursquare

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.viewmodel.PlaceViewModel
import com.example.foursquare.viewmodel.ReviewViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_add_review.*
import kotlinx.android.synthetic.main.activity_details_screen.*

class AddReviewActivity : AppCompatActivity() {

    private lateinit var reviewViewModel: ReviewViewModel
    lateinit var reviewTextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        supportActionBar?.hide()

        reviewViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ReviewViewModel::class.java)

        reviewTextInput = findViewById(R.id.review_txt_input)

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }

        loadPlaceData()
//        Log.d("r11","r11")

        submit_btn_addreview.setOnClickListener {
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
        }
    }

    private fun loadPlaceData() {

        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWliaGF2aUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MDUyMTIsImlhdCI6MTYyMDg4NzIxMn0.tdfvDyW2-RAWvraegZVaLXgPFRatDHJD6DfYh4g9iPftuICADfScIo_e9j7cTJ0jtq_oVslt5zqzM_xTmgdNNw"
        var reviewText = "The food is delicious and the atmosphere is brilliant"

        reviewViewModel.addReviews(Token,129,12,reviewText)
            ?.observe(this, {
                Log.d("res", "re")
                val msg = it.message

                Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show()

            })
    }
}