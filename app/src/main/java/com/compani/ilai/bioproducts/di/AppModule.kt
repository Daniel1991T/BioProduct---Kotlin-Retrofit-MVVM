package com.compani.ilai.bioproducts.di

import com.compani.ilai.bioproducts.auth.support.Validator
import com.compani.ilai.bioproducts.data.remote.BioProductDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductDatabase() = BioProductDatabase()

    @Singleton
    @Provides
    fun provideValidator() = Validator.instance


    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()


}