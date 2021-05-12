package com.example.foursquare

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.foursquare.repository.HomeScreenRepository
import com.example.foursquare.viewmodel.AuthenticationViewModel
import com.example.foursquare.viewmodel.HomeViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_details_screen.*

class DetailsScreenActivity : AppCompatActivity() {
    lateinit var myDialog : Dialog
    private lateinit var homeViewModel : HomeViewModel
    //private lateinit var repmodel : HomeScreenRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)
        supportActionBar?.hide()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(HomeViewModel::class.java)

        val detscreen_tb=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        /*detscreen_tb.setNavigationOnClickListener {
            onBackPressed()
        }*/
        myDialog = Dialog(this)
        homeViewModel.getPlaceDetailsByPlaceId(10)
        setlivedata()

        add_review.setOnClickListener {
            Toast.makeText(this,"hii",Toast.LENGTH_LONG).show()
        }

    }

    fun ShowPopup(v : View?){
        myDialog.setContentView(R.layout.rating_popup)
        val close : TextView = myDialog.findViewById(R.id.close_popup)
        close.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                myDialog.dismiss()
            }
        })
        //myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    fun setlivedata(){
        homeViewModel.homeRepository.resPlace.observe(this,{
            val place_name = it.data.name
            val place_image = it.data.image
            val place_type = it.data.placeType[0].name
            val overall_rating = it.data.overallRating
            val overview = it.data.overview
            val address = it.data.address
            val phone_num = it.data.phone
            val latitude = it.data.latitude
            val longitude = it.data.longitude

            setDataToLayout(place_name,place_image,place_type,overall_rating,overview,address,phone_num,latitude,longitude)
        })
        /*homeViewModel.getPlaceDetailsByPlaceId(10).observe(this,{
        })*/
    }

    fun setDataToLayout(
        place_name: String,
        place_image: String,
        place_type: String,
        overall_rating: Double,
        overview: String,
        address: String,
        phone_num: Long,
        latitude: Double,
        longitude: Double
    ) {
        detscreentoolbar_title.setText(place_name)
        res_type.setText(place_type)
        res_overview.setText(overview)
        res_address.setText(address)
        res_phno.setText(phone_num.toString())

    }

}


