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
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider

import com.example.foursquare.authentication.Constents

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.adapters.AddReviewPhotoAdapter
import com.example.foursquare.model.ApiResponse
import com.example.foursquare.model.ReviewPhotos
import com.example.foursquare.services.RetrofitApiInstance

import com.example.foursquare.viewmodel.AddReviewViewModel
import kotlinx.android.synthetic.main.activity_add_review.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

private const val REQUEST_CODE=101
class AddReviewActivity : AppCompatActivity() {

    private lateinit var reviewViewModel: AddReviewViewModel
    lateinit var reviewTextInput: EditText
    lateinit var addImage : ImageView
    lateinit var recyclerView : RecyclerView
    lateinit var photoAdapter: AddReviewPhotoAdapter
    //
    private var selectedImage: Uri?=null
    var modelList=ArrayList<ReviewPhotos>()
    var adapter: AddReviewPhotoAdapter? = null
    private val PhotosApi= RetrofitApiInstance.getApiInstance(com.example.foursquare.services.PhotosApi::class.java)

    var chaptersList: ArrayList<String> = ArrayList()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var tvchptname: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        supportActionBar?.hide()

        reviewViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(AddReviewViewModel::class.java)

        reviewTextInput = findViewById(R.id.review_txt_input)

        var topAppbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }


        addImage = findViewById(R.id.camera_icon)

        addImage.setOnClickListener{
           openImageChooser()
        }



//        photoAdapter = AddReviewPhotoAdapter()
//        Image.layoutManager = LinearLayoutManager(this)
//        //Image.focusable = false
//        Image.adapter = photoAdapter
//
//        addImage.setOnClickListener(View.OnClickListener {
//            fun onClick(v : View){
//                requestPermissions()
//            }
//        })




//        Log.d("r11","r11")

        submit_btn_addreview.setOnClickListener {
            Addreview()
            // Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
        }

    }
    //
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
                    initialise(modelList)
                }

            }
        }

    }

    private fun initialise(capturedPhoto: ArrayList<ReviewPhotos>) {

        val recyclerView = findViewById<RecyclerView>(R.id.Addreview_photos_recyclerview)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        adapter?.notifyDataSetChanged()
        adapter = AddReviewPhotoAdapter(capturedPhoto)
        recyclerView.setAdapter(adapter)

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

    private fun addReviewImage() {
        val reviewImagesParts = arrayListOf<MultipartBody.Part>()
        val sharedPreferences = getSharedPreferences(
            Constents.Shared_pref,
            MODE_PRIVATE
        )
        var token = sharedPreferences.getString(Constents.USER_TOKEN, "")
        val placeId = sharedPreferences.getInt(Constents.PLACE_ID,1)
        val userId = sharedPreferences.getString(Constents.USER_ID,"")

        if(token!=null && placeId!=null && userId!=null) {
            token = "Bearer $token"

            for (image in modelList) {
                image.image?.let {
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
            Log.d("Images",reviewImagesParts.size.toString())
            if (reviewImagesParts.size > 0) {
                Toast.makeText(applicationContext, "Uploading Images..Please wait..", Toast.LENGTH_SHORT).show()

                PhotosApi.uploadReviewImage(
                    placeId, userId.toInt(), token, reviewImagesParts
                ).enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                        if (response.isSuccessful) {
                            if (response.body()?.getStatus() == 200) {
                                Toast.makeText(
                                    applicationContext,
                                    "Review Added",
                                    Toast.LENGTH_LONG
                                ).show()
                                onBackPressed()
                            }
                        } else {
                            Toast.makeText(applicationContext, "Error Uploading Image", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })
            }
        }
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
//
    private fun Addreview() {

        //
        val sharedPreferences = getSharedPreferences(
            Constents.Shared_pref,
            MODE_PRIVATE
        )
        val token = sharedPreferences.getString(Constents.USER_TOKEN, "")
        val userId1 = sharedPreferences.getString(Constents.USER_ID,"")
        val placeId=sharedPreferences.getInt(Constents.PLACE_ID,1)
        Log.d("placeid", "${userId1}+${token}")
        //
        // val userId = 110
        //val placeId = intent.getIntExtra("pid",0)
        // val placeId =23

        //var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MjU5MjUsImlhdCI6MTYyMDkwNzkyNX0.ZzTN92UMvhSSwS6ydFBHOMZ3KhP8MA9Xbv8QYzif3m07o4p_0CvTXgeukTKB0EnJt0NtSRXVnaXHcYjCwyVaeQ"

        val review=reviewTextInput.text.toString()
        if (review.isEmpty()){
            // startActivity(Intent(this, DetailsScreenActivity::class.java))
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show()

        }
        else {

            if (token != null && placeId!=null && userId1!=null) {
                val newtoken = "Bearer $token"

                val userReview = hashMapOf(
                    "userId" to userId1.toString(),
                    "placeId" to placeId.toString(),
                    "review" to review
                )

                reviewViewModel.addReview(newtoken, userReview).observe(this, {
                    if (it != null) {
                        if (it.getStatus() == 200) {
                            if (modelList.isNotEmpty()){
                                addReviewImage()
                            }else{
                                Toast.makeText(applicationContext,"Review Added",Toast.LENGTH_LONG).show()
                                onBackPressed()
                            }
                        } else {
                            Toast.makeText(applicationContext, it.getMessage(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }
        }
    }
}