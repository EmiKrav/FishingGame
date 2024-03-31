package com.example.fishinggame

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val PREFERENCE_NAME = "History"
//Instance of DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

/**
 * Add Integer to the data store
 */
suspend fun Context.writeInt(key: String, value: Int) {
    dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
}

/**
 * Reading the Int value from the data store
 */
fun Context.readInt(key: String): Flow<Int> {
    return dataStore.data.map { pref ->
        pref[intPreferencesKey(key)] ?: 0
    }
}

/**
 * Add Float to the data store
 */
suspend fun Context.writeFloat(key: String, value: Float) {
    dataStore.edit { pref -> pref[floatPreferencesKey(key)] = value }
}

/**
 * Reading the float value from the data store
 */
fun Context.readFloat(key: String): Flow<Float> {
    return dataStore.data.map { pref ->
        pref[floatPreferencesKey(key)] ?: 0F
    }
}

suspend fun Context.writeString(key: String, value: String) {
    dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
}


fun Context.readString(key: String): Flow<String> {
    return dataStore.data.map { pref ->
        pref[stringPreferencesKey(key)] ?: ""
    }
}