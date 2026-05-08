package com.example.letssopt.core.navigation

import kotlinx.serialization.Serializable

// 앱 전체 주소록
@Serializable
sealed interface Route {
    @Serializable
    data object AuthGraph : Route
    @Serializable
    data object MainGraph : Route

    // Auth
    @Serializable
    data object LOGIN : Route
    @Serializable
    data object SIGNUP : Route

    // BottomNavBar
    @Serializable
    data object HOME : Route
    @Serializable
    data object STORE : Route
    @Serializable
    data object WEBTOON : Route
    @Serializable
    data object SEARCH : Route
    @Serializable
    data object LIBRARY : Route
}