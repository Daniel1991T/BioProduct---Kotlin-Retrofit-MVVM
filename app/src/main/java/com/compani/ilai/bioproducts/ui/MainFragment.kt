package com.compani.ilai.bioproducts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var bioProductDatabase: BioProductDatabase

    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance()
        val userId = firebaseAuth.currentUser!!.uid
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        mainViewModel.checkedUserExist(Resource.loading(userId)).observe(
            viewLifecycleOwner,
            {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        cv_my_profile.visibility = View.VISIBLE
                        view_gone.visibility = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        cv_my_profile.visibility = View.GONE
                        view_gone.visibility = View.VISIBLE

                    }
                }
            })

        cv_vegetable_category.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToVegetablesFragment())
        }

        cv_meat_category.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMeatFragment())
        }

        cv_cheese_category.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCheeseFragment())
        }

        cv_fruit_category.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToFruitFragment())
        }

        cv_diverse_category.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDiverseFragment())
        }

        cv_my_profile.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMyProfileFragment())
        }
    }

    fun categoryClickDirection(view: View) {
        when(view.id) {
            R.id.cv_vegetable_category -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToVegetablesFragment())
            R.id.cv_meat_category -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToMeatFragment())
            R.id.cv_cheese_category -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToCheeseFragment())
            R.id.cv_fruit_category -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToFruitFragment())
            R.id.cv_diverse_category -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToDiverseFragment())
            R.id.cv_my_profile -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToMyProfileFragment())
        }
    }
}