package com.compani.ilai.bioproducts.data.entities

import android.icu.text.CaseMap

data class Product(
    val productId: String,
    val title: String,
    val description: String,
    val photoUrl: String,
    val producerId: String
)