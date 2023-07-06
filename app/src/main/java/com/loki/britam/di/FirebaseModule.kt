package com.loki.britam.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepository
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepositoryImpl
import com.loki.britam.data.remote.firebase.company.CompanyStorage
import com.loki.britam.data.remote.firebase.company.CompanyStorageImpl
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
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseRepository(auth: FirebaseAuth): FirebaseAccountRepository {
        return FirebaseAccountRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun provideCompanyFirestoreStorage(auth: FirebaseAccountRepository, firestore: FirebaseFirestore): CompanyStorage {
        return CompanyStorageImpl(auth, firestore)
    }
}