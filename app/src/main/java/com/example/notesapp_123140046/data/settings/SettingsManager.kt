package com.example.notesapp_123140046.data.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsManager(
    private val context: Context
) {
    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
        private val SORT_ORDER_KEY = stringPreferencesKey("sort_order")
    }

    val themeFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_KEY] ?: "system"
    }

    val sortOrderFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[SORT_ORDER_KEY] ?: "newest"
    }

    suspend fun setTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    suspend fun setSortOrder(sortOrder: String) {
        context.dataStore.edit { preferences ->
            preferences[SORT_ORDER_KEY] = sortOrder
        }
    }
}