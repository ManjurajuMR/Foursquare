package com.example.foursquare

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foursquare.repository.HomeScreenRepository
import com.example.foursquare.viewmodel.AuthenticationViewModel
import com.example.foursquare.viewmodel.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_details_screen.*

class DetailsScreenActivity : AppCompatActivity() {
    lateinit var myDialog : Dialog
    private lateinit var homeViewModel : HomeViewModel
    lateinit var locnManager: FusedLocationProviderClient
    private  var googleMap : GoogleMap? = null
    private var mapReady : Boolean = false
    private var lati : Double = 0.0
    private var long : Double = 0.0
    private var pid : Long = 0
    private var pno : Long = 0
    private var psize : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)
        //supportActionBar?.hide()
        homeViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(HomeViewModel::class.java)

        val detscreen_tb=findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailscreen_toolbar)
        detscreen_tb.setNavigationOnClickListener {
            onBackPressed()
        }
        myDialog = Dialog(this)

        homeViewModel.getPlaceDetailsByPlaceId(12)
        setlivedata()

        locnManager = this.let { LocationServices.getFusedLocationProviderClient(it) }!!
        val supportMapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.reslocn_img, supportMapFragment).commit()
        supportMapFragment.getMapAsync{
            googleMap = it
            mapReady = true
            if(mapReady){
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lati, long), 15.0f))
                googleMap?.addMarker(MarkerOptions().position(LatLng(lati,long)).title("Me"))
            }
        }

        add_review.setOnClickListener {
            val intent = Intent(this,AddReviewActivity::class.java)
            intent.putExtra("pid",pid)
            startActivity(intent)
        }

        check_photos.setOnClickListener {
            val intent = Intent(this,PhotosActivity::class.java)
            intent.putExtra("pid",pid)
            startActivity(intent)
        }

        check_reviews.setOnClickListener {
            val intent = Intent(this,ReviewScreenActivity::class.java)
            intent.putExtra("pid",pid)
            //intent.putExtra("pno",pno)
            //intent.putExtra("psize",psize)
            startActivity(intent)
        }

        detscreen_tb.setOnMenuItemClickListener { item ->
            val id = item?.itemId
            if (id == R.id.share_det) {
                var intent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT, "share")
                    this.type = "image"
                }
                startActivity(intent)

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
            pid = it.data.id
            pno = it.pageNo
            psize = it.pageSize

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
        lati = latitude
        long = longitude
        val res_img = findViewById<ImageView>(R.id.rest_img)
        detscreentoolbar_title.setText(place_name)
        res_type.setText(place_type)
        res_overview.setText(overview)
        res_address.setText(address)
        res_phno.setText(phone_num.toString())
        Glide.with(this).load(place_image).override(500,350).into(res_img)

    }

}


