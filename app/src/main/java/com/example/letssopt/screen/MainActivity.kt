package com.example.letssopt.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.R
import com.example.letssopt.designsystem.component.BottomNavigationBar
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.PrimaryRed
import com.example.letssopt.designsystem.theme.TextPrimary
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
                            Greeting()
                        }
                        composable(NavigationType.STORE) { Text("Store Screen", color = Color.White) }
                        composable(NavigationType.WEBTOON) { Text("Webtoon Screen", color = Color.White) }
                        composable(NavigationType.SEARCH) { Text("Search Screen", color = Color.White) }
                        composable(NavigationType.LIBRARY) { Text("Library Screen", color = Color.White) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                top = 60.dp,
                end = 20.dp,
                bottom = 26.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.logo),
            color = PrimaryRed,
            style = typography.displayLarge
        )
        Spacer(modifier.height(60.dp))
        Text(
            text = "메인 화면입니다!",
            color = TextPrimary,
            style = typography.bodyLarge
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainFullPreview() {
    LETSSOPTTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Greeting()
            }
        }
    }
}