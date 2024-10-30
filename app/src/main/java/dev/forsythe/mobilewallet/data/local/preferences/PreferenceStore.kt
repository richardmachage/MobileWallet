package dev.forsythe.mobilewallet.data.local.preferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PreferenceStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_FILE_KEY, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFERENCES_FILE_KEY = "com.forsythe.mobileWallet.PREFERENCES"
    }

    fun saveData(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            commit()
        }
    }

    fun loadData(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun deleteData(key: String) {
        sharedPreferences
            .edit()
            .remove(key)
            .commit()
    }
}