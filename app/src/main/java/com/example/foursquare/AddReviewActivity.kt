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


        loadPlaceData()
//        Log.d("r11","r11")

        submit_btn_addreview.setOnClickListener {
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
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

    private fun loadPlaceData() {

        var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWliaGF2aUBnbWFpbC5jb20iLCJleHAiOjE2MjA5MDUyMTIsImlhdCI6MTYyMDg4NzIxMn0.tdfvDyW2-RAWvraegZVaLXgPFRatDHJD6DfYh4g9iPftuICADfScIo_e9j7cTJ0jtq_oVslt5zqzM_xTmgdNNw"
        var reviewText = "The food is delicious and the atmosphere is brilliant"

        reviewViewModel.addReviews(Token,129,12,reviewText)
            ?.observe(this, {
                Log.d("res", "re")
                val msg = it.message

                Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show()

            })
    }
}