package com.compani.ilai.bioproducts.ui.productType.diverse

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.utils.Constants.DIVERS_PRODUCT

class DiverseViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
) : ViewModel(){

    private val _diverseProducts = MutableLiveData<List<Product>>()
    val diverseProducts: LiveData<List<Product>> = _diverseProducts

    init {
        _diverseProducts.postValue(bioProductDatabase.getAllProduct(DIVERS_PRODUCT))
    }
}