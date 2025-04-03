package com.example.belajarbottombarjet.core.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.belajarbottombarjet.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

const val PREFERENCES_NAME = "credential"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreManager (private val context: Context) {
    private val USER_INFO_KEY = stringPreferencesKey("user_info")

    suspend fun saveUserInfo(userInfo: User) {
        Log.d(TAG, "saveUserInfo: $userInfo")
        context.dataStore.edit { preferences ->
            val userInfoJson = Json.encodeToString(userInfo)
            preferences[USER_INFO_KEY] = userInfoJson
        }
    }

    fun getUserInfo(): Flow<User?> = context.dataStore.data.map { preferences ->
        val userInfoJson = preferences[USER_INFO_KEY]
        Log.d(TAG, "getUserInfo: $userInfoJson")
        if (userInfoJson != null) {
            Json.decodeFromString<User>(userInfoJson)
        } else {
            null
        }
    }

    suspend fun clearUserInfo() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_INFO_KEY)
        }
    }

    companion object {
        const val TAG = "DataStoreManager"
    }
}