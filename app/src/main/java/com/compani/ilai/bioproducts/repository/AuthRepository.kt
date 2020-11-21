package com.compani.ilai.bioproducts.repository

import com.compani.ilai.bioproducts.utils.Resource
import com.google.firebase.auth.AuthResult
import retrofit2.Response

interface AuthRepository {

    suspend fun register(email: String, username: String, password: String): Resource<AuthResult>

    suspend fun login(email: String, password: String): Resource<AuthResult>
}