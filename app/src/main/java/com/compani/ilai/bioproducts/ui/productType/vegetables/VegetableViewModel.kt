package com.compani.ilai.bioproducts.ui.productType.vegetables

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.utils.Constants.VEGETABLES_PRODUCT

class VegetableViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
) : ViewModel() {

    private val _vegetableProducts = MutableLiveData<List<Product>>()
    val vegetablesProducts: LiveData<List<Product>> = _vegetableProducts

    init {
        _vegetableProducts.postValue(bioProductDatabase.getAllProduct(VEGETABLES_PRODUCT))
    }
}