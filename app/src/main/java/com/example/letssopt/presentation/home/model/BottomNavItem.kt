package com.example.letssopt.presentation.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.letssopt.R

@Immutable
enum class BottomNavItem (
    @get:StringRes val title: Int,
    @get:DrawableRes val icon: Int,
    val route: String,
){
    HOME(R.string.nav_home, R.drawable.ic_nav_home, "home"),
    STORE(R.string.nav_store, R.drawable.ic_nav_store, "store"),
    WEBTOON(R.string.nav_webtoon, R.drawable.ic_nav_webtoon, "webtoon"),
    SEARCH(R.string.nav_search, R.drawable.ic_nav_search, "search"),
    LIBRARY(R.string.nav_library, R.drawable.ic_nav_library, "library");

    companion object {
        // 경로 -> 아이템
        fun fromRoute(route: String?): BottomNavItem =
            entries.find { it.route == route } ?: HOME
    }
}