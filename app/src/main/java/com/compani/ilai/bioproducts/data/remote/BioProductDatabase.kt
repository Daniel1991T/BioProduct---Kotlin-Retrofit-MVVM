package com.compani.ilai.bioproducts.data.remote

import com.compani.ilai.bioproducts.data.entities.Producer
import com.compani.ilai.bioproducts.data.entities.Product
import com.compani.ilai.bioproducts.utils.Constants.PRODUCER_LIST
import com.compani.ilai.bioproducts.utils.Resource
import com.google.android.gms.tasks.Task
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

    fun createProfileProducer(producer: Producer): Task<Void> {
        val writeBatch = firestore.batch()
        writeBatch.set(firestore.collection(PRODUCER_LIST).document(producer.producerId), producer)
        return writeBatch.commit()
    }

    fun createProfileProducerUser(producer: Producer) : Resource<Task<Void>> {
        return try {
            val result =
                firestore.collection(PRODUCER_LIST).document(producer.producerId).set(producer)
            Resource.success(result)
        } catch (throwable: Throwable) {
            Resource.error(throwable.message)
        }
    }

}