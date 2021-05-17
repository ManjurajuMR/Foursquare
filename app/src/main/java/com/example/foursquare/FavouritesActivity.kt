package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.adapters.FavouritesAdapter
import com.example.foursquare.viewmodel.FavouritesViewModel
import com.example.foursquare.viewmodel.ReviewsViewModel

class FavouritesActivity : AppCompatActivity(), FavouritesAdapter.OnFavItemClickListener {
    private lateinit var favouritesViewModel: FavouritesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        favouritesViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(FavouritesViewModel::class.java)

        val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5pc2gxMUBnbWFpbC5jb20iLCJleHAiOjE2MjEyNDk2NTAsImlhdCI6MTYyMTIzMTY1MH0.lvJd0_aIlLIYWhe5_Omq_ViSGWvYmBYAHs80AKMIpTUGpU0VhSi2org104T-93htzjH8CkQqE7RwIGQvcF7eKg"
        val userid = 126
        val pageno = 0
        val pagesize = 50

        if (token != null && userid!=null) {
            val newtoken = "Bearer $token"

            favouritesViewModel.getFavouritesByUserId(newtoken,userid,pageno,pagesize).observe(this, {
                if (it != null) {
                    val adapter = FavouritesAdapter(it.data, this)
                    val favourite_rv : RecyclerView = findViewById(R.id.frecyclerView)!!
                    favourite_rv.adapter = adapter
                    favourite_rv.layoutManager = LinearLayoutManager(this)
                }
            })
        }

    }

    override fun onSiteClick(PlaceId: Long) {
        //TODO("Not yet implemented")
    }
}