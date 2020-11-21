package com.compani.ilai.bioproducts.auth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.auth.AuthViewModel
import com.compani.ilai.bioproducts.ui.MainActivity
import com.compani.ilai.bioproducts.utils.EventObserver
import com.compani.ilai.bioproducts.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        subscribeToObserver()

        btn_login.setOnClickListener {
            viewModel.login(
                et_login_email.text.toString(),
                et_login_password.text.toString()
            )
        }

        tv_login_to_register.setOnClickListener {
            if (findNavController().previousBackStackEntry != null) {
                findNavController().popBackStack()
            } else {
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            }
        }
    }

    private fun subscribeToObserver() {
        viewModel.loginStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                snackbar(it)
            }
        ) {
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        })
    }


}