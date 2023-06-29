package com.loki.britam.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.loki.britam.data.remote.firebase.FirebaseAccountRepository
import com.loki.britam.data.remote.firebase.FirebaseAccountRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideAuthentication(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseRepository(auth: FirebaseAuth): FirebaseAccountRepository {
        return FirebaseAccountRepositoryImpl(auth)
    }
}