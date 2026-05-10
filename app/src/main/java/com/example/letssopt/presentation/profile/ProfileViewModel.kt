package com.example.letssopt.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.profile.service.ProfileService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val profileService = RetrofitClient.create(ProfileService::class.java)

    fun fetchUserProfile(userId: Int) {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading

            runCatching {
                profileService.getUserProfile(userId)
            }.onSuccess { response ->
                val body = response.body()
                if (response.isSuccessful && body?.data != null) {
                    _uiState.value = ProfileUiState.Success(body.data)
                } else {
                    _uiState.value = ProfileUiState.Error("사용자 정보를 불러올 수 없습니다.")
                }
            }.onFailure { _uiState.value = ProfileUiState.Failure(R.string.network_error) }
        }
    }

}