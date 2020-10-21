package com.compani.ilai.bioproducts.data.remote

import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.utils.Constants.PRODUCER_LIST
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class BioProductDatabase {

    private val firestore = FirebaseFirestore.getInstance()

    fun getAllProduct(dataType: String): List<Product> {
        return try {
            firestore.collection(dataType).get().result!!.toObjects(Product::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }


}