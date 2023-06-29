package com.loki.britam.data.remote.firebase

import kotlinx.coroutines.flow.Flow

interface FirebaseAccountRepository {

    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun createAccount(names: String, email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}