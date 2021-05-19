package com.example.foursquare

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.GridView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.adapters.PhotosAdapter
import com.example.foursquare.authentication.Constents
import com.example.foursquare.model.ApiResponse
import com.example.foursquare.model.ReviewPhotos
import com.example.foursquare.services.RetrofitApiInstance
import com.example.foursquare.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.activity_photos.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

private const val REQUEST_CODE=101
class PhotosActivity : AppCompatActivity() {
    private lateinit var photosViewModel : PhotosViewModel
    //
    private var selectedImage: Uri?=null
    private val PhotosApi= RetrofitApiInstance.getApiInstance(com.example.foursquare.services.PhotosApi::class.java)
    var modelList=ArrayList<ReviewPhotos>()

    override fun onCreate(savedInstanceState: Bundle?) {

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        supportActionBar?.hide()

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
          onBackPressed()
        }
        topAppbar.setOnMenuItemClickListener {
            openImageChooser()
            true
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
                    if (it.data != null) {
                        // Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    /*    val adapter = PhotosAdapter(it, this)
                        var gridview = findViewById<GridView>(R.id.photos_gridview)
                        gridview.adapter = adapter*/
                        val adapter = PhotosAdapter(it,/*requireContext(),*/this)
                        val rv : RecyclerView =findViewById(R.id.photos_gridview)!!
                        rv.adapter = adapter
                        rv.layoutManager = GridLayoutManager(this,3,
                            GridLayoutManager.VERTICAL,false)
                    }else{
                        Toast.makeText(
                            applicationContext,
                            "NO Photos added!!",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
        }
    }


    //
    private fun openImageChooser() {
        val permission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )
        } else {
            Intent(Intent.ACTION_PICK).also {
                it.type = "image/*"
                val minType = arrayOf("image/jpeg", "image/png")
                it.putExtra(Intent.EXTRA_MIME_TYPES, minType)
                it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                startActivityForResult(it, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode== REQUEST_CODE){
            if (resultCode== Activity.RESULT_OK){
                if (data!!.clipData!=null){
                    val count=data.clipData!!.itemCount
                    for (i in 0 until count){
                        selectedImage=data.clipData!!.getItemAt(i).uri
                        modelList.add(ReviewPhotos(selectedImage!!))
                    }
                }
                addReviewImage()
            }
        }
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String?
        val cursor: Cursor? = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 101)
                openImageChooser()
        }
    }
    private fun addReviewImage() {
        val reviewImagesParts = arrayListOf<MultipartBody.Part>()
        val sharedPreferences = getSharedPreferences(
            Constents.Shared_pref,
            MODE_PRIVATE
        )
        var token = sharedPreferences.getString(Constents.USER_TOKEN, "")
        val userId = sharedPreferences.getString(Constents.USER_ID,"")
        val placeId = sharedPreferences.getInt(Constents.PLACE_ID,1)
        if(token!=null && userId!=null && placeId!=null){
            val userId = userId.toInt()
            token = "Bearer $token"

            for (image in modelList) {
                image.image?.let{
                    val file = File(getRealPathFromURI(it))
                    val reviewBody = RequestBody.create(MediaType.parse("image/*"), file)
                    val part = MultipartBody.Part.createFormData(
                        "files",
                        file.name,
                        reviewBody
                    )
                    reviewImagesParts.add(part)
                }


            }

            Toast.makeText(applicationContext, "Photo uploading", Toast.LENGTH_LONG).show()
            PhotosApi.uploadReviewImage(
                placeId, userId, token, reviewImagesParts
            ).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        if (response.body()?.getStatus() == 200) {
                            Toast.makeText(applicationContext, "Photo Added", Toast.LENGTH_LONG).show()
                            loadPhotosData()
                        } else {

                            Toast.makeText(applicationContext, response.body()?.getMessage(), Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error Uploading Image",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
        modelList.clear()
    }


}