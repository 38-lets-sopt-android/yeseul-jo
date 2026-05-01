package com.example.letssopt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.component.BottomNavigationBar
import com.example.letssopt.presentation.home.component.HomeTopAppBar
import com.example.letssopt.presentation.home.component.BottomNavItem
import com.example.letssopt.presentation.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        HomeTopAppBar()
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavItem.Home.route,
                        modifier = Modifier.Companion.padding(innerPadding)
                    ) {
                        composable(BottomNavItem.Home.route) {
                            HomeScreen()
                        }
                        composable(BottomNavItem.Store.route) {
                            Text(
                                "Store Screen",
                                color = Color.Companion.White
                            )
                        }
                        composable(BottomNavItem.Webtoon.route) {
                            Text(
                                "Webtoon Screen",
                                color = Color.Companion.White
                            )
                        }
                        composable(BottomNavItem.Search.route) {
                            Text(
                                "Search Screen",
                                color = Color.Companion.White
                            )
                        }
                        composable(BottomNavItem.Library.route) {
                            Text(
                                "Library Screen",
                                color = Color.Companion.White
                            )
                        }
                    }
                }
            }
        }
    }
}