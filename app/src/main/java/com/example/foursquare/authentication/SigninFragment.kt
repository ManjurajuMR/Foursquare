package com.example.foursquare.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foursquare.Home.HomeActivity
import com.example.foursquare.R
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signin.view.*


class SigninFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root  = inflater.inflate(R.layout.fragment_signin, container, false)

        root.create_account.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_signupFragment)
        }
        root.forgot_password.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_forgotpasswordFragment)
        }
        root.login_btn.setOnClickListener {
            val intent = Intent(activity,HomeActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }

        return root
    }

}