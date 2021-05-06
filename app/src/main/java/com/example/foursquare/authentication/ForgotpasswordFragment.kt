package com.example.foursquare.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foursquare.R
import kotlinx.android.synthetic.main.fragment_forgotpassword.*
import kotlinx.android.synthetic.main.fragment_forgotpassword.view.*

class ForgotpasswordFragment : Fragment() {

    //oncreateview

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_forgotpassword, container, false)

        root.get_in.setOnClickListener {
            findNavController().navigate(R.id.action_forgotpasswordFragment_to_createnewpwFragment)
        }




        return root
    }

}