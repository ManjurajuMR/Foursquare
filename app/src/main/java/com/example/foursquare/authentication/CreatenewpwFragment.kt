package com.example.foursquare.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foursquare.R
import kotlinx.android.synthetic.main.fragment_createnewpw.*
import kotlinx.android.synthetic.main.fragment_createnewpw.view.*


class CreatenewpwFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_createnewpw, container, false)

        root.submit.setOnClickListener {
            findNavController().navigate(R.id.action_createnewpwFragment_to_signinFragment)
        }

        return root
    }

}