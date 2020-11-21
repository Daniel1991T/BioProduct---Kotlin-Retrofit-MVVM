package com.compani.ilai.bioproducts.data.entities

import android.net.Uri

data class Producer(
    val producerId: String = "",
    val fullName: String = "",
    val address: String = "",
    val phone: Int? = 0,
    val shortBio: String = "",
    val imageUrl: String = "",
    val productType: String = ""
)

