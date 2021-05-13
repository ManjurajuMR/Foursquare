package com.example.foursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foursquare.adapters.PhotosAdapter
import com.example.foursquare.viewmodel.PhotosViewModel

class PhotoDetaisActivity : AppCompatActivity() {
    private lateinit var photosViewModel : PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detais)
        supportActionBar?.hide()

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }

        loadPhotoDetailsData()
    }

    private fun loadPhotoDetailsData() {
        val photoID= intent.getIntExtra("photoId",0)

        photosViewModel.getPhotoDetails(photoID)
            ?.observe(this, {
                Log.d("res","re")
                if (it != null) {
                   val photo=findViewById<ImageView>(R.id.photo_detail)
                   val profilePhoto=findViewById<ImageView>(R.id.profile_photo)
                   val userName=findViewById<TextView>(R.id.user_name)
                   val addedDate=findViewById<TextView>(R.id.added_date)
                    Glide.with(applicationContext).load(it.data.photoUrl).placeholder(R.drawable.loading).into(photo)
                    Glide.with(applicationContext).load(it.data.userImage).placeholder(R.drawable.loading).into(profilePhoto)
                    userName.setText(it.data.userName)
                    addedDate.setText(it.data.date)
                }

            })
    }

}