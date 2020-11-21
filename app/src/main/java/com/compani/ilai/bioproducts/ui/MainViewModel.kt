package com.compani.ilai.bioproducts.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compani.ilai.bioproducts.utils.Resource

class MainViewModel @ViewModelInject constructor(
    private val bioProductDatabase: BioProductDatabase
) : ViewModel() {


    private val _exist = MutableLiveData<Resource<Boolean>>()
    var exist: LiveData<Resource<Boolean>> = _exist



    fun checkedUserExist(userId: Resource<String>): LiveData<Resource<Boolean>> {
        bioProductDatabase.checkUserType(userId.data.toString()).addOnSuccessListener { doc ->
            if (!doc.exists()) {
                Log.d("TAG", "checkedUserExist: " + doc.data.toString())
                _exist.postValue(Resource.error("User not exist"))
            } else {
                _exist.postValue(Resource.success(true))

                Log.d("TAG", "checkedUserExist: " + doc.data.toString())
            }
        }
        exist = _exist
        return exist
    }
}