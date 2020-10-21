package com.compani.ilai.bioproducts.auth.support

import android.content.res.Resources
import com.compani.ilai.bioproducts.BioProductsApplication
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.utils.Constants.EMAIl_REGEX
import com.google.android.material.textfield.TextInputLayout

class Validator() {

    companion object {
        val instance: Validator by lazy { HOLDER.INSTANCE }
    }

    private object HOLDER {
        val INSTANCE = Validator()
    }

    fun isEmailValid(email: String, inputLayoutEmail: TextInputLayout, errorEmpty: String, errorInvalid: String): Boolean {
        if (email.isEmpty()) {
            inputLayoutEmail.error = errorEmpty
            return false
        }
        val regex = EMAIl_REGEX.toRegex()
        val matchRegex = regex.containsMatchIn(email)
        return if (!matchRegex) {
            inputLayoutEmail.error = errorInvalid
            false
        } else {
            inputLayoutEmail.error = null
            true
        }
    }

    fun isPasswordValid(password: String,
                        inputLayoutPassword: TextInputLayout,
                        errorEmpty: String,
                        errorLength: String,
                        errorUpperCase: String,
                        errorLowerCase: String,
                        errorDigitCase: String): Boolean {
        if (password.isEmpty()) {
            inputLayoutPassword.error = errorEmpty
            return false
        }

        val upperCaseMatch = "[A-Z]".toRegex().containsMatchIn(password)
        val lowerCaseMatch = "[a-z]".toRegex().containsMatchIn(password)
        val digitCaseMatch = "[0-9]".toRegex().containsMatchIn(password)

        if (password.length < 8) {
            inputLayoutPassword.error = errorLength
            return false
        } else {
            inputLayoutPassword.error = null
        }

        if (!upperCaseMatch) {
            inputLayoutPassword.error =  errorUpperCase
            return false
        } else  {
            inputLayoutPassword.error = null
        }

        if (!lowerCaseMatch) {
            inputLayoutPassword.error = errorLowerCase
            return false
        } else {
            inputLayoutPassword.error = null
        }

        if (!digitCaseMatch) {
            inputLayoutPassword.error = errorDigitCase
            return false
        } else {
            inputLayoutPassword.error = null
        }
        return true
    }

    fun isSamePassword(
        password: String,
        inputLayoutPassword: TextInputLayout,
        retryPassword: String,
        inputLayoutRetryPassword: TextInputLayout,
        errorNotMatch: String?
    ): Boolean {
        if (password != retryPassword) {
            inputLayoutPassword.error = errorNotMatch
            inputLayoutRetryPassword.error = errorNotMatch
            return false
        } else {
            inputLayoutPassword.error = null
            inputLayoutRetryPassword.error = null
        }
        return true
    }

    fun isFieldEmpty(inputLayout: TextInputLayout, errorEmpty: String?): Boolean {
        val field = inputLayout.editText.toString()
        if (field.isEmpty()) {
            inputLayout.error = errorEmpty
            return true
        }
        inputLayout.error = null
        return false
    }


}