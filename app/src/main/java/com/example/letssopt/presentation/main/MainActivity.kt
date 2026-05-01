package com.example.letssopt.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.core.data.DataStore
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.login.LoginScreen
import com.example.letssopt.presentation.signup.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    val context = LocalContext.current
                    // 화면 이동 컨트롤러
                    val navController = rememberNavController()
                    val isAutoLogin = remember { DataStore.getAutoLogin(context) }
                    NavHost(
                        navController = navController,
                        // 자동로그인 여부에 따라 시작 화면 결정
                        startDestination = if (isAutoLogin) Route.MainGraph else Route.AuthGraph,

                        ) {
                        navigation<Route.AuthGraph>(startDestination = Route.LOGIN) {
                            composable<Route.LOGIN> {
                                LoginScreen(
                                    onSignupClick = { navController.navigate(Route.SIGNUP) },
                                    onLoginSuccess = {
                                        DataStore.setAutoLogin(context, true)
                                        navController.navigate(Route.MainGraph) {
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
                                        Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                )
                            }
                        }
                        composable<Route.MainGraph> {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}