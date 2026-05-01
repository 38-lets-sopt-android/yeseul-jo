package com.example.letssopt.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.HomeViewModel
import com.example.letssopt.presentation.home.component.BottomNavigationBar
import com.example.letssopt.presentation.home.component.HomeTopAppBar
import com.example.letssopt.presentation.home.component.ContentSection
import com.example.letssopt.presentation.home.component.NewBannerCard
import com.example.letssopt.presentation.home.component.VerticalCard
import com.example.letssopt.presentation.home.component.WatchaPartyCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier
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
                spacerHeight = 24,
                modifier = Modifier.padding(top = 24.dp),
                content = { NewBannerCard(it) }
            )
        }

        // 왓고리즘
        item {
            ContentSection(
                items = viewModel.watGorithmContents,
                titleIcon = R.drawable.ic_watgorithm,
                subtitle = R.string.home_subtitle,
                showMore = true,
                content = { VerticalCard(it) }
            )
        }

        // 공개 예정 콘텐츠
        item {
            ContentSection(
                items = viewModel.watGorithmContents,
                title = R.string.home_upcoming_title,
                showMore = true,
                content = { VerticalCard(it) }
            )
        }

        // 왓챠 파티
        item {
            ContentSection(
                items = viewModel.watchaPartyContents,
                title = R.string.home_watcha_party_title,
                showMore = true,
                content = { WatchaPartyCard(it) }
            )
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
            HomeScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}