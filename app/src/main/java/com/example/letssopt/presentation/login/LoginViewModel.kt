package com.example.letssopt.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var registeredEmail by mutableStateOf("")
        private set
    var registeredPassword by mutableStateOf("")
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updateRegisteredInfo(email: String, password: String) {
        registeredEmail = email
        registeredPassword = password
    }

    val isLoginEnabled get() = email.isNotEmpty() && password.isNotEmpty()

    fun checkLoginSuccess(): Boolean {
        return email.trim() == registeredEmail.trim() &&
                password.trim() == registeredPassword.trim()
    }
}