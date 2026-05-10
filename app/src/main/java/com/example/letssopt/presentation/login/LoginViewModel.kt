package com.example.letssopt.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.login.dto.LoginRequest
import com.example.letssopt.core.data.network.login.service.LoginService
import com.example.letssopt.core.util.getErrorMsg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var id by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val loginService = RetrofitClient.create(LoginService::class.java)

    fun updateId(newId: String) {
        id = newId
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    val isLoginEnabled get() = id.isNotEmpty() && password.isNotEmpty()

    fun login() {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            runCatching {
                loginService.login(LoginRequest(id, password))
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    val userId = response.body()?.data?.userId ?: -1
                    _uiState.value = LoginUiState.Success(userId)
                } else {
                    _uiState.value = LoginUiState.Error(response.getErrorMsg())
                }
            }.onFailure {
                _uiState.value = LoginUiState.Failure(R.string.network_error)
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Idle
    }
}