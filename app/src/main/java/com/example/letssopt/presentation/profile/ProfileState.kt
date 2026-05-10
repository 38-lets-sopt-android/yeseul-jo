package com.example.letssopt.presentation.profile

import com.example.letssopt.core.data.network.profile.dto.ProfileResponse

sealed class ProfileUiState {
    object Idle : ProfileUiState()
    object Loading : ProfileUiState()
    data class Success(val profileData: ProfileResponse) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
    data class Failure(val messageRes: Int) : ProfileUiState()
}