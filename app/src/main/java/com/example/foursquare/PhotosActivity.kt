package com.example.foursquare

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.adapters.PhotosAdapter
import com.example.foursquare.viewmodel.PhotosViewModel
import com.example.foursquare.viewmodel.PlaceViewModel
import java.util.ArrayList

class PhotosActivity : AppCompatActivity() {
    private lateinit var photosViewModel : PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        supportActionBar?.hide()

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
          onBackPressed()
        }

        loadPhotosData()
/*        var gridview=findViewById<GridView>(R.id.photos_gridview)
        //var images = intArrayOf(R.drawable.food,R.drawable.food,R.drawable.food,R.drawable.food,R.drawable.food,R.drawable.food)
        var adapter=PhotosAdapter(images,this)
        gridview.adapter=adapter*/
    }

    private fun loadPhotosData() {

        photosViewModel.getPhotos(10)
            ?.observe(this, {
                Log.d("res","re")
                if (it != null) {
                    // Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    val adapter = PhotosAdapter(it,this)
                    var gridview=findViewById<GridView>(R.id.photos_gridview)
                    gridview.adapter = adapter
                }

            })
    }


}