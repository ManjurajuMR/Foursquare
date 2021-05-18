package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.adapters.FavouritesAdapter
import com.example.foursquare.viewmodel.FavouritesViewModel
import com.example.foursquare.viewmodel.ReviewsViewModel

class FavouritesActivity : AppCompatActivity(), FavouritesAdapter.OnFavItemClickListener {
    private lateinit var favouritesViewModel: FavouritesViewModel
    val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5pc2gxMUBnbWFpbC5jb20iLCJleHAiOjE2MjEzMjk3NzksImlhdCI6MTYyMTMxMTc3OX0.xo_pnRwYeyio35ttJomKzwuH9yIbo33mIXRhTglDeEbTnKJbmLQvqXMB_R5qqRWVMtmRqw3WqHCJGOA3W-abhA"
    val userid = 126
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        favouritesViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(FavouritesViewModel::class.java)

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

    override fun delFav(PlaceId: Long) {
        //TODO("Not yet implemented")
        if (token != null && userid!=null) {
            val ntoken = "Bearer $token"
            favouritesViewModel.delFavourite(ntoken, userid, PlaceId.toInt()).observe(this, {
                if (it != null){
                    val stat : Int = it.getStats()
                    if (stat == 200){
                        Toast.makeText(applicationContext,"Deleted from Favourites",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(applicationContext, it.getMesage(), Toast.LENGTH_SHORT).show()
                    }
                }

                //Toast.makeText(applicationContext,"${it.message}",Toast.LENGTH_LONG).show()
            })
        }

    }
}