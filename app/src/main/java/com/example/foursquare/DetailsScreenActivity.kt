package com.example.foursquare

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment

import android.widget.ImageView
import android.widget.TextView

import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.example.foursquare.repository.HomeScreenRepository
import com.example.foursquare.viewmodel.AddFavouriteViewModel
import com.example.foursquare.viewmodel.AuthenticationViewModel

import com.example.foursquare.authentication.Constents

import com.example.foursquare.viewmodel.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_details_screen.*

class DetailsScreenActivity : AppCompatActivity() {
    lateinit var myDialog: Dialog
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var addfavViewModel: AddFavouriteViewModel
    lateinit var locnManager: FusedLocationProviderClient
    private var googleMap: GoogleMap? = null
    private var mapReady: Boolean = false
    private var lati: Double = 0.0
    private var long: Double = 0.0
    private var pid: Long = 0
    private var pname: String = ""
    private var pno: Long = 0
    private var psize: Long = 0
    var tkn : String = ""
    var placeID : Int = 0
    var userid : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)
        //supportActionBar?.hide()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(HomeViewModel::class.java)

        addfavViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(AddFavouriteViewModel::class.java)

        val detscreen_tb = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailscreen_toolbar)
        detscreen_tb.setNavigationOnClickListener {
            onBackPressed()
        }
        myDialog = Dialog(this)


        val sharedPreferences = getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
        placeID = sharedPreferences.getInt(Constents.PLACE_ID,1)
        tkn = sharedPreferences.getString(Constents.USER_TOKEN,"").toString()
        userid = sharedPreferences.getString(Constents.USER_ID,"").toString()
        //val placeID= intent.getIntExtra("placeId",0)
        if (placeID != null){

            homeViewModel.getPlaceDetailsByPlaceId(placeID)
            setlivedata()
        }

        locnManager = this.let { LocationServices.getFusedLocationProviderClient(it) }!!
        val supportMapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.reslocn_img, supportMapFragment)
            .commit()
        supportMapFragment.getMapAsync {
            googleMap = it
            mapReady = true
            if (mapReady) {
                googleMap?.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(lati, long),
                        15.0f
                    )
                )
                googleMap?.addMarker(MarkerOptions().position(LatLng(lati, long)).title("Me"))
            }
        }

        add_review.setOnClickListener {

            val intent = Intent(this,AddReviewActivity::class.java)
            //intent.putExtra("pid",placeID)
            intent.putExtra("pname",pname)
            startActivity(intent)
        }

        check_photos.setOnClickListener {
            val intent = Intent(this,PhotosActivity::class.java)
            //intent.putExtra("pid",pid)
            intent.putExtra("pname",pname)

            startActivity(intent)
        }

        check_reviews.setOnClickListener {

            val intent = Intent(this,ReviewScreenActivity::class.java)

            intent.putExtra("pname",pname)
            //intent.putExtra("pid",pid)

            //intent.putExtra("pno",pno)
            //intent.putExtra("psize",psize)
            startActivity(intent)
        }

        detscreen_tb.setOnMenuItemClickListener { item ->
            val id = item?.itemId
            if (id == R.id.share_det) {
                /*var intent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT, "share")
                    this.type = "image"
                }
                startActivity(intent)*/
                Toast.makeText(this,"share",Toast.LENGTH_LONG).show()
            }
            if (id == R.id.addplace_tofav) {
                addFavourites()
                //Toast.makeText(this,"fav",Toast.LENGTH_LONG).show()
            }
            super.onOptionsItemSelected(item)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.detscreen_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item?.itemId
//        if (id == R.id.share_det) {
//            var intent = Intent().apply {
//                this.action = Intent.ACTION_SEND
//                this.putExtra(Intent.EXTRA_TEXT,"We are sharing data between 2 apps")
//                this.type = "plain/text"
//            }
//            startActivity(intent)
//
//        }
//            return super.onOptionsItemSelected(item)
//    }


    fun ShowPopup(v: View?) {
        myDialog.setContentView(R.layout.rating_popup)

        var rate : Int = 1
        val close : TextView = myDialog.findViewById(R.id.close_popup)

        close.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                myDialog.dismiss()
            }
        })
        val ratingBar : RatingBar = myDialog.findViewById(R.id.ratbar_popup)
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, b ->
            //Toast.makeText(this,"$rating", Toast.LENGTH_LONG).show()
            rate = rating.toInt()
        }
        val submit : Button = myDialog.findViewById(R.id.submit_rating_popup)
        submit.setOnClickListener {
            //Toast.makeText(this,"$rate", Toast.LENGTH_LONG).show()
             submitUserRating(rate)
            myDialog.dismiss()
        }

        //myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    fun setlivedata() {
        homeViewModel.homeRepository.resPlace.observe(this, {
            val place_name = it.data.name
            val place_image = it.data.image
            val place_type = it.data.placeType[0].name
            val overall_rating = it.data.overallRating
            val overview = it.data.overview
            val address = it.data.address
            val phone_num = it.data.phone
            val latitude = it.data.latitude
            val longitude = it.data.longitude
            pid = it.data.id
            pname = it.data.name
            pno = it.pageNo
            psize = it.pageSize

            setDataToLayout(place_name, place_image, place_type, overall_rating, overview, address, phone_num, latitude, longitude)
        })
        /*homeViewModel.getPlaceDetailsByPlaceId(10).observe(this,{
        })*/
    }

    private fun submitUserRating(userRating: Int) {
        if (userRating > 0) {
            val sharedPreferences =
                getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
            val userId = sharedPreferences.getString(Constents.USER_ID, "")
            val token = "Bearer ${sharedPreferences.getString(Constents.USER_TOKEN, "")}"
            val placeId = sharedPreferences.getInt(Constents.PLACE_ID,1)
            val rating = hashMapOf<String, String>(
                "userId" to userId.toString(),
                "placeId" to placeId.toString(),
                "rating" to userRating.toString()
            )
            homeViewModel.addRating(token, rating).observe(this, {
                if (it.getStatus() == 200)
                    Toast.makeText(
                        applicationContext,
                        "Thank you for your Rating!",
                        Toast.LENGTH_LONG
                    ).show()
                else
                    Toast.makeText(applicationContext, it.getMessage(), Toast.LENGTH_LONG)
                        .show()
            })
        }
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
        lati = latitude
        long = longitude
        val res_img = findViewById<ImageView>(R.id.rest_img)
        detscreentoolbar_title.setText(place_name)
        res_type.setText(place_type)
        res_overview.setText(overview)
        res_address.setText(address)
        res_phno.setText(phone_num.toString())
        Glide.with(this).load(place_image).override(500, 350).into(res_img)

        when (overall_rating.toInt()) {
            1 -> show_rating.rating = 0.5F
            2 -> show_rating.rating = 1F
            3 -> show_rating.rating = 1.5F
            4 -> show_rating.rating = 2F
            5 -> show_rating.rating = 2.5F
            6 -> show_rating.rating = 3F
            7 -> show_rating.rating = 3.5F
            8 -> show_rating.rating = 4F
            9 -> show_rating.rating = 4.5F
            10 -> show_rating.rating = 5F
        }

    }

