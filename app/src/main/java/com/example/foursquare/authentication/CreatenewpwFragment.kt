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
import kotlinx.android.synthetic.main.fragment_createnewpw.view.*


class CreatenewpwFragment : Fragment() {
    private lateinit var authenticationViewModel : AuthenticationViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        authenticationViewModel= ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(AuthenticationViewModel::class.java)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_createnewpw, container, false)


     root.submit_newpw.setOnClickListener {

         val email = arguments?.getString("email")!!
         val password = view?.enter_pw?.text.toString()
         val confirm_pw = view?.confirm_pw?.text.toString()

         Log.d("pw", email.toString())
         if (evaluateUserInputs(password, confirm_pw)) {
             val user = hashMapOf("email" to email, "password" to password)
             authenticationViewModel.updateNewPassword(user).observe(viewLifecycleOwner, {
                 if (it != null) {
                     if (it.getStatus() == 200) {
                         Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                         findNavController().navigate(R.id.action_createnewpwFragment_to_signinFragment)
                         /*  activity?.finish()*/
                     } else {
                         Toast.makeText(activity, it.getMessage(), Toast.LENGTH_SHORT).show()
                     }
                 }
             })
         }
         //
        }

        return root
    }


    private fun evaluateUserInputs(password: String, rePassword: String): Boolean {
        var isValid = false
        when{
            password.isEmpty()->{
                view?.enter_pw?.error = "Enter Password"
                view?.enter_pw?.requestFocus()

            }
            rePassword.isEmpty() ->{
                view?.confirm_pw?.error = "Re Enter Password"
                view?.confirm_pw?.requestFocus()

            }
            password!=rePassword ->{
                view?.confirm_pw?.error = "Password Doesn't match"
                view?.confirm_pw?.requestFocus()

            }
            else-> isValid = true
        }
        return isValid


    }

}