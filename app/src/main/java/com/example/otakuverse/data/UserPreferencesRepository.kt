package com.example.otakuverse.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException


class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
){
    private companion object {
        val USERNAME = stringPreferencesKey("username")
        val THEMEMODE = booleanPreferencesKey("themeMode")
        const val TAG = "UserPreferencesRepository"
    }

    suspend fun savePreferences(userPrefs : UserPreferences) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[USERNAME] = userPrefs.username
                preferences[THEMEMODE] = userPrefs.themeMode
            }
        }
    }

    val userPrefs : Flow<UserPreferences> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val username = preferences[USERNAME] ?: "anonimus"
            val themeMode = preferences[THEMEMODE] ?: false
             UserPreferences(username, themeMode)
        }
}