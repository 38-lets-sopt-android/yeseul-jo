package com.example.letssopt.core.util

import com.example.letssopt.core.data.network.BaseResponse
import com.example.letssopt.core.data.network.RetrofitClient
import retrofit2.Response

fun Response<*>.getErrorMsg(): String {
    return runCatching {
        val jsonString = this.errorBody()?.string() ?: return "오류 메시지가 없습니다."

        RetrofitClient.json.decodeFromString<BaseResponse<Unit>>(jsonString).message
    }.getOrDefault("네트워크 오류가 발생했습니다.")
}