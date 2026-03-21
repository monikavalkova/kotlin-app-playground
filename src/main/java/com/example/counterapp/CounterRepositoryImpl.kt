package com.example.counterapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "counter_prefs")
private val COUNTER_KEY = intPreferencesKey("counter_value")

class CounterRepositoryImpl(private val context: Context) : CounterRepository {
    
    override fun getCounter(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }
    }
    
    override suspend fun incrementCounter() {
        context.dataStore.edit { preferences ->
            val current = preferences[COUNTER_KEY] ?: 0
            preferences[COUNTER_KEY] = current + 1
        }
    }
    
    override suspend fun resetCounter() {
        context.dataStore.edit { preferences ->
            preferences[COUNTER_KEY] = 0
        }
    }
}
