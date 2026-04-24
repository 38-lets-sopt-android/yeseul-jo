package com.example.letssopt.data

import android.content.Context
import androidx.core.content.edit

object DataStore {
    private const val PREF_NAME = "pref"
    private const val KEY_IS_LOGIN = "is_login"

    fun setAutoLogin(context: Context, isLogin: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit { putBoolean(KEY_IS_LOGIN, isLogin) }
    }

    fun getAutoLogin(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_LOGIN, false)
    }
}