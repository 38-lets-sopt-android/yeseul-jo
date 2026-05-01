package com.example.letssopt.presentation.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.letssopt.R
import com.example.letssopt.core.navigation.Route

@Immutable
enum class BottomNavItem (
    @get:StringRes val title: Int,
    @get:DrawableRes val icon: Int,
    val route: Route,
){
    HOME(R.string.nav_home, R.drawable.ic_nav_home, Route.HOME),
    STORE(R.string.nav_store, R.drawable.ic_nav_store, Route.STORE),
    WEBTOON(R.string.nav_webtoon, R.drawable.ic_nav_webtoon, Route.WEBTOON),
    SEARCH(R.string.nav_search, R.drawable.ic_nav_search, Route.SEARCH),
    LIBRARY(R.string.nav_library, R.drawable.ic_nav_library, Route.LIBRARY);

    companion object {
        // 경로 -> 아이템
        fun fromRoute(route: Route?): BottomNavItem =
            entries.find { it.route == route } ?: HOME
    }
}