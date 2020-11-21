package com.compani.ilai.bioproducts.repository

import com.compani.ilai.bioproducts.data.entities.Producer
import com.compani.ilai.bioproducts.utils.Constants.PRODUCER_LIST
import com.compani.ilai.bioproducts.utils.Resource
import com.compani.ilai.bioproducts.utils.safeCall
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DefaultAuthRepository: AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val users = FirebaseFirestore.getInstance().collection(PRODUCER_LIST)

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val producerId = result.user?.uid!!
                val user = Producer(producerId = producerId, fullName = username)
                users.document(producerId).set(user).await()
                Resource.Success(result)
            }
        }
    }

    override suspend fun login(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }
}