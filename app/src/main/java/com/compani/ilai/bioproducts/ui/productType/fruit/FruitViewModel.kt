package com.compani.ilai.bioproducts.ui.productType.fruit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.data.remote.BioProductDatabase
import com.compani.ilai.bioproducts.utils.Constants.FRUIT_PRODUCT

class FruitViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
) : ViewModel() {

    private val _fruitProducts = MutableLiveData<List<Product>>()
    val fruitProducts: LiveData<List<Product>> = _fruitProducts

    init {
        _fruitProducts.postValue(bioProductDatabase.getAllProduct(FRUIT_PRODUCT))
    }
}