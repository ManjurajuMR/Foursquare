package com.example.foursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.adapters.ReviewScreenAdapter
import com.example.foursquare.authentication.Constents
import com.example.foursquare.viewmodel.ReviewsViewModel
import kotlinx.android.synthetic.main.activity_review_screen.*

class ReviewScreenActivity : AppCompatActivity() {
    private lateinit var reviewsViewModel : ReviewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_screen)

        reviewsViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ReviewsViewModel::class.java)

        //val plc_id = intent.extras?.getLong("pid")?.toInt()
        val placeName= intent.getStringExtra("pname")
        val sharedPreferences = getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
        val plc_id=sharedPreferences.getInt(Constents.PLACE_ID,1)

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
                        if(it.data != null){
                            val adapter = ReviewScreenAdapter(it.data,this)
                            val review_rv : RecyclerView = findViewById(R.id.rvscrn_rv)!!
                            review_rv.adapter = adapter
                            review_rv.layoutManager = LinearLayoutManager(this)

                        }else{
                            Toast.makeText(
                                applicationContext,
                                "NO Reviews added!!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
        }

        add_rev.setOnClickListener {
            val intent = Intent(this,AddReviewActivity::class.java)
            intent.putExtra("pid",plc_id)
            intent.putExtra("pname",placeName)
            startActivity(intent)
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        *//*val inflater = menuInflater
        inflater.inflate(R.menu.rvscreen_menu,menu)*//*
        menuInflater.inflate(R.menu.rvscreen_menu,menu)
        Log.d("zzz","abc")
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_rv ->{
                *//*val intent = Intent(this,AddReviewActivity::class.java)
                intent.putExtra("pid",placeID)
                intent.putExtra("pname",pname)
                startActivity(intent)*//*
                Toast.makeText(this,"click",Toast.LENGTH_LONG).show()
                Log.d("bye","bye")
            }
        }
        Log.d("xxx","def")
        return super.onOptionsItemSelected(item)
    }*/

}