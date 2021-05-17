package com.example.foursquare

import android.content.Intent

import android.media.Image
import android.net.Uri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.viewmodel.AddReviewViewModel
import kotlinx.android.synthetic.main.activity_add_review.*


class AddReviewActivity : AppCompatActivity() {

    private lateinit var reviewViewModel: AddReviewViewModel
    lateinit var reviewTextInput: EditText
    lateinit var addImage : ImageView
    lateinit var recyclerView : RecyclerView
    lateinit var photoAdapter: AddReviewPhotoAdapter

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
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(intent, 456)
        }

        recyclerView = findViewById<RecyclerView>(R.id.Addreview_photos_recyclerview)
        //tvchptname = findViewById<ImageView>(R.id.imageView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = AddReviewPhotoAdapter(this@AddReviewActivity ,chaptersList)

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

//    fun requestPermission(){
//        var permissionListener = PermissionListener {
//            fun onPermissionGranted() {
//                openBottomPicker()
//                Toast.makeText(this@AddReviewActivity, "Permission Granted", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            fun onPermissionDenied(deniedPermissions: <String?>) {
//                Toast.makeText(
//                    this@AddReviewActivity,
//                    "Permission Denied\n$deniedPermissions",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//            TedPermission.with(this)
//            .setPermissionListener(permissionListener)
//            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
//            .check();
//
//    }

//    fun openBottomPicker() {
//        TedBottomPicker.OnMultiImageSelectedListener listener = object  {
//
//        }{
//
//            fun onImagezselected(uri: List<Uri> ){
//                photoAdapter.setData(uri);
//            }
//        };
//        var tedBottomPicker = TedBottomPicker.Builder(this)
//            .setOnMultiImageSelectedListener(listener)
//            .setCompleteButtonText("DONE")
//            .setEmptySelectionText("No Image")
//            .create();
//        tedBottomPicker.show(supportFragmentManager);
//
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 456) {
                if (data?.clipData != null) {
                    val mClipData = data.clipData
                    for (i in 0 until mClipData!!.itemCount) {
                        val item = mClipData.getItemAt(i)
                        val uri: Uri = item.uri
                        // display your images
                        tvchptname?.setImageURI(uri)
                    }
                } else if (data?.data != null) {
                    val uri: Uri? = data.data
                    // display your image
                    tvchptname?.setImageURI(uri)
                }
            }
        }
    }

/*    private fun loadPlaceData() {

        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MDQxMTUsImlhdCI6MTYyMDg4NjExNX0.cEP7tyg3RXq3Mf5yi3mVUGCE85STexidTPUe9tvFIILi25SwaleNh6ndqhPJ5xHG29gA8OttPXqIdrQIPMJxOA"
        //var reviewText = "The food is delicious and the atmosphere is brilliant"
        var reviewText=reviewTextInput.text.toString()

        reviewViewModel.addReviews(Token,110,11,reviewText)
            ?.observe(this, {
                Log.d("res", "it")
                val msg = it.data

                Toast.makeText(this, "${msg}", Toast.LENGTH_SHORT).show()

            })
    }*/

    private fun Addreview() {
        val userId = 110
        val placeId = intent.getIntExtra("pid",0)
       // val placeId =23
        Log.d("placeid", "${placeId}")
        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MjU5MjUsImlhdCI6MTYyMDkwNzkyNX0.ZzTN92UMvhSSwS6ydFBHOMZ3KhP8MA9Xbv8QYzif3m07o4p_0CvTXgeukTKB0EnJt0NtSRXVnaXHcYjCwyVaeQ"

        val review=reviewTextInput.text.toString()
        if (review.isEmpty()){
            startActivity(Intent(this, DetailsScreenActivity::class.java))
        }
        else {

            if (Token != null && placeId!=null) {
                val newtoken = "Bearer $Token"

                val userReview = hashMapOf(
                    "userId" to userId.toString(),
                    "placeId" to placeId.toString(),
                    "review" to review
                )

                reviewViewModel.addReview(newtoken, userReview).observe(this, {
                    if (it != null) {
                        println(it)
                        if (it.getStatus() == 200) {
                                Toast.makeText(applicationContext,"Review Added",Toast.LENGTH_LONG).show()
                                onBackPressed()
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