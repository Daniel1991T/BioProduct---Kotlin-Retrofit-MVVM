package com.compani.ilai.bioproducts.auth.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.auth.AuthViewModel
import com.compani.ilai.bioproducts.ui.MainActivity
import com.compani.ilai.bioproducts.utils.Constants.CLIENT_TYPE
import com.compani.ilai.bioproducts.utils.EventObserver
import com.compani.ilai.bioproducts.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

   private lateinit var viewModel: AuthViewModel

    private var userType: String? = CLIENT_TYPE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        subscribeToObserver()

        radio_customer.setOnCheckedChangeListener { group, _ ->
            val checked = group.findViewById(group.checkedRadioButtonId) as? RadioButton
            checked?.let {
                if (checked.isChecked) {
                    userType = checked.text as String?
                }
            }
        }

        btn_register.setOnClickListener {
            val email = et_register_email.text.toString()
            val password = tv_register_password.text.toString()
            val username = et_register_username.text.toString()
            val repeaterPassword = tv_register_retry_password.text.toString()
            viewModel.register(email, username, password, repeaterPassword)

        }

        tv_register_to_login.setOnClickListener {
            if (findNavController().previousBackStackEntry != null) {
                findNavController().popBackStack()
            } else {
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            }
        }
    }

    private fun subscribeToObserver() {
        viewModel.registerStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                isShowing(false)
                snackbar(it)
            },
            onLoading = { isShowing(true)}
        ) {
            isShowing(false)
            snackbar("Successfully registered account")
            if (userType == CLIENT_TYPE) {
                Intent(requireActivity(), MainActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finish()
                }
            } else {
                RegisterFragmentDirections.actionRegisterFragmentToProfileFragment()
            }
        })
    }

    private fun isShowing(isShow: Boolean) {
        register_progress_bar.isVisible = isShow
    }

}