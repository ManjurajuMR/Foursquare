package com.example.foursquare

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foursquare.adapters.PhotosAdapter
import com.example.foursquare.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.activity_photo_detais.*
import java.io.File
import java.io.FileOutputStream

class PhotoDetaisActivity : AppCompatActivity() {
    private lateinit var photosViewModel : PhotosViewModel
    lateinit var shareIcon : ImageView
    lateinit var shareImage : ImageView

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

        shareIcon = findViewById(R.id.share_img)
        shareImage = findViewById(R.id.photo_detail)

        shareIcon.setOnClickListener(View.OnClickListener { // Now share image function will be called
            // here we will be passing the text to share
            // Getting drawable value from image
            val bitmapDrawable = shareImage.getDrawable() as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap
            shareImageandText(bitmap)
        })
    }

    private fun shareImageandText(bitmap: Bitmap) {
        val uri = getmageToShare(bitmap)
        val intent = Intent(Intent.ACTION_SEND)

        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri)

        // adding text to share
//        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image")
//
//        // Add subject Here
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")

        // setting type to image
        intent.type = "image/png"

        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"))
    }

    private fun getmageToShare(bitmap: Bitmap): Uri? {
        val imagefolder = File(cacheDir, "images")
        var uri: Uri? = null
        try {
            imagefolder.mkdirs()
            val file = File(imagefolder, "shared_images.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
            uri = FileProvider.getUriForFile(this, "com.anni.shareimage.fileprovider", file)
        }
        catch (e: Exception) {
            Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
        }
        return uri
    }


    private fun loadPhotoDetailsData() {
        val photoID= intent.getIntExtra("photoId",0)

        if (photoID!=null) {
            photosViewModel.getPhotoDetails(photoID)
                ?.observe(this, {
                    Log.d("res", "re")
                    if (it != null) {
                        val photo = findViewById<ImageView>(R.id.photo_detail)
                        val profilePhoto = findViewById<ImageView>(R.id.profile_photo)
                        val userName = findViewById<TextView>(R.id.user_name)
                        val addedDate = findViewById<TextView>(R.id.added_date)
                        Glide.with(applicationContext).load(it.data.photoUrl)
                            .placeholder(R.drawable.loading).into(photo)
                        Glide.with(applicationContext).load(it.data.userImage)
                            .placeholder(R.drawable.loading).into(profilePhoto)
                        userName.setText(it.data.userName)
                        addedDate.setText(it.data.date)
                        toolbar_title.setText(it.data.placeName)
                    }

                })
        }
    }

}