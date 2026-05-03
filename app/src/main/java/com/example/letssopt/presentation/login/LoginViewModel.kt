package com.example.letssopt.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LoginState(
    val isLoginSuccess: Boolean = false,
    val errorMessage: String? = null
)

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var state by mutableStateOf(LoginState())
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    val isLoginEnabled get() = email.isNotEmpty() && password.isNotEmpty()

    fun login(savedEmail: String, savedPassword: String) {
        val isSuccess = email.trim() == savedEmail.trim() &&
                password.trim() == savedPassword.trim()

        if (isSuccess) {
            state = LoginState(isLoginSuccess = true)
        } else {
            state = LoginState(errorMessage = "로그인 정보가 올바르지 않습니다.")
        }
    }

    fun clearErrorMessage() {
        state = state.copy(errorMessage = null)
    }
}