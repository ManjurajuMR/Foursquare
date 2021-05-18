package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.adapters.PhotosAdapter
import com.example.foursquare.authentication.Constents
import com.example.foursquare.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.activity_photos.*

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
        val sharedPreferences = getSharedPreferences(Constents.Shared_pref, MODE_PRIVATE)
        val placeID=sharedPreferences.getInt(Constents.PLACE_ID,1)
        //val placeID= intent.getLongExtra("pid",0).toInt()
        val placeName= intent.getStringExtra("pname")
        if (placeID!=null) {
            toolbar_title.setText(placeName)
            photosViewModel.getPhotos(placeID)
                ?.observe(this, {
                    Log.d("res", "re")
                    if (it != null) {
                        // Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    /*    val adapter = PhotosAdapter(it, this)
                        var gridview = findViewById<GridView>(R.id.photos_gridview)
                        gridview.adapter = adapter*/
                        val adapter = PhotosAdapter(it,/*requireContext(),*/this)
                        val rv : RecyclerView =findViewById(R.id.photos_gridview)!!
                        rv.adapter = adapter
                        rv.layoutManager = GridLayoutManager(this,3,
                            GridLayoutManager.VERTICAL,false)
                    }

                })
        }
    }


}