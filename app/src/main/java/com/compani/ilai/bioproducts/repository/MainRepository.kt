package com.compani.ilai.bioproducts.repository

import android.net.Uri
import com.compani.ilai.bioproducts.data.entities.UpdateProfile
import com.compani.ilai.bioproducts.utils.Resource

interface MainRepository {

    suspend fun updateProfile(updateProfile: UpdateProfile): Resource<Any>

    suspend fun updateProfilePicture(uid: String, imageUri: Uri): Uri?
}