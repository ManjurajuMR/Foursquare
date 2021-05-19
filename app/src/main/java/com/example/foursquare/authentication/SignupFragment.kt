package com.example.foursquare.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.foursquare.Home.HomeActivity
import com.example.foursquare.R
import com.example.foursquare.viewmodel.AuthenticationViewModel
import kotlinx.android.synthetic.main.fragment_signup.view.*

class SignupFragment : Fragment(){
    private lateinit var authenticationViewModel : AuthenticationViewModel
    lateinit var email: EditText
    lateinit var phoneNumber: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        authenticationViewModel= ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(AuthenticationViewModel::class.java)

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_signup, container, false)

        root.loginbutton.setOnClickListener {
            signUp()
        }

        return root
    }

    private fun signUp() {
        val email = view?.Email?.text.toString().trim()
        val phonenumber = view?.Phonenumber?.text.toString().trim()
        val password = view?.Password?.text.toString().trim()
        val confirmPassword = view?.Confirmpassword?.text.toString().trim()
        if(evaluateUserInputs(email,phonenumber,password,confirmPassword)) {
            val user = hashMapOf("email" to email,"phone" to phonenumber,"password" to password)
            authenticationViewModel.registerUser(user).observe(viewLifecycleOwner, {
                if (it != null) {
                    if (it.getStatus() == 200) {
                        val loginUser = hashMapOf("email" to email, "password" to password)
                        authenticationViewModel.authenticateUser(loginUser).observe(viewLifecycleOwner, {
                            if (it != null) {
                                if (it.getStatus() == 200) {
                                    val sharedPreferences = requireContext().getSharedPreferences(Constents.Shared_pref,
                                        Context.MODE_PRIVATE)
                                    val sharedEditor = sharedPreferences.edit()
                                    val userId = it.getData().getUserData().getUserId().toString()
                                    val token = it.getData().getToken()
                                    val userName = it.getData().getUserData().getUserName()
                                    val userImage = it.getData().getUserData().getImage()
                                    sharedEditor.putString(Constents.USER_ID, userId)
                                    sharedEditor.putString(Constents.USER_TOKEN, token)
                                    sharedEditor.putString(Constents.USER_NAME, userName)
                                    sharedEditor.putString(Constents.USER_IMAGE, userImage)
                                    sharedEditor.apply()
                                    val intent = Intent(activity, HomeActivity::class.java)
                                    activity?.startActivity(intent)
                                    activity?.finish()
                                }
                                else
                                    Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }

    private fun evaluateUserInputs(email: String, phonenumber: String, password: String, confirmPassword: String): Boolean {
        var Validity = false
        when{
            email.isEmpty() ->{
                view?.Email?.error = "Enter Email Id"
                view?.Email?.requestFocus()

            }
            phonenumber.isEmpty() ->{
                view?.Phonenumber?.error = "Enter Phone Number"
                view?.Phonenumber?.requestFocus()

            }
            phonenumber.length != 10 -> {
                view?.Phonenumber?.error = "Enter a Valid Phonenumber"
                view?.Phonenumber?.requestFocus()
            }
            password.isEmpty()->{
                view?.Password?.error = "Enter Password"
                view?.Password?.requestFocus()

            }
            confirmPassword.isEmpty() ->{
                view?.Confirmpassword?.error = "Re Enter Password"
                view?.Confirmpassword?.requestFocus()

            }
            password!=confirmPassword ->{
                view?.Confirmpassword?.error = "Password Doesn't match"
                view?.Confirmpassword?.requestFocus()

            }
            else-> Validity = true
        }
        return Validity


    }
}