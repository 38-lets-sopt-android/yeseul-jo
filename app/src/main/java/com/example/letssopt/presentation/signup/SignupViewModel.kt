package com.example.letssopt.presentation.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.letssopt.R

data class SignupState(
    val isSignupSuccess: Boolean = false,
    val errorMessageRes: Int? = null
)

class SignupViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var passwordConfirm by mutableStateOf("")
        private set

    var state by mutableStateOf(SignupState())

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updatePasswordConfirm(newPasswordConfirm: String) {
        passwordConfirm = newPasswordConfirm
    }

    private val isEmailValid get() = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    private val isPasswordValid get() = password.trim().length in 8..12
    private val isPasswordConfirmed get() = password.trim() == passwordConfirm.trim()
    val isAllInputFilled get() = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()

    fun signup() {
        when {
            !isEmailValid -> {
                state = SignupState(errorMessageRes = R.string.signup_email_toast)
            }
            !isPasswordValid -> {
                state = SignupState(errorMessageRes = R.string.signup_password_toast)
            }
            !isPasswordConfirmed -> {
                state = SignupState(errorMessageRes = R.string.signup_password_confirmed_toast)
            }
            else -> {
                state = SignupState(isSignupSuccess = true)
            }
        }
    }

    fun clearErrorMessage() {
        state = state.copy(errorMessageRes = null)
    }
}