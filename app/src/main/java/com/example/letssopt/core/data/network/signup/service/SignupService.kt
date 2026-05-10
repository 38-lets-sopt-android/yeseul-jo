package com.example.letssopt.core.data.network.signup.service

import com.example.letssopt.core.data.network.BaseResponse
import com.example.letssopt.core.data.network.signup.dto.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {
    @POST("api/v1/auth/signup")
    suspend fun signup (@Body request: SignUpRequest): Response<BaseResponse<Unit>>
}