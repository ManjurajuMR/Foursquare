package com.example.foursquare

import android.content.Intent
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
//        Log.d("r11","r11")

        submit_btn_addreview.setOnClickListener {
            Addreview()
           // Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
        }
    }

/*    private fun loadPlaceData() {

        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MDQxMTUsImlhdCI6MTYyMDg4NjExNX0.cEP7tyg3RXq3Mf5yi3mVUGCE85STexidTPUe9tvFIILi25SwaleNh6ndqhPJ5xHG29gA8OttPXqIdrQIPMJxOA"
        //var reviewText = "The food is delicious and the atmosphere is brilliant"
        var reviewText=reviewTextInput.text.toString()

        reviewViewModel.addReviews(Token,110,11,reviewText)
            ?.observe(this, {
                Log.d("res", "it")
                val msg = it.data

                Toast.makeText(this, "${msg}", Toast.LENGTH_SHORT).show()

            })
    }*/

    private fun Addreview() {
        val userId = 110
        val placeId = 11
        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MDQxMTUsImlhdCI6MTYyMDg4NjExNX0.cEP7tyg3RXq3Mf5yi3mVUGCE85STexidTPUe9tvFIILi25SwaleNh6ndqhPJ5xHG29gA8OttPXqIdrQIPMJxOA"

        val review=reviewTextInput.text.toString()
        if (review.isEmpty()){
            startActivity(Intent(this, DetailsScreenActivity::class.java))
        }
        else {

            if (Token != null) {
                val newtoken = "Bearer $Token"

                val userReview = hashMapOf(
                    "userId" to userId.toString(),
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