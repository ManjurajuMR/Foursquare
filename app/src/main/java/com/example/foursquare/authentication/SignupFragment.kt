package com.example.foursquare.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.foursquare.Home.HomeActivity
import com.example.foursquare.R
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*

class SignupFragment : Fragment(){

    lateinit var email: EditText
    lateinit var phoneNumber: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_signup, container, false)

        root.loginbutton.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }

        return root
    }
}