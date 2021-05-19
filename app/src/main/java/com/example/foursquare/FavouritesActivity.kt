package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.adapters.FavouritesAdapter
import com.example.foursquare.authentication.Constents
import com.example.foursquare.viewmodel.FavouritesViewModel

class FavouritesActivity : AppCompatActivity(), FavouritesAdapter.OnFavItemClickListener {
    private lateinit var favouritesViewModel: FavouritesViewModel
    var token : String = ""
    //var placeID : Int = 0
    var userid : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        favouritesViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(FavouritesViewModel::class.java)
        //val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5pc2gxMUBnbWFpbC5jb20iLCJleHAiOjE2MjEyNDk2NTAsImlhdCI6MTYyMTIzMTY1MH0.lvJd0_aIlLIYWhe5_Omq_ViSGWvYmBYAHs80AKMIpTUGpU0VhSi2org104T-93htzjH8CkQqE7RwIGQvcF7eKg"
        //val userid = 115

        val pageno = 0
        val pagesize = 50

        val sharedPreferences = getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
        token = sharedPreferences.getString(Constents.USER_TOKEN,"").toString()
        userid = sharedPreferences.getString(Constents.USER_ID,"").toString()
        //placeID = sharedPreferences.getInt(Constents.PLACE_ID,1)

        if (token != null && userid!=null) {
            val newtoken = "Bearer $token"

            favouritesViewModel.getFavouritesByUserId(newtoken,userid,pageno,pagesize).observe(this, {
                if (it != null) {
                    val adapter = FavouritesAdapter(it.data, this)
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

    override fun onSiteClick(PlaceId: Long) {
        //TODO("Not yet implemented")
    }

    override fun delFav(PlaceId: Long) {
        //TODO("Not yet implemented")
        if (token != null && userid!=null) {
            val ntoken = "Bearer $token"

            favouritesViewModel.delFavourite(ntoken, userid.toInt(), PlaceId.toInt()).observe(this, {
                if (it != null){
                    val stat : Int = it.gtStats()
                    if (stat == 200){
                        Toast.makeText(applicationContext,"Deleted from Favourites",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(applicationContext, "vvv", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        //Toast.makeText(applicationContext,"$userid",Toast.LENGTH_LONG).show()

    }
}