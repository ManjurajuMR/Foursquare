package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.Home.Adapter.ReviewScreenAdapter
import com.example.foursquare.viewmodel.HomeViewModel
import com.example.foursquare.viewmodel.PlaceViewModel
import com.example.foursquare.viewmodel.ReviewsViewModel
import kotlinx.android.synthetic.main.activity_review_screen.*

class ReviewScreenActivity : AppCompatActivity() {
    private lateinit var reviewsViewModel : ReviewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_screen)

        reviewsViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ReviewsViewModel::class.java)
        val plc_id = intent.extras?.getLong("pid")?.toInt()
        val placeName= intent.getStringExtra("pname")

        //val page_no = intent.extras?.getLong("pno")?.toInt()
        //val page_size = intent.extras?.getLong("psize")?.toInt()

        supportActionBar?.hide()
        val rvscreen_tb=findViewById<androidx.appcompat.widget.Toolbar>(R.id.reviewscreen_toolbar)
        rvscreen_tb.setNavigationOnClickListener {
            onBackPressed()
        }

        if (plc_id != null) {
                    reviewscreentoolbar_title.setText(placeName)
                    reviewsViewModel.getPlaceDetailsByPlaceId(plc_id,0,50).observe(this,{
                        if(it != null){
                            val adapter = ReviewScreenAdapter(it.data,this)
                            val review_rv : RecyclerView = findViewById(R.id.rvscrn_rv)!!
                            review_rv.adapter = adapter
                            review_rv.layoutManager = LinearLayoutManager(this)

                        }
                    })
        }


    }
}