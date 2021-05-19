package com.example.foursquare

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.adapters.FavouritesAdapter
import com.example.foursquare.authentication.Constents
import com.example.foursquare.model.FavPdata
import com.example.foursquare.model.Favourites
import com.example.foursquare.viewmodel.FavouritesViewModel

class FavouritesActivity : AppCompatActivity(), FavouritesAdapter.OnFavItemClickListener {
    private lateinit var favouritesViewModel: FavouritesViewModel
    var token : String = ""
    //var placeID : Int = 0
    var userid : String = ""
    lateinit var fav_data: List<FavPdata>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        favouritesViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(FavouritesViewModel::class.java)
        //val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5pc2gxMUBnbWFpbC5jb20iLCJleHAiOjE2MjEyNDk2NTAsImlhdCI6MTYyMTIzMTY1MH0.lvJd0_aIlLIYWhe5_Omq_ViSGWvYmBYAHs80AKMIpTUGpU0VhSi2org104T-93htzjH8CkQqE7RwIGQvcF7eKg"
        //val userid = 115

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }


        val sharedPreferences = getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
        token = sharedPreferences.getString(Constents.USER_TOKEN,"").toString()
        userid = sharedPreferences.getString(Constents.USER_ID,"").toString()
        //placeID = sharedPreferences.getInt(Constents.PLACE_ID,1)

        val pageno = 0
        val pagesize = 50

        if (token != null && userid!=null) {
            val newtoken = "Bearer $token"

            favouritesViewModel.getFavouritesByUserId(newtoken,userid,pageno,pagesize).observe(this, {
                if (it.data != null) {
                    fav_data=it.data
                    val adapter = FavouritesAdapter(fav_data, this)
                    val favourite_rv : RecyclerView = findViewById(R.id.frecyclerView)!!
                    favourite_rv.adapter = adapter
                    favourite_rv.layoutManager = LinearLayoutManager(this)
                }else{
                    Toast.makeText(
                        applicationContext,
                        "NO Favourites added!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }


    }

    private fun getfavdata():List<FavPdata>{
        val pageno = 0
        val pagesize = 50

        if (token != null && userid!=null) {
            val newtoken = "Bearer $token"

            favouritesViewModel.getFavouritesByUserId(newtoken,userid,pageno,pagesize).observe(this, {
                if (it.data != null) {
                    fav_data=it.data
                }else{
                    Toast.makeText(
                        applicationContext,
                        "NO Favourites added!!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
        return fav_data
    }

    override fun onSiteClick(PlaceId: Long) {
        //TODO("Not yet implemented")
    }

    override fun delFav(PlaceId: Long) {
        val newToken = "Bearer $token"

        val userFavourite = hashMapOf("userId" to userid, "placeId" to PlaceId.toString())

        if (PlaceId != null) {
            favouritesViewModel.deleteFavourite(newToken, userFavourite).observe(this) {
                if(it!=null) {
                    if (it.getStatus() == 200) {
                        // startActivity(Intent(this,FavouriteActivity::class.java))
                            initialize()

                        Toast.makeText(this, it.getMessage(), Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, it.getMessage(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        //Toast.makeText(applicationContext,"$userid",Toast.LENGTH_LONG).show()

    }

    private fun initialize(){
        val fav_datas=getfavdata()
        val adapter = FavouritesAdapter(fav_datas, this)
        val favourite_rv : RecyclerView = findViewById(R.id.frecyclerView)!!
        favourite_rv.adapter = adapter
        favourite_rv.layoutManager = LinearLayoutManager(this)
    }
}