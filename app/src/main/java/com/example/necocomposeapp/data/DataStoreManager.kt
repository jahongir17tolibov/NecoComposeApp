package com.example.necocomposeapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.necocomposeapp.utils.FOLLOW_SYSTEM_THEME
import com.example.necocomposeapp.utils.THEME_STORE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_KEY)
    private val dataStore = context.dataStore

    companion object {
        private const val STORE_KEY = "my_data_store"
        private val themeModeKey = stringPreferencesKey(THEME_STORE_KEY)
    }

    suspend fun setTheme(mode: String) = dataStore.edit { pref ->
        pref[themeModeKey] = mode
    }

    fun getTheme(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { pref ->
            pref[themeModeKey] ?: FOLLOW_SYSTEM_THEME
        }

}