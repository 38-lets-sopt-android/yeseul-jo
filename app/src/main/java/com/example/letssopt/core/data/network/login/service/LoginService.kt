package com.example.letssopt.core.data.network.login.service

import com.example.letssopt.core.data.network.BaseResponse
import com.example.letssopt.core.data.network.login.dto.LoginRequest
import com.example.letssopt.core.data.network.login.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/v1/auth/signin")
    suspend fun login(@Body request: LoginRequest): Response<BaseResponse<LoginResponse>>
}