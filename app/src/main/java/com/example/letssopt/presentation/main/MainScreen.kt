package com.example.letssopt.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.HomeScreen
import com.example.letssopt.presentation.home.component.BottomNavigationBar
import com.example.letssopt.presentation.library.LibraryScreen
import com.example.letssopt.presentation.search.SearchScreen
import com.example.letssopt.presentation.store.StoreScreen
import com.example.letssopt.presentation.webtoon.WebtoonScreen

@Composable
fun MainScreen() {
    val mainNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = mainNavController) }
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = Route.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.HOME> { HomeScreen() }
            composable<Route.STORE> { StoreScreen() }
            composable<Route.WEBTOON> { WebtoonScreen() }
            composable<Route.SEARCH> { SearchScreen() }
            composable<Route.LIBRARY> { LibraryScreen() }
        }
    }
}