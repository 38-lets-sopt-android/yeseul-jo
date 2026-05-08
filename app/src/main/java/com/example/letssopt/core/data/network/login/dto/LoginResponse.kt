package com.example.letssopt.core.data.network.login.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("userId")
    val userId: Int
)