package com.compani.ilai.bioproducts.ui.productType.meat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.utils.Constants.MEAT_PRODUCT

class MeatViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
): ViewModel() {

    private val _meatProduct = MutableLiveData<List<Product>>()
    val meatProduct: LiveData<List<Product>> = _meatProduct

    init {
        _meatProduct.postValue(bioProductDatabase.getAllProduct(MEAT_PRODUCT))
    }

}