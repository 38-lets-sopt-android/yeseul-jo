package com.example.letssopt.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.R
import com.example.letssopt.data.viewmodel.HomeViewModel
import com.example.letssopt.designsystem.component.BottomNavigationBar
import com.example.letssopt.designsystem.component.HomeTopAppBar
import com.example.letssopt.designsystem.component.home.ContentSection
import com.example.letssopt.designsystem.component.home.NewBannerCard
import com.example.letssopt.designsystem.component.home.VerticalCard
import com.example.letssopt.designsystem.component.home.WatchaPartyCard
import com.example.letssopt.designsystem.theme.Background
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
                            Home()
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

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentPadding = PaddingValues(bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        // 신상 콘텐츠
        item {
            ContentSection(
                items = viewModel.newContents,
                title = R.string.home_new_title,
                subtitle = R.string.home_subtitle,
                showMore = false,
                spacerHieght = 24,
                modifier = Modifier.padding(top = 24.dp)
            ) { item ->
                NewBannerCard(item = item)
            }
        }

        // 왓고리즘
        item {
            ContentSection(
                items = viewModel.watGorithmContents,
                titleIcon = R.drawable.ic_watgorithm,
                subtitle = R.string.home_subtitle,
                showMore = true
            ) { item ->
                VerticalCard(item = item)
            }
        }

        // 공개 예정 콘텐츠
        item {
            ContentSection(
                items = viewModel.watGorithmContents,
                title = R.string.home_upcoming_title,
                showMore = true
            ) { item ->
                VerticalCard(item = item)
            }
        }

        // 왓챠 파티
        item {
            ContentSection(
                items = viewModel.watchaPartyContents,
                title = R.string.home_watcha_party_title,
                showMore = true
            ) { item ->
                WatchaPartyCard(item = item)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePreview() {
    LETSSOPTTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeTopAppBar()
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Home()
            }
        }
    }
}