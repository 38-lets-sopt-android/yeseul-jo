package com.example.letssopt.screen

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
import com.example.letssopt.designsystem.component.BottomNavigationBar
import com.example.letssopt.designsystem.component.HomeTopAppBar
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.navigation.NavigationType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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
                        startDestination = NavigationType.HOME,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavigationType.HOME) {
                            HomeScreen()
                        }
                        composable(NavigationType.STORE) {
                            Text(
                                "Store Screen",
                                color = Color.White
                            )
                        }
                        composable(NavigationType.WEBTOON) {
                            Text(
                                "Webtoon Screen",
                                color = Color.White
                            )
                        }
                        composable(NavigationType.SEARCH) {
                            Text(
                                "Search Screen",
                                color = Color.White
                            )
                        }
                        composable(NavigationType.LIBRARY) {
                            Text(
                                "Library Screen",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

