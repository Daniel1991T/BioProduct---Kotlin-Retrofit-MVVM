package com.compani.ilai.bioproducts.auth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.auth.support.Validator
import com.compani.ilai.bioproducts.ui.MainActivity
import com.compani.ilai.bioproducts.utils.Constants.CLIENT_TYPE
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val REGISTER_TAG = "register"

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    private var userType: String? = CLIENT_TYPE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radio_customer.setOnCheckedChangeListener { group, _ ->
            val checked = group.findViewById(group.checkedRadioButtonId) as? RadioButton
            checked?.let {
                if (checked.isChecked) {
                    userType = checked.text as String?
                    Log.d(REGISTER_TAG, "onViewCreated: $userType")
                }
            }
        }

        btn_register.setOnClickListener {
            val email = tv_register_email.text.toString()
            val password = tv_register_password.text.toString()

            if (!isEmailValid(email,til_register_email ) ||
                !isPasswordValid(password, til_register_password) ||
                !isSamePassword(password, til_register_password,
                    tv_register_retry_password.text.toString(), til_register_retry_password)) {
                return@setOnClickListener
            } else {
                registerUserFirebase(email, password)
            }
        }

        tv_register_to_login.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }


    }

    private fun registerUserFirebase(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                FancyToast.makeText(context, task.exception?.message, FancyToast.ERROR, FancyToast.LENGTH_SHORT, false).show()
            } else {
                FancyToast.makeText(context, "Register successful", FancyToast.SUCCESS, FancyToast.LENGTH_SHORT, false).show()
                if (userType == CLIENT_TYPE) {
                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    val action =
                        RegisterFragmentDirections.actionRegisterFragmentToProfileFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun isEmailValid(email: String, inputLayoutEmail: TextInputLayout): Boolean {
        val errorEmpty = getString(R.string.email_empty)
        val errorInvalid = getString(R.string.email_invalid)
        return validator.isEmailValid(email, inputLayoutEmail, errorEmpty, errorInvalid)
    }

    private fun isPasswordValid(password: String, inputLayoutPassword: TextInputLayout): Boolean {
        val errorEmpty = getString(R.string.password_empty)
        val errorLength = getString(R.string.password_length)
        val errorUpperCase = getString(R.string.password_upper_case)
        val errorLowerCase = getString(R.string.password_lower_case)
        val errorDigitCase = getString(R.string.password_digit_case)
        return validator.isPasswordValid(password, inputLayoutPassword,errorEmpty, errorLength, errorUpperCase, errorLowerCase, errorDigitCase)
    }

    private fun isSamePassword(
        password: String,
        inputLayoutPassword: TextInputLayout,
        retryPassword: String,
        inputLayoutRetryPassword: TextInputLayout
    ): Boolean {
        val errorNotMatch = getString(R.string.password_match)
        return validator.isSamePassword(password, inputLayoutPassword, retryPassword, inputLayoutRetryPassword, errorNotMatch)
    }

}