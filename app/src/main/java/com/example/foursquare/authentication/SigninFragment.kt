package com.example.foursquare.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foursquare.Home.HomeActivity
import com.example.foursquare.R
import com.example.foursquare.viewmodel.AuthenticationViewModel
import kotlinx.android.synthetic.main.fragment_signin.view.*


class SigninFragment : Fragment() {
    private lateinit var authenticationViewModel : AuthenticationViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        authenticationViewModel= ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(AuthenticationViewModel::class.java)
        // Inflate the layout for this fragment
        val root  = inflater.inflate(R.layout.fragment_signin, container, false)

        root.create_account.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_signupFragment)
        }

        root.login_btn.setOnClickListener {
            signIn()
        }

        root.forgot_password.setOnClickListener {
            forgotPassword()
            /*val signInUserName = view?.input_email?.text.toString()
            val bundle = Bundle()
            bundle.putString("email",signInUserName)
            findNavController().navigate(R.id.action_signinFragment_to_forgotpasswordFragment,bundle)*/

        }

        return root
    }

    private fun signIn() {
        val signInEmailId = view?.input_email?.text.toString()
        val signInPassword = view?.input_password?.text.toString()
        if(evaluateUserInputs(signInEmailId,signInPassword)) {

            val loginUser = hashMapOf("email" to signInEmailId, "password" to signInPassword)
            authenticationViewModel.authenticateUser(loginUser).observe(viewLifecycleOwner, {
                Log.d("user", "login")
                if (it != null) {
                    if (it.getStatus() == 200) {
                        val userId = it.getData().getUserData().getUserId().toString()
                        val token = it.getData().getToken()
                        val intent=Intent(activity,HomeActivity::class.java)
                        activity?.startActivity(intent)
                    } else {
                        Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                    }
                }
            })}
    }

    private fun forgotPassword() {
        val signInUserName = view?.input_email?.text.toString()
        if(signInUserName.isEmpty()){
            view?.input_email?.error = "Enter Registered Email to Send OTP"
            view?.input_email?.requestFocus()
        }else{
            val email = hashMapOf("email" to signInUserName)
            authenticationViewModel.generateOtp(email).observe(viewLifecycleOwner, {
                if (it.getStatus() == 200) {
                    Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()
                    bundle.putString("email",signInUserName)
                    findNavController().navigate(R.id.action_signinFragment_to_forgotpasswordFragment,bundle)
                }else{
                    Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun evaluateUserInputs(email: String, password: String): Boolean {
    var validity = false
    when{
        email.isEmpty() ->{
            view?.input_email?.error = "Enter Email Address"
            view?.input_email?.requestFocus()

        }
        password.isEmpty()->{
            view?.input_password?.error = "Enter Password"
            view?.input_password?.requestFocus()

        }
        else-> validity = true
    }
    return validity
}

}