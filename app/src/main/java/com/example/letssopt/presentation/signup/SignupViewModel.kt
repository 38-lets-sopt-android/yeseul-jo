package com.example.letssopt.presentation.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var passwordConfirm by mutableStateOf("")
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updatePasswordConfirm(newPasswordConfirm: String) {
        passwordConfirm = newPasswordConfirm
    }

    val isEmailValid get() = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val isPasswordValid get() = password.trim().length in 8..12
    val isPasswordConfirmed get() = password.trim() == passwordConfirm.trim()
    val isAllInputFilled get() = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()
}