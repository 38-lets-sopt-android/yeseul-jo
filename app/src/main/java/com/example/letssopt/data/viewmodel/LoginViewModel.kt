package com.example.letssopt.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var registeredEmail by mutableStateOf("")
    var registeredPassword by mutableStateOf("")

    val isLoginEnabled get() = email.isNotEmpty() && password.isNotEmpty()

    fun checkLoginSuccess(): Boolean {
        return email.trim() == registeredEmail.trim() &&
                password.trim() == registeredPassword.trim()
    }
}