package com.example.letssopt.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.core.data.DataStore
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.HomeScreen
import com.example.letssopt.presentation.home.component.BottomNavigationBar
import com.example.letssopt.presentation.home.component.HomeTopAppBar
import com.example.letssopt.presentation.library.LibraryScreen
import com.example.letssopt.presentation.login.LoginScreen
import com.example.letssopt.presentation.search.SearchScreen
import com.example.letssopt.presentation.signup.SignupScreen
import com.example.letssopt.presentation.store.StoreScreen
import com.example.letssopt.presentation.webtoon.WebtoonScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val context = LocalContext.current
                val isAutoLogin = remember { DataStore.getAutoLogin(context) }
                // 화면 이동 컨트롤러
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        HomeTopAppBar(navController = navController)
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        // 자동로그인 여부에 따라 시작 화면 결정
                        startDestination = if (isAutoLogin) Route.HOME else Route.LOGIN,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Route.LOGIN> {
                            LoginScreen(
                                onSignupClick = { navController.navigate(Route.SIGNUP) },
                                onLoginSuccess = {
                                    DataStore.setAutoLogin(context, true)
                                    navController.navigate(Route.HOME) {
                                        popUpTo<Route.LOGIN> { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable<Route.SIGNUP> {
                            SignupScreen(
                                onSignupSuccess = { email, password ->
                                    DataStore.saveUser(context, email, password)
                                    navController.popBackStack()
                                    Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                        composable<Route.HOME> {
                            HomeScreen()
                        }
                        composable<Route.STORE> {
                            StoreScreen()
                        }
                        composable<Route.WEBTOON> {
                            WebtoonScreen()
                        }
                        composable<Route.SEARCH> {
                            SearchScreen()
                        }
                        composable<Route.LIBRARY> {
                            LibraryScreen()
                        }
                    }
                }
            }
        }
    }
}