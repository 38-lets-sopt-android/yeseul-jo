package com.example.letssopt.presentation.home.model

import androidx.annotation.DrawableRes

data class ContentItem(
    val id: Int,
    val title: String? = null,
    val startTime: String? = null,
    @get:DrawableRes val imageUrl: Int // drawable 임의
)