package com.example.foursquare.authentication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foursquare.R
import com.example.foursquare.viewmodel.AuthenticationViewModel
import kotlinx.android.synthetic.main.fragment_forgotpassword.view.*

class ForgotpasswordFragment : Fragment() {

    private lateinit var authenticationViewModel : AuthenticationViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        authenticationViewModel= ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(AuthenticationViewModel::class.java)

        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_forgotpassword, container, false)

        root.get_in.setOnClickListener {
           /* val mail1 = arguments?.getString("email")
            val bundle = Bundle()
            bundle.putString("email",mail1)
            findNavController().navigate(R.id.action_forgotpasswordFragment_to_createnewpwFragment,bundle)*/
            //val enteredOtp = root.otp_value.text.toString()
            verifyOtp()
        }

        root.resend_otp.setOnClickListener {
            resendopt()
        }


        return root
    }

    fun resendopt(){
            val mail1 = arguments?.getString("email")
            val mail=mail1!!
            val email = hashMapOf("email" to mail)
            authenticationViewModel.generateOtp(email).observe(viewLifecycleOwner, {
                if (it.getStatus() == 200) {
                    Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                    verifyOtp()
                } else {
                    Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                }
            })
        }


    private fun verifyOtp() {
        val enteredOtp = view?.otp_value?.text.toString()
        val mail1 = arguments?.getString("email")
        val mail=mail1!!
        Log.d("pwF", mail.toString())
        val otp = hashMapOf("email" to mail,"otpNum" to enteredOtp)
        authenticationViewModel.verifyOtp(otp).observe(viewLifecycleOwner,{
            if (it.getStatus() == 200) {
                Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putString("email",mail)
                findNavController().navigate(R.id.action_forgotpasswordFragment_to_createnewpwFragment,bundle)
            }else{
                Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}