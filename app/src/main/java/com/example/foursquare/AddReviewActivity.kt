package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.foursquare.viewmodel.AddReviewViewModel
import kotlinx.android.synthetic.main.activity_add_review.*

class AddReviewActivity : AppCompatActivity() {

    private lateinit var reviewViewModel: AddReviewViewModel
    lateinit var reviewTextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        supportActionBar?.hide()

        reviewViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(AddReviewViewModel::class.java)

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