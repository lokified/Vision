package com.loki.britam.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.loki.britam.data.local.datastore.DataStoreStorage.CompanyPreferences.COMPANY_DESCRIPTION_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.CompanyPreferences.COMPANY_ID_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.CompanyPreferences.COMPANY_LOCATION_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.CompanyPreferences.COMPANY_NAME_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.CompanyPreferences.COMPANY_TYPE_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.CompanyPreferences.COMPANY_USER_ID_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.UserPreferences.USER_EMAIL_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.UserPreferences.USER_ID_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.UserPreferences.USER_LOGGEDIN_KEY
import com.loki.britam.data.local.datastore.DataStoreStorage.UserPreferences.USER_NAME_KEY
import com.loki.britam.data.remote.firebase.models.Company
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreStorageImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
): DataStoreStorage {

    override suspend fun saveUser(user: User) {

        datastore.edit { preference ->
            preference[USER_ID_KEY] = user.userId
            preference[USER_NAME_KEY] = user.name
            preference[USER_EMAIL_KEY] = user.email
            preference[USER_LOGGEDIN_KEY] = user.isLoggedIn
        }
    }

    override suspend fun getUser(): Flow<User> {

        return datastore.data.map { preferences ->
            val userId = preferences[USER_ID_KEY] ?: ""
            val name = preferences[USER_NAME_KEY] ?: ""
            val email = preferences[USER_EMAIL_KEY] ?: ""
            val isLoggedIn = preferences[USER_LOGGEDIN_KEY] ?: false

            User(userId, name, email, isLoggedIn)
        }
    }

    override suspend fun saveActiveCompany(company: Company) {
        datastore.edit { preference ->
            preference[COMPANY_ID_KEY] = company.id
            preference[COMPANY_NAME_KEY] = company.name
            preference[COMPANY_TYPE_KEY] = company.businessType
            preference[COMPANY_LOCATION_KEY] = company.location
            preference[COMPANY_DESCRIPTION_KEY] = company.description
            preference[COMPANY_USER_ID_KEY] = company.userId
        }
    }

    override suspend fun getActiveCompany(): Flow<Company> {
        return datastore.data.map { preferences ->
            val id = preferences[COMPANY_ID_KEY] ?: ""
            val name = preferences[COMPANY_NAME_KEY] ?: ""
            val type = preferences[COMPANY_TYPE_KEY] ?: ""
            val location = preferences[COMPANY_LOCATION_KEY] ?: ""
            val description = preferences[COMPANY_DESCRIPTION_KEY] ?: ""
            val userId = preferences[COMPANY_USER_ID_KEY] ?: ""

            Company(
                id = id,
                name = name,
                businessType = type,
                location = location,
                description = description,
                userId = userId
            )
        }
    }
}