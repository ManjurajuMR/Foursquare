package com.example.foursquare

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.*

class DetailsScreenActivity : AppCompatActivity() {
    lateinit var myDialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val detscreen_tb=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        detscreen_tb.setNavigationOnClickListener {
            onBackPressed()
        }

        setContentView(R.layout.activity_details_screen)
        myDialog = Dialog(this)
    }

    fun ShowPopup(v : View?){
        myDialog.setContentView(R.layout.rating_popup)
        val close : TextView = myDialog.findViewById(R.id.close_popup)
        close.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                myDialog.dismiss()
            }
        })
        //myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

}


