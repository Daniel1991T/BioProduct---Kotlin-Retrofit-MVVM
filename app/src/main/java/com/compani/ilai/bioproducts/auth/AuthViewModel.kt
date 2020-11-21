package com.compani.ilai.bioproducts.auth

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.repository.AuthRepository
import com.compani.ilai.bioproducts.utils.Constants.MIN_PASSWORD_LENGTH
import com.compani.ilai.bioproducts.utils.Event
import com.compani.ilai.bioproducts.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    private val repository: AuthRepository,
    private val applicationContext: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _registerStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val registerStatus: LiveData<Event<Resource<AuthResult>>> = _registerStatus

    private val _loginStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val loginStatus: LiveData<Event<Resource<AuthResult>>> = _loginStatus

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            val error = applicationContext.getString(R.string.field_empty)
            _loginStatus.postValue(Event(Resource.Error(error)))
        } else {
            _loginStatus.postValue(Event(Resource.Loading()))
            viewModelScope.launch(dispatcher) {
                val result = repository.login(email, password)
                _loginStatus.postValue(Event(result))
            }
        }
    }

    fun register(email: String, username: String, password: String, repeaterPassword: String) {
        val error = if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            applicationContext.getString(R.string.field_empty)
        } else if (password != repeaterPassword) {
            applicationContext.getString(R.string.password_match)
        } else if (password.length < MIN_PASSWORD_LENGTH) {
            applicationContext.getString(R.string.password_length, MIN_PASSWORD_LENGTH)
        } else null
        error?.let {
            _registerStatus.postValue(Event(Resource.Error(it)))
        }
        _registerStatus.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val result = repository.register(email, username, password)
            _registerStatus.postValue(Event(result))
        }
    }





















}