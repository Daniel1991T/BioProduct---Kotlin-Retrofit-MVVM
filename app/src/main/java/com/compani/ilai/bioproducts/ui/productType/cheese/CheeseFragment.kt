package com.compani.ilai.bioproducts.ui.productType.cheese

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.compani.ilai.bioproducts.R

class CheeseFragment: Fragment(R.layout.fragment_cheese) {

    private lateinit var mContext: Context;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.title = "CHEESE PRODUCTS"
    }
}