package com.example.letssopt.core.data.network.profile.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val id: Int,
    val loginId: String,
    val name: String,
    val email: String,
    val age: Int,
    val part: String
)