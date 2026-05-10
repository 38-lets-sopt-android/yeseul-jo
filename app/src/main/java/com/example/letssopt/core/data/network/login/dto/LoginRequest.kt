package com.example.letssopt.core.data.network.login.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String
)