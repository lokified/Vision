package com.loki.britam.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.loki.britam.data.remote.firebase.models.Company
import kotlinx.coroutines.flow.Flow

interface DataStoreStorage {

    suspend fun saveUser(user: User)

    suspend fun getUser(): Flow<User>

    suspend fun saveActiveCompany(company: Company)

    suspend fun getActiveCompany(): Flow<Company>

    object UserPreferences {
        val USER_ID_KEY = stringPreferencesKey("user_id_key")
        val USER_NAME_KEY = stringPreferencesKey("user_name_key")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email_key")
        val USER_LOGGEDIN_KEY = booleanPreferencesKey("user_loggedin_key")
    }

    object CompanyPreferences {
        val COMPANY_ID_KEY = stringPreferencesKey("company_id_key")
        val COMPANY_NAME_KEY = stringPreferencesKey("company_name_key")
        val COMPANY_TYPE_KEY = stringPreferencesKey("company_type_key")
        val COMPANY_LOCATION_KEY = stringPreferencesKey("company_location_key")
        val COMPANY_DESCRIPTION_KEY = stringPreferencesKey("company_description_key")
        val COMPANY_USER_ID_KEY = stringPreferencesKey("company_user_id_key")
    }
}