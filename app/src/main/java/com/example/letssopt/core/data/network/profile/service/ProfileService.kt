package com.example.letssopt.core.data.network.profile.service

import com.example.letssopt.core.data.network.BaseResponse
import com.example.letssopt.core.data.network.profile.dto.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {
    @GET("api/v1/users/{userId}")
    suspend fun getUserProfile(
        @Path("userId") userId: Int
    ): Response<BaseResponse<ProfileResponse>>
}