/*    private fun loadPlaceData() {

        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWliaGF2aUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MDUyMTIsImlhdCI6MTYyMDg4NzIxMn0.tdfvDyW2-RAWvraegZVaLXgPFRatDHJD6DfYh4g9iPftuICADfScIo_e9j7cTJ0jtq_oVslt5zqzM_xTmgdNNw"

        addfavViewModel.addfav(Token,129,12)
            ?.observe(this, {
//                Log.d("res", "re")
                if(it!= null){
                    if (it.getStatus() == 200)
                        Toast.makeText(applicationContext, "Added to favourite", Toast.LENGTH_SHORT).show()
                    else {
                        val msg = it.getMessage()

                        Toast.makeText(applicationContext, "msg", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }*/

    private fun addFavourites() {
        //val userId = 126
        //val placeId = intent.getIntExtra("pid", 0)
        // val placeId =23
        //Log.d("placeid", "${placeId}")
        //var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5pc2gxMUBnbWFpbC5jb20iLCJleHAiOjE2MjEzMjk3NzksImlhdCI6MTYyMTMxMTc3OX0.xo_pnRwYeyio35ttJomKzwuH9yIbo33mIXRhTglDeEbTnKJbmLQvqXMB_R5qqRWVMtmRqw3WqHCJGOA3W-abhA"

        /*if (tkn != null && placeID != null) {
            val newtoken = "Bearer $tkn"

            val user = hashMapOf(
                "userId" to userid,
                "placeId" to placeID.toString(),
                //"review" to review
            )

            addfavViewModel.addfav(newtoken, user).observe(this, {
                if (it != null) {
                    println(it)
                    if (it.status.toInt() == 200) {
                        Toast.makeText(applicationContext, "Added to favourites", Toast.LENGTH_LONG).show()
                        onBackPressed()
                    } else {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }*/

        Toast.makeText(applicationContext, "$tkn", Toast.LENGTH_LONG).show()

    }

}


