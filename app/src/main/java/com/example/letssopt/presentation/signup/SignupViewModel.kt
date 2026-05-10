package com.example.letssopt.presentation.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.signup.dto.SignUpRequest
import com.example.letssopt.core.data.network.signup.service.SignupService
import com.example.letssopt.core.util.getErrorMsg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    var id by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var passwordConfirm by mutableStateOf("")
        private set

    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var age by mutableStateOf("")
        private set

    var part by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateId(newId: String) {
        id = newId
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun updatePasswordConfirm(newPasswordConfirm: String) {
        passwordConfirm = newPasswordConfirm
    }

    fun updateName(newName: String) {
        name = newName
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updateAge(newAge: String) {
        age = newAge
    }

    fun updatePart(newPart: String) {
        part = newPart
    }

    private val isEmailValid get() = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    private val isPasswordValid get() = password.trim().length in 8..12
    private val isPasswordConfirmed get() = password.trim() == passwordConfirm.trim()
    val isAllInputFilled get() = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()

    private val signupService = RetrofitClient.create(SignupService::class.java)

    fun signup() {
        // 유효성 검사
        val errorRes = when {
            !isEmailValid -> R.string.signup_email_toast
            !isPasswordValid -> R.string.signup_password_toast
            !isPasswordConfirmed -> R.string.signup_password_confirmed_toast
            else -> null
        }

        if (errorRes != null) {
            _uiState.value = SignUpUiState.Failure(errorRes)
            return
        }

        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading

            runCatching {
                signupService.signup(
                    SignUpRequest(
                        loginId = id,
                        password = password,
                        name = name,
                        email = email,
                        age = age.toIntOrNull() ?: 0,
                        part = part
                    )
                )
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    _uiState.value = SignUpUiState.Success
                } else {
                    _uiState.value = SignUpUiState.Error(response.getErrorMsg())
                }
            }.onFailure { e ->
                _uiState.value = SignUpUiState.Failure(R.string.network_error)
            }
        }
    }

    fun resetUiState() {
        _uiState.value = SignUpUiState.Idle
    }
}