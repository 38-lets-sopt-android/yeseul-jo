package com.example.letssopt.core.data

import android.content.Context
import androidx.core.content.edit

object DataStore {
    private const val PREF_NAME = "pref"
    private const val KEY_IS_LOGIN = "is_login"
    private const val KEY_SAVED_EMAIL = "saved_email"
    private const val KEY_SAVED_PASSWORD = "saved_password"

    fun setAutoLogin(context: Context, isLogin: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit { putBoolean(KEY_IS_LOGIN, isLogin) }
    }

    fun getAutoLogin(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_LOGIN, false)
    }

    // 회원가입 정보 저장
    fun saveUser(context: Context, email: String, pw: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_SAVED_EMAIL, email)
            putString(KEY_SAVED_PASSWORD, pw)
        }
    }

    // 회원가입 정보 가져오기
    fun getSavedEmail(context: Context): String =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_SAVED_EMAIL, "") ?: ""

    fun getSavedPassword(context: Context): String =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_SAVED_PASSWORD, "") ?: ""
}