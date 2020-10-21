package com.compani.ilai.bioproducts.auth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.auth.support.Validator
import com.compani.ilai.bioproducts.auth.support.Validator.Companion.instance
import com.compani.ilai.bioproducts.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener { signInUser() }

        tv_login_to_register.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun signInUser() {
        val email = tv_login_email.text.toString()
        val password = tv_login_password.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                FancyToast.makeText(context, task.exception?.message, FancyToast.ERROR, FancyToast.LENGTH_SHORT, false).show()
            } else {
                startActivity(Intent(context, MainActivity::class.java))
            }
        }
    }
}