package com.example.foursquare.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.foursquare.R

class SignupFragment : Fragment(), View.OnClickListener {

    lateinit var email: EditText
    lateinit var phoneNumber: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_signup, container, false)

        val myView: View = inflater.inflate(R.layout.fragment_signup, container, false)
        var loginButton = myView.findViewById<Button>(R.id.loginbutton)
        loginButton.setOnClickListener(this)
        return myView
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}