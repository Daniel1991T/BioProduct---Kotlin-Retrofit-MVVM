package com.compani.ilai.bioproducts.di

import com.compani.ilai.bioproducts.auth.support.Validator
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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

    @Singleton
    @Provides
    fun provideUserId() = FirebaseAuth.getInstance().currentUser?.uid


}