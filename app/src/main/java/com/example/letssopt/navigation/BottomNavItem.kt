package com.example.letssopt.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R

sealed class BottomNavItem (
    @get:StringRes val title: Int,
    @get:DrawableRes val icon: Int,
    val route: String,
){
    companion object {
        const val HOME = "home"
        const val STORE = "store"
        const val WEBTOON = "webtoon"
        const val SEARCH = "search"
        const val LIBRARY = "library"
    }

    data object Home : BottomNavItem(
        title = R.string.nav_home,
        icon = R.drawable.ic_nav_home,
        route = HOME
    )
    data object Store : BottomNavItem(
        title = R.string.nav_store,
        icon = R.drawable.ic_nav_store,
        route = STORE
    )
    data object Webtoon : BottomNavItem(
        title = R.string.nav_webtoon,
        icon = R.drawable.ic_nav_webtoon,
        route = WEBTOON
    )
    data object Search : BottomNavItem(
        title = R.string.nav_search,
        icon = R.drawable.ic_nav_search,
        route = SEARCH
    )
    data object Library : BottomNavItem(
        title = R.string.nav_library,
        icon = R.drawable.ic_nav_library,
        route = LIBRARY
    )
}