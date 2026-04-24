package com.example.letssopt.data.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirm by mutableStateOf("")

    val isEmailValid get() = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val isPasswordValid get() = password.trim().length in 8..12
    val isPasswordConfirmed get() = password.trim() == passwordConfirm.trim()
    val isAllInputFilled get() = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()
}