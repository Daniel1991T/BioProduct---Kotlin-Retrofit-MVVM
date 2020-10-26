package com.compani.ilai.bioproducts.auth.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.data.entities.Producer
import com.compani.ilai.bioproducts.data.remote.BioProductDatabase
import com.compani.ilai.bioproducts.utils.Resource
import com.google.android.gms.tasks.Task

class ProfileViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
) : ViewModel() {

    private val _productUser = MutableLiveData<Resource<Task<Void>>>()
    var producerUser: LiveData<Resource<Task<Void>>> = _productUser

//    suspend fun saveProducer(producer: Producer): Task<Void> {
//        return bioProductDatabase.createProfileProducer(producer)
//    }

    fun saveProducer(producer: Producer) {
        _productUser.postValue(bioProductDatabase.createProfileProducerUser(producer))
        producerUser = _productUser
    }

}