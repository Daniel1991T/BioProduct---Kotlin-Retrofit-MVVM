package com.compani.ilai.bioproducts.ui.productType.cheese

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.data.entities.Producer
import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.data.remote.BioProductDatabase
import com.compani.ilai.bioproducts.utils.Constants

class CheeseViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
) : ViewModel() {

    private val _cheeseProducts = MutableLiveData<List<Product>>()
    val cheeseProducts: LiveData<List<Product>> = _cheeseProducts

    init {
        _cheeseProducts.postValue(bioProductDatabase.getAllProduct(Constants.CHEESE_PRODUCT))
    }
}
