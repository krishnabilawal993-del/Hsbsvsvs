package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.model.AppLanguage
import com.example.model.ScholarshipCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.sahayakDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "gujarat_sahayak_prefs"
)

data class UserPreferences(
    val language: AppLanguage = AppLanguage.GUJARATI,
    val darkModeEnabled: Boolean = false,
    val bookmarkedIds: Set<String> = emptySet(),
    val checkedDocumentIds: Set<String> = emptySet(),
    val preferredCategory: ScholarshipCategory = ScholarshipCategory.ALL,
    val preferredDistrict: String = UiStrings.gujaratDistricts.first(),
    val adminPin: String = "1234"
)

class UserPreferencesRepository(private val context: Context) {

    private object Keys {
        val LANGUAGE = stringPreferencesKey("app_language")
        val DARK_MODE = booleanPreferencesKey("dark_mode_enabled")
        val BOOKMARKS = stringSetPreferencesKey("bookmarked_item_ids")
        val CHECKED_DOCS = stringSetPreferencesKey("checked_document_ids")
        val PREFERRED_CATEGORY = stringPreferencesKey("preferred_category")
        val PREFERRED_DISTRICT = stringPreferencesKey("preferred_district")
        val ADMIN_PIN = stringPreferencesKey("local_admin_pin")
    }

    val preferencesFlow: Flow<UserPreferences> = context.sahayakDataStore.data.map { prefs ->
        UserPreferences(
            language = AppLanguage.fromCode(prefs[Keys.LANGUAGE]),
            darkModeEnabled = prefs[Keys.DARK_MODE] ?: false,
            bookmarkedIds = prefs[Keys.BOOKMARKS] ?: emptySet(),
            checkedDocumentIds = prefs[Keys.CHECKED_DOCS] ?: emptySet(),
            preferredCategory = ScholarshipCategory.entries.firstOrNull {
                it.code == prefs[Keys.PREFERRED_CATEGORY]
            } ?: ScholarshipCategory.ALL,
            preferredDistrict = prefs[Keys.PREFERRED_DISTRICT] ?: UiStrings.gujaratDistricts.first(),
            adminPin = prefs[Keys.ADMIN_PIN]?.takeIf { it.length >= 4 } ?: "1234"
        )
    }

    suspend fun setLanguage(language: AppLanguage) {
        context.sahayakDataStore.edit { it[Keys.LANGUAGE] = language.code }
    }

    suspend fun setDarkMode(enabled: Boolean) {
        context.sahayakDataStore.edit { it[Keys.DARK_MODE] = enabled }
    }

    suspend fun toggleBookmark(itemId: String) {
        context.sahayakDataStore.edit { prefs ->
            val current = prefs[Keys.BOOKMARKS] ?: emptySet()
            prefs[Keys.BOOKMARKS] = if (itemId in current) current - itemId else current + itemId
        }
    }

    suspend fun toggleChecklistDocument(docId: String) {
        context.sahayakDataStore.edit { prefs ->
            val current = prefs[Keys.CHECKED_DOCS] ?: emptySet()
            prefs[Keys.CHECKED_DOCS] = if (docId in current) current - docId else current + docId
        }
    }

    suspend fun setPreferredCategory(category: ScholarshipCategory) {
        context.sahayakDataStore.edit { it[Keys.PREFERRED_CATEGORY] = category.code }
    }

    suspend fun setPreferredDistrict(district: String) {
        context.sahayakDataStore.edit { it[Keys.PREFERRED_DISTRICT] = district }
    }

    suspend fun setAdminPin(newPin: String) {
        if (newPin.length >= 4) {
            context.sahayakDataStore.edit { it[Keys.ADMIN_PIN] = newPin }
        }
    }
}
