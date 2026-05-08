package com.example.letssopt.core.data

import android.content.Context
import androidx.core.content.edit

class DataStore(context: Context) {
    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "pref"
        private const val KEY_IS_LOGIN = "is_login"
        private const val KEY_SAVED_EMAIL = "saved_email"
        private const val KEY_SAVED_PASSWORD = "saved_password"
    }

    fun setAutoLogin(isLogin: Boolean) {
        prefs.edit { putBoolean(KEY_IS_LOGIN, isLogin) }
    }

    fun getAutoLogin(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGIN, false)
    }

    fun saveUser(email: String, pw: String) {
        prefs.edit {
            putString(KEY_SAVED_EMAIL, email)
            putString(KEY_SAVED_PASSWORD, pw)
        }
    }

    fun getSavedEmail(): String =
        prefs.getString(KEY_SAVED_EMAIL, "") ?: ""

    fun getSavedPassword(): String =
        prefs.getString(KEY_SAVED_PASSWORD, "") ?: ""
}