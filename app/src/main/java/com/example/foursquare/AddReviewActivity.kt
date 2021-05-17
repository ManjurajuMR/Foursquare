package com.example.foursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.foursquare.authentication.Constents
import com.example.foursquare.viewmodel.AddReviewViewModel
import kotlinx.android.synthetic.main.activity_add_review.*
import kotlinx.android.synthetic.main.fragment_signin.view.*

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
//        Log.d("r11","r11")

        submit_btn_addreview.setOnClickListener {
            Addreview()
           // Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
        }
    }



    private fun Addreview() {
        //
        val sharedPreferences = getSharedPreferences(
            Constents.Shared_pref,
            MODE_PRIVATE
        )
        val token = sharedPreferences.getString(Constents.USER_TOKEN, "")
        val userId1 = sharedPreferences.getString(Constents.USER_ID,"")
        val placeId=sharedPreferences.getInt(Constents.PLACE_ID,1)
        Log.d("placeid", "${userId1}+${token}")
        //
       // val userId = 110
        //val placeId = intent.getIntExtra("pid",0)
       // val placeId =23

        //var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MjU5MjUsImlhdCI6MTYyMDkwNzkyNX0.ZzTN92UMvhSSwS6ydFBHOMZ3KhP8MA9Xbv8QYzif3m07o4p_0CvTXgeukTKB0EnJt0NtSRXVnaXHcYjCwyVaeQ"

        val review=reviewTextInput.text.toString()
        if (review.isEmpty()){
           // startActivity(Intent(this, DetailsScreenActivity::class.java))
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show()

        }
        else {

            if (token != null && placeId!=null && userId1!=null) {
                val newtoken = "Bearer $token"

                val userReview = hashMapOf(
                    "userId" to userId1.toString(),
                    "placeId" to placeId.toString(),
                    "review" to review
                )

                reviewViewModel.addReview(newtoken, userReview).observe(this, {
                    if (it != null) {
                        println(it)
                        if (it.getStatus() == 200) {
                                Toast.makeText(applicationContext,"Review Added",Toast.LENGTH_LONG).show()
                                onBackPressed()
                        } else {
                            Toast.makeText(applicationContext, it.getMessage(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }
        }

    }
}