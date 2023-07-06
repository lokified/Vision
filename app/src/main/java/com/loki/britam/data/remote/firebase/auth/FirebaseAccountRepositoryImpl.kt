package com.loki.britam.data.remote.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.loki.britam.data.remote.firebase.models.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAccountRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): FirebaseAccountRepository {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let {

                    User(
                        id = it.uid,
                        username = it.displayName!!,
                        email = it.email!!
                    )
                } ?: User())
            }

            auth.addAuthStateListener(listener)
            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }

    override suspend fun authenticate(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun createAccount(names: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val request = UserProfileChangeRequest.Builder().apply {
                    displayName = names
                }.build()

                auth.currentUser?.updateProfile(request)
            }

        }.await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}