package com.loki.britam.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.loki.britam.data.local.datastore.DataStoreStorage.Preferences.USER_EMAIL_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.Preferences.USER_LOGGEDIN_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.Preferences.USER_NAME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreStorageImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
): DataStoreStorage {

    override suspend fun saveUser(user: User) {

        datastore.edit { preference ->
            preference[USER_NAME_KEY] = user.name
            preference[USER_EMAIL_KEY] = user.email
            preference[USER_LOGGEDIN_KEY] = user.isLoggedIn
        }
    }

    override suspend fun getUser(): Flow<User> {

        return datastore.data.map { preferences ->
            val name = preferences[USER_NAME_KEY] ?: ""
            val email = preferences[USER_EMAIL_KEY] ?: ""
            val isLoggedIn = preferences[USER_LOGGEDIN_KEY] ?: false

            User(name, email, isLoggedIn)
        }
    }
}