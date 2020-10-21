package com.compani.ilai.bioproducts.auth.support

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.compani.ilai.bioproducts.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_auth) as NavHostFragment

        navController = navHostFragment.findNavController()


    }
}