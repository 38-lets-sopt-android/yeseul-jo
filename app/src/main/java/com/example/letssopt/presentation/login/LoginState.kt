package com.example.letssopt.presentation.login

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val userId: Int) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    data class Failure(val messageRes: Int) : LoginUiState()
}